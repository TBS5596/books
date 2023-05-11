package com.book.bookshelf.models.user;

import com.book.bookshelf.models.forum.Forum;
import com.book.bookshelf.models.forum.ForumRepository;
import com.book.bookshelf.models.message.Message;
import com.book.bookshelf.models.message.MessageRepository;
import com.book.bookshelf.models.shelf.Shelf;
import com.book.bookshelf.models.shelf.ShelfRepository;
import com.book.bookshelf.models.token.Token;
import com.book.bookshelf.models.token.TokenRepository;
import com.book.bookshelf.models.token.TokenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TokenService tokenService;

    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ShelfRepository shelfRepository;
    @Autowired
    private ForumRepository forumRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String addUser(User user) {
        // get user username, emails and password from the input and query the database
        boolean userByUsername = userRepository.findUserByUsername(user.getUsername()).isPresent();
        boolean userByEmail = userRepository.findUserByEmail(user.getEmail()).isPresent();
        boolean userByPhoneNumber = userRepository.findUserByPhoneNumber(user.getPhoneNumber()).isPresent();
        if (userByUsername) {
            throw new IllegalStateException("Username already in use");
        } else if (userByEmail) {
            throw new IllegalStateException("Email already in use");
        } else if (userByPhoneNumber) {
            throw new IllegalStateException("Phone number already in use");
        }
        // encrypt the password
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        // set date joined
        user.setDateJoined(LocalDate.now());
        // save the user to the database
        userRepository.save(user);
        // create a token
        String userToken = UUID.randomUUID().toString();
        Token token = new Token(
                userToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        tokenService.saveToken(token);
        return userToken;
    }

    @Transactional
    public User confirmToken(String token) {
        Token userToken = tokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        if (userToken.getConfirmedAt() != null) {
            throw new IllegalStateException("username already confirmed");
        }
        LocalDateTime expiredAt = userToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        tokenService.setConfirmedAt(token);
        userRepository.enabled(userToken.getUser().getUsername());
        return userRepository.findUserByUsername(userToken.getUser().getUsername()).orElse(null);
    }

    public void deleteUserById(Long id) {
        // delete all data associated to the user
        List<Token> userTokens = tokenRepository.findByUser(getUserById(id));
        for(Token token : userTokens) tokenRepository.deleteById(token.getId());
        List<Message> userMessages = messageRepository.findByUser(getUserById(id));
        if (userMessages != null) for(Message message : userMessages) messageRepository.deleteById(message.getId());
        List<Shelf> userShelfEntries = shelfRepository.findByUser(getUserById(id));
        if (userShelfEntries != null) for(Shelf entry : userShelfEntries) shelfRepository.deleteById(entry.getId());
        // change forum administrators
        List<Forum> userForums = forumRepository.findByUser(getUserById(id));
        if (userForums != null) {
            for (Forum forum : userForums) forum.setUser(getUserById(0L));
            forumRepository.saveAll(userForums);
        }
        // delete user
        userRepository.deleteById(id);
    }
}