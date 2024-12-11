package com.internal.tomafinal.service;

import com.internal.tomafinal.service.model.MovieSearchResponse;
import com.internal.tomafinal.service.model.WatchProvidersResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    @Value("${api.tmdb.key}")
    private String apiKey;

    private final String TMDB_URL = "https://api.themoviedb.org/3";

    public List<String> getProviderNames(String filmName) {
        Integer movieId = searchMovie(filmName);
        if (movieId != null) {
            return getStreamingProviders(movieId);
        }
        return new ArrayList<>();
    }

    private Integer searchMovie(String movieName) {
        String url = TMDB_URL + "/search/movie?api_key=" + apiKey + "&language=es-ES&query=" + movieName;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MovieSearchResponse> response = restTemplate.getForEntity(url, MovieSearchResponse.class);

        if (response.getBody() != null && !response.getBody().getResults().isEmpty()) {
            return response.getBody().getResults().get(0).getId();
        }
        return null;
    }

    private List<String> getStreamingProviders(Integer movieId) {
        String url = TMDB_URL + "/movie/" + movieId + "/watch/providers?api_key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WatchProvidersResponse> response = restTemplate.getForEntity(url, WatchProvidersResponse.class);

        List<String> providerNames = new ArrayList<>();
        if (response.getBody() != null) {
            WatchProvidersResponse.CountryProviders countryProviders = response.getBody().getResults().get("ES");
            if (countryProviders != null && countryProviders.getFlatrate() != null) {
                for (WatchProvidersResponse.Provider provider : countryProviders.getFlatrate()) {
                    providerNames.add(provider.getProviderName());
                }
            }
        }
        return providerNames;
    }
}
