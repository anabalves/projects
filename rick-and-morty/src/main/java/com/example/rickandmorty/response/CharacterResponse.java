package com.example.rickandmorty.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CharacterResponse {

    private String id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginResponse origin;
    private LocationResponse location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class OriginResponse {

        private String name;
        private String url;

    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class LocationResponse {

        private String name;
        private String url;

    }

}
