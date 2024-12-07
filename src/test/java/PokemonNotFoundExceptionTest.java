import ar.edu.davinci.Backend.DAO.PokemonImplMysql;

import ar.edu.davinci.Backend.Excepion.PokemonNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokemonNotFoundExceptionTest {

    @DisplayName("No se encontro el pokemon")
    @Test
    void testPokemonNotFoundException() throws PokemonNotFoundException {
        PokemonImplMysql pokemonImplMysql = new PokemonImplMysql();

        assertThrows(PokemonNotFoundException.class, () -> pokemonImplMysql.getPokemonsWild());
    }
}
