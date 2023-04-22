package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransferService {


    private String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService(String baseUrl) {
        this.API_BASE_URL = baseUrl;
    }

    // SENDS PUT REQUEST TO TRANSFER MONEY FROM ONE ACCOUNT TO ANOTHER
    public void sendMoney(int accountTo, int accountFrom, BigDecimal amount, String token) {
        String url = API_BASE_URL + "sendMoney/" + accountTo + "/" + accountFrom + "/" + amount;
        Transfer transferRequest = new Transfer(accountFrom, amount);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, makeEntity(token, transferRequest), Void.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }

    public Transfer getTransferById(int transferId, String token) {
        String url = API_BASE_URL + "transfers/" + transferId;
        Transfer transfer = null;
        try{
            ResponseEntity<Transfer> response = restTemplate.exchange(url, HttpMethod.GET, makeTransferEntity(token), Transfer.class);
            transfer = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }

    public List<Transfer> getUserTransfers(int accountId, String token) {
        String url = API_BASE_URL + "userTransfers/" + accountId;
        List<Transfer> transferList = new ArrayList<>();
        try{
            ResponseEntity<Transfer[]> response = restTemplate.exchange(url, HttpMethod.GET, makeTransferEntity(token), Transfer[].class);
            Transfer[] transferArray = response.getBody();

            for(Transfer transfer: transferArray){
                transferList.add(transfer);
            }
            System.out.println("Found " + transferList.size() + "transfer for account ID " + accountId);
        } catch (RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return transferList;
    }


    private HttpEntity<Transfer> makeTransferEntity(String token) {
        Transfer transfer = new Transfer();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        return new HttpEntity<>(transfer, headers);
    }


    //CREATES HTTPENTITY OBJECT, CONTAINS TRANSFER OBJECT, AN AUTHORIZATION TOKEN
    private HttpEntity<Transfer> makeEntity(String token, Transfer transferRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<Transfer> entity = new HttpEntity<>(transferRequest, headers);
        return entity;
    }
}
