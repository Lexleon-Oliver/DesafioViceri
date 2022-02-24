# Desafio Backend – Java Viceri

# Objetivo
API REST de um aplicativo para gerenciamento de tarefas desenvolvido para resolução do desafio técnico de programação proposto pelo processo seletivo Dev Java Júnior Viceri

# Requisitos
Segue abaixo o resumo dos requisitos desenvolvidos:
* Cadastro de um novo usuário 
* Inclusão de uma nova tarefa 
* Exclusão de uma tarefa 
* Alteração de uma tarefa 
* Marcar uma tarefa como concluída 
* Listar as tarefas pendentes, filtrando opcionalmente pela prioridade 
* Autenticação do usuário por meio de e-mail e senha 
* Disponibilização da documentação da API Swagger.

# Cadastro de um novo usuário
A API recebe o nome, e-mail e senha do usuário para realizar a criação do usuário. Todos
os campos são obrigatórios.
A senha é armazenada criptografada. Afinal, segurança é
importante.
É gerado automaticamente um ID para o usuário.
Não é permitido criar dois usuários com o mesmo e-mail, pois isso daria uma boa confusão
na hora do login.
Implementada uma política de segurança de senha para aumentar a
segurança da aplicação sendo necessários pelo menos 8 caracteres ( Obrigatório ter pelo menos 
1 número + 1 letra maiúscula + 1 letra minúscula + 1 caractere especial)

#Inclusão de uma nova tarefa
A API recebe no JSON a descrição da tarefa, e a prioridade (Alta, Média, Baixa).
O usuário não é passado no JSON da requisição. O usuário é obtido através do
token de acesso, que deve ser passado por exemplo no cabeçalho “Authorization”.
É gerado automaticamente um ID para a tarefa criada.

#Exclusão de uma tarefa
A API recebe o código da tarefa a ser excluída.
É realizada uma validação para que um usuário não exclua tarefas de outro usuário, para aumentar a
segurança da aplicação.

#Alteração de uma tarefa
A API tem um método para que seja atualizado a descrição e prioridade de uma tarefa.
É realizada uma validação para que um usuário não altere tarefas de outro usuário.

#Marcar uma tarefa como concluída
A API tem um método para que uma tarefa seja marcada como concluída.
É realizada uma validação para que um usuário não conclua tarefas de outro usuário.

#Listar as tarefas pendentes, filtrando opcionalmente pela prioridade
A API deve tem método para retornar a lista de tarefas pendentes de um usuário. Não
lista tarefas concluídas. Também é possível filtrar pela prioridade.
O usuário é identificado pelo token de autenticação.

#Autenticação do usuário por meio de e-mail e senha
A API recebe o e-mail do usuário, e a senha. O sistema então procura o usuário pelo
e-mail e valida a senha.
Após verificado as credenciais, é gerado um token de acesso. Foi utilizado o
JWT.

#Disponibilização da documentação da API por meio da OpenAPI
A API tem uma documentação que utiliza o Swagger.


# Requisitos técnicos:
* Plataforma: a API foi desenvolvida em Java com SpringBoot.
* Banco de dados: utilizado banco de dados em memória, o h2. 
* Build e Execução: Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

O endpoint disponibilizado para a criação de usuários é o :

```
http://localhost:8080/api/v1/users
```
O endpoint disponibilizado para o login de usuários é o :

```
http://localhost:8080/api/v1/login
```
O endpoint disponibilizado para as funções das tarefas é o :

```
http://localhost:8080/api/v1/tasks
```


