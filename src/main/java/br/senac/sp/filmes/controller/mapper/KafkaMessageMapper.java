package br.senac.sp.filmes.controller.mapper;

import br.senac.sp.filmes.controller.request.FilmesLegendaryVideoRequest;
import br.senac.sp.filmes.models.KafkaMessageLegendaryVideoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KafkaMessageMapper {

    KafkaMessageMapper INSTANCE = Mappers.getMapper(KafkaMessageMapper.class);

    KafkaMessageLegendaryVideoModel toModel(final FilmesLegendaryVideoRequest request);

}
