import ar.edu.davinci.Backend.BattleStep.BattleStep;
import ar.edu.davinci.Backend.BattleStep.BattleTrainer;
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
import ar.edu.davinci.Backend.BattleStep.WildPokemonBattle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainerImplMysqlTest {
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
        pokemon = new Pokemon(1,new Water(), "Squirtle", 55);
        pokemon2 = new Pokemon(2 ,new Fire(), "Charmander", 55);
        ash = new Trainer( "Ash", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        user = new User("nico", "messi", "nico@gmail.com", "messi19", "messi123");
    }


  @DisplayName("Guardar un entrenador en el usuario")
    @Test
    public void setTrainerToUser(){
      Trainer resultado = trainerMysql.setTrainerToUser(ash, 1);
      assertEquals(ash, resultado);
  }

  @DisplayName("traer todos los entrenadores")
    @Test
    public void getAllTrainers(){
        trainerMysql.setTrainerToUser(ash,1);
        assertEquals(1,trainerMysql.getAllTrainers().size());
  }

@DisplayName("Traer un entrenador segun el id del usuario")
    @Test
    public void getTrainersByUserId(){
    userMysql.saveUser(user);
    trainerMysql.setTrainerToUser(ash,1);
    List<Trainer> resultado = trainerMysql.getTrainersByUserId(1);
    assertEquals(ash.getName(),resultado.get(0).getName());
}

@DisplayName("Traer la cantidad de entrenadores del usuario")
    @Test
    public void getTrainerCountByUserId(){
    userMysql.saveUser(user);
    trainerMysql.setTrainerToUser(ash,1);
    int resultado = trainerMysql.getTrainerCountByUserId(1);
    assertEquals(1,resultado);
}
@DisplayName("Borrar un entrendador de la base de datos")
@Test
    public void deleteTrainer(){
        trainerMysql.setTrainerToUser(ash, 1);

        trainerMysql.deleteTrainer(1);
        assertEquals(0,trainerMysql.getAllTrainers().size());
    }
    @DisplayName("Donde transcurre la batalla entre entrenadores y luego te devuelve los pasos ")
    @Test
    public void battleTrainerPokemon(){

        User user1 = new User("juan", "ramirez", "juan@gmail.com", "juan19", "messi123");
        user1 = userMysql.saveUser(user1);
        Trainer rival = new Trainer( "user", new Date(2024, 06, 8), "Argentino", Gender.Masculino);
        rival = trainerMysql.setTrainerToUser(rival,user1.getUserId());
        user1.addEntrenador(rival);
        pokemonMysql.setPokemonToTrainer(pokemon, rival.getTrainerId());
        rival.capturePokemon(pokemon);


        user = userMysql.saveUser(user);
        ash = trainerMysql.setTrainerToUser(ash,user.getUserId());
        user.addEntrenador(ash);
        pokemonMysql.setPokemonToTrainer(pokemon2, ash.getTrainerId());
        ash.capturePokemon(pokemon2);

        BattleTrainer battleTrainer = new BattleTrainer(ash, rival, pokemonMysql);
        List<BattleStep> battleinfo = battleTrainer.startBattle();


        boolean estavacio = !battleinfo.isEmpty();
        assertTrue(estavacio);
    }


    @DisplayName("Donde transcurre la batalla contra el pokemon salvaje y luego te devuelve los pasos ")
    @Test
    public void battleWildPokemon() throws PokemonNotFoundException {


        user = userMysql.saveUser(user);
        ash = trainerMysql.setTrainerToUser(ash,user.getUserId());
        user.addEntrenador(ash);
        pokemonMysql.setPokemonToTrainer(pokemon2, ash.getTrainerId());
        ash.capturePokemon(pokemon2);

        Pokemon pokerandom = pokemonMysql.getPokemonsWild();

        WildPokemonBattle battleWildPokemon = new WildPokemonBattle(ash, pokemonMysql);
        List<BattleStep> battleinfo = battleWildPokemon.battleWithWildPokemon(pokerandom);

        boolean estavacio = battleinfo.isEmpty();
        assertTrue(estavacio);
    }


}
