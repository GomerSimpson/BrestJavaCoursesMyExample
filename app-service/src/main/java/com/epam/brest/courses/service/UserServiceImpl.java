package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final String USER_ID_IS_NULL_ERROR = "User's ID can't be null";
    private static final String USER_ID_IS_NOT_NULL_ERROR = "User's ID must be null";
    private static final String USER_ID_IS_LESS_LOW_BORDER_ERROR = "User's ID can't be equals or less one!";
    private static final String USER_REFERENCE_IS_NULL_ERROR =  "Reference is null!";
    private static final String USER_NAME_IS_EMPTY_OR_NULL_ERROR = "User's name can't be empty!";
    private static final String USER_LOGIN_IS_EMPTY_OR_NULL_ERROR = "User's login can't be empty!";
    private static final String LOGIN_IS_OCCUPIED_ERROR = "User with such login has already existed!";
    private static final String ADMIN_LOGIN = "admin";
    private static final String EMPTY_STRING = "";
    private static final Long LOW_BORDER_OF_ID = 1L;                //because i suppose admin has id 1L and we can't change his data
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        if(user == null){
            LOGGER.debug(USER_REFERENCE_IS_NULL_ERROR);
            throw new IllegalArgumentException(USER_REFERENCE_IS_NULL_ERROR);
        } else if(user.getUserId() != null){
            LOGGER.debug(USER_ID_IS_NOT_NULL_ERROR);
            throw new IllegalArgumentException(USER_ID_IS_NOT_NULL_ERROR);
        } else if(user.getUserName() == null || user.getUserName().equals(EMPTY_STRING)){
            LOGGER.debug(USER_NAME_IS_EMPTY_OR_NULL_ERROR);
            throw new IllegalArgumentException(USER_NAME_IS_EMPTY_OR_NULL_ERROR);
        } else if(user.getLogin() == null || user.getLogin().equals(EMPTY_STRING) || user.getLogin().equals(ADMIN_LOGIN)){
            LOGGER.debug(USER_LOGIN_IS_EMPTY_OR_NULL_ERROR);
            throw new IllegalArgumentException(USER_LOGIN_IS_EMPTY_OR_NULL_ERROR);
        }

        User existingUser = userDao.getUserByLogin(user.getLogin());

        if(existingUser != null ){
            LOGGER.debug(LOGIN_IS_OCCUPIED_ERROR);
            throw new IllegalArgumentException(LOGIN_IS_OCCUPIED_ERROR);
        }

        userDao.addUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(Long userId) {
        if(userId == null || userId <= LOW_BORDER_OF_ID){
            LOGGER.debug(USER_ID_IS_LESS_LOW_BORDER_ERROR);
            throw new IllegalArgumentException(USER_ID_IS_NULL_ERROR);
        }

        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        if(login == null || login.equals(EMPTY_STRING)){
            LOGGER.debug(USER_LOGIN_IS_EMPTY_OR_NULL_ERROR);
            throw new IllegalArgumentException(USER_LOGIN_IS_EMPTY_OR_NULL_ERROR);
        }

        return userDao.getUserByLogin(login);
    }

    @Override
    public List<User> getUsersByName(String userName) {

        if(userName == null || userName.equals(EMPTY_STRING)){
            LOGGER.debug(USER_NAME_IS_EMPTY_OR_NULL_ERROR);
            throw new IllegalArgumentException(USER_NAME_IS_EMPTY_OR_NULL_ERROR);
        }

        return userDao.getUsersByName(userName);
    }

    @Override
    public void updateUser(User user) {
        if(user == null){
            LOGGER.debug(USER_REFERENCE_IS_NULL_ERROR);
            throw new IllegalArgumentException(USER_REFERENCE_IS_NULL_ERROR);
        } else if(user.getUserId() == null){
            LOGGER.debug(USER_ID_IS_NULL_ERROR);
            throw new IllegalArgumentException(USER_ID_IS_NULL_ERROR);
        } else if(user.getUserId() <= LOW_BORDER_OF_ID){
            LOGGER.debug(USER_ID_IS_LESS_LOW_BORDER_ERROR);
            throw new IllegalArgumentException(USER_ID_IS_LESS_LOW_BORDER_ERROR);
        } else if(user.getUserName() == null || user.getUserName().equals(EMPTY_STRING)){
            LOGGER.debug(USER_NAME_IS_EMPTY_OR_NULL_ERROR);
            throw new IllegalArgumentException(USER_NAME_IS_EMPTY_OR_NULL_ERROR);
        } else if(user.getLogin() == null || user.getLogin().equals(EMPTY_STRING) || user.getLogin().equals(ADMIN_LOGIN)){
            LOGGER.debug(USER_LOGIN_IS_EMPTY_OR_NULL_ERROR);
            throw new IllegalArgumentException(USER_LOGIN_IS_EMPTY_OR_NULL_ERROR);
        }

        User existingUser = userDao.getUserByLogin(user.getLogin());

        if(existingUser != null ){
            LOGGER.debug(LOGIN_IS_OCCUPIED_ERROR);
            throw new IllegalArgumentException(LOGIN_IS_OCCUPIED_ERROR);
        }

        userDao.updateUser(user);
    }

    @Override
    public void removeUser(Long userId) {
        if(userId == null){
            throw new IllegalArgumentException(USER_ID_IS_NULL_ERROR);
        } else if(userId <= LOW_BORDER_OF_ID){
            throw new IllegalArgumentException(USER_ID_IS_LESS_LOW_BORDER_ERROR);
        }

        userDao.removeUser(userId);
    }
}