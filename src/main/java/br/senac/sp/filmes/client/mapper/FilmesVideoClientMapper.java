package br.senac.sp.filmes.client.mapper;

import br.senac.sp.filmes.client.model.LegendaryVideoModel;
import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmesVideoClientMapper {

    FilmesVideoClientMapper INSTANCE = Mappers.getMapper(FilmesVideoClientMapper.class);

    List<FilmesLegendaryVideoModel> listLegendaryVideoToListFilmesLegendaryVideo(List<LegendaryVideoModel> models);

}
