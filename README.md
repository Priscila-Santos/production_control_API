# Production Control API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de produção baseado no consumo de matérias-primas.

O sistema permite:

* Cadastro de produtos
* Cadastro de matérias-primas
* Associação entre produtos e matérias-primas
* Controle de estoque
* Cálculo de **sugestão de produção com base no estoque disponível**

Essa aplicação simula um **cenário real de controle de produção industrial**, onde cada produto consome determinadas matérias-primas e a API calcula **quantas unidades podem ser produzidas com o estoque atual**.

---

# Arquitetura

O projeto segue uma arquitetura em camadas baseada em **boas práticas do ecossistema Spring Boot**.

```
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

Estrutura de pacotes:

```
src/main/java/com/production/production_control

controller
dto
   ├── request
   └── response
entity
repository
service
exception
config
```

---

# Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Lombok
* Jakarta Validation
* JUnit 5
* Mockito
* Maven

---

# Modelo de domínio

## Product

Representa um produto que pode ser produzido.

| Campo | Tipo       |
| ----- | ---------- |
| id    | Long       |
| name  | String     |
| price | BigDecimal |

---

## RawMaterial

Representa uma matéria-prima usada na produção.

| Campo         | Tipo       |
| ------------- | ---------- |
| id            | Long       |
| name          | String     |
| stockQuantity | BigDecimal |

---

## ProductRawMaterial

Relaciona **produto e matéria-prima**, indicando quanto da matéria-prima é necessário para produzir uma unidade do produto.

| Campo            | Tipo       |
| ---------------- | ---------- |
| id               | Long       |
| productId        | Long       |
| rawMaterialId    | Long       |
| quantityRequired | BigDecimal |

---

# Funcionalidade principal

## Sugestão de produção

A API calcula **quantas unidades de cada produto podem ser produzidas com o estoque disponível**.

### Exemplo

Produto: **Bolo de Chocolate**

| Matéria Prima | Necessário por unidade | Estoque |
| ------------- | ---------------------- | ------- |
| Farinha       | 0.5 kg                 | 10 kg   |
| Açúcar        | 0.3 kg                 | 6 kg    |

Cálculo:

```
Farinha → 10 / 0.5 = 20
Açúcar → 6 / 0.3 = 20
```

Produção possível:

```
20 unidades
```

A API retorna o **mínimo entre os materiais necessários**.

---

# Endpoints da API

Base URL:

```
http://localhost:8080/api
```

---

# Products

## Criar produto

POST `/products`

Request

```json
{
  "name": "Chocolate Cake",
  "price": 10.00
}
```

Response

```json
{
  "id": 1,
  "name": "Chocolate Cake",
  "price": 10.00
}
```

---

## Listar produtos

GET `/products`

Response

```json
[
  {
    "id": 1,
    "name": "Chocolate Cake",
    "price": 10.00
  }
]
```

---

# Raw Materials

## Criar matéria-prima

POST `/raw-materials`

Request

```json
{
  "name": "Sugar",
  "stockQuantity": 50
}
```

---

## Listar matérias-primas

GET `/raw-materials`

---

# Product Raw Materials

Relaciona produtos com matérias-primas.

---

## Criar relação produto ↔ matéria-prima

POST `/product-materials`

Request

```json
{
  "productId": 1,
  "rawMaterialId": 2,
  "quantityRequired": 0.5
}
```

---

## Listar matérias-primas de um produto

GET

```
/product-materials/product/{productId}
```

Exemplo

```
/product-materials/product/1
```

Response

```json
[
  {
    "id": 1,
    "productId": 1,
    "rawMaterialName": "Sugar",
    "quantityRequired": 0.5
  }
]
```

---

## Remover relação produto ↔ matéria-prima

DELETE

```
/product-materials?productId=1&rawMaterialId=2
```

---

# Production Suggestion

Calcula sugestão de produção baseada no estoque.

GET

```
/production/suggestion
```

Response

```json
[
  {
    "productId": 1,
    "productName": "Chocolate Cake",
    "quantity": 20,
    "unitPrice": 10.00,
    "totalValue": 200.00
  }
]
```

---

# DTOs

O projeto utiliza **Java Records para DTOs**, reduzindo boilerplate e garantindo imutabilidade.

Exemplo:

```java
public record ProductionSuggestionResponse(
        Long productId,
        String productName,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalValue
) {}
```

Benefícios:

* Imutabilidade
* Menos código
* Melhor serialização com Jackson

---

# Testes

A aplicação possui **testes unitários utilizando JUnit 5 e Mockito**.

Exemplo de padrão utilizado:

```
Arrange
Act
Assert
```

Exemplo simplificado:

```java
@Test
void shouldReturnProductionSuggestions() {

    // Arrange
    when(service.calculateProduction()).thenReturn(mockResponse);

    // Act
    List<ProductionSuggestionResponse> result = controller.calculateProduction();

    // Assert
    assertEquals(1, result.size());
}
```

---

# Como executar o projeto

## 1 Clonar repositório

```
git clone https://github.com/seu-usuario/production-control
```

---

## 2 Entrar na pasta

```
cd production-control
```

---

## 3 Rodar aplicação

```
./mvnw spring-boot:run
```

ou

```
mvn spring-boot:run
```

---

## 4 Acessar API

```
http://localhost:8080/api
```

---

# Testando a API

Ferramentas recomendadas:

* Insomnia
* Postman
* Curl

Fluxo recomendado de testes:

1 Criar matéria-prima
2 Criar produto
3 Criar relação produto ↔ matéria-prima
4 Atualizar estoque
5 Consultar sugestão de produção

---

# Melhorias futuras

* Autenticação com **Spring Security + JWT**
* Documentação com **Swagger / OpenAPI**
* Cache com **Redis**
* Containerização com **Docker**
* Testes de integração com **Testcontainers**
* Paginação e filtros

---

# Objetivo do projeto

Este projeto foi desenvolvido com o objetivo de:

* Demonstrar domínio de **Spring Boot**
* Aplicar **arquitetura em camadas**
* Utilizar **DTOs e boas práticas REST**
* Implementar **testes unitários**
* Simular um **problema real de negócio**

---

# Autora

Desenvolvido por **Priscila Santos**

