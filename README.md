# Personal Book Management System

A web application that allows users to manage their offline book collections in an online database. Users can add books to their collections by entering the ISBN, and the system looks up the book's details via the OpenLibrary API and stores them in the database. The system binds the user to the book. The user can create a forum for the book to find other readers and have conversations with them.

## Installation

To install the project, follow these steps:
Clone the repository to your local machine.
Install the required dependencies using mvn install.
Start the server using mvn spring-boot:run.

## Issues

If you encounter any issues when using the application, please report them on the GitHub Issues page. Be sure to include detailed information about the issue, including steps to reproduce it and any error messages you received.

## License

The project is licensed under the MIT License. See the LICENSE file for more information.


# API Documentation

## User API

## Registration Endpoint

### Create a new user
Create a new user with the provided details.

**URL** : `/api/register/create`

**Method** : `POST`

**Request Body** :
```json
{
    "username": "johndoe",
    "firstName": "John",
    "lastName": "Doe",
    "email": "johndoe@example.com",
    "password": "password",
    "phoneNumber": "1234567890",
    "role": "USER"
}
```

**Response** : HTTP Status Code 201 Created

**Response Body** :
```json
"token"
```
The authentication token for the newly created user.

## User Endpoint

### Get all users
Get all registered users in the system.

**URL** : `/api/users/all`

**Method** : `GET`

**Response** : HTTP Status Code 200 OK

**Response Body** :
```json
[
    {
        "id": 1,
        "username": "johndoe",
        "firstName": "John",
        "lastName": "Doe",
        "email": "johndoe@example.com",
        "dateJoined": "2023-05-09",
        "phoneNumber": "1234567890",
        "role": "USER",
        "locked": false,
        "enabled": false
    },
    {
        "id": 2,
        "username": "janedoe",
        "firstName": "Jane",
        "lastName": "Doe",
        "email": "janedoe@example.com",
        "dateJoined": "2023-05-09",
        "phoneNumber": "0987654321",
        "role": "ADMIN",
        "locked": false,
        "enabled": true
    }
]
```
An array of all registered users in the system.

### Get a specific user by ID
Get the user with the specified ID.

**URL** : `/api/users/{id}`

**Method** : `GET`

**URL Parameters** :
- `id` : ID of the user to retrieve.

**Response** : HTTP Status Code 200 OK

**Response Body** :
```json
{
    "id": 1,
    "username": "johndoe",
    "firstName": "John",
    "lastName": "Doe",
    "email": "johndoe@example.com",
    "dateJoined": "2023-05-09",
    "phoneNumber": "1234567890",
    "role": "USER",
    "locked": false,
    "enabled": false
}
```
The user with the specified ID.

### Delete a user
Delete the user with the specified ID.

**URL** : `/api/users/{id}`