import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Pokemon.Type.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonTest {

    @Test
    public void testReturnWater() {

        Type agua = new Water();
        Pokemon DEAGUA = new Pokemon(agua, "hola", 55);

        String respuesta = DEAGUA.getTipo().toString();

        assertEquals("Agua", respuesta);


    }

    @DisplayName("Pokemon tipo fuego ataca otro pokemon dependiendo su tipo")
    @Test
    public void damage() {
        Pokemon Charmander = new Pokemon(1, new Fire(), "Charmander", 55);
        Pokemon Squirtle = new Pokemon(1, new Water(), "Squirtle", 25);
        Pokemon Pikachu = new Pokemon(1, new Electric(), "Pikachu", 25);
        Pokemon Bulbasaur = new Pokemon(1, new Plant(), "Bulbasaur", 25);
        Pokemon Onix = new Pokemon(1, new Stone(), "Onix", 25);

        float damegeRespuesta = Charmander.atacar(Squirtle);


        assertEquals(55.0f, damegeRespuesta, 0.01f);


    }


    @DisplayName("Pokemon recibe daño al atacar otro pokemon")
    @Test
    public void autoDanio() {
        Pokemon Pikachu = new Pokemon(1, new Electric(), "Pikachu", 25);
        Pokemon Squirtle = new Pokemon(1, new Water(), "Squirtle", 25);


        float respuesta = Pikachu.autoDanio(Squirtle);

        assertEquals(0.05f * Pikachu.getPoder(), respuesta, 0.01f);
    }

    @DisplayName("Disminucion de vida al pokemon")
    @Test
    public void restarVida() {
        Pokemon Pikachu = new Pokemon(1, new Electric(), "Pikachu", 25);
        float cantidad = 20f;
        Pikachu.restarVida(cantidad);
        assertEquals(80, Pikachu.getVida(), 0.01f);
    }

    @DisplayName("Aumento de vida al pokemon post disminucion de la misma")
    @Test
    public void aumentarVida() {
        Pokemon Pikachu = new Pokemon(1, new Electric(), "Pikachu", 25);

        float cantidad1 = 20f;
        float cantidad2 = 40f;

        Pikachu.restarVida(cantidad2);
        Pikachu.aumentarVida(cantidad1);
        assertEquals(80, Pikachu.getVida(), 0.01f);
    }


}
