# Shopping Cart

**shopping-cart** foi desenvolvido para um teste usando SpringBoot e AngularJS. A aplicação funciona como um e-commerce, aonde o usuário pode adicionar produtos pré-cadastrados no carrinho, acesssar a página do carrinho para alterar a quantidade ou excluir os itens que não deseja e finalizar o pedido. Assim que o usuário finaliza o pedido é informado qual o ID do pedido e o valor da compra.

## Tecnologias
* **spring-boot** - utilizando os módulos spring-boot-starter-data-jpa para persistencia com hibernate e spring-boot-starter-web para criar RESTful Web Service.
* **MySQL ou PostgresSQL** - base de dados relacional.
* **angular-js 1.6.3** - tecnologia para desenvolver o front-end.
* **bootstrap 3** - paginas responsivas.
* **gulp** - para build, automatização e taks como juntar todos os arquivos js e css em um "minified".
* **npm** - para baixar as dependencias do front-end.
* **maven** para iniciar o build do front, realizar o build do projeto Java e criar o jar. 

## Demo
Foi realizado deploy do projeto no Heroku, que pode ser acessado neste link: [Heroku](https://shopping-cart-dc.herokuapp.com/shoppingcart)


## Configurações importantes antes do Build

O projeto vem pré-configurado para as bases MySQL e PostgresSQL.
No arquivo ```src/main/resources/application.properties``` se encontram as configurações da base de dados. Verifique qual base deseja utilizar e as configurações de acesso: 
```
spring.datasource.username=root
spring.datasource.password=root
```

## Para realizar o Build
Use o Maven:
```
$ maven clean install
```

## Para rodar o projeto
Rode o seguinte comando na pasta target/ criada pelo build:
```
$ java -jar shopping-cart-0.0.1-SNAPSHOT
```
e acesse:
```
$ localhost:8080/shoppingcart
```

## TODO
* Autenticação.
* Página de confirmação do pedido.
* Página de histórico de pedidos.
* Testes unitários e de integração.


