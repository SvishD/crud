package app.service;

import app.dao.UserDAO;
import app.model.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class DBService {
    private final Connection connection;
    private static DBService instance = new DBService();

    private DBService() {
        this.connection = getMysqlConnection();
    }

    public static DBService getInstance() {
        return instance;
    }

    public User getUser(long id) throws DBException {
        try {
            return (new UserDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<User> getUsers() throws DBException {
        try {
            return (new UserDAO(connection).getAllUsers());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void deleteUser(String id) throws DBException {
        try {
            new UserDAO(connection).deleteUser(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }



    public void addUser(String name, String login, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.createTable();
            dao.insertUser(name,login,password);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void updateUser(String id, String name, String login, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.updateUser(id,name,login,password);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }



    public static Connection getMysqlConnection() {
        try {

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").           //login
                    append("password=root&").       //password
                    append("serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
