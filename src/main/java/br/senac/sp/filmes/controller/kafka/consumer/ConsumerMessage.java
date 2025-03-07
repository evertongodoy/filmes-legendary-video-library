package br.senac.sp.filmes.controller.kafka.consumer;

import br.senac.sp.filmes.controller.response.FilmesLegendaryVideoResponse;
import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Service
public class ConsumerMessage {

    private static final Logger logger = LogManager.getLogger(ConsumerMessage.class);

    @KafkaListener(topics = "devolve-videos-library", groupId = "filmes-consumer-group")
    public void consumirMensagem(ConsumerRecord<String, String> consumerRecordVideos,
                                 Acknowledgment ack) {
        logger.info("[ConsumerMessage]-[consumirMensagem] - Mensagem recebida: {}", consumerRecordVideos.value());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Suporte a Java 8 Date/Time API
            logger.info("[ConsumerMessage]-[consumirMensagem] - Convertendo JSON para objeto KafkaMessageLegendaryVideoModel");
            var listaVideos = objectMapper.readValue(consumerRecordVideos.value(), // Aqui vai o JSON retornado pelo consumerRecordVideos.value()
                    new TypeReference<List<FilmesLegendaryVideoModel>>() {} // Define que o JSON é uma lista de objetos
            );
            logger.info("[ConsumerMessage]-[consumirMensagem] - Mensagem recuperada: {}", listaVideos);
            listaVideos.forEach(filme ->
                    logger.info("[ConsumerMessage]-[consumirMensagem] - Video: {}", filme.toString()));
            ack.acknowledge();
        } catch (Exception e) {
            logger.error("[ConsumerMessage]-[consumirMensagem] - Erro ao desserializar JSON {}", e.getMessage());
        }
    }
}
