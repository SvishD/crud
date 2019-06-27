package app.service;

import app.dao.UserDao;
import app.dao.UserDaoImpl;
import app.model.User;
import app.util.PropertyReader;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl implements UserService {
    private final Connection connection;
    private static UserService instance = new UserServiceImpl();
    private UserDao dao;
    private boolean createNewTable;


    private UserServiceImpl() {
        connection = getMysqlConnection();
        dao = new UserDaoImpl(connection);
        createNewTable = Boolean.parseBoolean(PropertyReader.get("createNewTable"));
    }

    public static UserService getInstance() {
        return instance;
    }

    public User getUser(long id) throws UserException {
        try {
            return dao.get(id);
        } catch (SQLException e) {
            throw new UserException(e);
        }
    }

    public List<User> getUsers() throws UserException {
        try {
            return dao.getAllUsers();
        } catch (SQLException e) {
            throw new UserException(e);
        }
    }

    public void deleteUser(String id) throws UserException {
        try {
            dao.deleteUser(id);
        } catch (SQLException e) {
            throw new UserException(e);
        }
    }


    public void addUser(String name, String login, String password) throws UserException {
        try {
            if(createNewTable) {dao.createTable();}
            dao.insertUser(name,login,password);
        } catch (SQLException e) {
            throw new UserException(e);
        }
    }


    public void updateUser(String id, String name, String login, String password) throws UserException {
        try {
            dao.updateUser(id,name,login,password);
        } catch (SQLException e) {
            throw new UserException(e);
        }
    }



    public static Connection getMysqlConnection() {
        try {

            StringBuilder url = new StringBuilder();

            url.append(PropertyReader.get("url")).
                    append(PropertyReader.get("dbname")).
                    append("?user=").
                    append(PropertyReader.get("username")).
                    append("&password=").
                    append(PropertyReader.get("pass")).
                    append("&serverTimezone=").
                    append(PropertyReader.get("serverTimezone"));

            System.out.println("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
