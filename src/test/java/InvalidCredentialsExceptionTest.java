import ar.edu.davinci.Backend.DAO.PokemonImplMysql;
import ar.edu.davinci.Backend.DAO.UserImplMysql;
import ar.edu.davinci.Backend.Excepion.InvalidCredentialsException;
import ar.edu.davinci.Backend.Excepion.PokemonNotFoundException;
import ar.edu.davinci.Frontend.UI.Login;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidCredentialsExceptionTest {


    @DisplayName("Ver lo que dice la exception")
    @Test
    public void InvalidCredentialsExceptionTest() throws InvalidCredentialsException {

        UserImplMysql userImplMysql = new UserImplMysql();

        assertThrows(InvalidCredentialsException.class,
                () -> userImplMysql.validateUser("hola", "mundo"));
    }



}
