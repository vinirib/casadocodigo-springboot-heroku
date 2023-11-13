# Study case project 'Casa do Código' with Spring boot

This project was created during a course made in the platform
[Alura](https://www.alura.com.br/). After finished the course,
I added some extra features and deployed on a cloud platform.

Readme in Portuguese - [HERE](README-PT.md)
<hr/>

### Technologies used during the course

- Java 8
- Spring Boot
- Spring Security
- Spring Cache
- Spring Data JPA
- Postgres 9
- Frontend
    - Thymeleaf
    - HTML
    - CSS
    - Javascript

### Technologies used after upgrading - 2023

- Java 17
- Spring Boot 2.7.2
- Spring Security 5
- Spring Cache 5
- Spring Data JPA 5
- H2
- Frontend
  - Thymeleaf
  - HTML
  - CSS
  - Javascript
- Github Actions
  - Renovate
  - Snyc

## How to use

This application is deployed on [fly.io](https://heroku.com/).
You can access the application on the address bellow:

https://casa-do-codigo-vini.fly.dev/

Besides, this application is using Heroku free nodes, when you send
the first request, the nodes may being hibernating, the first request
will start the nodes again and after 1 minute or so, you can
check again and the application will be ready to navigate.

### Running locally

To run this application locally, you will need:

- OpenJDK 17 +
- Docker

First, in the root folder, execute the command bellow to raise
the database container:

```
docker build -t casa-do-codigo .
docker run -d --name casa-do-codigo -p 8080:8080 casa-do-codigo
```

### Warning

This project is not following all Java conventions, this project was made just
for practice the mainly Spring Boot, Thymeleaf and Spring Security features.
You may see some code smell if you analyse the code deeper, for that reason is not recommended
use that code as example, or in the production environment.