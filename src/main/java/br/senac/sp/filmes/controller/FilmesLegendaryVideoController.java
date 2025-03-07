package br.senac.sp.filmes.controller;

import br.senac.sp.filmes.controller.mapper.KafkaMessageMapper;
import br.senac.sp.filmes.controller.request.FilmesLegendaryVideoRequest;
import br.senac.sp.filmes.controller.response.FilmesLegendaryVideoResponse;
import br.senac.sp.filmes.models.KafkaMessageLegendaryVideoModel;
import br.senac.sp.filmes.usecase.FilmesLegendaryVideoUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/publicar-solicitacao-videos")
    public ResponseEntity<String> publicarSolicitacaoVideos(@RequestBody FilmesLegendaryVideoRequest request) {
        logger.info("[FilmesLegendaryVideoController]-[publicarSolicitacaoVideos] - Publicando solicitação de vídeos!");
        var kafkaMessageVideoModel = KafkaMessageMapper.INSTANCE.toModel(request);
        filmesLegendaryVideoUseCase.enviarParaKafka(kafkaMessageVideoModel);
        return ResponseEntity.ok().body("Solicitação de vídeos publicada com sucesso!");
    }

}