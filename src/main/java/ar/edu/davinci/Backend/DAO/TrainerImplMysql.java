package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.BattleStep.BattleStep;
import ar.edu.davinci.Backend.BattleStep.BattleTrainer;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Trainer.Gender;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.BattleStep.WildPokemonBattle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerImplMysql implements TrainerDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Pokemon";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "entrenador";

    public TrainerImplMysql() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255), age INT)";
            statement.executeUpdate(createTableQuery);
            statement.close();
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Trainer setTrainerToUser(Trainer trainer, int userId) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(nombre, fecha_nacimiento, nacionalidad,genero,usuario_id_usuario) VALUES (?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, trainer.getName());
            preparedStatement.setDate(2, new java.sql.Date(trainer.getFechaNacimientoDate().getTime()));
            preparedStatement.setString(3, trainer.getNationality());
            preparedStatement.setString(4, trainer.getGender().toString());
            preparedStatement.setInt(5, userId);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    trainer.setTrainerId(generatedKeys.getInt(1));
                }
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainer;
    }

    @Override
    public List<Trainer> getTrainersByUserId(int userId) {
        List<Trainer> entrenadores = new ArrayList<>();
        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE usuario_id_usuario = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Trainer trainer = new Trainer(
                        resultSet.getInt("id_entrenador"),
                        resultSet.getString("nombre"),
                        new java.util.Date(resultSet.getDate("fecha_nacimiento").getTime()),
                        resultSet.getString("nacionalidad"),
                        Gender.valueOf(resultSet.getString("genero"))
                );
                entrenadores.add(trainer);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrenadores;
    }

    @Override
    public int getTrainerCountByUserId(int userId) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectQuery = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE usuario_id_usuario = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt(1);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public void deleteTrainer(int trainerid) {

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "DELETE FROM " + TABLE_NAME + " WHERE id_entrenador = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, trainerid);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectQuery = "SELECT * FROM " + TABLE_NAME;

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Trainer trainer = new Trainer(
                        resultSet.getInt("id_entrenador"),
                        resultSet.getString("nombre"),
                        new java.util.Date(resultSet.getDate("fecha_nacimiento").getTime()),
                        resultSet.getString("nacionalidad"),
                        Gender.valueOf(resultSet.getString("genero"))
                );
                trainers.add(trainer);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }
    @Override
    public List<BattleStep> battleTrainerPokemon(Trainer mytrainer, Trainer rival,PokemonDAO pokemonDAO) {

        BattleTrainer battleTrainer = new BattleTrainer(mytrainer, rival, pokemonDAO);
        List<BattleStep> battleinfo = battleTrainer.startBattle();

        return battleinfo;
    }


    @Override
    public List<BattleStep> battleWildPokemon(Trainer myTrainer, Pokemon pokemonWild, PokemonDAO pokemonDAO) {
        WildPokemonBattle wildBattle = new WildPokemonBattle(myTrainer, pokemonDAO);

        return wildBattle.battleWithWildPokemon(pokemonWild);
    }

}
