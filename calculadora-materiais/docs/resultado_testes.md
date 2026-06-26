# Resultado dos testes

Comando para executar:

```bash
mvn test
```

Testes criados:

1. `CalculadoraServiceTest.deveCalcularVolumeConcretoDaFundacao`
   - Entrada: duas arestas, altura da viga baldrame 0,30 m.
   - Resultado esperado: volume total de concreto = 0,825 m3.

2. `CalculadoraServiceTest.deveCalcularQuantidadeDeTijolosDescontandoPortaEJanela`
   - Entrada: parede de 4 m x 3 m, uma porta e uma janela.
   - Resultado esperado: area liquida = 9,12 m2 e quantidade = 124 tijolos.

3. `CalculoControllerTest.deveCalcularConcretoViaEndpointRest`
   - Valida o endpoint POST `/api/calculos/concreto`.

4. `CalculoControllerTest.deveCalcularTijolosViaEndpointRest`
   - Valida o endpoint POST `/api/calculos/tijolos`.

Observacao: o PDF `resultado_testes_calculadora_materiais.pdf` resume estes testes e pode ser entregue junto com o link do repositorio.
