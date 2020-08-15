# Spring Boot and spring security sample with Mongo DB Authentication and JWT.  
## Mongo DB Auth with JWT token. 
### REST end point for now only modifies or returns a static student object.

A minimal, secure RESTFUL api for Spring Boot. This project includes login, access control of end-points, and encrypted hashing of passwords right out of the box!

# Installation

- Clone the repo by using `git clone`.
- Add the DB properties in application.properties.
- Run `mvn spring-boot:run` on the cloned directory.
 
## Points to get started
- All the rest end points are secured with spring security.
- In order to access secured end points you need to add ```Authorization: Bearer {{token}}``` in header.
- Rest end points are accessible to selected roles only.
- ROLE_USER can only read specific student.
- ROLE_TRAINER can read all the students.
- ROLE_ADMIN can read, write, modify, delete students.
- STUDENTS is a static array of STUDENTS.
 

# Running the software
- `mvn spring-boot:run` for simple setups.
 
## Endpoints
 
### API Endpoints AUTH
| Method | Url                                      | Expected Input                   | Header Required                       | Expected Output                    |
| ------ | ---------------------------------------- | -------------------------------- | ------------------------------------- | ---------------------------------- |
| POST   | `http://host:8080/auth/login`            | [SignUpRequest](#SignUpRequest)  |                                       | String                             |    
| POST   | `http://host:8080/auth/login`            | [LoginRequest](#LoginRequest)    |                                       | [LoginResponse](#LoginResponse)    |
| GET    | `http://host:8080/auth/logout`           |                                  | ```Authorization: Bearer {{token}}``` | String                             |
| GET    | `http://host:8080/auth/logoutAll`        |                                  | ```Authorization: Bearer {{token}}``` | String                             |
| GET    | `http://host:8080/auth/tryAutoLogin`     |                                  | ```Authorization: Bearer {{token}}``` | String                             |
| DELETE | `http://host:8080/auth/`                 |                                  | ```Authorization: Bearer {{token}}``` | String                             |

### API End points STUDENTS
| Method | Url                                      | Expected Input                   | Required Role        | Expected Output     | Header Required                       |
| ------ | ---------------------------------------- | -------------------------------- | -------------------- | ------------------- | ------------------------------------- |
| POST   | `http://host:8080/student/`              | [Student](#Student)              | ADMIN                | String              | ```Authorization: Bearer {{token}}``` |
| GET    | `http://host:8080/student/{id}`          |                                  | USER/TRAINER/ADMIN   | [Student](#Student) | ```Authorization: Bearer {{token}}``` |
| GET    | `http://host:8080/student/`              |                                  | ADMIN/TRAINER        | [Student](#Student) | ```Authorization: Bearer {{token}}``` |
| PUT    | `http://host:8080/student`               | [Student](#Student)              | ADMIN                | [Student](#Student) | ```Authorization: Bearer {{token}}``` |
| DELETE | `http://host:8080/student/{id}`          |                                  | ADMIN                | [Student](#Student) | ```Authorization: Bearer {{token}}``` |

## Documents

### SignUpRequest
| Name      | Type   | Description                | Required |
| ------    | ------ | -------------------------- | -------- |
| Username  | String | Username of user           | Yes      |
| Email     | String | Email of user              | Yes      |
| ROLE      | String | ROLE_{USER/ADMIN/TRAINER}  | Yes      |
| Password  | String | password                   | Yes      |

### LoginRequest
| Name      | Type   | Description                | Required |
| ------    | ------ | -------------------------- | -------- |
| Username  | String | Username of user           | Yes      |
| Password  | String | password                   | Yes      |

### LoginResponse
| Name      | Type   | Description                |
| ------    | ------ | -------------------------- |
| Token     | String | JWT Token                  |
| Type      | String | ```Bearer```               |
| Id        | String | Mongodb generated id       |
| Username  | String | Username of user           |

### Student
| Name      | Type   | Description                | Required |
| ------    | ------ | -------------------------- | -------- |
| id        | Long   | id of user                 | Yes      |
| Name      | String | Name of user               | Yes      |
