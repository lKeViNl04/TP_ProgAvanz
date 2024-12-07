import ar.edu.davinci.Backend.DAO.UserImplMysql;
import ar.edu.davinci.Backend.Excepion.InvalidCredentialsException;
import ar.edu.davinci.Backend.Excepion.UserNotFoundException;
import ar.edu.davinci.Backend.Trainer.Gender;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

public class UserImplMysqlTest {
    UserImplMysql usersql;
    User user;
    Trainer trainer;

    @BeforeEach
    public void setUp() {
        usersql = new UserImplMysql();
        user = new User("nico", "messi", "nico@gmail.com", "messi19", "messi123");
        Trainer ash = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);

    }

    @DisplayName("Guardar usuario en la base de datos")
    @Test
    public void saveUser() {
        User resultado = usersql.saveUser(user);
        assertEquals(user, resultado);
    }

    @DisplayName("Validacion de usuarios a traves del username y contraseña")
    @Test
    public void validateUser() throws InvalidCredentialsException {
        usersql.saveUser(user);
        User resultado = usersql.validateUser("messi19", "messi123");
        assertEquals(user, resultado);
    }

    @DisplayName("Verificacion de nickname unico")
    @Test
    public void NickUnique() {
        boolean respuesta = usersql.NickUnique(user.getNickname());

        assertTrue(respuesta);
    }

    @DisplayName("Traer un usuario random")
    @Test
    public void getRandomUser() throws UserNotFoundException, InvalidCredentialsException {
        User horacio = new User("horacio", "messi", "nico@gmail.com", "kevin19", "messi123");
        User kevin = new User("kevin", "messi", "nico@gmail.com", "messi19", "messi123");
        usersql.saveUser(kevin);
        usersql.saveUser(horacio);
        User usuarioRandom = usersql.getRandomUser(1);

        assertEquals(kevin.getNickname() ,usuarioRandom.getNickname());

    }




    @DisplayName("agregar y eliminar un usuario de la base de datos segun su id")
    @Test
    public void deleteUser() {
        usersql.saveUser(user);
        usersql.deleteUser(1);

        assertEquals(0,usersql.getAllUsers().size() );

    }

    @DisplayName("Obtener todos los usuarios de la base de datos")
    @Test
    public void getAllUsers(){
        usersql.saveUser(user);
        usersql.getAllUsers();
        assertEquals(1,usersql.getAllUsers().size());
    }



}
