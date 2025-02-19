package br.senac.sp.filmes.client.impl;

import br.senac.sp.filmes.client.FilmeDataprovider;
import br.senac.sp.filmes.client.mapper.FilmesVideoClientMapper;
import br.senac.sp.filmes.client.model.ResponseLegendaryVideoModel;
import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Component
public class FilmeDataProviderImpl implements FilmeDataprovider {

    private static final Logger logger = LogManager.getLogger(FilmeDataProviderImpl.class);

    private final WebClient.Builder webClientBuilder;

    public FilmeDataProviderImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public List<FilmesLegendaryVideoModel> recuperarTodos() {
        logger.info("[FilmeDataProviderImpl]-[recuperarTodos] - Recuperando todos os filmes no webservice!");
        var webClient = webClientBuilder.baseUrl(LEGENDARY_VIDEO_LIBRARY_BASE_URL).build();

        var response = webClient.get()
                .uri(RECUPERAR_TODOS_URI)
                .retrieve()
                .bodyToMono(ResponseLegendaryVideoModel.class)
                .block();
        if(Objects.isNull(response)){
            return List.of();
        }
        return FilmesVideoClientMapper.INSTANCE.listLegendaryVideoToListFilmesLegendaryVideo(
                response.getLegendaryVideos()
        );
    }

}
