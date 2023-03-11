package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.controller.TransferController;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


public interface TransferDao {

    public List<Transfer> getUserTransfers(int accountId);

    public Transfer getTransferById(int transferId);

    public void sendMoney(int accountTo, int accountFrom, BigDecimal amount);
}
