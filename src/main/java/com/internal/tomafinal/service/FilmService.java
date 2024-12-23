package com.internal.tomafinal.service;

import com.internal.tomafinal.controller.model.FilmDTO;
import com.internal.tomafinal.controller.model.ReviewDTO;
import com.internal.tomafinal.repository.FilmCustomRepository;
import com.internal.tomafinal.repository.ReviewMongoRepository;
import com.internal.tomafinal.repository.model.FilmDocument;
import com.internal.tomafinal.repository.model.ReviewDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private FilmCustomRepository filmRepository;
    private ReviewMongoRepository reviewRepository;
    private ProviderService providerService;

    public FilmService(FilmCustomRepository filmRepository, ReviewMongoRepository reviewRepository, ProviderService providerService) {
        this.filmRepository = filmRepository;
        this.reviewRepository = reviewRepository;
        this.providerService = providerService;
    }

    public List<FilmDTO> getFilms() {
        List<FilmDocument> films = filmRepository.findAll();
        List<FilmDTO> filmDTOS = new ArrayList<>();
        for (FilmDocument f : films) {
            FilmDTO filmDTO = getFilmDTO(f);
            filmDTOS.add(filmDTO);
        }
        return filmDTOS;
    }

    public FilmDTO getFilm(String name) {
        FilmDocument film = filmRepository.findByName(name);
        FilmDTO filmdto = getFilmDTO(film);
        List<String> providerNames = providerService.getProviderNames(name);
        filmdto.setProviderNames(providerNames);
        return filmdto;
    }

    public void postFilm(String name, Integer year, String director, String synopsis, FilmDTO.GenreDTO genre, String urlFilm) {
        FilmDocument film = new FilmDocument(null, name, year, director, synopsis, genre.toString(), urlFilm);
        filmRepository.save(film);
    }

    public boolean deleteFilm(String name) {
        FilmDocument film = filmRepository.findByName(name);
        if (film != null) {
            filmRepository.delete(film);
            return true;
        }
        return false;
    }

    public List<FilmDTO> getFilmsByFilter(String name, Integer year, String genre) {
        List<FilmDocument> films = filmRepository.findByDynamicFilter(name, year, genre);
        List<FilmDTO> filmDTOS = new ArrayList<>();
        for (FilmDocument f : films) {
            FilmDTO filmDTO = getFilmDTO(f);
            filmDTOS.add(filmDTO);
        }
        return filmDTOS;
    }

    private FilmDTO getFilmDTO(FilmDocument film) {
        List<ReviewDocument> reviews = reviewRepository.findAllByIdFilm(film.getId());
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        float pointTotalRating = 0;
        for (ReviewDocument r : reviews) {
            ReviewDTO review = new ReviewDTO(r.getId(), r.getComment(), r.getRating());
            reviewDTOS.add(review);
            pointTotalRating += r.getRating();
        }
        Float avgRating = !reviewDTOS.isEmpty() ? (pointTotalRating / reviewDTOS.size()) : null;
        FilmDTO filmDTO = new FilmDTO(film.getName(), film.getYear(), film.getDirector(),
                film.getSynopsis(), FilmDTO.GenreDTO.fromString(film.getGenre()), avgRating, reviewDTOS, film.getUrlFilm());
        return filmDTO;
    }


}
