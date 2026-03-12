# Production Control API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de produção industrial baseado no consumo de matérias-primas.

A aplicação permite:

- Gerenciar produtos
- Controlar estoque de matérias-primas
- Definir composição de produtos
- Calcular sugestão de produção
- Gerar métricas para dashboard

---

## Frontend da aplicação

Interface web disponível em:

👉 [Repositório do Frontend](https://github.com/Priscila-Santos/production_control_app_frontend.git)

---

## Arquitetura

O projeto segue arquitetura em camadas:

```

Controller
↓
Service
↓
Repository
↓
Database

```

---

## Tecnologias utilizadas

```
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Flyway
- PostgreSQL
- Lombok
- Jakarta Validation
- Maven
```

---

# Estrutura do projeto

```

src/main/java/com/production/production_control

controller
├ ProductController
├ RawMaterialController
├ ProductRawMaterialController
├ ProductionController
└ DashboardController

service
├ ProductService
├ RawMaterialService
├ CompositionService
├ ProductionService
└ DashboardService

repository
├ ProductRepository
├ RawMaterialRepository
├ ProductRawMaterialRepository
└ ProductionRepository

dto
├ request
└ response

entity
exception
config

```

---

## Banco de dados

O banco é gerenciado com **Flyway migrations**.

Localização:

```

src/main/resources/db/migration

````

---

### V1 — Products

```sql
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL
);
````

---

### V2 — Raw Materials

```sql
CREATE TABLE raw_materials (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    stock_quantity NUMERIC(15,3) NOT NULL CHECK (stock_quantity >= 0)
);
```

---

### V3 — Product Composition

Relacionamento N:N entre produtos e matérias-primas.

```sql
CREATE TABLE product_raw_material (
    product_id BIGINT NOT NULL,
    raw_material_id BIGINT NOT NULL,
    required_quantity NUMERIC(15,3) NOT NULL,

    PRIMARY KEY (product_id, raw_material_id),

    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (raw_material_id) REFERENCES raw_materials(id)
);
```

---

### V4 — Production

```sql
CREATE TABLE production (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    production_date DATE NOT NULL,
    total_value NUMERIC(12,2) NOT NULL,

    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

---

### V5 — Seed Data

Popula o banco com dados de desenvolvimento:

* 50 produtos
* 50 matérias-primas
* 300 registros de produção

---

## Endpoints da API

Base URL:

```
http://localhost:8080/api
```

---

## Products

| Método | Endpoint       |
| ------ | -------------- |
| GET    | /products      |
| POST   | /products      |
| PUT    | /products/{id} |
| DELETE | /products/{id} |

---

## Raw Materials

| Método | Endpoint            |
| ------ | ------------------- |
| GET    | /raw-materials      |
| POST   | /raw-materials      |
| PUT    | /raw-materials/{id} |
| DELETE | /raw-materials/{id} |

---

## Product Composition

| Método | Endpoint                               |
| ------ | -------------------------------------- |
| GET    | /product-materials/product/{productId} |
| POST   | /product-materials                     |
| DELETE | /product-materials                     |

---

## Production Suggestions

Calcula a produção possível com base no estoque.

```
GET /production/suggestions
```

---

## Dashboard

Retorna métricas agregadas da produção.

```
GET /dashboard
```

Response exemplo:

```json
{
  "totalProducts": 50,
  "totalRawMaterials": 50,
  "totalStockQuantity": 21340,
  "estimatedProductionValue": 734000,
  "productionValueTrend": [],
  "stockDistribution": []
}
```

---

## Como executar

### 1 Clonar repositório

```
git clone https://github.com/Priscila-Santos/production_control_API.git
```

---

### 2 Configurar banco PostgreSQL

Criar banco:

```
production_control
```

---

### 3 Rodar aplicação

```
./mvnw spring-boot:run
```

ou

```
mvn spring-boot:run
```

---

## Testando API

Ferramentas recomendadas:

* Postman
* Insomnia
* Curl

Fluxo recomendado:

1 Criar matéria-prima
2 Criar produto
3 Definir composição
4 Atualizar estoque
5 Consultar sugestão de produção
6 Visualizar dashboard

---

## Melhorias futuras

* Autenticação com Spring Security
* Documentação com Swagger
* Cache com Redis
* Docker
* Testcontainers
* Paginação e filtros

---

## Autora

Projeto desenvolvido por **Priscila Santos**

