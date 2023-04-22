package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //RETURNS ACCOUNT OBJECT FROM ACCOUNT TABLE THAT CORRESPONDS WITH USER
    @Override
    public Account getAccountById(int userId) {

        Account account = null;
        String sql = "SELECT * FROM account WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()){
            account = mapRowToAccounts(results);
        }

        return account;
    }

    @Override
    public void updateBalance(Account account) {
        String sql = "UPDATE account " +
                "SET balance = ?" +
                "WHERE account_id = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
    }



    //RETRIEVES ACCOUNT BALANCE WITH QUERY
    @Override
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

    //MAPS EACH ROW TO ACCOUNT OBJECT
    private Account mapRowToAccount(SqlRowSet results){
        return new Account(results.getBigDecimal("balance"), results.getInt("account_id"), results.getInt("user_id"));
    }

    //MAPS ROW OF ACCOUNT TABLE TO OBJECT
    private Account mapRowToAccounts(SqlRowSet rs){
        Account accounts = new Account();
        accounts.setAccountId(rs.getInt("account_id"));
        accounts.setUserId(rs.getInt("user_id"));
        accounts.setBalance(rs.getBigDecimal("balance"));
        return accounts;
    }
}
