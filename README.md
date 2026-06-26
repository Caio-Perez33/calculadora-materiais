# Calculadora de Materiais para Obra Residencial

Projeto desenvolvido para a disciplina de **Desenvolvimento de Sistemas**.

O objetivo da atividade é criar um backend capaz de representar uma planta baixa residencial como um grafo, onde os vértices representam os encontros das paredes e as arestas representam as paredes. A partir desses dados, o sistema realiza cálculos relacionados à construção civil, como o volume de concreto necessário para a fundação e a quantidade aproximada de tijolos para as paredes.

## Link do Repositório

https://github.com/Caio-Perez33/calculadora-materiais

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- H2 Database
- Hibernate
- IntelliJ IDEA
- Git e GitHub

## Funcionalidades

O sistema possui serviços backend para:

- Modelar uma planta baixa usando conceitos de grafo.
- Representar vértices da planta.
- Representar arestas, que correspondem às paredes.
- Representar cômodos da residência.
- Informar se uma parede possui porta ou janela.
- Calcular o volume de concreto da viga baldrame.
- Calcular a quantidade aproximada de tijolos necessários para as paredes.
- Disponibilizar os cálculos por meio de serviços REST.

## Modelagem da Planta Baixa

A planta baixa foi modelada como um grafo `G = (V, A)`.

- `V` representa os vértices, que são os encontros das paredes.
- `A` representa as arestas, que são as paredes.
- Cada parede possui comprimento, largura, altura, porta e/ou janela.
- Cada cômodo possui nome, largura, comprimento e altura.

## Cálculo de Volume de Concreto

A viga baldrame é calculada usando a fórmula:

~~~text
Volume = largura x altura x comprimento
~~~

No sistema, o usuário informa a altura da viga baldrame, enquanto as demais medidas são obtidas a partir das arestas/paredes cadastradas.

Exemplo:

~~~text
Comprimento da parede: 10 m
Largura da parede: 0,20 m
Altura da viga baldrame: 0,30 m

Volume = 10 x 0,20 x 0,30
Volume = 0,60 m³
~~~

## Cálculo da Quantidade de Tijolos

Para calcular a quantidade de tijolos, o sistema considera a área das paredes e desconta as áreas ocupadas por portas e janelas.

Fórmula geral:

~~~text
Área da parede = comprimento x altura
Área útil = área da parede - área de portas - área de janelas
Quantidade de tijolos = área útil / área da face do tijolo
~~~

## Endpoints da API

### Listar arestas

~~~http
GET /api/planta/arestas
~~~

Exemplo de acesso pelo navegador:

~~~text
http://localhost:8080/api/planta/arestas
~~~

Resposta esperada quando não há dados cadastrados:

~~~json
[]
~~~

### Calcular volume de concreto

~~~http
POST /api/calculos/concreto
~~~

Exemplo de JSON enviado:

~~~json
{
  "alturaBaldrame": 0.30,
  "arestas": [
    {
      "codigo": "a12",
      "verticeInicial": {
        "nome": "V1",
        "coordenadaX": 0,
        "coordenadaY": 0
      },
      "verticeFinal": {
        "nome": "V2",
        "coordenadaX": 10,
        "coordenadaY": 0
      },
      "comprimento": 10.0,
      "largura": 0.20,
      "alturaParede": 3.0,
      "possuiPorta": false,
      "larguraPorta": 0,
      "alturaPorta": 0,
      "possuiJanela": false,
      "larguraJanela": 0,
      "alturaJanela": 0
    }
  ]
}
~~~

Resposta esperada:

~~~json
{
  "quantidadeArestas": 1,
  "volumeConcretoM3": 0.6
}
~~~

### Calcular quantidade de tijolos

~~~http
POST /api/calculos/tijolos
~~~

Exemplo de JSON enviado:

~~~json
{
  "alturaTijolo": 0.19,
  "larguraTijolo": 0.14,
  "comprimentoTijolo": 0.39,
  "arestas": [
    {
      "codigo": "a12",
      "verticeInicial": {
        "nome": "V1",
        "coordenadaX": 0,
        "coordenadaY": 0
      },
      "verticeFinal": {
        "nome": "V2",
        "coordenadaX": 4,
        "coordenadaY": 0
      },
      "comprimento": 4.0,
      "largura": 0.15,
      "alturaParede": 3.0,
      "possuiPorta": true,
      "larguraPorta": 0.80,
      "alturaPorta": 2.10,
      "possuiJanela": true,
      "larguraJanela": 1.20,
      "alturaJanela": 1.00
    }
  ]
}
~~~

