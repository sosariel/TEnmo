package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcTransferDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Transfer method in DAO to create an ID to detail transfers as they appear.
    public Integer createTransfer(Transfer transfer) {
        String sql = "INSERT INTO public.transfer(\n" +
                "\ttransfer_type_id, transfer_status_id, account_from, account_to, amount)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getTransferType(), transfer.getTransferStatusId(),
                transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());

        return newId;
    }
/*
    public List<Transfer> viewTransfersById(Transfer transfer) {
        String viewTransfersForUser = "SELECT transfer_id, transfer_type_desc, transfer_status_desc, username, account_to, amount\n" +
                "FROM transfer JOIN transfer_type ON transfer_type_id = transfer_type_id\n" +
                "JOIN transfer_status ON transfer_status_id = transfer_status_id\n" +
                "JOIN account ON account_id = account_from\n" +
                "JOIN tenmo_user ON user_id = a.user_id\n" +
                "WHERE account_to = ? OR account_from = ?;";

        SqlRowSet rowSql = jdbcTemplate.queryForRowSet(viewTransfersForUser, transfer);
        while (rowSql.next()) {
            viewTransfersForUser.add(mapRowToTransfer(rowSql));
        }

        return viewTransfersForUser;
    }
*/

//    public void sendMoney(int accountTo, int accountFrom, BigDecimal amount){
//
//        String sendMoneySql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount\n" +
//                "\tFROM public.transfer;";
//    }


    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rowSet.getInt("transfer_id"));
        transfer.setTransferType(rowSet.getInt("transfer_type"));
        transfer.setTransferStatusId(rowSet.getInt("transfer_status_id"));
        transfer.setAccountFrom(rowSet.getInt("account_from"));
        transfer.setAccountTo(rowSet.getInt("account_to"));
        transfer.setAmount(rowSet.getBigDecimal("amount"));
        return transfer;
    }


}
