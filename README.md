<a name="readme-top"></a>

# Introdução

O Pedido-Service é responsável por gerenciar os pedidos de compra dos usuários. Quando um usuário finaliza a seleção de produtos e suas quantidades desejadas, o Pedido-Service entra em ação para criar o pedido, dando continuidade ao processo de compra.

Este microserviço realiza as seguintes etapas:

1. **Comunicação com Carrinho-Service:** O Pedido-Service se comunica com o Carrinho-Service para obter os itens selecionados pelo usuário, juntamente com suas quantidades.

2. **Acesso ao Estoque-Service:** Em seguida, o Pedido-Service acessa o Estoque-Service para verificar a disponibilidade dos itens conforme a quantidade desejada pelo usuário.

3. **Reserva de Itens no Estoque:** Uma vez confirmada a disponibilidade, o Pedido-Service efetua a reserva dos itens necessários no Estoque-Service.

4. **Persistência do Pedido:** Após a reserva dos itens, Pedido-Service procede à criação do pedido na base de dados do sistema.

5. **Criação da Solicitação de Pagamento:** Pedido-Service acessa o Pagamento-Service para criar uma solicitação de pagamento contendo o ID do pedido.

6. **Inativação do Carrinho:** Por fim, com o pedido criado e pagamento confirmado, o Pedido-Service acessa novamente o Carrinho-Service para inativar o carrinho de compras associado ao pedido.

É importante notar que caso ocorra falha em alguma etapa do processo, o Pedido-Service realizará o rollback das etapas anteriores, garantindo a integridade e consistência dos dados durante todo o fluxo de compra.

## Sumário
* [Instruções](#instrucoes)
* [Funcionalidades de Pedido Service](#funcionalidades-de-pedido-service)
* [Ilustração do MS Pedido Service](#ilustração-do-ms-pedido-service)


## Instruções

- Maven: Para build do projeto. **Para buildar:** mvn clean install
- Foi utilizado Lombok, Validation e MySql, portanto é necessário adicionar os plugins na IDE
- Antes de iniciar a instância dos Microserviços, é necessário garantir que os seguintes serviços estejam operacionais para garantir a operação adequada:</br>

	* **Service Discovery** - Inicie o Service Discovery. Execute e verifique se pelo menos uma instância do Service Discovery está operacional.</br></br>
	
	* **API Gateway** - Inicie o API Gateway. Verifique se pelo menos uma instância do API Gateway está em execução.

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

### Funcionalidades de Pedido Service

>[ Base URL: http://localhost:porta ] 

`Substitua <porta> pela porta atribuída dinamicamente pelo ambiente.`

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

### ``POST``  
`*Para criar pedido`

```
	/pedidos
```
<details>
  <summary>Exemplo Request:</summary>

```
curl --location 'http://localhost:8082/pedidos' \
--header 'idUsuario: 1' \
--header 'Content-Type: application/json' \
--data '{
    "carrinhoId": 31
}'
```
</details>

<details>
  <summary>Exemplo Responses:</summary>

200 - _OK_
`- Será retornado o id do pedido`

```
2
```
</details>

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

<a name="tecnologias"></a>
## 📍️ Tecnologias

- A API foi construída em Java 18 utilizando Spring Framework 3.2.3
- Padrão REST na construção das rotas e retornos
- SLF4J para registro de logs
- Utilização de código limpo e princípios **SOLID**
- Boas práticas da Linguagem/Framework
- Clean architecture
- Banco de Dados MySql
- Para facilitar a comunicação entre microserviços, o projeto utiliza o Spring Cloud Feign. 
- Service Discovery
- API Gateway

<p align="right">(<a href="#readme-top">Ir ao topo</a>)</p>

---------

### Ilustração do MS Pedido Service

![pedido-service.png](src%2Fmain%2Fjava%2Fdocument%2Fpedido-service.png)