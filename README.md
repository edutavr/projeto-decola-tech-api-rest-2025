# Desafio Decola Tech API REST 2025

## Descrição
O código trata de um backend utilizando Java Spring Boot e PostgresSQL com autenticação JWT implementada, onde cada usuário deverá listar em seu perfil uma lista contendo seus álbuns de música preferidos. Apenas o usuário que criou o álbum e o administrador podem deletar o(s) álbum(s) criado(s).

## Guia de Insatalação

- Clone o Repositório

```https://github.com/edutavr/projeto-decola-tech-api-rest-2025.git```

- Verifique as dependências no arquivo pom.xml

- Execute o arquivo ProjetoDecolaTechApplication.java

## Instruções

### Criação do Usuário

- Para registrar um usuário no Postman, utilizando o método POST em 'http://localhost:8080/register', adicione o seguinte body:
  
  ```json
  {"username":"nomeDoUsuario","password":"senha", "role":"ROLE_USER"}
  ```

- Para autenticar o usuário, utilizando o método POST em 'http://localhost:8080/authenticate', adicione o seguinte body:

  ```json
  {"username":"nomeDoUsuario","password":"senha"}
  ```
- O usuário receberá um token ao autenticar. Após isso, coloque a key Authorization em Headers e "Bearer seu_token" em Value;

- Para verificar que está autorizado, utilize o método GET em 'http://localhost:8080/private'

### Lista de Álbuns
  
- Para adicionar um álbum na lista, o usuário autenticado deve utilizar o método POST em 'http://localhost:8080/api/albuns', contendo as seguintes informações (exemplo de álbum):
  
  ```json
  {
  "titulo": "...And Justice for All",
  "artista": "Metallica",
  "anoDeLancamento": 1988,
  "genero": "Heavy Metal"
  }
  ```

- Para deletar um álbum da lista, você deve referenciar o id do álbum desejado na URL. Além disso, é necessário que você seja o criador do álbum que deseja deletar, ou que seja um administrador (RULE_ADMIN). Ex.: Na URL 'http://localhost:8080/api/albuns/1', utilize o método DELETE.
