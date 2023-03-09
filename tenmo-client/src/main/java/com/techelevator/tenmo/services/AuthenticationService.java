package com.techelevator.tenmo.services;

import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;

public class AuthenticationService {

    private final String baseUrl = "http://localhost:8080/"; //ray-added this
    private final RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void sethAuthToken(String authToken) {
        this.authToken = authToken;
    }

    //    public AuthenticationService(String url) {
//        this.baseUrl = url;
//    }

    public String login(String username, String password) { //ray- lines 25 thru I am copying 16 Securing API lecture Authenicated User class
        UserCredentials userCredentials = new UserCredentials(username, password);
        userCredentials.setUsername(username);
        userCredentials.setPassword(password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserCredentials> entity = new HttpEntity<>(userCredentials, headers);
        String token = null;

        try {
            ResponseEntity<AuthenticatedUser> response = restTemplate.exchange(baseUrl + "login", HttpMethod.POST, entity, AuthenticatedUser.class);
            AuthenticatedUser body = response.getBody();
            if (body != null) {
                token = body.getToken(); //if we have wrong credentials we wont get body back
            }
        } catch (RestClientResponseException | ResourceAccessException e) { //ray - I dont know what this does
            BasicLogger.log(e.getMessage());
        }
        return token;
    }
//        HttpEntity<UserCredentials> entity = createCredentialsEntity(credentials);
//        AuthenticatedUser user = null;
//        try {
//            ResponseEntity<AuthenticatedUser> response =
//                    restTemplate.exchange(baseUrl + "login", HttpMethod.POST, entity, AuthenticatedUser.class);
//            user = response.getBody();
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        return user;

    public boolean register(UserCredentials credentials) {
        HttpEntity<UserCredentials> entity = createCredentialsEntity(credentials);
        boolean success = false;
        try {
            restTemplate.exchange(baseUrl + "register", HttpMethod.POST, entity, Void.class);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    private HttpEntity<UserCredentials> createCredentialsEntity(UserCredentials credentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(credentials, headers);
    }
}
