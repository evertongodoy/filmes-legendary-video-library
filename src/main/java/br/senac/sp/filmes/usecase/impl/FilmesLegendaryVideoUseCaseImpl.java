package br.senac.sp.filmes.usecase.impl;

import org.apache.kafka.clients.producer.ProducerRecord;
import br.senac.sp.filmes.client.FilmeDataprovider;
import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;
import br.senac.sp.filmes.models.KafkaMessageLegendaryVideoModel;
import br.senac.sp.filmes.usecase.FilmesLegendaryVideoUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilmesLegendaryVideoUseCaseImpl implements FilmesLegendaryVideoUseCase {

    private static final Logger logger = LogManager.getLogger(FilmesLegendaryVideoUseCaseImpl.class);

    private final FilmeDataprovider filmeDataprovider;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public FilmesLegendaryVideoUseCaseImpl(FilmeDataprovider filmeDataprovider,
                                           KafkaTemplate<String, String> kafkaTemplate,
                                           ObjectMapper objectMapper) {
        this.filmeDataprovider = filmeDataprovider;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<FilmesLegendaryVideoModel> recuperarTodos(final String usuario) {
        logger.info("[FilmesLegendaryVideoUseCaseImpl]-[recuperarTodos] - Recuperando todos os filmes no webservice!");
        return filmeDataprovider.recuperarTodos(usuario);
    }

    @Override
    public void enviarParaKafka(final KafkaMessageLegendaryVideoModel kafkaMessageLegendaryVideoModel) {
        logger.info("[FilmesLegendaryVideoUseCaseImpl]-[enviarParaKafka] - Enviando mensagem para o topico {}",
                kafkaMessageLegendaryVideoModel.getTopico());
        var key = UUID.randomUUID().toString();
        try {
            logger.info("[FilmesLegendaryVideoUseCaseImpl]-[enviarParaKafka] - Convertendo para JSON");
            var json = objectMapper.writeValueAsString(kafkaMessageLegendaryVideoModel);

            logger.info("[FilmesLegendaryVideoUseCaseImpl]-[enviarParaKafka] - Criar ProducerRecord com chave e valor JSON");
            ProducerRecord<String, String> record = new ProducerRecord<>(kafkaMessageLegendaryVideoModel.getTopico(), key, json);

            logger.info("[FilmesLegendaryVideoUseCaseImpl]-[enviarParaKafka] - Enviar mensagem para o Kafka");
            kafkaTemplate.send(record).whenComplete((result, ex) -> {
                if (ex == null) {
                    logger.info("[FilmesLegendaryVideoUseCaseImpl]-[enviarParaKafka] - Sucesso, mensagem {} enviada no topico {}",
                            kafkaMessageLegendaryVideoModel.getMensagem(), kafkaMessageLegendaryVideoModel.getTopico());
                } else {
                    logger.error("[FilmesLegendaryVideoUseCaseImpl]-[enviarParaKafka] - Problemas ao enviar mensagem {}", ex.getMessage());
                }
            });

        } catch (JsonProcessingException e) {
            logger.info("[FilmesLegendaryVideoUseCaseImpl]-[JsonProcessingException] - Erro ao serializar objeto para JSON {}", e.getMessage());
        }
    }

}