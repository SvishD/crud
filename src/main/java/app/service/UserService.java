package app.service;

import app.exceptions.UserException;
import app.model.User;

import java.util.List;

public interface UserService {


    public User get(long id) throws UserException;

    public List<User> getAllUsers() throws UserException;

    public void addUser(User user) throws UserException;

    public void deleteUser(User user) throws UserException;

    public void updateUser(User user) throws UserException;


}
