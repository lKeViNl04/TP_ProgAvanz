package ar.edu.davinci.Backend.DAO;

import ar.edu.davinci.Backend.User.User;

import java.util.List;

public interface UserDAO {
    public User saveUser(User user);
    public List<User> getAllUser();
    public User validateUser(String username, String password);
}
