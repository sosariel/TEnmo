package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;


@RestController
@RequestMapping("/account")


//@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    //NEW METHODS

    //GET REQUEST FOR BALANCE FROM USER
    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET)
    public Account getBalance(@PathVariable int id) {
        Account balance = accountDao.getBalance(id);
        return balance;
    }


    //TAKES CURRENT USER AND RETRIEVES USER'S ID FROM DATABASE
    private int getCurrentUserId(Principal principal){
        return userDao.findByUsername(principal.getName()).getId();
    }


}
