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
        ...
    },
    {
        "id": 2,
        "username": "johndoe",
        "firstName": "john",
        "lastName": "doe",
        "email": "johndoe@gmail.com",
        "password": "$2a$10$loBNPu/2cqX23t9vJ8mw2O4HI6eHWMKbPnVw5CYfzOp2YjArKOAiy",
        ...
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
    ...
}
```

### Delete a User
Method: `DELETE` | Endpoint: `/api/users/[insert id here]`

## Book Endpoints
### Add a New Book
Method: `POST` | Endpoint: `/api/books/add`
```
{
    "isbn_13": ["9780091906382"]
}
```
It makes an external request to OpenLibrary API and requests for information about the book. It then compiles the information gathered and store it in the database. Lastly returns the book information.
```
{
    "id": 1,
    "title": "The Power Of Positive Thinking",
    "description": null,
    "authors": [
        {
            "id": 2,
            "key": "/authors/OL9291283A",
            "name": "NORMAN VINCENT PEALE"
        }
    ],
    "isbn_10": [
        "0091906385"
    ],
    "isbn_13": [
        "9780091906382"
    ],
    "works": [
        {
            "id": 2,
            "key": "/works/OL24646378W"
        }
    ],
    "subjects": null,
    "publishers": [
        "EBURY PRESS"
    ],
    "publish_date": "Jul 01, 2004",
    "covers": [
        11332349
    ]
}
```
If OpenLibrary API can not find a match, a new book will be added and sent with null values.
```
{
    "isbn_13": ["9789390213757"]
}
```
```
{
    "id": 2,
    "title": null,
    "description": "Open cannot find information about the book. Manually insert book information",
    "authors": null,
    "isbn_10": null,
    "isbn_13": [
        "9789390213757"
    ],
    "works": null,
    "subjects": null,
    "publishers": null,
    "publish_date": null,
    "covers": null
}
```
### Get all Books
Method: `GET` | Endpoint: `/api/books/all`

Returns all books:
```
[
    {
        "id": 1,
        "title": "The Power Of Positive Thinking",
        "description": null,
        "authors": [
            {
                "id": 2,
                "key": "/authors/OL9291283A",
                "name": "NORMAN VINCENT PEALE"
            }
        ],
        ...
    },
    {
        "id": 2,
        "title": null,
        "description": "OpenLibrary API cannot find information about the book. Manually insert book information",
        "authors": [],
        ...
    },
    {
        "id": 3,
        "title": "Seventh-day Adventists believe",
        "description": null,
        "authors": [
            {
                "id": 1,
                "key": null,
                "name": "UNKNOWN"
            }
        ],
        ...
    }
]
```
### Get a Books
Method: `GET` | Endpoint: `/api/books/[insert id here]`

Returns book details:
```
{
    "id": 1,
    "title": "The Power Of Positive Thinking",
    "description": null,
    "authors": [
        {
            "id": 2,
            "key": "/authors/OL9291283A",
            "name": "NORMAN VINCENT PEALE"
        }
    ],
    ...
}
```
### Get book by ISBN
Method: `GET` | Endpoint: `/api/books/isbn/[insert isbn here]`
### Get books title
Method: `GET` | Endpoint: `/api/books/title/[insert title here]`
### Get books by Subject
Method: `GET` | Endpoint: `/api/books/subject/[insert subject here]`
### Delete a Books
Method: `DELETE` | Endpoint: `/api/books/[insert id here]`