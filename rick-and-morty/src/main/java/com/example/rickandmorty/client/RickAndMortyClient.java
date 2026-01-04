package com.example.rickandmorty.client;

import com.example.rickandmorty.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findACharacterById(String id) {
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(CharacterResponse.class);
    }

    public Flux<ListOfCharactersResponse> listAllCharacters() {
        log.info("Listando todos os personagens cadastrados");
        return webClient
                .get()
                .uri("/character")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToFlux(ListOfCharactersResponse.class);
    }

    public Mono<LocationResponse> findALocationById(String id) {
        log.info("Buscando o localização com o id [{}]", id);
        return webClient
                .get()
                .uri("/location/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(LocationResponse.class);
    }

    public Flux<ListOfLocationsResponse> listAllLocations() {
        log.info("Listando todas as localizações cadastradas");
        return webClient
                .get()
                .uri("/location")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToFlux(ListOfLocationsResponse.class);
    }

    public Mono<EpisodeResponse> findAEpisodeById(String id) {
        log.info("Buscando o episódio com o id [{}]", id);
        return webClient
                .get()
                .uri("/episode/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(EpisodeResponse.class);
    }

    public Flux<ListOfEpisodesResponse> listAllEpisodes() {
        log.info("Listando todos os episodios cadastrados");
        return webClient
                .get()
                .uri("/episode")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToFlux(ListOfEpisodesResponse.class);
    }

}
