package ar.edu.davinci.DAO;

import ar.edu.davinci.POKEMON.Pokemon;
import ar.edu.davinci.POKEMON.TIPO.Tipo;
import ar.edu.davinci.POKEMON.TIPO.TipoFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PokemonImplMysql implements PokemonDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Pokemon";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "pokemons";

    public PokemonImplMysql () {
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
    public Pokemon savePokemon(Pokemon pokemon) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(tipo, especie, poder) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, pokemon.getTipo().toString());
            preparedStatement.setString(2, pokemon.getEspecie());
            preparedStatement.setInt(3, pokemon.getPoder());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //person.setId(????)
        return pokemon;
    }





    @Override
    public List<Pokemon> getAllPokemon() {

        List<Pokemon> pokemons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_NAME)) {

            while (resultSet.next()) {
                String tipoStr = resultSet.getString("tipo");
                String especie = resultSet.getString("especie");
                int poder = resultSet.getInt("poder");



                Tipo tipo = TipoFactory.getInstance().crearInstancia( tipoStr );
                Pokemon pokemon = new Pokemon(tipo, especie, poder);
                pokemons.add(pokemon);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Pokemones guardados en el Array");
        return  pokemons;

    }
}

