package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

//I want to make a class that has all the methods to get information for the Account. By doing this I can then call this service in the App class for Balance, Transfer, Pending etc. (?)

    public class AccountInfoService {

        public static String API_BASE_URL = "http://localhost:8080";
        private RestTemplate restTemplate = new RestTemplate();
        //NEW INSTANCE VARIABLES
        public String baseUrl;
        private String authToken = null;

        //NEW CONSTRUCTOR
        public AccountInfoService(String baseUrl) {
            this.baseUrl = baseUrl + "account/";
        }

        //NEW METHOD TO GET BALANCE
        public BigDecimal getBalance(){
            BigDecimal balance = null;
            try{
                ResponseEntity<BigDecimal> response = restTemplate.exchange(baseUrl + "balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
                balance = response.getBody();
            } catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            } return balance;
        }

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

        //Register new username and password
/*
   public UserCredentials addUser(UserCredentials newUser){
       UserCredentials returnedUser = null;

   }
*/
        //Returns an HttpEntity with the `Authorization: Bearer:` header
        private HttpEntity<Void> makeAuthEntity() {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(authToken);
            return new HttpEntity<>(headers);
        }
    }


