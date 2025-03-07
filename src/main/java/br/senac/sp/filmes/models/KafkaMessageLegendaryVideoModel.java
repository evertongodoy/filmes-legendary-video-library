package br.senac.sp.filmes.models;

public class KafkaMessageLegendaryVideoModel {

    private String acao;
    private String subject;
    private String topico;
    private String mensagem;

    public String getAcao() {
        return acao;
    }

    public KafkaMessageLegendaryVideoModel setAcao(String acao) {
        this.acao = acao;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public KafkaMessageLegendaryVideoModel setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getTopico() {
        return topico;
    }

    public KafkaMessageLegendaryVideoModel setTopico(String topico) {
        this.topico = topico;
        return this;
    }

    public String getMensagem() {
        return mensagem;
    }

    public KafkaMessageLegendaryVideoModel setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

}