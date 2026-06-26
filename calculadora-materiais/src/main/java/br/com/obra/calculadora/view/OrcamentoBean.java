package br.com.obra.calculadora.view;

import br.com.obra.calculadora.dto.ArestaRequest;
import br.com.obra.calculadora.dto.OrcamentoRequest;
import br.com.obra.calculadora.dto.OrcamentoResponse;
import br.com.obra.calculadora.dto.VerticeRequest;
import br.com.obra.calculadora.service.OrcamentoService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("orcamentoBean")
@Scope("view")
public class OrcamentoBean implements Serializable {

    private final OrcamentoService orcamentoService;

    private String nomeUsuario = "Cliente Exemplo";
    private double alturaBaldrame = 0.30;
    private double alturaTijolo = 0.19;
    private double larguraTijolo = 0.14;
    private double comprimentoTijolo = 0.39;
    private double valorConcretoPorM3 = 450.00;
    private double valorTijolo = 1.20;
    private double margemLucroPercentual = 20.00;
    private String numeroBusca;
    private String nomeBusca;

    private String textoPlanta = "a12;10;0.20;3.00;false;0;0;false;0;0\n"
            + "a23;4;0.15;3.00;true;0.80;2.10;true;1.20;1.00";

    private OrcamentoResponse resultado;
    private List<OrcamentoResponse> resultadosBusca = new ArrayList<>();

    public OrcamentoBean(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    public void gerarOrcamento() {
        try {
            List<ArestaRequest> arestas = converterTextoEmArestas(textoPlanta);
            OrcamentoRequest request = new OrcamentoRequest(
                    nomeUsuario,
                    arestas,
                    alturaBaldrame,
                    alturaTijolo,
                    larguraTijolo,
                    comprimentoTijolo,
                    valorConcretoPorM3,
                    valorTijolo,
                    margemLucroPercentual
            );
            resultado = orcamentoService.gerarOrcamento(request);
            resultadosBusca = List.of(resultado);
            adicionarMensagem(FacesMessage.SEVERITY_INFO, "Orcamento gerado com sucesso", resultado.numeroOrcamento());
        } catch (RuntimeException ex) {
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao gerar orcamento", ex.getMessage());
        }
    }

    public void buscarPorNumero() {
        try {
            resultado = orcamentoService.buscarPorNumero(numeroBusca);
            resultadosBusca = List.of(resultado);
            adicionarMensagem(FacesMessage.SEVERITY_INFO, "Orcamento encontrado", resultado.numeroOrcamento());
        } catch (RuntimeException ex) {
            resultadosBusca = new ArrayList<>();
            adicionarMensagem(FacesMessage.SEVERITY_WARN, "Orcamento nao encontrado", ex.getMessage());
        }
    }

    public void buscarPorNome() {
        resultadosBusca = orcamentoService.buscarPorNomeUsuario(nomeBusca == null ? "" : nomeBusca);
        if (resultadosBusca.isEmpty()) {
            adicionarMensagem(FacesMessage.SEVERITY_WARN, "Nenhum orcamento encontrado", "Tente outro nome de usuario.");
        } else {
            resultado = resultadosBusca.get(0);
            adicionarMensagem(FacesMessage.SEVERITY_INFO, "Busca concluida", resultadosBusca.size() + " orcamento(s) encontrado(s).");
        }
    }

    public void listarTodos() {
        resultadosBusca = orcamentoService.listarTodos();
        if (!resultadosBusca.isEmpty()) {
            resultado = resultadosBusca.get(0);
        }
        adicionarMensagem(FacesMessage.SEVERITY_INFO, "Lista atualizada", resultadosBusca.size() + " orcamento(s) no banco.");
    }

    public void preencherExemplo() {
        nomeUsuario = "Cliente Exemplo";
        alturaBaldrame = 0.30;
        alturaTijolo = 0.19;
        larguraTijolo = 0.14;
        comprimentoTijolo = 0.39;
        valorConcretoPorM3 = 450.00;
        valorTijolo = 1.20;
        margemLucroPercentual = 20.00;
        textoPlanta = "a12;10;0.20;3.00;false;0;0;false;0;0\n"
                + "a23;4;0.15;3.00;true;0.80;2.10;true;1.20;1.00";
        adicionarMensagem(FacesMessage.SEVERITY_INFO, "Exemplo preenchido", "Clique em Gerar orcamento.");
    }

    private List<ArestaRequest> converterTextoEmArestas(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Informe pelo menos uma parede da planta.");
        }

        List<ArestaRequest> arestas = new ArrayList<>();
        String[] linhas = texto.split("\\R");
        int indice = 1;
        for (String linha : linhas) {
            if (linha == null || linha.isBlank()) {
                continue;
            }
            String[] campos = linha.trim().split(";");
            if (campos.length != 10) {
                throw new IllegalArgumentException("Linha " + indice + " deve ter 10 campos separados por ponto e virgula.");
            }
            String codigo = campos[0].trim();
            double comprimento = numero(campos[1]);
            double largura = numero(campos[2]);
            double alturaParede = numero(campos[3]);
            boolean possuiPorta = Boolean.parseBoolean(campos[4].trim());
            double larguraPorta = numero(campos[5]);
            double alturaPorta = numero(campos[6]);
            boolean possuiJanela = Boolean.parseBoolean(campos[7].trim());
            double larguraJanela = numero(campos[8]);
            double alturaJanela = numero(campos[9]);

            VerticeRequest inicial = new VerticeRequest("V" + indice, 0.0, 0.0);
            VerticeRequest fim = new VerticeRequest("V" + (indice + 1), comprimento, 0.0);
            arestas.add(new ArestaRequest(
                    codigo,
                    inicial,
                    fim,
                    comprimento,
                    largura,
                    alturaParede,
                    possuiPorta,
                    larguraPorta,
                    alturaPorta,
                    possuiJanela,
                    larguraJanela,
                    alturaJanela
            ));
            indice++;
        }
        if (arestas.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma parede valida foi informada.");
        }
        return arestas;
    }

