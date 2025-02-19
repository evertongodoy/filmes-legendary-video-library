package br.senac.sp.filmes.controller.response;

import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;

import java.util.List;

public class FilmesLegendaryVideoResponse {

    private List<FilmesLegendaryVideoModel> legendaryVideos;

    public List<FilmesLegendaryVideoModel> getLegendaryVideos() {
        return legendaryVideos;
    }

    public FilmesLegendaryVideoResponse setLegendaryVideos(List<FilmesLegendaryVideoModel> legendaryVideos) {
        this.legendaryVideos = legendaryVideos;
        return this;
    }

    @Override
    public String toString() {
        return "FilmesLegendaryVideoResponse{" +
                "legendaryVideos=" + legendaryVideos +
                '}';
    }

}