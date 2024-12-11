package com.internal.tomafinal.controller;

import com.internal.tomafinal.controller.model.FilmDTO;
import com.internal.tomafinal.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmDTO> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/{name}")
    public FilmDTO getFilm(@PathVariable String name) {
        return filmService.getFilm(name);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> postFilm(@RequestBody FilmDTO filmDTO) {
        filmService.postFilm(filmDTO.getName(), filmDTO.getYear(), filmDTO.getDirector(), filmDTO.getSynopsis(), filmDTO.getGenre(), filmDTO.getUrlFilm());
        return ResponseEntity.status(HttpStatus.CREATED).body(filmDTO);
    }

    @PutMapping("/{name}")
    public String putFilm(@RequestBody FilmDTO filmDTO) {
        if (filmService.getFilm(filmDTO.getName()) != null) {
            filmService.postFilm(filmDTO.getName(), filmDTO.getYear(), filmDTO.getDirector(), filmDTO.getSynopsis(), filmDTO.getGenre(), filmDTO.getUrlFilm());
            return "Se ha modificado correctamente la película con nombre " + filmDTO.getName();
        }
        return "No se ha encontrado la película con nombre " + filmDTO.getName();
    }


    @DeleteMapping("/{name}")
    public String deleteFilm(@PathVariable String name) {
        if (filmService.deleteFilm(name)) {
            return "Se ha borrado correctamente la película con nombre ";
        }
        return "No se ha encontrado la película con nombre ";
    }

    @GetMapping("/filter")
    public ResponseEntity<List<FilmDTO>> getFilmsByName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String genre) {

        // Opcional: Prevenir valores nulos enviando un valor por defecto.
        String safeName = (name != null && !name.isEmpty()) ? name : "";

        List<FilmDTO> films = filmService.getFilmsByFilter(safeName, year, genre);
        if (films.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devolver 204 si no hay resultados
        }

        return ResponseEntity.ok(films);
    }


}