    private double numero(String texto) {
        return Double.parseDouble(texto.trim().replace(',', '.'));
    }

    private void adicionarMensagem(FacesMessage.Severity severidade, String resumo, String detalhe) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidade, resumo, detalhe));
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public double getAlturaBaldrame() {
        return alturaBaldrame;
    }

    public void setAlturaBaldrame(double alturaBaldrame) {
        this.alturaBaldrame = alturaBaldrame;
    }

    public double getAlturaTijolo() {
        return alturaTijolo;
    }

    public void setAlturaTijolo(double alturaTijolo) {
        this.alturaTijolo = alturaTijolo;
    }

    public double getLarguraTijolo() {
        return larguraTijolo;
    }

    public void setLarguraTijolo(double larguraTijolo) {
        this.larguraTijolo = larguraTijolo;
    }

    public double getComprimentoTijolo() {
        return comprimentoTijolo;
    }

    public void setComprimentoTijolo(double comprimentoTijolo) {
        this.comprimentoTijolo = comprimentoTijolo;
    }

    public double getValorConcretoPorM3() {
        return valorConcretoPorM3;
    }

    public void setValorConcretoPorM3(double valorConcretoPorM3) {
        this.valorConcretoPorM3 = valorConcretoPorM3;
    }

    public double getValorTijolo() {
        return valorTijolo;
    }

    public void setValorTijolo(double valorTijolo) {
        this.valorTijolo = valorTijolo;
    }

    public double getMargemLucroPercentual() {
        return margemLucroPercentual;
    }

    public void setMargemLucroPercentual(double margemLucroPercentual) {
        this.margemLucroPercentual = margemLucroPercentual;
    }

    public String getTextoPlanta() {
        return textoPlanta;
    }

    public void setTextoPlanta(String textoPlanta) {
        this.textoPlanta = textoPlanta;
    }

    public String getNumeroBusca() {
        return numeroBusca;
    }

    public void setNumeroBusca(String numeroBusca) {
        this.numeroBusca = numeroBusca;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public OrcamentoResponse getResultado() {
        return resultado;
    }

    public List<OrcamentoResponse> getResultadosBusca() {
        return resultadosBusca;
    }
}
