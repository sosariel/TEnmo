package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

//I want to make a class that has all the methods to get information for the Account. By doing this I can then call this service in the App class for Balance, Transfer, Pending etc. (?)



public class AccountInfoService {
    public static String API_BASE_URL = "http://localhost:8080";
    private RestTemplate restTemplate = new RestTemplate();

    //I want to make a method to get the balance
    public User getViewBalance(User viewBalance){ //we pass in the users id for the method.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(viewBalance, headers);

        User returnedBalance = null;
        try {
            returnedBalance = restTemplate.postForObject(API_BASE_URL, entity, User.class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedBalance;

    }

}
