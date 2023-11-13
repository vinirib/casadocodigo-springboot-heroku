# Case Study: "Casa do Código" Project with Spring Boot

This project was created as part of a course on the [Alura](https://www.alura.com.br/) platform. After completing the 
course, additional features were incorporated, and the application was deployed on a cloud platform.

Readme in Portuguese - [HERE](README-PT.md)
<hr/>

### Technologies Used During the Course

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

### Technologies Used After Upgrading - 2023

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

## How to Use

This application is deployed on [fly.io](https://fly.io). You can access the application at the following address:

[https://casa-do-codigo-vini.fly.dev/](https://casa-do-codigo-vini.fly.dev/)

Additionally, note that this application uses fly.io free nodes. When you send the first request, 
the nodes may be in a hibernating state. The initial request will wake up the nodes, and after approximately 1 minute, 
you can check again, and the application will be ready to navigate.

### Running Locally

To run this application locally, you will need:

- OpenJDK 17+
- Docker

First, in the root folder, execute the following command to set up the database container:

```bash
docker build -t casa-do-codigo .
docker run -d --name casa-do-codigo -p 8080:8080 casa-do-codigo
```

### Warning

This project does not strictly adhere to all Java conventions. It was created primarily for practicing Spring Boot, 
Thymeleaf, and Spring Security features. Upon closer examination of the code, you may notice some code smells. 
For this reason, it is not recommended to use this code as an example or in a production environment.