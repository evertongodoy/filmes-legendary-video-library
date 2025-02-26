package br.senac.sp.filmes.usecase;

import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;

import java.util.List;

public interface FilmesLegendaryVideoUseCase {

    List<FilmesLegendaryVideoModel> recuperarTodos(final String usuario);

}
