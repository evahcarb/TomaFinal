package com.internal.tomafinal.service;

import com.internal.tomafinal.controller.model.FilmDTO;
import com.internal.tomafinal.controller.model.ReviewDTO;
import com.internal.tomafinal.repository.FilmMongoRepository;
import com.internal.tomafinal.repository.ReviewMongoRepository;
import com.internal.tomafinal.repository.model.FilmDocument;
import com.internal.tomafinal.repository.model.ReviewDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {
    private FilmMongoRepository filmRepository;
    private ReviewMongoRepository reviewRepository;

    public FilmService(FilmMongoRepository filmRepository, ReviewMongoRepository reviewRepository) {
        this.filmRepository = filmRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<FilmDTO> getFilms() {
        List<FilmDocument> films = filmRepository.findAll();
        List<FilmDTO> filmDTOS = new ArrayList<>();
        for (FilmDocument i : films) {
            FilmDTO film = new FilmDTO(i.getName(), i.getYear(), i.getDirector(), i.getSynopsis());
            filmDTOS.add(film);
        }
        return filmDTOS;
    }

    public FilmDTO getFilm(String name) {
        FilmDocument film = filmRepository.findByName(name);
        List<ReviewDocument> reviews = reviewRepository.findAllByIdFilm(film.getId());
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (ReviewDocument i : reviews) {
            ReviewDTO review = new ReviewDTO(i.getId(), i.getComment(), i.getRating());
            reviewDTOS.add(review);
        }
        FilmDTO filmdto = new FilmDTO(film.getName(), film.getYear(), film.getDirector(), film.getSynopsis(), reviewDTOS);
        return filmdto;
    }

    public void postFilm(String name, Integer year, String director, String synopsis) {
        FilmDocument film = new FilmDocument(name, year, director, synopsis);
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

}
