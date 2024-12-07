import ar.edu.davinci.Backend.DAO.UserImplMysql;
import ar.edu.davinci.Backend.Excepion.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserNotFoundExceptionTest {

    @DisplayName("No se encontro el pokemon")
    @Test
    void UserNotFoundExceptionTest()  throws UserNotFoundException {

        UserImplMysql userImplMysql = new UserImplMysql();

        assertThrows(UserNotFoundException.class, () -> userImplMysql.getRandomUser(2));
    }

}
