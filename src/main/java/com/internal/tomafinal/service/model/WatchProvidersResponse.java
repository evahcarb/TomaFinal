package com.internal.tomafinal.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class WatchProvidersResponse {

    @JsonProperty("results")
    private Map<String, CountryProviders> results;

    public Map<String, CountryProviders> getResults() {
        return results;
    }

    public void setResults(Map<String, CountryProviders> results) {
        this.results = results;
    }

    public static class CountryProviders {
        @JsonProperty("flatrate")
        private List<Provider> flatrate;

        public List<Provider> getFlatrate() {
            return flatrate;
        }

        public void setFlatrate(List<Provider> flatrate) {
            this.flatrate = flatrate;
        }
    }

    public static class Provider {
        @JsonProperty("provider_name")
        private String providerName;

        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }
    }
}
