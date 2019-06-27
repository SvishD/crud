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

    public User get(long id) throws SQLException {


        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
        });
    }

    public List<User> getAllUsers() throws SQLException {

       return executor.execQuery("select * from users", result -> {
           List<User> users = new ArrayList<>();
           while(result.next()){
                User user = new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4));
                users.add(user);
            }
           return users;
        });

    }


    public void insertUser(String name, String login, String password) throws SQLException {
        executor.execUpdate("insert into users (user_name,login,password) values ('" + name + "','"+ login +"','"+ password +"')");
    }

    public void deleteUser(String id) throws SQLException {
        executor.execUpdate("delete from users where id=" + id);
    }

    public void updateUser(String id, String name, String login, String password) throws SQLException {
        executor.execUpdate("update users" +
                " set user_name='"+ name +
                "', login='"+ login +
                "', password='"+ password +
                "' where id="+ id);
    }


    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), login varchar(256), password varchar(256), primary key (id))");
    }


}
