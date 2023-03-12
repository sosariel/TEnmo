package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransferService {

    private Transfer transfers;
    private String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService(String baseUrl) {
        this.API_BASE_URL = baseUrl;
    }

    public void sendMoney(int accountTo, int accountFrom, BigDecimal amount, String token) {
        String url = API_BASE_URL + "sendMoney/" + accountTo + "/" + amount;
        try {
            restTemplate.exchange(url, HttpMethod.POST, makeEntity(token), void.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }


    private HttpEntity makeEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
