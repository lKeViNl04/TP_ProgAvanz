
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Pokemon.Type.Electric;
import ar.edu.davinci.Backend.Trainer.Gender;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @DisplayName("Agregar una lista de entrenadores al usuario")
    @Test
    public void addEntrenadores() {

        List<Trainer> trainers = new ArrayList<>();

        User nico = new User(1, "juan", "nico", "gomez@sadasd.com", "Argentino", "contraseña");
        Trainer Pikachu = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        Trainer Pikachu2 = new Trainer(1, "nose", new Date(2024, 06, 8), "Argentino", Gender.Masculino);

        trainers.add(Pikachu);
        trainers.add(Pikachu2);

        nico.addEntrenadores(trainers);

        assertEquals(2, nico.getTrainers().size());

    }

    @DisplayName("Agregar un entrenador a la lista de entrenadores del usuario")
    @Test
    public void addEntrenador() {

        User nico = new User(1, "juan", "nico", "gomez@sadasd.com", "Argentino", "contraseña");
        Trainer Pikachu = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);

        nico.addEntrenador(Pikachu);

        assertEquals(1, nico.getTrainers().size());

    }


    @DisplayName("Mostrar la lista de entrenadores del usuario")
    @Test
    public void mostrartrainers() {
        User nico = new User(1, "juan", "nico", "gomez@sadasd.com", "Argentino", "contraseña");

        Trainer Pikachu = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        Trainer Pikachu2 = new Trainer(1, "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);

        nico.addEntrenador(Pikachu);
        nico.addEntrenador(Pikachu2);

        nico.mostrartrainers();


        assertTrue(nico.getTrainers().contains(Pikachu));

    }


}
