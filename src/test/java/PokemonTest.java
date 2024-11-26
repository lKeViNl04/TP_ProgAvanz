import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Pokemon.TIPO.Agua;
import ar.edu.davinci.Backend.Pokemon.TIPO.Tipo;
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
