package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Transfer> getUserTransfers(int accountId) {
        return null;
    }

    @Override
    public Transfer getTransferById(int transferId) {
        return null;
    }

    //SQL UPDATES ACCOUNT BALANCES AND INSERTS NEW ROW INTO 'TRANSFER' TABLE
    @Override
    public void sendMoney(int accountTo, int accountFrom, BigDecimal amount) {
        String sql = "BEGIN TRANSACTION; " +
                "UPDATE account SET balance = balance + ? WHERE account_id = ?; " +
                "UPDATE account SET balance = balance - ? WHERE account_id = ?; " +
                "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (2, 2, ?, ?, ?); " +
                "COMMIT;";
        jdbcTemplate.update(sql, amount, accountFrom, amount, accountTo, accountFrom, accountTo, amount);

    }

    @Override
    public void requestMoney(int accountTo, BigDecimal amount) {

    }



    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rowSet.getInt("transfer_id"));
        transfer.setType(rowSet.getString("type"));
        transfer.setDescription(rowSet.getString("description"));
        transfer.setAccountFrom(rowSet.getString("account_from"));
        transfer.setAccountTo(rowSet.getInt("account_to"));
        transfer.setAmount(rowSet.getBigDecimal("amount"));
        return transfer;
    }


}
