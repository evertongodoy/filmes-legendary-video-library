package br.senac.sp.filmes.models;

import java.time.LocalDate;
import java.util.Arrays;

public class FilmesLegendaryVideoModel {

    private String id;
    private String nome;
    private String genero;
    private int duracao;
    private String origem;
    private LocalDate lancamento;
    private String sinopse;
    private String[] atores;
    private String diretor;
    private int classificacao;

    public String getId() {
        return id;
    }

    public FilmesLegendaryVideoModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public FilmesLegendaryVideoModel setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getGenero() {
        return genero;
    }

    public FilmesLegendaryVideoModel setGenero(String genero) {
        this.genero = genero;
        return this;
    }

    public int getDuracao() {
        return duracao;
    }

    public FilmesLegendaryVideoModel setDuracao(int duracao) {
        this.duracao = duracao;
        return this;
    }

    public String getOrigem() {
        return origem;
    }

    public FilmesLegendaryVideoModel setOrigem(String origem) {
        this.origem = origem;
        return this;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public FilmesLegendaryVideoModel setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
        return this;
    }

    public String getSinopse() {
        return sinopse;
    }

    public FilmesLegendaryVideoModel setSinopse(String sinopse) {
        this.sinopse = sinopse;
        return this;
    }

    public String[] getAtores() {
        return atores;
    }

    public FilmesLegendaryVideoModel setAtores(String[] atores) {
        this.atores = atores;
        return this;
    }

    public String getDiretor() {
        return diretor;
    }

    public FilmesLegendaryVideoModel setDiretor(String diretor) {
        this.diretor = diretor;
        return this;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public FilmesLegendaryVideoModel setClassificacao(int classificacao) {
        this.classificacao = classificacao;
        return this;
    }

    @Override
    public String toString() {
        return "LegendaryVideoModel{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", duracao=" + duracao +
                ", origem='" + origem + '\'' +
                ", lancamento=" + lancamento +
                ", sinopse='" + sinopse + '\'' +
                ", atores=" + Arrays.toString(atores) +
                ", diretor='" + diretor + '\'' +
                ", classificacao=" + classificacao +
                '}';
    }

}