package com.internal.tomafinal.repository;

import com.internal.tomafinal.repository.model.FilmDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FilmCustomRepositoryTest {

    @InjectMocks
    private FilmCustomRepository filmCustomRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private FilmMongoRepository filmMongoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByDynamicFilter_WithAllParameters() {
        String name = "Test Film";
        Integer year = 2022;
        String genre = "Action";

        List<FilmDocument> mockFilms = new ArrayList<>();
        mockFilms.add(new FilmDocument(null, "Test Film", year, "Director", "Synopsis", genre, "url"));

        when(mongoTemplate.find(any(Query.class), eq(FilmDocument.class))).thenReturn(mockFilms);

        List<FilmDocument> result = filmCustomRepository.findByDynamicFilter(name, year, genre);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mongoTemplate, times(1)).find(any(Query.class), eq(FilmDocument.class));
    }

    @Test
    void testFindByDynamicFilter_WithPartialParameters() {
        String name = "Test";
        Integer year = null;
        String genre = null;

        List<FilmDocument> mockFilms = new ArrayList<>();
        mockFilms.add(new FilmDocument(null, "Test Film", 2022, "Director", "Synopsis", "Action", "url"));

        when(mongoTemplate.find(any(Query.class), eq(FilmDocument.class))).thenReturn(mockFilms);

        List<FilmDocument> result = filmCustomRepository.findByDynamicFilter(name, year, genre);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mongoTemplate, times(1)).find(any(Query.class), eq(FilmDocument.class));
    }

    @Test
    void testFindByName() {
        String name = "Test Film";
        FilmDocument mockFilm = new FilmDocument(null, name, 2022, "Director", "Synopsis", "Action", "url");

        when(filmMongoRepository.findByName(name)).thenReturn(mockFilm);

        FilmDocument result = filmCustomRepository.findByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(filmMongoRepository, times(1)).findByName(name);
    }

    @Test
    void testFindAll() {
        List<FilmDocument> mockFilms = new ArrayList<>();
        mockFilms.add(new FilmDocument(null, "Test Film", 2022, "Director", "Synopsis", "Action", "url"));

        when(filmMongoRepository.findAll()).thenReturn(mockFilms);

        List<FilmDocument> result = filmCustomRepository.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(filmMongoRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        FilmDocument mockFilm = new FilmDocument(null, "Test Film", 2022, "Director", "Synopsis", "Action", "url");

        filmCustomRepository.delete(mockFilm);

        verify(filmMongoRepository, times(1)).delete(mockFilm);
    }

    @Test
    void testSave() {
        FilmDocument mockFilm = new FilmDocument(null, "Test Film", 2022, "Director", "Synopsis", "Action", "url");

        filmCustomRepository.save(mockFilm);

        verify(filmMongoRepository, times(1)).save(mockFilm);
    }
}
