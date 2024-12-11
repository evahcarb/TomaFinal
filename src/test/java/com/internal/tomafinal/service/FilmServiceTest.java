package com.internal.tomafinal.service;

import com.internal.tomafinal.controller.model.FilmDTO;
import com.internal.tomafinal.repository.FilmCustomRepository;
import com.internal.tomafinal.repository.ReviewMongoRepository;
import com.internal.tomafinal.repository.model.FilmDocument;
import com.internal.tomafinal.repository.model.ReviewDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Mock
    private FilmCustomRepository filmRepository;

    @Mock
    private ReviewMongoRepository reviewRepository;

    @Mock
    private ProviderService providerService;

    @InjectMocks
    private FilmService filmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilms() {
        FilmDocument film1 = new FilmDocument("1", "Film1", 2020, "Director1", "Synopsis1", "ACTION", "url1");
        FilmDocument film2 = new FilmDocument("2", "Film2", 2021, "Director2", "Synopsis2", "DRAMA", "url2");
        when(filmRepository.findAll()).thenReturn(Arrays.asList(film1, film2));

        ReviewDocument review1 = new ReviewDocument("1", "Good Movie", 5.00f);
        ReviewDocument review2 = new ReviewDocument("1", "Not Bad", 3.0f);
        when(reviewRepository.findAllByIdFilm(film1.getId())).thenReturn(Arrays.asList(review1, review2));
        when(reviewRepository.findAllByIdFilm(film2.getId())).thenReturn(Collections.emptyList());

        List<FilmDTO> films = filmService.getFilms();

        assertEquals(2, films.size());
        assertEquals("Film1", films.get(0).getName());
        assertEquals(4.0f, films.get(0).getAvgRating());
        assertNull(films.get(1).getAvgRating()); // No reviews for Film2
        verify(filmRepository, times(1)).findAll();
        verify(reviewRepository, times(1)).findAllByIdFilm(film1.getId());
        verify(reviewRepository, times(1)).findAllByIdFilm(film2.getId());
    }

    @Test
    void testGetFilm() {
        String filmName = "Film1";
        FilmDocument film = new FilmDocument(null, filmName, 2020, "Director1", "Synopsis1", "ACTION", "url1");
        when(filmRepository.findByName(filmName)).thenReturn(film);

        List<String> providers = Arrays.asList("Netflix", "HBO");
        when(providerService.getProviderNames(filmName)).thenReturn(providers);

        FilmDTO filmDTO = filmService.getFilm(filmName);

        assertNotNull(filmDTO);
        assertEquals(filmName, filmDTO.getName());
        assertEquals(providers, filmDTO.getProviderNames());
        verify(filmRepository, times(1)).findByName(filmName);
        verify(providerService, times(1)).getProviderNames(filmName);
    }

    @Test
    void testPostFilm() {
        String name = "Film1";
        int year = 2020;
        String director = "Director1";
        String synopsis = "Synopsis1";
        FilmDTO.GenreDTO genre = FilmDTO.GenreDTO.ACTION;
        String urlFilm = "url1";

        filmService.postFilm(name, year, director, synopsis, genre, urlFilm);

        verify(filmRepository, times(1)).save(any(FilmDocument.class));
    }

    @Test
    void testDeleteFilm_FilmExists() {
        String filmName = "Film1";
        FilmDocument film = new FilmDocument(null, filmName, 2020, "Director1", "Synopsis1", "ACTION", "url1");
        when(filmRepository.findByName(filmName)).thenReturn(film);

        boolean result = filmService.deleteFilm(filmName);

        assertTrue(result);
        verify(filmRepository, times(1)).findByName(filmName);
        verify(filmRepository, times(1)).delete(film);
    }

    @Test
    void testDeleteFilm_FilmDoesNotExist() {
        String filmName = "NonExistentFilm";
        when(filmRepository.findByName(filmName)).thenReturn(null);

        boolean result = filmService.deleteFilm(filmName);

        assertFalse(result);
        verify(filmRepository, times(1)).findByName(filmName);
        verify(filmRepository, never()).delete(any());
    }
}
