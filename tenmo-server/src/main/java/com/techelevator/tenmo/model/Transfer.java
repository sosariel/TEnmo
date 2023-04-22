package com.techelevator.tenmo.model;

import java.math.BigDecimal;

//NEW CLASS
public class Transfer {

    private int transferId;
    private String type;
    private String description;
    private String accountFrom;
    private int accountTo;
    private BigDecimal amount;

    public Transfer() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String transferType) {
        this.type = type;
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
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", amount=" + amount +
                '}';
    }
}
