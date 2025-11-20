# Prática 09 - Documentação e Hospedagem

## Descrição do Projeto

Este projeto é uma aplicação **Spring Boot** que implementa um sistema de **CRUD (Create, Read, Update, Delete)** para duas entidades relacionadas: **Documento** e **Categoria**. O objetivo principal é demonstrar a implementação de uma API RESTful completa, utilizando **Spring Data JPA** para persistência de dados (com H2 em memória para este exemplo) e a integração do **Swagger/OpenAPI** para a documentação automática da API.

O relacionamento entre as entidades é de **um-para-muitos (One-to-Many)**, onde uma `Categoria` pode ter múltiplos `Documentos`, e um `Documento` pertence a uma única `Categoria`.

## Passos para Execução Local

Para executar a aplicação localmente, você precisará ter o **Java Development Kit (JDK) 17** ou superior e o **Apache Maven** instalados.

1.  **Clone o repositório** (assumindo que este código será versionado no GitHub):
    ```bash
    git clone [LINK_DO_SEU_REPOSITORIO]
    cd pratica09-hospedagem
    ```

2.  **Compile e empacote a aplicação** usando o Maven:
    ```bash
    mvn clean package
    ```

3.  **Execute o arquivo JAR** gerado:
    ```bash
    java -jar target/pratica09-hospedagem-0.0.1-SNAPSHOT.jar
    ```

4.  A aplicação estará acessível em `http://localhost:8080`.

## Documentação da API (Swagger/OpenAPI)

A documentação interativa da API foi gerada automaticamente utilizando o **Springdoc OpenAPI**.

*   **Link da Documentação Swagger (Local):**
    `http://localhost:8080/swagger-ui.html`

*   **Link da Documentação Swagger (Hospedada no Render):**
    `https://hospedagem-caun.onrender.com/swagger-ui/index.html` (Este link deve ser substituído pelo link real após o deploy)

### Como Acessar e Usar a Documentação

1.  Após iniciar a aplicação (localmente ou acessando o link do Render), navegue para o endereço do Swagger UI.
2.  Você verá uma lista de *endpoints* agrupados por *tags* (Documentos e Categorias).
3.  Clique em qualquer *endpoint* para expandir e ver os detalhes, incluindo parâmetros de requisição, exemplos de corpo de requisição e respostas.
4.  Para testar um *endpoint*, clique no botão **"Try it out"**, preencha os campos necessários e clique em **"Execute"**.
