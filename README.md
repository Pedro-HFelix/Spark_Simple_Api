# Simple Spark API

Este é um projeto simples criado para entender e explorar o framework [Spark](https://github.com/perwendel/spark). A aplicação demonstra a construção de uma API REST básica usando Spark, um microframework para Java que facilita a criação de aplicações web e APIs.

## Descrição do Projeto

Este projeto é uma API simples para gerenciamento de alimentos. Utiliza o Spark Java para a criação de rotas e a manipulação de requisições HTTP. O projeto inclui exemplos de operações CRUD (Create, Read, Update, Delete) para um recurso de `food`, e é uma boa base para aprender como configurar e trabalhar com o Spark.

## Dependências

O projeto é construído usando Maven e as seguintes bibliotecas:

- **[Spark Java](https://github.com/perwendel/spark)**: `2.5.4`  
  Microframework para criar aplicações web e APIs em Java.

- **[Gson](https://github.com/google/gson)**: `2.10.1`  
  Biblioteca para converter objetos Java para JSON e vice-versa.

- **[Spark Debug Tools](https://github.com/perwendel/spark-debug-tools)**: `0.5`  
  Ferramentas adicionais para depuração e análise de aplicações Spark.

- **[PostgreSQL JDBC Driver](https://jdbc.postgresql.org/)**: `42.5.0`  
  Driver JDBC para conectar e interagir com bancos de dados PostgreSQL.

- **[Dotenv Java](https://github.com/cdimascio/dotenv-java)**: `2.2.0`  
  Biblioteca para carregar variáveis de ambiente a partir de um arquivo `.env`.

## Estrutura do Projeto

- **`src/main/java`**: Código-fonte da aplicação.
  - **`spark.api.controllers.food`**: Contém os controladores e manipuladores para as rotas relacionadas a alimentos.
  - **`spark.api.repository`**: Contém a interface e implementação para a camada de repositório.
  - **`spark.api.dtos`**: Contém as classes de Data Transfer Object (DTO).
  - **`spark.api.infra.database`**: Configurações de banco de dados.

- **`pom.xml`**: Arquivo de configuração do Maven, contendo as dependências e configurações do projeto.

## Como Executar

1. **Clone o repositório**:
    ```sh
    git clone https://github.com/Pedro-HFelix/Spark_Simple_Api.git
    ```

2. **Navegue até o diretório do projeto**:
    ```sh
    cd Spark_Simple_Api
    ```

3. **Compile e execute a aplicação**:
    ```sh
    mvn compile exec:java -Dexec.mainClass="spark.api.Main"
    ```

4. **Acesse a API**:
    - **Health Check**: `GET http://localhost:4567/health`
    - **CRUD Operations**:
      - **Create**: `POST http://localhost:4567/foods`
      - **Read All**: `GET http://localhost:4567/foods`
      - **Read by ID**: `GET http://localhost:4567/foods/:id`
      - **Update**: `PUT http://localhost:4567/foods/:id`
      - **Delete**: `DELETE http://localhost:4567/foods/:id`
