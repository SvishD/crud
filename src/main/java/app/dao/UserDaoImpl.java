package app.dao;


import app.model.User;
import app.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao{

    private Executor executor;

    public UserDaoImpl(Connection connection) {
        this.executor = new Executor(connection);
    }

    public User get(long id) {

        User user = null;
        try {
            user = executor.execQuery("select * from users where id=" + id, result -> {
                result.next();
                return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {

        List<User> users = null;

        try {
            users = executor.execQuery("select * from users", result -> {
               List<User> userList = new ArrayList<>();
               while(result.next()){
                    User user = new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
                    userList.add(user);
                }
               return userList;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }


    public void insertUser(User user) {
        try {
            executor.execUpdate("insert into users (user_name,login,password) values ('" + user.getName() + "','"+ user.getLogin() +"','"+ user.getPassword() +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user)  {
        try {
            executor.execUpdate("delete from users where id=" + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            executor.execUpdate("update users" +
                    " set user_name='"+ user.getName() +
                    "', login='"+ user.getLogin() +
                    "', password='"+ user.getPassword() +
                    "' where id="+ user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createTable() {
        try {
            executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), login varchar(256), password varchar(256), primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
