package com.webflux.pokedex.controller;

import com.webflux.pokedex.model.Pokemon;
import com.webflux.pokedex.repository.PokemonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
@Api(value = "/pokemons", tags = "Pokedex API", description = "Construindo uma Pokedex com Spring Webflux")
public class PokemonController {

    private PokemonRepository pokemonRepository;
    public PokemonController(PokemonRepository pokemonRepository) { this.pokemonRepository = pokemonRepository; }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Buscando todos os pokemons cadastrados")
    public Flux<Pokemon> getAllPokemons() { return pokemonRepository.findAll(); }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscando pokemon por id")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id) {
        return pokemonRepository.findById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criando um novo pokemon")
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) { return pokemonRepository.save(pokemon); }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizando um pokemon por id")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id") String id, @RequestBody Pokemon pokemon) {
        return pokemonRepository.findById(id)
                .flatMap(existingPokemon -> {
                    existingPokemon.setName(pokemon.getName());
                    existingPokemon.setCategory(pokemon.getCategory());
                    existingPokemon.setAbility(pokemon.getAbility());
                    existingPokemon.setWeight(pokemon.getWeight());
                    existingPokemon.setHeight(pokemon.getHeight());
                    return pokemonRepository.save(existingPokemon);
                })
                .map(updatePokemon -> ResponseEntity.ok(updatePokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Removendo um pokemon por id")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value = "id") String id) {
        return pokemonRepository.findById(id)
                .flatMap(existingPokemon ->
                        pokemonRepository.delete(existingPokemon)
                                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Removendo todos os pokemons cadastrados")
    public Mono<Void> deleteAllPokemons() { return pokemonRepository.deleteAll(); }

}
