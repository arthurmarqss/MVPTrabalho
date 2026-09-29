# Sistema de Supermercado

Aplicação desktop em Java para cadastro de produtos e categorias, cálculo do preço de venda a partir da margem de lucro de cada categoria e consulta do histórico de preços. Desenvolvida com o padrão Model-View-Presenter (Passive View) e repositórios em memória.

## Integrantes

| Nome | Matrícula |
|---|---|
| Arthur Marques Campos | 2022200209 |
| Hiago do Carmo Lopes | 2022200636 |
| Gustavo Wesley de Souza | 2023200945 |

## Requisitos

- Java 21
- Maven

## Como executar

```bash
git clone https://github.com/arthurmarqss/MVPTrabalho.git
cd MVPTrabalho
mvn compile exec:java
```

Também é possível abrir a pasta no NetBeans e executar o projeto com **Run**.

Como os repositórios são em memória, os dados cadastrados se perdem ao fechar a aplicação. A cada nova execução o Seeder recarrega os dados iniciais.

## Organização

| Pacote | Responsabilidade |
|---|---|
| `model` | Classes de domínio: `Categoria`, `Produto` e `HistoricoPreco` |
| `repositorio` | Contratos de acesso aos dados e implementações em memória |
| `servico` | Regras de negócio: cálculo de preço, intervalo mínimo de 10 dias e validações |
| `seeder` | Carga dos dados iniciais a cada inicialização |
| `view` | Telas Swing (com os arquivos `.form` do NetBeans) e suas interfaces |
| `presenter` | Lógica de apresentação e navegação entre as telas |
