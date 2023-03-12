package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class UsersController {

    private UserDao userDao;

    public UsersController(UserDao userDao){
        this.userDao = userDao;
    }

    @RequestMapping(path = "users", method = RequestMethod.GET) {
        public List<User> getUsers () {
            return userDao.findAll();
        }
    }

    @RequestMapping(path = "username/{id}", method = RequestMethod.GET) {
        public getUsername(@PathVariable int account) {
            return userDao.getUserById(account);
        }
    }
}
