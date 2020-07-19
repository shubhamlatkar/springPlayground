# Spring Boot and spring security sample with in memory Authentication  
## Basic Auth with in memory Authentication
### REST end point for now only sysout(method) or return static students object

A minimal, secure RESTFUL api for Spring Boot. This project includes basic http login, access control of end-points, and encrypted hashing of passwords right out of the box!

## Installation

- Clone the repo by using `git clone`.
- Add the DB properties in application.properties.
- Run `mvn spring-boot:run` on the cloned directory.
- Visit `http://localhost:8080/inset/sample/users` to insert authorities, roles and sample users.
 
## Steps to add new API
- Copy the test controller model (models) to a new file in the **controller** package and make the modifications for routs.
`copy com.shubham.SpringSecurity.controller.TestController.java --> com.shubham.SpringSecurity.controller.CustomController.java`
- Add the routing line to CustomController , like so:

```
@GetMapping("/")
public String defaultGet() {
    return ...
}
```

## Running the software
- `mvn spring-boot:run` for simple setups.

#### Can be access having ROLE_USER
To create users, simply send a GET to /api/students/{id} with the required fields in the query string, like so:

```
http://localhost:8080/api/students/1
```


#### Can be access having ROLE_USER and ROLE_ADMIN
To create users, simply send a GET to /management/students , like so:

```
http://localhost:8080/management/students/
```

#### Can be access having authority user:write
To create users, simply send a DELETE to /management/students/{id} with the required fields in the query string, like so:

```
http://localhost:8080/management/students/
```
 
## API Endpoints
| Method | Url | Expected Output |
| ------ | ------ | ------ |
| POST | `http://host:8080/users` | creates object with fields foo=hello, bar=world |
| GET | `http://host:8080/management/students` | get all students |
| GET | `http://host:8080/api/students/{id}` | gets single student |
| POST | `http://host:8080/management/students` | sout(post student) |
| DELETE | `http://host:8080/management/students/{id}` | sout(del) |
| PUT | `http://host:8080/management/students/{id}` | sout(put student) |



