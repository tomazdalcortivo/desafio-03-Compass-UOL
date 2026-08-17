# Desafio-03

*[🇺🇸 Read the English version below](#desafio-03-english)*

# Índice

- [Descrição](#descricao)
- [Micro serviços](#micro-servicos)
    * [Servidor Eureka](#eureka-server)
    * [API Gateway](#gateway)
- [Como Começar](#como-começar)
- [Autores](#autores)

# <a name="descricao"></a>Sistema de Votação Empresarial

O Sistema de Votação Empresarial é uma API de backend dedicada à gestão dos processos de decisão dentro de uma empresa. Sua arquitetura é baseada REST, proporcionando acesso aos recursos por meio de endpoints. A API assegura a persistência no dados no banco de dados.

# <a name="micro-servicos"></a>Micro Serviços

## <a name="gateway"></a>API Gateway

* **Descrição**: O API Gateway unifica as solicitações de vários microsserviços, oferecendo um único ponto de entrada para os clientes acessarem os recursos do sistema. Ele gerencia roteamento, autenticação, autorização e outras funcionalidades relacionadas à comunicação entre sistemas, simplificando o acesso aos serviços distribuídos por meio da url padrao `http://localhost:8080`.

## <a name="eureka-server"></a>Servidor Eureka

* **Descrição**: O Servidor Eureka é uma aplicação centralizada que permite que os microsserviços se registrem e descubram uns aos outros dinamicamente em um ambiente de computação em nuvem. Ele simplifica a comunicação entre os microsserviços, oferecendo um mecanismo de descoberta de serviços confiável e escalável. Seu console pode ser acessado por `http://localhost:8761`.

## Serviço para a gestão de Funcionários

* **Descrição**: Gerencia o cadastro e a administração de Funcionários.
* **Entidade**:
    * Funcionario
        * nome
        * CPF
        * endereço
        * telefone
        * email
* **Funcionalidades**:
    * Cadastra um Funcionário
    * Excluir um Funcionário
    * buscar um funcionário específico e todos
    * editar um funcionário

* **Endereço individual**: `http://localhost:8081`

### Endpoints

| Método    | URL                       | Descrição                                                                                  |
|-----------|---------------------------|--------------------------------------------------------------------------------------------|
| `POST`    | /api/v1/funcionarios      | [Cadastra um novo Funcionário no sistema.](#cadastrar)                                     |
| `PUT`     | /api/v1/funcionarios      | [Edita um Funcionário.](#editar)                                                           |
| `GET`     | /api/v1/funcionarios      | [Recupera todos os Funcionários cadastrados no sistema.](#buscartodos)                     |
| `GET`     | /api/v1/funcionarios/{id} | [Recupera um Funcionário pelo seu ID.](#buscarporid)                                       |
| `DELETE`  | /api/v1/funcionarios/{id} | [Deleta um Funcionário pelo seu ID.](#excluir)                                             |
| navegador | /swagger-ui/index.html#/  | Documentação do swagger acessado pelo com acesso pelo endereço individual do micro serviço |

## Serviço para a gestão de Propostas

* **Descrição**: Gerencia o cadastro e a administração de propostas.
* **Entidades**:
    * Proposta
        * nome
        * descrição
        * id do funcionário
        * duração da votação
        * inicio da votação
        * lista de votos
    * Votos
        * id do funcionário
        * proposta
        * decisão
* **Funcionalidades**:
    * Cadastra, excluir, edita e busca por uma proposta
    * permite um funcionário votar e criar a sua proposta
    * contabiliza os votos
* **Endereço individual**: `http://localhost:8082`

### Endpoints

| Método    | URL                                          | Descrição                                                                                               |
|-----------|----------------------------------------------|---------------------------------------------------------------------------------------------------------|
| `POST`    | /api/v1/propostas                            | [Cadastra um nova proposta no sistema.](#cadastrar2)                                                    |
| `PUT`     | /api/v1/propostas                            | [Edita um proposta.](#editar2)                                                                          |
| `GET`     | /api/v1/propostas                            | [Recupera todos os propostas cadastrados no sistema.](#buscartodos2)                                    |
| `GET`     | /api/v1/propostas/{id}                       | [Recupera uma proposta pelo seu ID.](#buscarporid2)                                                     |
| `PUT`     | /api/v1/propostas/votar                      | [Permite o Funcionário votar em uma proposta](#votar)                                                   |
| `GET`     | /api/v1/propostas/calcular/1?funcionarioId=1 | [Permite o gerenciador da votação calcular o resultado da votação respeitando o seu termino](#calcular) |
| `DELETE`  | /api/v1/propostas/{id}                       | [Deleta uma proposta pelo seu ID.](#excluir2)                                                           |
| navegador | /swagger-ui/index.html#/                     | Documentação do swagger acessado pelo com acesso pelo endereço individual do micro serviço              |

## Serviço para a gestão de Resultados

* **Descrição**: Gerencia o armazenamento e a visualização de resultados de votações.
* **Entidade**:
    * Resultado
        * id da proposta
        * resultado
* **Funcionalidades**:
    * Persiste os dados das votações anteriores
    * permite consultar uma votação por id e todas paginadas

* **Endereço individual**: `http://localhost:8083`

### Endpoints

| Método    | URL                      | Descrição                                                                                  |
|-----------|--------------------------|--------------------------------------------------------------------------------------------|
| `GET`     | /api/v1/resultados       | [Recupera todos os resultados armazenados no sistema.](#buscartodos3)                      |
| `GET`     | /api/v1/resultados/{id}  | [Recupera um resultado pelo seu ID.](#buscarporid3)                                        |
| navegador | /swagger-ui/index.html#/ | Documentação do swagger acessado pelo com acesso pelo endereço individual do micro serviço |

## Documentação dos Endpoints

Esta seção define os métodos e o formato necessários para enviar uma solicitação, com base no link do endpoints, caso necessário.

### MS-Funcionario

#### Cadastrar Funcionário <a name="cadastrar"></a>
- **Método**: `POST`
- **Body**:

```json
{
    "nome": "Maria de Lourdes",
    "cpf": "12588017010",
    "endereco": "Rua Antonio Carlos",
    "telefone": "1199821994",
    "email": "maria@email.com"
}
```

#### Buscar todos os Funcionários <a name="buscartodos"></a>
- **Método**: `GET`
- **Body**: Vazio

#### Buscar Funcionário por ID <a name="buscarporid"></a>
- **Método**: `GET`
- **Body**: Vazio

#### Excluir Funcionário <a name="excluir"></a>
- **Método**: `DELETE`
- **Body**: Vazio

#### Editar Funcionário <a name="editar"></a>
- **Método**: `PUT`
- **Descrição**: Este método edita o Funcionário.
- **Body**:

```json
{
    "nome": "Maria de Lourdes",
    "cpf": "40892323094",
    "endereco": "Rua Antonio Carlos",
    "telefone": "43995663217",
    "email": "mariadelourdes@email.com"
}
```

### MS-Proposta

#### Cadastrar Proposta <a name="cadastrar2"></a>
- **Método**: `POST`
- **Body**:

```json
{
    "titulo": "Melhoria no Processo de Vendas",
    "descricao": "Proposta para otimizar o processo de vendas com novas ferramentas",
    "funcionarioId": 12
}
```

#### Buscar todas as Propostas <a name="buscartodos2"></a>
- **Método**: `GET`
- **Body**: Vazio

#### Buscar Proposta por ID <a name="buscarporid2"></a>
- **Método**: `GET`
- **Body**: Vazio

#### Editar Proposta <a name="editar2"></a>
- **Método**: `PUT`
- **Body**:

```json
{
    "id": 2,
    "nome": "Proposta 1",
    "descricao": "Essa é uma proposta numero 1",
    "criador": {
        "id": 1,
        "nome": "Maria de Lourdes",
        "cpf": "06976003940",
        "endereco": "Rua Antonio Carlos",
        "telefone": "1199821994",
        "email": "mariazinha@email.com"
    },
    "duracaoEmMinutos": 5,
    "inicioVotacao": "2024-06-11T13:49:53.435+00:00"
}
```

#### Deletar Proposta <a name="excluir2"></a>
- **Método**: `DELETE`
- **Body**: Vazio

#### Votar em Proposta <a name="votar"></a>
- **Método**: `PUT`
- **Body**:

```json
{
    "funcionarioId": 1,
    "propostaId": 1,
    "decisao": "REJEITAR"
}
```

#### Calcular Resultado da Proposta <a name="calcular"></a>
- **Método**: `GET`
- **Body**: Vazio

### MS-Resultados

#### Cadastrar Resultado <a name="cadastrar3"></a>
- **Método**: `POST`
- **Body**:

```json
{
    "propostaid": "1",
    "resultado": "APROVAR"
}
```

#### Buscar todos os Resultados <a name="buscartodos3"></a>
- **Método**: `GET`
- **Body**: Vazio

#### Buscar resultado por ID <a name="buscarporid3"></a>
- **Método**: `GET`
- **Body**: Vazio

# <a name="como-começar"></a> Como Começar

## Requisitos

Para executar esta aplicação, são necessários os seguintes pré-requisitos:

- JDK 17
- Maven

## Execução

Para executar a aplicação, siga os seguintes passos:

1. **Clone o Repositório**: Abra um terminal e clone o repositório do projeto para sua máquina local usando o comando `git clone <URL_do_repositório>`.
2. **Navegue até a Pasta do Projeto**: Abra um terminal na raiz do projeto clonado.
3. **Inicie o Servidor Eureka e Microsserviços**: Isso envolve navegar até as respectivas pastas dos microsserviços e executar `mvn spring-boot:run` para iniciar cada aplicação individualmente.

## <a name="autores"></a>Autores

- [Nicolas Marques](https://github.com/NMSilos)
- [Giovanni Eugenio](https://github.com/giovanieugenio)
- [Pedro Tomaz](https://github.com/tomazdalcortivo)
- [Diogo Meneses](https://github.com/diogo-meneses-franca)

---

# Desafio-03 (English)

*[🇧🇷 Leia a versão em Português acima](#desafio-03)*

# Table of Contents

- [Description](#description-en)
- [Microservices](#microservices-en)
    * [Eureka Server](#eureka-server-en)
    * [API Gateway](#gateway-en)
- [Getting Started](#getting-started-en)
- [Authors](#authors-en)

# <a name="description-en"></a>Corporate Voting System

The Corporate Voting System is a backend API dedicated to managing decision-making processes within a company. Its architecture is REST-based, providing access to resources via endpoints. The API ensures data persistence in the database.

# <a name="microservices-en"></a>Microservices

## <a name="gateway-en"></a>API Gateway

* **Description**: The API Gateway unifies requests from various microservices, offering a single entry point for clients to access system resources. It manages routing, authentication, authorization, and other functionalities related to cross-system communication, simplifying access to distributed services via the default URL `http://localhost:8080`.

## <a name="eureka-server-en"></a>Eureka Server

* **Description**: The Eureka Server is a centralized application that allows microservices to register and discover each other dynamically in a cloud computing environment. It simplifies communication between microservices, providing a reliable and scalable service discovery mechanism. Its console can be accessed at `http://localhost:8761`.

## Employee Management Service

* **Description**: Manages the registration and administration of Employees.
* **Entity**:
    * Employee
        * name
        * CPF
        * address
        * phone
        * email
* **Features**:
    * Register an Employee
    * Delete an Employee
    * Search for a specific employee and all employees
    * Edit an employee

* **Individual Address**: `http://localhost:8081`

### Endpoints

| Method    | URL                       | Description                                                                                  |
|-----------|---------------------------|--------------------------------------------------------------------------------------------|
| `POST`    | /api/v1/funcionarios      | [Registers a new Employee in the system.](#register-emp)                                   |
| `PUT`     | /api/v1/funcionarios      | [Edits an Employee.](#edit-emp)                                                            |
| `GET`     | /api/v1/funcionarios      | [Retrieves all Employees registered in the system.](#getall-emp)                           |
| `GET`     | /api/v1/funcionarios/{id} | [Retrieves an Employee by their ID.](#getbyid-emp)                                         |
| `DELETE`  | /api/v1/funcionarios/{id} | [Deletes an Employee by their ID.](#delete-emp)                                            |
| browser   | /swagger-ui/index.html#/  | Swagger documentation accessed via the microservice's individual address                   |

## Proposal Management Service

* **Description**: Manages the registration and administration of proposals.
* **Entities**:
    * Proposal
        * name
        * description
        * employee ID
        * voting duration
        * voting start time
        * list of votes
    * Votes
        * employee ID
        * proposal
        * decision
* **Features**:
    * Register, delete, edit, and search for a proposal
    * Allows an employee to vote and create their proposal
    * Tallies the votes
* **Individual Address**: `http://localhost:8082`

### Endpoints

| Method    | URL                                          | Description                                                                                               |
|-----------|----------------------------------------------|---------------------------------------------------------------------------------------------------------|
| `POST`    | /api/v1/propostas                            | [Registers a new proposal in the system.](#register-prop)                                               |
| `PUT`     | /api/v1/propostas                            | [Edits a proposal.](#edit-prop)                                                                         |
| `GET`     | /api/v1/propostas                            | [Retrieves all proposals registered in the system.](#getall-prop)                                       |
| `GET`     | /api/v1/propostas/{id}                       | [Retrieves a proposal by its ID.](#getbyid-prop)                                                        |
| `PUT`     | /api/v1/propostas/votar                      | [Allows the Employee to vote on a proposal.](#vote-prop)                                                |
| `GET`     | /api/v1/propostas/calcular/1?funcionarioId=1 | [Allows the voting manager to calculate the voting result respecting its end time.](#calc-prop)         |
| `DELETE`  | /api/v1/propostas/{id}                       | [Deletes a proposal by its ID.](#delete-prop)                                                           |
| browser   | /swagger-ui/index.html#/                     | Swagger documentation accessed via the microservice's individual address                                |

## Results Management Service

* **Description**: Manages the storage and visualization of voting results.
* **Entity**:
    * Result
        * proposal ID
        * result
* **Features**:
    * Persists data from previous votes
    * Allows querying a vote by ID and all paginated

* **Individual Address**: `http://localhost:8083`

### Endpoints

| Method    | URL                      | Description                                                                                  |
|-----------|--------------------------|--------------------------------------------------------------------------------------------|
| `GET`     | /api/v1/resultados       | [Retrieves all results stored in the system.](#getall-res)                                 |
| `GET`     | /api/v1/resultados/{id}  | [Retrieves a result by its ID.](#getbyid-res)                                              |
| browser   | /swagger-ui/index.html#/ | Swagger documentation accessed via the microservice's individual address                   |

## Endpoints Documentation

This section defines the necessary methods and format to send a request, based on the endpoints link, if necessary.

### MS-Employee

#### Register Employee <a name="register-emp"></a>
- **Method**: `POST`
- **Body**:

```json
{
    "nome": "Maria de Lourdes",
    "cpf": "12588017010",
    "endereco": "Rua Antonio Carlos",
    "telefone": "1199821994",
    "email": "maria@email.com"
}
```

#### Get All Employees <a name="getall-emp"></a>
- **Method**: `GET`
- **Body**: Empty

#### Get Employee by ID <a name="getbyid-emp"></a>
- **Method**: `GET`
- **Body**: Empty

#### Delete Employee <a name="delete-emp"></a>
- **Method**: `DELETE`
- **Body**: Empty

#### Edit Employee <a name="edit-emp"></a>
- **Method**: `PUT`
- **Description**: This method edits the Employee.
- **Body**:

```json
{
    "nome": "Maria de Lourdes",
    "cpf": "40892323094",
    "endereco": "Rua Antonio Carlos",
    "telefone": "43995663217",
    "email": "mariadelourdes@email.com"
}
```

### MS-Proposal

#### Register Proposal <a name="register-prop"></a>
- **Method**: `POST`
- **Body**:

```json
{
    "titulo": "Melhoria no Processo de Vendas",
    "descricao": "Proposta para otimizar o processo de vendas com novas ferramentas",
    "funcionarioId": 12
}
```

#### Get All Proposals <a name="getall-prop"></a>
- **Method**: `GET`
- **Body**: Empty

#### Get Proposal by ID <a name="getbyid-prop"></a>
- **Method**: `GET`
- **Body**: Empty

#### Edit Proposal <a name="edit-prop"></a>
- **Method**: `PUT`
- **Body**:

```json
{
    "id": 2,
    "nome": "Proposta 1",
    "descricao": "Essa é uma proposta numero 1",
    "criador": {
        "id": 1,
        "nome": "Maria de Lourdes",
        "cpf": "06976003940",
        "endereco": "Rua Antonio Carlos",
        "telefone": "1199821994",
        "email": "mariazinha@email.com"
    },
    "duracaoEmMinutos": 5,
    "inicioVotacao": "2024-06-11T13:49:53.435+00:00"
}
```

#### Delete Proposal <a name="delete-prop"></a>
- **Method**: `DELETE`
- **Body**: Empty

#### Vote on Proposal <a name="vote-prop"></a>
- **Method**: `PUT`
- **Body**:

```json
{
    "funcionarioId": 1,
    "propostaId": 1,
    "decisao": "REJEITAR"
}
```

#### Calculate Proposal Result <a name="calc-prop"></a>
- **Method**: `GET`
- **Body**: Empty

### MS-Results

#### Register Result <a name="register-res"></a>
- **Method**: `POST`
- **Body**:

```json
{
    "propostaid": "1",
    "resultado": "APROVAR"
}
```

#### Get All Results <a name="getall-res"></a>
- **Method**: `GET`
- **Body**: Empty

#### Get Result by ID <a name="getbyid-res"></a>
- **Method**: `GET`
- **Body**: Empty

# <a name="getting-started-en"></a> Getting Started

## Requirements

To run this application, the following prerequisites are required:

- JDK 17
- Maven

## Execution

To run the application, follow these steps:

1. **Clone the Repository**: Open a terminal and clone the project repository to your local machine using the command `git clone <repository_URL>`.
2. **Navigate to the Project Folder**: Open a terminal in the root of the cloned project.
3. **Start the Eureka Server and Microservices**: This involves navigating to the respective folders of the microservices and running `mvn spring-boot:run` to start each application individually.

## <a name="authors-en"></a>Authors

- [Nicolas Marques](https://github.com/NMSilos)
- [Giovanni Eugenio](https://github.com/giovanieugenio)
- [Pedro Tomaz](https://github.com/tomazdalcortivo)
- [Diogo Meneses](https://github.com/diogo-meneses-franca)
