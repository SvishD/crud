package app.dao;

import app.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public User get(long id) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

    public void insertUser(String name, String login, String password) throws SQLException;

    public void deleteUser(String id) throws SQLException;

    public void updateUser(String id, String name, String login, String password) throws SQLException;

    public void createTable() throws SQLException;
}
