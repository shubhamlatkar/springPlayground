# Spring Boot Mongo DB sample  
Spring Mongo Repository CURD sample. 

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

## Signup new user
```
SignUp url:-
http://host:8080/signup

Required object:-
{
    "username":"username",
    "password":"password",
    "email":"email@email.com",
    "roles": ["ROLE_ADMIN"]   
}
```

## SignIn existing user
```
SignIn url:-
http://host:8080/login

Required Object:-
{
    "username":"username",
    "password":"password"
}
```

## API Endpoints
| Method | Url | Expected Output |
| ------ | ------ | ------ |
| POST | `http://host:8080/login` | creates jwt token for user |
| GET | `http://host:8080/signup` | create obj for user |
| GET | `http://host:8080/test/data` | only user with ROLE_USER can access it |
| POST | `http://host:8080/management/students` | only user with user:write can access it |



