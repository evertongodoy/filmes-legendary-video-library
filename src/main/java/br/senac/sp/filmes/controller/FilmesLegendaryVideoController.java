package br.senac.sp.filmes.controller;

import br.senac.sp.filmes.controller.response.FilmesLegendaryVideoResponse;
import br.senac.sp.filmes.usecase.FilmesLegendaryVideoUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "videos")
public class FilmesLegendaryVideoController {

    private static final Logger logger = LogManager.getLogger(FilmesLegendaryVideoController.class);

    private final FilmesLegendaryVideoUseCase filmesLegendaryVideoUseCase;

    public FilmesLegendaryVideoController(FilmesLegendaryVideoUseCase filmesLegendaryVideoUseCase) {
        this.filmesLegendaryVideoUseCase = filmesLegendaryVideoUseCase;
    }

    @GetMapping(value = "/library/recuperar/todos")
    public ResponseEntity<FilmesLegendaryVideoResponse> recuperarTodos(@RequestHeader(value = "subject") String usuario) {
        logger.info("[FilmesLegendaryVideoController]-[recuperarTodos] - Recuperando todos os filmes no webservice!");
        var models = filmesLegendaryVideoUseCase.recuperarTodos(usuario);
        return ResponseEntity.ok().body(
                new FilmesLegendaryVideoResponse().setLegendaryVideos(models)
        );
    }

}