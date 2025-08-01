const pokemonData = document.querySelector('.pokemon__data');
const pokemonName = document.querySelector('.pokemon__name');
const pokemonNumber = document.querySelector('.pokemon__number');
const pokemonImage = document.querySelector('.pokemon__image');
const pokemonForm = document.querySelector('.pokemon__form');
const pokemonSearch = document.querySelector('.pokemon_search');
const pokemonButtonPrev = document.querySelector('.btn-prev');
const pokemonButtonNext = document.querySelector('.btn-next');

let searchPokemon = 1;

const fetchPokemon = async (pokemon) => {
    const APIResponse = await fetch(`https://pokeapi.co/api/v2/pokemon/${pokemon.toString().toLowerCase()}`);
    
    if (APIResponse.status === 200) {
        const data = await APIResponse.json();
        return data;
    }
}

const renderPokemon = async (pokemon) => {
    pokemonName.innerHTML = 'Loading...';
    pokemonNumber.innerHTML = '';

    const data = await fetchPokemon(pokemon);

    if (data) {
        pokemonImage.style.display = 'block';
        pokemonName.innerHTML = data.name;
        pokemonNumber.innerHTML = data.id;

        const urlImage = data['sprites']['versions']['generation-v']['black-white']['animated']['front_default'];
        pokemonImage.src = urlImage == null ? "./assets/pikachu.gif" : urlImage;
        pokemonSearch.value = '';
        searchPokemon = data.id;
    } else {
        pokemonImage.style.display = 'none';
        pokemonName.innerHTML = 'Not Found :(';
        pokemonNumber.innerHTML = '';
    }
}

pokemonForm.addEventListener('submit', () => {
    event.preventDefault();
    renderPokemon(pokemonSearch.value);
});

pokemonButtonPrev.addEventListener('click', () => {
    if (searchPokemon > 1) {
        searchPokemon--;
        renderPokemon(searchPokemon);
    }
});

pokemonButtonNext.addEventListener('click', () => {
    searchPokemon++;
    renderPokemon(searchPokemon);
});

renderPokemon(searchPokemon);