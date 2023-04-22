package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

//NEW CLASS
public interface AccountDao {

    Account getAccountById(int userId);

    Account getBalance(int userId);

    void updateBalance(Account account);
}
