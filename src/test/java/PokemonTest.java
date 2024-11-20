import ar.edu.davinci.POKEMON.Pokemon;
import ar.edu.davinci.POKEMON.TIPO.Agua;
import ar.edu.davinci.POKEMON.TIPO.Tipo;
import ar.edu.davinci.POKEMON.TIPO.TipoFactory;
import org.junit.jupiter.api.Test;

public class PokemonTest {

    @Test
    public void testReturnagua() {

        Tipo agua = new Agua();
        Pokemon DEAGUA = new Pokemon(agua,"hola",55);

        System.out.println(DEAGUA.getTipo());

        System.out.println("hola");


    }
}
