package com.internal.tomafinal.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieSearchResponse {

    @JsonProperty("results")
    private List<MovieResult> results;

    public List<MovieResult> getResults() {
        return results;
    }

    public void setResults(List<MovieResult> results) {
        this.results = results;
    }

    public static class MovieResult {
        @JsonProperty("id")
        private Integer id;

        @JsonProperty("title")
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
