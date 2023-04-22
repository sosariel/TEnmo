package com.techelevator.tenmo.model;
import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;
    private int accountId;
    private int userId;

    public Account() {

    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public Account(BigDecimal balance, int accountId, int userId) {
        this.balance = balance;
        this.accountId = accountId;
        this.userId = userId;
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }
}
