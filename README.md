# Gestão de Loja de Roupas - API RESTful

## Objetivo
Desenvolver uma API RESTful (back-end) utilizando Spring Boot para a gestão de uma loja de roupas.

## Entidades
- **Cliente**:
  - `id`
  - `nome`
  - `cpf`
  - `idade`
  - `telefone`
  
- **Funcionário**:
  - `id`
  - `nome`
  - `idade`
  - `matrícula`

- **Venda**:
  - `id`
  - `endereço da entrega`
  - `valor total`

- **Produto**:
  - `id`
  - `nome`
  - `valor`

## Relacionamentos
- Um **cliente** pode realizar várias compras (vendas), mas uma venda só pode estar associada a um único cliente.
- Um **funcionário** pode realizar várias vendas, mas uma venda só pode estar associada a um único funcionário.
- Uma **venda** pode incluir vários produtos, e um produto pode estar vinculado a várias vendas.

## Regras de Negócio e Validações
- **Entidade Principal**: Venda. O sistema deve permitir salvar uma venda com cliente, funcionário e produtos em uma única requisição (cascade).
- Todos os atributos das entidades são obrigatórios, exceto o endereço de entrega, que é opcional.
- Além dos métodos básicos de CRUD (`findAll`, `findById`, `delete`, `save` e `update`), implementar pelo menos 3 filtros (`findBy...`), sendo:
  - 1 filtro com JPQL
  - 2 filtros com métodos automáticos
- Cada entidade deve ter seu próprio controlador, serviço e repositório, com endpoints apropriados para cada uma.

 # POSTMAN
  
- https://www.postman.com/geisoncorassini/workspace/projeto-loja-de-roupas/collection/37364143-e152becc-86ec-469c-913a-ef9e208363bc?action=share&creator=37364143
