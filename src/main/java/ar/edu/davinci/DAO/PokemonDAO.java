package ar.edu.davinci.DAO;

import ar.edu.davinci.POKEMON.Pokemon;

import java.util.List;

public interface PokemonDAO {

    public Pokemon savePokemon(Pokemon p_pokemon);
    public List<Pokemon> getAllPokemon();

}
