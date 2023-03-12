package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;


public interface TransferDao {

    public List<Transfer> getUserTransfers(int accountId);

    public Transfer getTransferById(int transferId);

    public void sendMoney(int accountTo, BigDecimal amount);

    public void requestMoney(int accountTo, BigDecimal amount);


}
