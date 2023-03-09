package com.techelevator.tenmo.controller;

import javax.validation.Valid;

import com.techelevator.tenmo.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller to authenticate users.
 */
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping ("/account")
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserDao userDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseDto login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);

        User user = userDao.findByUsername(loginDto.getUsername());

        return new LoginResponseDto(jwt, user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDto newUser) {
        if (!userDao.create(newUser.getUsername(), newUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration failed.");
        }
    }
////////////////////////////////////////////////////////////////////////
 /*   //I am trying to allow the user to see their balance
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
// goes to account > id and from id we can retrieve the BODY of info which will contain the balance (maybe?)
    public BigDecimal getBalance(User id) {
        AccountBalance balance =
    }
*/
//////////////////////////////////////////////




    //4.1 I should be able to choose from a list of users to send TE Bucks to.
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> list(@RequestParam(defaultValue = "") String username) {
        return userDao.findAll(); //im referencing hw16securingAPI
    }

}