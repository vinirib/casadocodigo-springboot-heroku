# Projeto Casa do Código Spring boot

Projeto realizado para aplicar práticas desenvolvidas nos módulos dos cursos de Spring do Alura.
<hr/>

### Tecnologias utilizadas

- Java 8
- Spring Boot
- Spring Security
- Spring Cache
- Spring Data JPA
- Postgres
- Frontend
    - Thymeleaf
    - HTML
    - CSS
    - Javascript

### Tecnologias utilizadas após upgrade - 2023

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

## Utilização

Aplicação publicada na plataforma [fly.io](https://heroku.com/).
Para utilizar acesse o endereço abaixo:

https://casa-do-codigo-vini.fly.dev/

Como esta aplicação está utilizando um serviço gratuito do Heroku, pode ser que sua primeira request
irá inicializar o servidor gerando maior tempo de resposta, aguarde 1 minuto e tente novamente.

### Executando localmente

Para rodar localmente você deverá utilizar:

- Java 17
- Docker

Execute o container do banco de dados localmente executando este comando na pasta raiz:

```
docker build -t casa-do-codigo .
docker run -d --name casa-do-codigo -p 8080:8080 casa-do-codigo
```

## Aviso

Este projeto não está seguindo todas as normas, convenções e padrões
da plataforma Java, portanto, naturalmente, ao analisar o código você poderá
se deparar com algum anti pattern. Esta aplicação teve objetivo totalmente acadêmico
e não deverá ser utilizado para aprendizado ou replicação.