package com.techelevator.tenmo.model;

import java.math.BigDecimal;

//NEW CLASS
public class Transfer {

    private Long transferId;
    private String transferTypeId;
    private String transferStatusId;
    private User accountFrom;
    private User accountTo;
    private BigDecimal amount;

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public String getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(String transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public String getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(String transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public User getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(User accountFrom) {
        this.accountFrom = accountFrom;
    }

    public User getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(User accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
