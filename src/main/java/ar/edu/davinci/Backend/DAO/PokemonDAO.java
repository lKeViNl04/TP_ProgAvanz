package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.Excepion.PokemonNotFoundException;
import ar.edu.davinci.Backend.Pokemon.Pokemon;

import java.util.List;

public interface PokemonDAO {

    public Pokemon setPokemonToTrainer(Pokemon p_pokemon,int trainerId);
    public List<Pokemon> getAllPokemons();
    public List<Pokemon> getPokemonsByTrainerId(int TrainerId);
    public Pokemon getPokemonsWild() throws PokemonNotFoundException;
    public void updatePokemonVida(Pokemon pokemon);
    public void deletePokemon(int pokemonId);




}