Resposta esperada:

~~~json
{
  "quantidadeArestas": 1,
  "areaTotalParedesM2": 9.12,
  "areaFaceTijoloM2": 0.0741,
  "quantidadeTijolos": 124,
  "volumeAproximadoTijolosM3": 1.286376
}
~~~

## Como Executar o Projeto

1. Baixe ou clone este repositório:

~~~bash
git clone https://github.com/Caio-Perez33/calculadora-materiais.git
~~~

2. Abra o projeto no IntelliJ IDEA.

3. Aguarde o Maven carregar as dependências.

4. Execute a classe principal:

~~~text
CalculadoraMateriaisApplication.java
~~~

5. Após iniciar o projeto, acesse no navegador:

~~~text
http://localhost:8080/api/planta/arestas
~~~

## Como Testar Pelo Navegador

Como os cálculos usam requisições do tipo `POST`, o teste pode ser feito pelo Console do navegador.

1. Abra o navegador.
2. Acesse:

~~~text
http://localhost:8080/api/planta/arestas
~~~

3. Aperte `F12`.
4. Vá até a aba `Console`.
5. Cole o código de teste usando `fetch`.

Exemplo para testar concreto:

~~~javascript
fetch("http://localhost:8080/api/calculos/concreto", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    alturaBaldrame: 0.30,
    arestas: [
      {
        codigo: "a12",
        verticeInicial: { nome: "V1", coordenadaX: 0, coordenadaY: 0 },
        verticeFinal: { nome: "V2", coordenadaX: 10, coordenadaY: 0 },
        comprimento: 10.0,
        largura: 0.20,
        alturaParede: 3.0,
        possuiPorta: false,
        larguraPorta: 0,
        alturaPorta: 0,
        possuiJanela: false,
        larguraJanela: 0,
        alturaJanela: 0
      }
    ]
  })
})
.then(res => res.json())
.then(data => console.log(data));
~~~

Exemplo para testar tijolos:

~~~javascript
fetch("http://localhost:8080/api/calculos/tijolos", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    alturaTijolo: 0.19,
    larguraTijolo: 0.14,
    comprimentoTijolo: 0.39,
    arestas: [
      {
        codigo: "a12",
        verticeInicial: { nome: "V1", coordenadaX: 0, coordenadaY: 0 },
        verticeFinal: { nome: "V2", coordenadaX: 4, coordenadaY: 0 },
        comprimento: 4.0,
        largura: 0.15,
        alturaParede: 3.0,
        possuiPorta: true,
        larguraPorta: 0.80,
        alturaPorta: 2.10,
        possuiJanela: true,
        larguraJanela: 1.20,
        alturaJanela: 1.00
      }
    ]
  })
})
.then(res => res.json())
.then(data => console.log(data));
~~~

## Banco de Dados

O projeto utiliza o banco de dados H2 em memória.

Console do H2:

~~~text
http://localhost:8080/h2-console
~~~

Configurações:

~~~text
JDBC URL: jdbc:h2:mem:calculadora_materiais
User: sa
Password: deixar vazio
~~~

## Testes

Os testes foram realizados para validar os serviços REST de cálculo de concreto e quantidade de tijolos.

Também foi gerado um relatório em PDF com os resultados dos testes, conforme solicitado na atividade.

## Estrutura do Projeto

~~~text
calculadora-materiais
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br.com.obra.calculadora
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── domain
│   │   │       ├── dto
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── CalculadoraMateriaisApplication.java
│   │   └── resources
│   └── test
├── pom.xml
└── docs
~~~

## Autor

Caio de Almeida Perez

## Conclusão

O projeto atende ao objetivo da atividade ao implementar um backend para cálculo de materiais de uma obra residencial. A aplicação utiliza conceitos de orientação a objetos, serviços REST, banco de dados H2, JPA/Hibernate e modelagem da planta baixa como grafo.
