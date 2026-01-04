package com.webflux.pokedex;

import com.webflux.pokedex.model.Pokemon;
import com.webflux.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

    @Bean
    CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository pokemonRepository) {
        return args -> {
            Flux<Pokemon> pokemonFlux = Flux.just(
                    new Pokemon(null, "Bulbasaur", "Seed", "Overgrow", 6.9, 0.7),
                    new Pokemon(null, "Charmander", "Lizard", "Blaze", 8.5, 0.6),
                    new Pokemon(null, "Charizard", "Flame", "Blaze", 90.5, 1.7),
                    new Pokemon(null, "Squirtle", "Tiny Turtle", "Torrent", 9.0, 0.5),
                    new Pokemon(null, "Blastoise", "Shellfish", "Torrent", 85.5, 1.6),
                    new Pokemon(null, "Caterpie", "Worm", "Shield Dust", 2.9, 0.3),
                    new Pokemon(null, "Pidgey", "Tiny Bird", "Keen Eye", 1.8, 0.3),
                    new Pokemon(null, "Rattata", "Mouse", "Run Away", 3.5, 0.3),
                    new Pokemon(null, "Pikachu", "Mouse", "Static", 6.0, 0.4),
                    new Pokemon(null, "Clefairy", "Fairy", "Cute Charm", 7.5, 0.6))
                    .flatMap(pokemonRepository::save);

            pokemonFlux
                    .thenMany(pokemonRepository.findAll())
                    .subscribe(System.out::println);
        };
    }

}