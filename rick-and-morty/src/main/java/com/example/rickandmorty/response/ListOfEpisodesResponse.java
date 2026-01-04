package com.example.rickandmorty.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ListOfEpisodesResponse {

    private InfoResponse info;
    private List<EpisodeResponse> results;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static class InfoResponse {

        private String count;
        private String pages;
        private String next;
        private String prev;

    }

}
