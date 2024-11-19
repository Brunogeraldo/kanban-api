# Kanban Application

Bruno Geraldo Lima - RA: 20004294-2
Este projeto é uma aplicação de Kanban desenvolvida em Java usando Spring Boot. A aplicação permite a criação, leitura, atualização e exclusão de tarefas.

## Estrutura do Projeto

- `src/main/java/com/example/kanban`: Código-fonte da aplicação.
- `src/main/resources`: Recursos da aplicação, como arquivos de configuração.
- `endpoints`: Esta pasta contém prints dos endpoints da API, mostrando exemplos de requisições e respostas.

## Endpoints da API

A aplicação possui os seguintes endpoints:

- **Criar uma tarefa**: `POST /api/tasks`
- **Listar todas as tarefas**: `GET /api/tasks`
- **Atualizar uma tarefa**: `PUT /api/tasks/{id}`
- **Excluir uma tarefa**: `DELETE /api/tasks/{id}`

As prints dos endpoints e exemplos de requisições/respostas estão disponíveis na pasta `endpoints`.

