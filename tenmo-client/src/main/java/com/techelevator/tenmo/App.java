package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AccountInfoService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService();
    //ADDED OBJECTS
    private final AccountInfoService accountInfoService = new AccountInfoService(API_BASE_URL);
    //Ray-Added
    private final Transfer transfer = new Transfer();

    private AuthenticatedUser currentUser;

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
///////////////////////////////////////////////////////////////////////////////////////////////
    private void handleLogin() { //copying 16 Securing API Lecture handleLogin in App class
        String username = consoleService.promptForString("Username: ");
        String password = consoleService.promptForString("Password: ");
        String token = authenticationService.login(username, password);
        if (token != null) {
            authenticationService.sethAuthToken(token);
        } else {
            consoleService.printErrorMessage();
        }
////////////////////////////////////////////////////////////////////////////////////////////////
//        UserCredentials credentials = consoleService.promptForCredentials();
//        currentUser = authenticationService.login(credentials);
//        if (currentUser == null) {
//            consoleService.printErrorMessage();
//        }
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

//NEW METHOD
	private void viewCurrentBalance() {
BigDecimal balance = accountInfoService.getBalance();
if (balance != null) {
    System.out.println("Your current account balance is: " + balance);
} else {
    consoleService.printErrorMessage();
}


		
	}

	private void viewTransferHistory() { //ray-im guessing at this point
        int transferId = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");
        if (transferId == 0) {
            return;
        }
        Transfer transfer = authenticationService.getTransferId();
        if (transfer == null) {
            System.out.println("Transfer not found.");
            return;
        }
        System.out.println("--------------------------------------------");
        System.out.println("Transfer Details");
        System.out.println("--------------------------------------------");
        System.out.println("Id: " + transfer.getTransferId());
        System.out.println("From: " + transfer.getAccountFrom());
        System.out.println("To: " + transfer.getAccountTo());
        System.out.println("Type: " + transfer.getTransferTypeId());
        System.out.println("Status: " + transfer.getTransferStatusId());
        System.out.println("Amount: $" + transfer.getAmount());
    }


	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub
		
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
