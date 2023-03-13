package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountInfoService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AuthenticatedUser currentUser;
    private final AccountInfoService accountInfoService = new AccountInfoService(API_BASE_URL, currentUser);
    private final TransferService transferService = new TransferService(API_BASE_URL);

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
        String token = currentUser.getToken();
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        AccountInfoService accountService = new AccountInfoService(API_BASE_URL, currentUser);
        try {
            accountService.getBalance();
        } catch (NullPointerException e) {
            System.out.println("You got nothing");

        }
    }
	private void viewTransferHistory() {
        int transferId = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
        if (transferId == 0) {
            return;
        }
    }

	private void viewPendingRequests() {
		// TODO Auto-generated method stub

	}

	private void sendBucks() {
        List<User> users = accountInfoService.getUsers();
        for(User user : users){
            if(!Objects.equals(user.getId(), currentUser.getUser().getId())){
                System.out.println("ID: " + user.getId() + " | " + user.getUsername());
            }
        }
        int userIdSelection = consoleService.promptForInt("Please enter the recipient id: ");
        viewCurrentBalance();
        BigDecimal amountToSend = consoleService.promptForBigDecimal("Please enter the amount to send: ");
        String token = currentUser.getToken();
        BigDecimal currentBalance = accountInfoService.getBalance();
        if (amountToSend.compareTo(currentBalance) == 1){
            System.out.println("Not enough funds for transfer");
        } else if (amountToSend.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("You can't have zero or negative money");
        } else {
            transferService.sendMoney(accountInfoService.findUserAccountByid(userIdSelection), accountInfoService.getAccountById(currentUser.getUser().getId(), currentUser.getToken()), amountToSend, currentUser.getToken());
            System.out.println("Transfer confirmed.");
            viewCurrentBalance();
        }
	}

	private void requestBucks() {
		// TODO Auto-generated method stub

	}

}
