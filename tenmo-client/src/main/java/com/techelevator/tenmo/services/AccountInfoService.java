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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//I want to make a class that has all the methods to get information for the Account. By doing this I can then call this service in the App class for Balance, Transfer, Pending etc. (?)

    public class AccountInfoService {

        private String API_BASE_URL;
        private RestTemplate restTemplate = new RestTemplate();
        //NEW INSTANCE VARIABLES
        private AuthenticatedUser currentUser;

        //NEW CONSTRUCTOR
        public AccountInfoService(String baseUrl, AuthenticatedUser currentUser) {
            this.API_BASE_URL = baseUrl;
            this.currentUser = currentUser;
        }

        //NEW METHOD TO GET BALANCE
        public BigDecimal getBalance(){
            BigDecimal balance = new BigDecimal(0);
            System.out.println();
            try{
                ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + "account/balance/1001", HttpMethod.GET,
                        makeAuthEntity(), Account.class);

                balance = response.getBody().getBalance();

                System.out.println("Current Balance:$" + balance);
            } catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            } return balance;
        }

        //NEW METHOD TO GET USERS
        public List<User> getUsers() {
            try{
                ResponseEntity<User[]> response = restTemplate.getForEntity(API_BASE_URL + "users", User[].class);
                return Arrays.asList(response.getBody());
            } catch (RestClientResponseException | ResourceAccessException e){
                BasicLogger.log(e.getMessage());
                return Collections.emptyList();
            }
        }
        //NEW METHOD TO GET ACCOUNTS BY USER ID
        public int findUserAccountByid(int userId){
            int id = 0;
            String url = API_BASE_URL + "useraccount/";
            try{
                ResponseEntity<Integer> response = restTemplate.exchange(url + userId, HttpMethod.GET, makeAuthEntity(), Integer.class);
                id = response.getBody();
            }catch (RestClientResponseException | ResourceAccessException e){
                BasicLogger.log(e.getMessage());
            }
            return id;
        }

        public int getAccountById(int userId, String token){
            int id = 0;
            String url = API_BASE_URL + "accounts/";
            try{
                ResponseEntity<Account> response = restTemplate.exchange(url + userId, HttpMethod.GET, makeAuthEntity(), Account.class);
                id = response.getBody().getAccountId();
            }catch (RestClientResponseException | ResourceAccessException e){
                BasicLogger.log(e.getMessage());
            }
            return id;

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
        private HttpEntity makeAuthEntity() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(currentUser.getToken());
            HttpEntity entity = new HttpEntity<>(headers);
            return entity;
        }
    }


