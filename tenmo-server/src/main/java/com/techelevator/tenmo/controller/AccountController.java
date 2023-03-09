package com.techelevator.tenmo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

//NEW CLASS
@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/account")
public class AccountController {


    //NEW METHOD
 //   @RequestMapping( path = "/balance", method = RequestMethod.GET)

}
