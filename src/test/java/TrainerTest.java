
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Pokemon.Type.Electric;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.Trainer.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainerTest {


    @DisplayName("Verifica que la vida de los pokemones de la lista sea mayor a 0")
    @Test
    public void alive() {
        Trainer ash = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        Pokemon Pikachu = new Pokemon(1, new Electric(), "Pikachu", 25);
        ash.getPokemons().add(Pikachu);

        boolean respuesta = ash.alive();

        assertTrue(respuesta);
    }


    @DisplayName("Capturar un pokemon salvaje a la lista")
    @Test
    public void capturePokemon() {
        Trainer ash = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        Pokemon Pikachu = new Pokemon(1, new Electric(), "Pikachu", 25);

        boolean respuesta = ash.capturePokemon(Pikachu);
        assertTrue(respuesta);
    }


    @DisplayName("Añadir pokemones a la lista")
    @Test
    public void addPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();

        Trainer ash = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        Pokemon Pikachu = new Pokemon(1, new Electric(), "Pikachu", 25);
        Pokemon Pikachu2 = new Pokemon(1, new Electric(), "Pikachu", 25);

        pokemons.add(Pikachu);
        pokemons.add(Pikachu2);

        ash.addPokemon(pokemons);

        assertEquals(2, ash.getPokemons().size());

    }


}
