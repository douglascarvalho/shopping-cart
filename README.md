# Shopping Cart

**shopping-cart** foi desenvolvido para um teste usando SpringBoot e AngularJS. A aplicação funciona como um e-commerce, onde o usuário pode adicionar produtos pré-cadastrados no carrinho, acessar a página do carrinho para alterar a quantidade ou excluir os itens que não deseja e finalizar o pedido. Assim que o usuário finaliza o pedido é informado qual o ID do pedido e o valor da compra.

## Arquitetura (Resumo)
Ao rodar o build o gulp pega as dependencias, minifica os .js e .css e coloca na pasta src/main/resources/static, para que fique disponível dentro do ```jar``` assim que o maven empacotar. O projeto em angular utiliza Controllers e Factories para gerenciar as requisições do DOM. Esses Controllers e Factories fazem requisições para o serviço REST que foi desenvolvido utilizando o Spring (Java). O servidor recebe as requisições, realiza a lógica na camada de Serviço e retorna as informações.

## Tecnologias
* **spring-boot** - utilizando os módulos spring-boot-starter-data-jpa para persistencia com hibernate e spring-boot-starter-web para criar RESTful Web Service.
* **MySQL ou PostgresSQL** - base de dados relacional.
* **angular-js 1.6.3** - tecnologia para desenvolver o front-end.
* **bootstrap 3** - paginas responsivas.
* **gulp** - para build, automatização e taks como juntar todos os arquivos js e css em um "minified".
* **npm** - para baixar as dependencias do front-end.
* **maven** para iniciar o build do front, realizar o build do projeto Java e criar o jar. 

## Demo
Foi realizado deploy do projeto no Heroku, que pode ser acessado neste link: 
[Heroku](https://shopping-cart-dc.herokuapp.com/shoppingcart)

**Nota:** Talvez demore alguns segundos para abrir pois o Heroku desliga os dynos quando o servidor para de receber requisições.


## Configurações importantes antes do Build

O projeto vem pré-configurado para as bases MySQL e PostgresSQL.
No arquivo ```src/main/resources/application.properties``` se encontram as configurações da base de dados. Verifique qual base deseja utilizar e as configurações de acesso: 
```
spring.datasource.username=root
spring.datasource.password=root
```
O Hibernate também esta configurado para dropar e criar a base no momento do deploy:
```
spring.jpa.hibernate.ddl-auto = create-drop
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

## EndPoints:

**GET** ```/products``` Retorna a lista de produtos.

**GET** ```/cart/productsCount``` Retorna a quantidade de produtos no carrinho.

**GET** ```/cart/productsAmount``` Retorna o valor total do carrinho.

**GET** ```/cart/productsOrderList``` Retorna os produtos que estão no carrinho. 

**POST** ```/cart/addToCart``` Adiciona um produto no carrinho. 

**PUT** ```/cart/updateProductQuantity``` Altera um produto do carrinho. 

**DELETE** ```/cart/deleteFromCart/{id}``` Remove um produto do carrinho. 

**POST** ```/cart/checkout``` Finaliza o pedido salvando os dados na base. 


## TODO
* Autenticação.
* Página de confirmação do pedido.
* Página de histórico de pedidos.
* Testes unitários e de integração.

## Outras Infos
**Nota**
Por algum motivo o PostgresSQl não cria IDs de forma incremental. Pelo pouco que pesquisei é necessário criar uma sequence para que funcione direito de forma incremental (1,2,3,4...). Como desenvolvi inicialmente no MySQL e não tinha esse problema, isso só fui perceber quando fiz deploy no Heroku, porém a consistência da base permanece normalmente.

![Select Image](http://i.imgur.com/woF3qcw.png)

