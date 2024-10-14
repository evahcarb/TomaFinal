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
        for (FilmDocument f : films) {
            FilmDTO filmDTO = getFilmDTO(f);
            filmDTOS.add(filmDTO);
        }
        return filmDTOS;
    }

    public FilmDTO getFilm(String name) {
        FilmDocument film = filmRepository.findByName(name);
        FilmDTO filmdto = getFilmDTO(film);
        return filmdto;
    }

    public void postFilm(String name, Integer year, String director, String synopsis, FilmDTO.Genre genre) {
        FilmDocument film = new FilmDocument(name, year, director, synopsis, genre.toString());
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

    private FilmDTO getFilmDTO(FilmDocument film) {
        List<ReviewDocument> reviews = reviewRepository.findAllByIdFilm(film.getId());
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        float pointTotalRating = 0;
        for (ReviewDocument r : reviews) {
            ReviewDTO review = new ReviewDTO(r.getId(), r.getComment(), r.getRating());
            reviewDTOS.add(review);
            pointTotalRating += r.getRating();
        }
        FilmDTO filmDTO = new FilmDTO(film.getName(), film.getYear(), film.getDirector(), film.getSynopsis(), FilmDTO.Genre.valueOf(film.getGenre()), (pointTotalRating / reviewDTOS.size()), reviewDTOS);
        return filmDTO;
    }

}
