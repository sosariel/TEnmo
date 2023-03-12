package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


public class TransferController {

    private TransferDao transferDao;

    public TransferController (TransferDao transferDao){
        this.transferDao = transferDao;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "accounttransfer/{id}", method = RequestMethod.GET)
    public List<Transfer> listTransfer(@PathVariable int id){
        return transferDao.getUserTransfers(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferByTransferId(@PathVariable int transferId) {
        return transferDao.getTransferById(transferId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "sendmoneyto/{recieverId}", method = RequestMethod.PUT)
    public void sendMoney(@PathVariable int accountTo, @Valid @PathVariable BigDecimal amount){
        transferDao.sendMoney(accountTo, amount);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "requestmoneyfrom/{senderId}", method = RequestMethod.GET)
    public void requestMoney(@PathVariable int accountFrom, @Valid @PathVariable BigDecimal amount){
        transferDao.requestMoney(accountFrom, amount);
    }

}
