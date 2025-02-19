package br.senac.sp.filmes.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseLegendaryVideoModel {

    @JsonProperty(value = "legendary_videos")
    private List<LegendaryVideoModel> legendaryVideos;

    public List<LegendaryVideoModel> getLegendaryVideos() {
        return legendaryVideos;
    }

    public ResponseLegendaryVideoModel setLegendaryVideos(List<LegendaryVideoModel> legendaryVideos) {
        this.legendaryVideos = legendaryVideos;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseLegendaryVideoModel{" +
                "legendaryVideos=" + legendaryVideos +
                '}';
    }

}