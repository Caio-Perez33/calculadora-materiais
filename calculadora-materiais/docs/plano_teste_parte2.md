# Plano de Teste - Parte 2

Projeto: Calculadora de Materiais para Obra Residencial
Aluno: Caio de Almeida Perez
Repositorio: https://github.com/Caio-Perez33/calculadora-materiais

## Objetivo
Validar a segunda parte da atividade, que adiciona uma tela em Jakarta Faces para solicitacao de orcamento, persistencia dos orcamentos no banco H2 e busca por numero do orcamento ou nome do usuario.

## Ambiente de teste
- Java 21
- Spring Boot 4.1.0
- Jakarta Faces com JoinFaces
- Maven
- Banco H2
- Navegador Google Chrome ou Microsoft Edge
- IntelliJ IDEA

## Casos de teste

| ID | Funcionalidade | Procedimento | Resultado esperado | Evidencia |
|---|---|---|---|---|
| CT01 | Acessar tela inicial | Rodar o projeto e abrir http://localhost:8080 | Tela de solicitacao de orcamento abre corretamente | Print da tela inicial |
| CT02 | Preencher planta | Usar o botao Preencher exemplo | Campos da planta e custos sao preenchidos | Print do formulario preenchido |
| CT03 | Gerar orcamento | Clicar em Gerar orcamento | Sistema calcula concreto, tijolos, custos e gera numero ORC | Print do resultado |
| CT04 | Buscar por numero | Copiar o numero ORC e buscar na tela de consulta | Orcamento salvo aparece na tabela | Print da busca por numero |
| CT05 | Buscar por nome | Buscar pelo nome do usuario | Lista retorna os orcamentos daquele usuario | Print da busca por nome |
| CT06 | Validar banco H2 | Abrir /h2-console e consultar tabela ORCAMENTO | Dados do orcamento aparecem no banco | Print do H2 Console |
| CT07 | Testar API REST | Enviar POST para /api/orcamentos | API retorna JSON com numero, volume, tijolos e valor total | Print do Console/Postman |
| CT08 | Rodar testes automaticos | Executar mvn test ou testes do IntelliJ | Testes ficam verdes | Print da aba de testes |

## Evidencias que devem ser anexadas
1. Print da tela http://localhost:8080/index.xhtml.
2. Print do resultado do orcamento gerado.
3. Print da busca por numero do orcamento.
4. Print da busca por nome do usuario.
5. Print do H2 Console mostrando a tabela ORCAMENTO.
6. Print dos testes automaticos verdes no IntelliJ.

## Status esperado
A atividade sera considerada aprovada se a tela abrir, o orcamento for calculado, o registro for salvo no banco, for possivel buscar por numero/nome e os testes automaticos forem executados com sucesso.
