package br.senac.sp.filmes.client;

import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;

import java.util.List;

public interface FilmeDataprovider {

    String LEGENDARY_VIDEO_LIBRARY_BASE_URL = "http://localhost:8090";
    String RECUPERAR_TODOS_URI = "/videos/recuperar/todos";

    List<FilmesLegendaryVideoModel> recuperarTodos(final String usuario);

}
