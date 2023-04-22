package com.techelevator.tenmo.model;

import java.math.BigDecimal;


public class Transfer {

    private int transferId;
    private String transferType;
    private String description;
    private int transferStatusId;
    private String accountFrom;
    private int accountTo;
    private BigDecimal amount;

    public Transfer() {

    }

    public Transfer(int accountFrom, BigDecimal amount) {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transfer(int transferId) {
        this.transferId = transferId;
    }

    public Transfer(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", type='" + transferType + '\'' +
                ", description='" + transferStatusId + '\'' +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", amount=" + amount +
                '}';
    }
}
