package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.Trainer.Entrenador;
import ar.edu.davinci.Backend.Trainer.Genero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorImplMysql implements EntrenadorDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Pokemon";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "entrenador";

    public EntrenadorImplMysql () {
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
    public Entrenador saveEntrenador(Entrenador entrenador) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(nombre, edad, nacionalidad,genero) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, entrenador.getNombre());
            preparedStatement.setInt(2, entrenador.getEdad());
            preparedStatement.setString(3, entrenador.getNacionalidad());
            preparedStatement.setString(4, entrenador.getGenero().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //person.setId(????)
        System.out.println("Entrenador guardado en la base de datos");
        return entrenador;
    }




    @Override
    public List<Entrenador> getAllEntrenador() {

        List<Entrenador> entrenadors = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(" SELECT * FROM " + TABLE_NAME)) {

            while (resultSet.next()) {
                String nombreStr = resultSet.getString("nombre");
                int edadInt = resultSet.getInt("edad");
                String nacionalidadStr = resultSet.getString("nacionalidad");
                String generoenum = resultSet.getString("genero");

                Entrenador entrenador = new Entrenador(nombreStr,edadInt,nacionalidadStr, Genero.valueOf(generoenum) );
                entrenadors.add(entrenador);

            }

            resultSet.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Entranadores guardados en el Array");
        return  entrenadors;

    }
}
