# REST API
Uma API REST escrita em Java.

Usando as seguintes ferramentas
* Spring Boot como framework
* Gradle como gerenciador de dependências e processo de build
* Mockito e JUnit para testes
* Hibernate para mapeamento de objeto relacional
* H2 Database como banco de dados em memória

# Build & Run
Para buildar o projeto e fazê-lo executar, apenas execute o comando
```sh
gradle clean build && java -jar build/libs/spring-rest-api-0.1.0.jar
```

Está aplicação também está hospedada no Heroku e você pode usá-la, basta utilizar a URL https://spring-rest-api-gabriel.herokuapp.com/ no lugar de localhost:8080

# Endpoints
- [POST /register](#register)
- [POST /login](#login)
- [POST /profile](#profile)

### POST /register

Você poderá usar este endpoint para registar usuários.

Exemplo de request
```
$ curl -H "Content-Type: application/json" -X POST -d
{
	"name": "Nome usuário",
	"email" : "teste@teste.com",
	"password" : "senha#senha",
	"phones" : [
		{
			"ddd":"21",
			"phone":"1231231"
		}]
} localhost:8080/register
```

Response esperada
```
{
  "id": 1,
  "name": "Nome usuário",
  "email": "teste@teste.com",
  "phones": [
    {
      "ddd": "21",
      "phone": "1231231"
    }
  ],
  "password": "senha#senha",
  "created": "07-01-2017  22:32:46 PM GMT-02:00",
  "lastLogin": "07-01-2017 22:32:46 PM GMT-02:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUBnYWJycWVxZWxxMTIzIiwiaWF0IjoxNDgzODM1NTY2LCJleHAiOjE0ODM4MzU2MjZ9._pDPBXSvtdq4tBISWgBFcj9Bp8mePNoKqt-fSqETsFhvngFtIH6UcKQy33DWmqy7aMp6OJFsD_HulRdMj6qcFQ"
}
```

### POST /login

Para gerar um novo token, caso sua sessão tenha expirado, você poderá usar este endpoint. O token gerado nesse endpoint só dura o tempo de 30 minutos.

Exemplo de request
```
$ curl -H "Content-Type: application/json" -X POST -d
{
	"email" : "teste@teste.com",
	"password" : "senha#senha"
} localhost:8080/login
```

Response esperada
```
{
  "id": 1,
  "name": "Nome usuário",
  "email": "teste@teste.com",
  "phones": [
    {
      "ddd": "21",
      "phone": "1231231"
    }
  ],
  "password": "senha#senha",
  "created": "07-01-2017  22:32:46 PM GMT-02:00",
  "lastLogin": "07-01-2017 22:32:46 PM GMT-02:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUBnYWJycWVxZWxxMTIzIiwiaWF0IjoxNDgzODM1NTY2LCJleHAiOjE0ODM4MzU2MjZ9._pDPBXSvtdq4tBISWgBFcj9Bp8mePNoKqt-fSqETsFhvngFtIH6UcKQy33DWmqy7aMp6OJFsD_1oij2oe1id1"
}
```

### POST /profile

Neste endpoint, você pode obter o perfil de um usuário. Lembre-se de passar o token na header, com a chave 'Authorization'.

Exemplo de request
```
$ curl -H "Content-Type: application/json" -H "Authorization: token" -X POST -d
{
	"email" : "teste@teste.com"
} localhost:8080/profile
```

Response esperada
```
{
  "id": 1,
  "name": "Nome usuário",
  "email": "teste@teste.com",
  "phones": [
    {
      "ddd": "21",
      "phone": "1231231"
    }
  ],
  "password": "senha#senha",
  "created": "07-01-2017  22:32:46 PM GMT-02:00",
  "lastLogin": "07-01-2017 22:32:46 PM GMT-02:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUBnYWJycWVxZWxxMTIzIiwiaWF0IjoxNDgzODM1NTY2LCJleHAiOjE0ODM4MzU2MjZ9._pDPBXSvtdq4tBISWgBFcj9Bp8mePNoKqt-fSqETsFhvngFtIH6UcKQy33DWmqy7aMp6OJFsD_1oij2oe1id1"
}
```

# Test
A aplicação possui teste unitário. Você pode executá-los utilizando

```
$ gradle check
```
