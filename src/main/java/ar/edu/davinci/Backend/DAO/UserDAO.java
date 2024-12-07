package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.Excepion.InvalidCredentialsException;
import ar.edu.davinci.Backend.Excepion.UserNotFoundException;
import ar.edu.davinci.Backend.User.User;

import java.util.List;


public interface UserDAO {
    public User saveUser(User user);

    public User validateUser(String username, String password) throws InvalidCredentialsException;

    public boolean NickUnique(String nick);

    public List<User> getAllUsers();

    public User getRandomUser(int excepcion) throws UserNotFoundException;

    public void deleteUser(int userId);
}
