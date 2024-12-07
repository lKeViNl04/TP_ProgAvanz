import ar.edu.davinci.Backend.DAO.PokemonImplMysql;
import ar.edu.davinci.Backend.DAO.TrainerImplMysql;
import ar.edu.davinci.Backend.DAO.UserImplMysql;
import ar.edu.davinci.Backend.Excepion.PokemonNotFoundException;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Pokemon.Type.Fire;
import ar.edu.davinci.Backend.Pokemon.Type.Water;
import ar.edu.davinci.Backend.Trainer.Gender;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonImplMysqlTest {
    UserImplMysql userMysql;
    PokemonImplMysql pokemonMysql;
    TrainerImplMysql trainerMysql;
    Pokemon pokemon;
    Pokemon pokemon2;
    Trainer ash;
    User user;


    @BeforeEach
    public void setup() {
        userMysql = new UserImplMysql();
        trainerMysql = new TrainerImplMysql();
        pokemonMysql = new PokemonImplMysql();
        pokemon = new Pokemon(1, new Water(), "Squirtle", 55);
        pokemon2 = new Pokemon(2, new Fire(), "Charmander", 55);
        ash = new Trainer( "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        user = new User("nico", "messi", "nico@gmail.com", "messi19", "messi123");
    }

    @DisplayName("guardar pokemon a la base de datos ")
    @Test
    public void setPokemonToTrainer() {
        Pokemon resultado = pokemonMysql.setPokemonToTrainer(pokemon2, 1);
        assertEquals(pokemon2.getEspecie(), resultado.getEspecie());
    }

    @DisplayName("eliminar pokemon a la base de datos ")
    @Test
    public void deletePokemon() {
        pokemonMysql.setPokemonToTrainer(pokemon, 1);

        pokemonMysql.deletePokemon(pokemon.getPokemonid());

        assertEquals(0, pokemonMysql.getAllPokemons().size());
    }


    @DisplayName("modificar vida del pokemon a la base de datos ")
    @Test
    public void updatePokemonVida() {
        pokemonMysql.setPokemonToTrainer(pokemon, 1);
        Pokemon nuevo = new Pokemon(1, new Water(), "Squirtle", 55,100,55);
        pokemonMysql.updatePokemonVida(nuevo);
        List<Pokemon> pokemons = pokemonMysql.getPokemonsByTrainerId(1);

        assertEquals(55,pokemons.get(0).getVida());
    }

    @DisplayName("Obtengo un pokemon salvaje aleatorio de la base de datos ")
    @Test
    public void getPokemonWild() throws PokemonNotFoundException {
        Pokemon PokemonWild = pokemonMysql.getPokemonsWild();
        assertEquals(pokemon.getEspecie(), PokemonWild.getEspecie());

    }

    @DisplayName("Obtengo todos los pokemons de ese entrenador (id) de la base de datos ")
    @Test
    public void getPokemonByTrainerId() {
        userMysql.saveUser(user);
        trainerMysql.setTrainerToUser(ash,1);
        pokemonMysql.setPokemonToTrainer(pokemon, 1);
        List<Pokemon> resultado = pokemonMysql.getPokemonsByTrainerId(1);
        assertEquals(pokemon.getEspecie(),resultado.get(0).getEspecie());
    }

    @DisplayName("Obtener todos los pokemones de la base de datos ")
    @Test
    public void getAllPokemons() {
        pokemonMysql.setPokemonToTrainer(pokemon,1);
        pokemonMysql.getAllPokemons();
        assertEquals(1,pokemonMysql.getAllPokemons().size());
    }


}
