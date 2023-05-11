# Personal Book Management API

A web API created using Java Spring Boot and PostgreSQL database. The API allows accommodates for:
1. User Registration and Authentication
2. Book Management
3. Personal Shelf Management
4. Forum creation and management

# Installation

# Usage
When the project is run locally, the root url is `http://localhost:8080`.
## User Endpoints
### Create a New User
Method: `POST` | Endpoint: `/api/register/create`
```
{
    "username": "johndoe",
    "firstName": "john",
    "lastName": "doe",
    "email": "johndoe@gmail.com",
    "phoneNumber":"260960123456",
    "password": "password"
}
```
Returns an authentication token:
```
47163296-96e3-403b-a19d-958336218237
```

### Token Authentication
Method: `GET` | Endpoint: `/api/users/confirm?token=[insert token here]`

Returns the user details:
```
{
    "id": 2,
    "username": "johndoe",
    "firstName": "john",
    "lastName": "doe",
    "email": "johndoe@gmail.com",
    "password": "$2a$10$loBNPu/2cqX23t9vJ8mw2O4HI6eHWMKbPnVw5CYfzOp2YjArKOAiy",
    "dateJoined": "2023-05-11",
    "phoneNumber": "260960123456",
    "role": "USER",
    "locked": false,
    "enabled": false,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "accountNonLocked": true,
    "authorities": [
        {
            "authority": "USER"
        }
    ]
}
```

### Get All Users
Method: `GET` | Endpoint: `/api/users/all`

Returns all users:
```
[
    {
        "id": 1,
        "username": "username",
        "firstName": "",
        "lastName": "",
        "email": "defaultuser@bookshelf.com",
        "password": "$2a$10$.ZbMkZ8swBoW.nmgfbtHlesq.ZFajuFMqjn353fVX.7hU7j3/X6jC",
        "dateJoined": "2023-05-11",
        "phoneNumber": "260960000001",
        "role": "USER",
        "locked": true,
        "enabled": true,
        "accountNonExpired": true,
        "credentialsNonExpired": true,
        "accountNonLocked": false,
        "authorities": [
            {
                "authority": "USER"
            }
        ]
    },
    {
        "id": 2,
        "username": "johndoe",
        "firstName": "john",
        "lastName": "doe",
        "email": "johndoe@gmail.com",
        "password": "$2a$10$loBNPu/2cqX23t9vJ8mw2O4HI6eHWMKbPnVw5CYfzOp2YjArKOAiy",
        "dateJoined": "2023-05-11",
        "phoneNumber": "260960123456",
        "role": "USER",
        "locked": false,
        "enabled": false,
        "accountNonExpired": true,
        "credentialsNonExpired": true,
        "accountNonLocked": true,
        "authorities": [
            {
                "authority": "USER"
            }
        ]
    }
]
```

### Get a User
Method: `GET` | Endpoint: `/api/users/[insert id here]`

Returns user details:
```
{
    "id": 2,
    "username": "johndoe",
    "firstName": "john",
    "lastName": "doe",
    "email": "johndoe@gmail.com",
    "password": "$2a$10$loBNPu/2cqX23t9vJ8mw2O4HI6eHWMKbPnVw5CYfzOp2YjArKOAiy",
    "dateJoined": "2023-05-11",
    "phoneNumber": "260960123456",
    "role": "USER",
    "locked": false,
    "enabled": false,
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "accountNonLocked": true,
    "authorities": [
        {
            "authority": "USER"
        }
    ]
}
```

### Delete a User
Method: `DELETE` | Endpoint: `/api/users/[insert id here]`