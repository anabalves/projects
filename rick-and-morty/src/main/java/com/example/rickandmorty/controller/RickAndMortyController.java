package com.example.rickandmorty.controller;

import com.example.rickandmorty.client.RickAndMortyClient;
import com.example.rickandmorty.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
@Api(value = "/webclient", tags = "Rick and Morty API", description = "Consumindo API com Spring Webflux e WebClient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Buscando o personagem por id")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id) {
        return rickAndMortyClient.findACharacterById(id);
    }

    @GetMapping("/characters")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Buscando todos os personagens cadastrados")
    public Flux<ListOfCharactersResponse> listAllCharacters() {
        return rickAndMortyClient.listAllCharacters();
    }

    @GetMapping("/location/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Buscando o localização por id")
    public Mono<LocationResponse> getLocationById(@PathVariable String id) {
        return rickAndMortyClient.findALocationById(id);
    }

    @GetMapping("/locations")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Buscando todas as localizações cadastradas")
    public Flux<ListOfLocationsResponse> listAllLocations() {
        return rickAndMortyClient.listAllLocations();
    }

    @GetMapping("/episode/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Buscando o episódio por id")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id) {
        return rickAndMortyClient.findAEpisodeById(id);
    }

    @GetMapping("/episodes")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Buscando todos os episodios cadastrados")
    public Flux<ListOfEpisodesResponse> listAllEpisodes() {
        return rickAndMortyClient.listAllEpisodes();
    }

}
