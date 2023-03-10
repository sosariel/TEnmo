package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.AccountBalance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

//NEW CLASS
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //RETRIEVES ACCOUNT BALANCE RECORD AND RETURNS IT AS OBJECT
    @Override
    public AccountBalance getAccountByUserId(int userId) {

        AccountBalance accountBalance = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            accountBalance = mapRowToAccount(results);
        }

        return accountBalance;
    }

    //MAPS EACH ROW TO ACCOUNTBALANCE OBJECT
    private AccountBalance mapRowToAccount(SqlRowSet results){
        return new AccountBalance(results.getBigDecimal("balance"), results.getLong("account_id"), results.getInt("user_id"));
    }
}
