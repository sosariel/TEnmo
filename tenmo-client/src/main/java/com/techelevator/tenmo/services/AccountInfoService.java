package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.util.BasicLogger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



    public class AccountInfoService {

        private String API_BASE_URL;
        private RestTemplate restTemplate = new RestTemplate();
        private AuthenticatedUser currentUser;

        public AccountInfoService(String baseUrl, AuthenticatedUser currentUser) {
            this.API_BASE_URL = baseUrl;
            this.currentUser = currentUser;
        }

        //SENDS GET REQUEST TO RETRIEVE ACCOUNT BALANCE
        public BigDecimal getBalance(int accountId, String token){
            BigDecimal balance = null;
            System.out.println();
            try{
                ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + "account/balance/" + accountId, HttpMethod.GET,
                        makeAccEntity(token), Account.class);

                balance = response.getBody().getBalance();

                System.out.println("Current Balance:$" + balance);
            } catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            } return balance;
        }


        public List<User> getUsers() {
            User[] userArr = null;
            try{
                ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL + "users", HttpMethod.GET, makeUserEntity(), User[].class);
                userArr = response.getBody();
            } catch (RestClientResponseException | ResourceAccessException e){
                BasicLogger.log(e.getMessage());

            }
            List<User> userList = new ArrayList<>();
            for (User user: userArr){
                userList.add(user);
            }
            return userList;
        }

        public String getUserName(int accountId){
            String userName = "";
            String url = API_BASE_URL + "username/" + accountId;
            try{
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, makeStrEntity(), String.class);
                userName = response.getBody();
            } catch (RestClientResponseException | ResourceAccessException e){
                BasicLogger.log(e.getMessage());
            }
            return userName;
        }


        // SENDS GET REQUEST TO RETRIEVE ID OF ACCOUNT
        public int getAccountById(int userId, String token) {
            int id = 0;
            String url = API_BASE_URL + "accounts/" + userId;
            try{
                ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.GET, makeAccEntity(token), Account.class);
                id = response.getBody().getAccountId();
            }catch (RestClientResponseException | ResourceAccessException e){
                BasicLogger.log(e.getMessage());

            }
            return id;

        }



        private HttpEntity<String> makeStrEntity() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new HttpEntity<>(headers);
        }

        private HttpEntity<User> makeUserEntity() {
            User user = new User();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new HttpEntity<>(user, headers);
        }

        //CREATES HTTPENTITY OBJECT, CONTAINS ACCOUNT OBJECT, AN AUTHORIZATION TOKEN
        private HttpEntity<Account> makeAccEntity(String token){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);
            HttpEntity<Account> accEntity = new HttpEntity<>(new Account(), headers);
            return accEntity;
        }


    }


