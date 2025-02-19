package br.senac.sp.filmes.usecase.impl;

import br.senac.sp.filmes.client.FilmeDataprovider;
import br.senac.sp.filmes.models.FilmesLegendaryVideoModel;
import br.senac.sp.filmes.usecase.FilmesLegendaryVideoUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmesLegendaryVideoUseCaseImpl implements FilmesLegendaryVideoUseCase {

    private static final Logger logger = LogManager.getLogger(FilmesLegendaryVideoUseCaseImpl.class);

    private final FilmeDataprovider filmeDataprovider;

    public FilmesLegendaryVideoUseCaseImpl(FilmeDataprovider filmeDataprovider) {
        this.filmeDataprovider = filmeDataprovider;
    }

    @Override
    public List<FilmesLegendaryVideoModel> recuperarTodos() {
        logger.info("[FilmesLegendaryVideoUseCaseImpl]-[recuperarTodos] - Recuperando todos os filmes no webservice!");
        return filmeDataprovider.recuperarTodos();
    }

}