package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

//NEW CLASS
@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //RETRIEVES ACCOUNT BALANCE RECORD AND RETURNS IT AS OBJECT
    @Override
    public Account getAccountByUserId(int userId) {

        Account accountBalance = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            accountBalance = mapRowToAccount(results);
        }

        return accountBalance;
    }

    @Override
    public void updateBalance(Account account) {
        String sql = "UPDATE account " +
                "SET balance = ?" +
                "WHERE account_id = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
    }


    public Account getBalance(int userId) {
        Account balance = null;
        String sql ="SELECT account_id, user_id, balance " +
                "FROM account " +
                "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            balance = mapRowToAccount(results);
        }
        return balance;
    }

    //MAPS EACH ROW TO ACCOUNTBALANCE OBJECT
    private Account mapRowToAccount(SqlRowSet results){
        return new Account(results.getBigDecimal("balance"), results.getLong("account_id"), results.getInt("user_id"));
    }
}
