package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.Pokemon.Pokemon;

import java.util.List;

public interface PokemonDAO {

    public Pokemon savePokemon(Pokemon p_pokemon);
    public List<Pokemon> getAllPokemon();

}
