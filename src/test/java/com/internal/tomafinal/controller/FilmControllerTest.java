package com.internal.tomafinal.controller;

import com.internal.tomafinal.controller.model.FilmDTO;
import com.internal.tomafinal.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmControllerTest {

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilms() {
        List<FilmDTO> mockFilms = new ArrayList<>();
        mockFilms.add(new FilmDTO("Film1", 2022, "Director1", "Synopsis1", FilmDTO.GenreDTO.ACTION, 4.5f, "url1"));
        mockFilms.add(new FilmDTO("Film2", 2023, "Director2", "Synopsis2", FilmDTO.GenreDTO.COMEDY, 3.8f, "url2"));

        when(filmService.getFilms()).thenReturn(mockFilms);

        List<FilmDTO> result = filmController.getFilms();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(filmService, times(1)).getFilms();
    }

    @Test
    void testGetFilm() {
        String name = "Film1";
        FilmDTO mockFilm = new FilmDTO(name, 2022, "Director1", "Synopsis1", FilmDTO.GenreDTO.ACTION, 4.5f, "url1");

        when(filmService.getFilm(name)).thenReturn(mockFilm);
        FilmDTO result = filmController.getFilm(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(filmService, times(1)).getFilm(name);
    }

    @Test
    void testPostFilm() {
        FilmDTO filmDTO = new FilmDTO("Film1", 2022, "Director1", "Synopsis1", FilmDTO.GenreDTO.ACTION, 4.5f, "url1");

        ResponseEntity<FilmDTO> response = filmController.postFilm(filmDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(filmDTO, response.getBody());
        verify(filmService, times(1)).postFilm(
                filmDTO.getName(),
                filmDTO.getYear(),
                filmDTO.getDirector(),
                filmDTO.getSynopsis(),
                filmDTO.getGenre(),
                filmDTO.getUrlFilm()
        );
    }

    @Test
    void testPutFilm_FilmExists() {
        FilmDTO filmDTO = new FilmDTO("Film1", 2022, "Director1", "Synopsis1", FilmDTO.GenreDTO.ACTION, 4.5f, "url1");
        when(filmService.getFilm(filmDTO.getName())).thenReturn(filmDTO);

        String result = filmController.putFilm(filmDTO);

        assertNotNull(result);
        assertTrue(result.contains("modificado correctamente"));
        verify(filmService, times(1)).getFilm(filmDTO.getName());
        verify(filmService, times(1)).postFilm(
                filmDTO.getName(),
                filmDTO.getYear(),
                filmDTO.getDirector(),
                filmDTO.getSynopsis(),
                filmDTO.getGenre(),
                filmDTO.getUrlFilm()
        );
    }

    @Test
    void testPutFilm_FilmDoesNotExist() {
        FilmDTO filmDTO = new FilmDTO("Film1", 2022, "Director1", "Synopsis1", FilmDTO.GenreDTO.ACTION, 4.5f, "url1");
        when(filmService.getFilm(filmDTO.getName())).thenReturn(null);

        String result = filmController.putFilm(filmDTO);

        assertNotNull(result);
        assertTrue(result.contains("No se ha encontrado"));
        verify(filmService, times(1)).getFilm(filmDTO.getName());
        verify(filmService, times(0)).postFilm(
                anyString(),
                anyInt(),
                anyString(),
                anyString(),
                any(FilmDTO.GenreDTO.class),
                anyString()
        );
    }

    @Test
    void testDeleteFilm_FilmExists() {
        String name = "Film1";
        when(filmService.deleteFilm(name)).thenReturn(true);

        String result = filmController.deleteFilm(name);

        assertNotNull(result);
        assertTrue(result.contains("borrado correctamente"));
        verify(filmService, times(1)).deleteFilm(name);
    }

    @Test
    void testDeleteFilm_FilmDoesNotExist() {
        String name = "Film1";
        when(filmService.deleteFilm(name)).thenReturn(false);

        String result = filmController.deleteFilm(name);

        assertNotNull(result);
        assertTrue(result.contains("No se ha encontrado"));
        verify(filmService, times(1)).deleteFilm(name);
    }

    @Test
    void testGetFilmsByName_WithResults() {
        String name = "Test";
        Integer year = 2022;
        FilmDTO.GenreDTO genre = FilmDTO.GenreDTO.ACTION;
        List<FilmDTO> mockFilms = List.of(new FilmDTO(name, year, "Director", "Synopsis", genre, 2.3f, "url"));

        when(filmService.getFilmsByFilter(name, year, genre.name())).thenReturn(mockFilms);

        ResponseEntity<List<FilmDTO>> response = filmController.getFilmsByName(name, year, genre.name());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(filmService, times(1)).getFilmsByFilter(name, year, genre.name());
    }

    @Test
    void testGetFilmsByName_NoResults() {
        when(filmService.getFilmsByFilter(anyString(), any(), anyString())).thenReturn(new ArrayList<>());

        ResponseEntity<List<FilmDTO>> response = filmController.getFilmsByName("Test", null, "Action");

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(filmService, times(1)).getFilmsByFilter(anyString(), any(), anyString());
    }
}
