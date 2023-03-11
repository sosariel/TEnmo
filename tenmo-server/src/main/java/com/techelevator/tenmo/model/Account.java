package com.techelevator.tenmo.model;
import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;
    //NEW INSTANCE VARIABLES
    private Long accountId;
    private int userId;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //NEW CONSTRUCTOR
    public Account(BigDecimal balance, Long accountId, int userId) {
        this.balance = balance;
        this.accountId = accountId;
        this.userId = userId;
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }
}
