package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.Excepion.InvalidCredentialsException;
import ar.edu.davinci.Backend.Excepion.UserNotFoundException;
import ar.edu.davinci.Backend.Trainer.Gender;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImplMysql implements UserDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/Pokemon";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "usuario";


    public UserImplMysql() {
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
    public  User saveUser(User user) {
        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(nombre, apellido, email, nick, contraseña) VALUES (?, ?, ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getNickname());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                }
            }


            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public User validateUser(String username, String password) throws InvalidCredentialsException {
        User User = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE nick = ? AND contraseña = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User = new User(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("email"),
                        resultSet.getString("nick"),
                        resultSet.getString("contraseña")
                );

            } else {
                throw new InvalidCredentialsException();
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return User;
    }

    @Override
    public boolean NickUnique(String nick) {
        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE nick = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, nick);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isUnique = !resultSet.next();


            resultSet.close();
            preparedStatement.close();
            connection.close();

            return isUnique;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getRandomUser(int excepcion) throws UserNotFoundException {

        User usuario = null;

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);


            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id_usuario != ? ORDER BY RAND() LIMIT 1;";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, excepcion);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new User(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("email"),
                        resultSet.getString("nick"),
                        "contraseña"
                );

            } else {
                throw new UserNotFoundException();
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;


    }

    @Override
    public void deleteUser(int userId) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "DELETE FROM " + TABLE_NAME + " WHERE id_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectQuery = "SELECT * FROM " + TABLE_NAME;

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User usuario = new User(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("email"),
                        resultSet.getString("nick"),
                        "contraseña"
                );
                users.add(usuario);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
