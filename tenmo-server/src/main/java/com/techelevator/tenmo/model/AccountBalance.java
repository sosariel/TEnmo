package com.techelevator.tenmo.model;
import java.math.BigDecimal;

public class AccountBalance {

    private BigDecimal balance;
    //NEW INSTANCE VARIABLES
    private Long accountId;
    private int userId;

    //NEW CONSTRUCTOR
    public AccountBalance(BigDecimal balance, Long accountId, int userId) {
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
