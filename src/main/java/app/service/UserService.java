package app.service;

import app.model.User;

import java.util.List;

public interface UserService {


    public User getUser(long id) throws UserException;

    public List<User> getUsers() throws UserException;

    public void deleteUser(String id) throws UserException;

    public void addUser(String name, String login, String password) throws UserException;

    public void updateUser(String id, String name, String login, String password) throws UserException;


}
