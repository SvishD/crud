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

    public User get(long id) throws UserException {
        try {
            return dao.get(id);
        } catch (Exception e) {
            throw new UserException(e);
        }
    }

    public List<User> getAllUsers() throws UserException {
        try {
            return dao.getAllUsers();
        } catch (Exception e) {
            throw new UserException(e);
        }
    }

    public void deleteUser(User user) throws UserException {
        try {
            dao.deleteUser(user);
        } catch (Exception e) {
            throw new UserException(e);
        }
    }


    public void addUser(User user) throws UserException {
        try {
            if(createNewTable) {dao.createTable();}
            dao.insertUser(user);
        } catch (Exception e) {
            throw new UserException(e);
        }
    }


    public void updateUser(User user) throws UserException {
        try {
            dao.updateUser(user);
        } catch (Exception e) {
            throw new UserException(e);
        }
    }



}
