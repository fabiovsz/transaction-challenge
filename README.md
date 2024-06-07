<h1 align="center" style="font-weight: bold;"> Transaction Challenge 💲</h1>

<p align="center">
 <a href="#tech">Technologies</a> • 
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a>
</p>

<p align="center">
    <b>Simple description of what your project do or how to use it.</b>
</p>

<h2 id="technologies">ℹ️ Project Details</h2>

This project was developed using Java and Spring Framework in order to practicing the concepts of software architecture, REST principles, hibernate, lombok, JPA repository, Spring Security, Tests with JUnit and Mockito and other technologies.

I took this back end challenge from this repo [picpay-desafio-backend](https://github.com/PicPay/picpay-desafio-backend) to improve my skills and test my knowledge.

<h2 id="technologies">💻 Technologies</h2>

- Java
- Spring Boot
- PostgreSQL
- Docker

<h2 id="started">🚀 Getting started</h2>

<h3>Pre-requisites</h3>

- Java
- Docker
- Maven

<h3>Cloning</h3>

How to clone your project

```bash
git clone git@github.com:fabiovsz/transaction-challenge.git
```

<h3>Config appliction.properties variables</h2>

Use the `application.properties.example` as reference to create your configuration file `application.properties` with your properties

```yaml
spring.application.name=
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=

spring.mail.host=
spring.mail.port=
spring.mail.username=
spring.mail.password=

api.security.token.secret=
```

<h3>Starting</h3>


```bash
cd transaction-challenge
docker-compose up
mvn spring-boot:run
```

<h2 id="routes">📍 API Endpoints</h2>
<h3>Auth</h3>

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /auth/register</kbd>     | register an user [request details](#get-auth-detail)
| <kbd>POST /auth/login</kbd>     | login an user [request details](#post-auth-detail)

<h3 id="get-auth-detail">POST /auth/register</h3>

**REQUEST**
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "userType": "USER",
    "email": "johndoe@domain.com",
    "password": "123123123",
    "document": "99999999998",
    "balance": "1000"
}
```

<h3 id="post-auth-detail">POST /auth/login</h3>

**REQUEST**
```json
{
    "email": "johndoe@domain.com",
    "password": "123123123"
}
```

<h3>Transactions</h3>

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /transactions/</kbd>     | create a new transaction [request details](#get-transaction-detail)

<h3 id="get-transaction-detail">POST /transactions/</h3>

**REQUEST**
```json
{
    "amount": "220",
    "senderId": "295fb0e5-73ad-4ce5-80d9-fee608129faf",
    "receiverId": "5e74c165-0ba1-48cd-89d6-69133ebccd0c"
}
```