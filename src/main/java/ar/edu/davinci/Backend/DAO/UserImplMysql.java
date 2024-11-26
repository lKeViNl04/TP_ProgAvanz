package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.User.User;

import java.sql.*;
import java.util.List;

public class UserImplMysql implements UserDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Pokemon";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "Usuario";


    public UserImplMysql () {
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
    public User saveUser(User user) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(tipo, especie, poder) VALUES (?, ?, ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.setString(2, user.getApellido());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getNickname());
            preparedStatement.setInt(5, user.getTelefono());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //person.setId(????)
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }

    @Override
    public User validateUser(String username, String password) {
        User user = null;
        return user;
    }
}
