package app.service;

import app.dao.UserDao;
import app.dao.UserDaoImpl;
import app.exceptions.UserException;
import app.model.User;
import app.util.DBHelper;
import app.util.PropertyReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl implements UserService {
    private final Connection connection;
    private static UserService instance = new UserServiceImpl();
    private UserDao dao;
    private boolean createNewTable;


    private UserServiceImpl() {
        connection = DBHelper.getConnection();
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



}
