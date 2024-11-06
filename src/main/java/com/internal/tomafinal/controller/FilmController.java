package com.internal.tomafinal.controller;

import com.internal.tomafinal.controller.model.FilmDTO;
import com.internal.tomafinal.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
@CrossOrigin(origins = "http://localhost:5500")
public class FilmController {
    //inyectamos el servicio
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


}
