package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.Excepion.PokemonNotFoundException;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Pokemon.Type.Type;
import ar.edu.davinci.Backend.Pokemon.Type.TypeMapper;
import ar.edu.davinci.Backend.Trainer.Gender;
import ar.edu.davinci.Backend.Trainer.Trainer;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PokemonImplMysql implements PokemonDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Pokemon";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "pokemon";

    public PokemonImplMysql() {
        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255), age INT)";
            statement.executeUpdate(createTableQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pokemon setPokemonToTrainer(Pokemon pokemon, int trainerId) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(tipo, especie, poder,entrenador_id_entrenador) VALUES (?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, pokemon.getTipo().getNombre());
            preparedStatement.setString(2, pokemon.getEspecie());
            preparedStatement.setInt(3, pokemon.getPoder());
            preparedStatement.setInt(4, trainerId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokemon;
    }

    @Override
    public List<Pokemon> getPokemonsByTrainerId(int TrainerId) {
        List<Pokemon> pokemones = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectQuery =
                    "SELECT * FROM " + TABLE_NAME + " WHERE entrenador_id_entrenador = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, TrainerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pokemon pokemon = new Pokemon(
                        resultSet.getInt("id_pokemon"),
                        TypeMapper.getInstance().crearInstancia(resultSet.getString("tipo")),
                        resultSet.getString("especie"),
                        resultSet.getInt("vida"),
                        resultSet.getInt("energia"),
                        resultSet.getInt("poder")
                );
                pokemones.add(pokemon);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemones;

    }

    @Override
    public Pokemon getPokemonsWild() throws PokemonNotFoundException {
        Pokemon pokemon = null;

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);


            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE entrenador_id_entrenador IS NULL ORDER BY RAND() LIMIT 1;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pokemon = new Pokemon(
                        resultSet.getInt("id_pokemon"),
                        TypeMapper.getInstance().crearInstancia(resultSet.getString("tipo")),
                        resultSet.getString("especie"),
                        resultSet.getInt("poder")
                );
            } else {
                throw new PokemonNotFoundException();
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemon;

    }

    @Override
    public void updatePokemonVida(Pokemon pokemon) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "UPDATE " + TABLE_NAME + " SET vida = ? , energia = ? WHERE id_pokemon = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDouble(1, pokemon.getVida());
            preparedStatement.setDouble(2, pokemon.getEnergia());
            preparedStatement.setInt(3, pokemon.getPokemonid());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deletePokemon(int pokemonId) {

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "DELETE FROM " + TABLE_NAME + " WHERE id_pokemon = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, pokemonId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Pokemon> getAllPokemons() {
        List<Pokemon> pokemones = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectQuery = "SELECT * FROM " + TABLE_NAME;

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pokemon pokemon = new Pokemon(
                        resultSet.getInt("id_pokemon"),
                        TypeMapper.getInstance().crearInstancia(resultSet.getString("tipo")),
                        resultSet.getString("especie"),
                        resultSet.getInt("vida"),
                        resultSet.getInt("energia"),
                        resultSet.getInt("poder")
                );
                pokemones.add(pokemon);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemones;
    }


}

