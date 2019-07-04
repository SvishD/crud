package app.dao;

import app.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public User get(long id);

    public List<User> getAllUsers();

    public void insertUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public void createTable();
}
