package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.AccountBalance;

//NEW CLASS
public interface AccountDao {

    AccountBalance getAccountByUserId(int userId);
}
