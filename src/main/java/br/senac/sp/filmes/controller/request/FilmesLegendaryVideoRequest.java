package br.senac.sp.filmes.controller.request;

public class FilmesLegendaryVideoRequest {

    private String acao;
    private String subject;
    private String topico;
    private String mensagem;

    public String getAcao() {
        return acao;
    }

    public FilmesLegendaryVideoRequest setAcao(String acao) {
        this.acao = acao;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public FilmesLegendaryVideoRequest setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getTopico() {
        return topico;
    }

    public FilmesLegendaryVideoRequest setTopico(String topico) {
        this.topico = topico;
        return this;
    }

    public String getMensagem() {
        return mensagem;
    }

    public FilmesLegendaryVideoRequest setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

}