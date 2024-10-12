package com.internal.tomafinal.controller;

import com.internal.tomafinal.controller.model.FilmDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @GetMapping
    public List<FilmDTO> getFilms(){
        return List.of();
    }

    @GetMapping("/{name}")
    public FilmDTO getFilm(@PathVariable String name){
        return null;
    }

    @PostMapping
    public FilmDTO postFilm(@RequestBody FilmDTO filmDTO){
        return null;
    }

    @PutMapping ("/{name}")
    public FilmDTO putFilm(@RequestBody FilmDTO filmDTO){
        return null;
    }

    @DeleteMapping("/{name}")
    public String deleteFilm(@PathVariable String name){
        return "";
    }


}
