ktor
===

This folder contains a small example of a web application written in kotlin using Ktor, an asynchronous Web framework.
The web application is a simple Rest API for managing comments on posts. 
For storage the application uses H2, an in-memory database. 
Since the application is in-memory the data is wiped and reinitialized when the application restarts. 

## Getting started

For simplicity this project is intended to be opened, built and executed within IntelliJ:
1. Select `file -> open` and navigate to this folder (ktor).
2. Open the project and wait for it to load
3. Open the file src/Application.kt, to the left of the main method there is a play symbol :arrow_forward:.
Click the symbol and select the first option "Run".  

When you see something like this in the Run window 

```
[main] TRACE Application - Application started: io.ktor.application.Application
```
the application has started.

Check that the Application is running by opening this url: http://0.0.0.0:8080/ in your favorite browser.

## REST API Overview

The Application has five routes which are:
- `GET /` - Responds with a simple "HELLO WORLD".
- `GET /comments` - Responds with a list of all the comments.
- `GET /comments/{commentId}` - Responds with the comment associated with the `commentId`.
- `GET /posts/{postId}/comments` - Responds with all comments associated with the post with the `postId`.
- `POST /posts/{postId}/comments` - Adds a comment and associates it with `postId`.

## Code explanation

There are two kotlin files in the project. 

### Application.kt

Application.kt contains main parts of the web application. 
- The installation and configuration of some necessary features. 
- The definition of the different routes and their handlers.
- The Comment data class which is the object used for the API
- Service methods for data access.
 
### db.kt

Contains all the database initialization logic.
- The table definition for the Comments table
- The setup of the database connection.
- Initialization of the dummy data.
- A convenience method for wrapping queries in a transaction and coroutine.

## Notes

The example is simple with most of the logic in a single file. 
The example is meant to demonstrate that Kotlin with Ktor provides a concise way to create a simple web application.
