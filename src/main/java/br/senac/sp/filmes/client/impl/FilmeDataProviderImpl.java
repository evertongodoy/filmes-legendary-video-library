package br.senac.sp.filmes.client.impl;

import br.senac.sp.filmes.client.FilmeDataprovider;
import br.senac.sp.filmes.client.mapper.FilmesVideoClientMapper;
import br.senac.sp.filmes.client.model.ResponseLegendaryVideoModel;
import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;
import br.senac.sp.security.tokens.application.usecase.GenerateTokenUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
public class FilmeDataProviderImpl implements FilmeDataprovider {

    private static final Logger logger = LogManager.getLogger(FilmeDataProviderImpl.class);

    private final WebClient.Builder webClientBuilder;
    private final GenerateTokenUseCase generateTokenUseCase;

    public FilmeDataProviderImpl(WebClient.Builder webClientBuilder,
                                 GenerateTokenUseCase generateTokenUseCase) {
        this.webClientBuilder = webClientBuilder;
        this.generateTokenUseCase = generateTokenUseCase;
    }

    @Override
    public List<FilmesLegendaryVideoModel> recuperarTodos(final String usuario) {
        logger.info("[FilmeDataProviderImpl]-[recuperarTodos] - Recuperando todos os filmes no webservice!");
        var webClient = webClientBuilder.baseUrl(LEGENDARY_VIDEO_LIBRARY_BASE_URL).build();
        var token = this.generateTokenUseCase.execute(usuario);

        var response = webClient.get()
                .uri(RECUPERAR_TODOS_URI) // uri() is used to set the endpoint
                .header("Authorization", "Bearer " + token.getValue()) // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZW5hYzIiLCJpYXQiOjE3NDA3ODM5NTQsImV4cCI6MTc0MDc4NDU1NH0.A_ZL6i6ZORqNZRyHtgrucslVszY9WSkPuDDlNVsS4CI
                .retrieve() // retrieve() is used to get the response
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new ResponseStatusException(
                                        clientResponse.statusCode(),
                                        "Erro 4xx - Cliente: " + errorBody))
                                )
                )
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new ResponseStatusException(
                                        clientResponse.statusCode(),
                                        "Erro 5xx - Servidor: " + errorBody))
                                )
                )
                .bodyToMono(ResponseLegendaryVideoModel.class) // bodyToMono() is used to convert the response to a Mono
                .block(); // block() is used to wait for the response
        if(Objects.isNull(response)){
            return List.of();
        }
        return FilmesVideoClientMapper.INSTANCE.listLegendaryVideoToListFilmesLegendaryVideo(
                response.getLegendaryVideos()
        );

    }

}
