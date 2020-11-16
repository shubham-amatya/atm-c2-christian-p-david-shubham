

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
// features left to add:
// -can only have one of each account type per user
// - confirmation message after creating account
// - menu option to close account
// - refactoring

public class ATM {
    private ArrayList<User> users;
    private User currentUser;
    private Console console;
    private Boolean isOn;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);


    public ATM(){
        users = new ArrayList<User>();
        console = new Console();
        isOn = true;
    }

    public Console getConsole(){
        return console;
    }

    public void init(){
        console.println("Welcome to the ATM.  ");

        while (isOn){
            console.println("Options: \n" +
                    " | Create User " +
                    " | Create Account" +
                    " | Login" +
                    " | Logout" +
                    "\n| Create Account" +
                    " | Close Account" +
                    "\n| Deposit" +
                    " | Withdraw" +
                    " | Transfer" +
                    " | Transaction History" +
                    " | Check Balance" +
                    "\n| Exit");
            String command = console.getStringInput("What would you like to do?").trim();
            //the user is prompted for a String "command", which is sent to the handleCommand method.
            handleCommand(command);
        }

    }

    public void handleCommand(String command){
        boolean loggedIn = !(currentUser == null);
        //This will tell us whether the user is logged in or not.

        //Depending on the given command, the command handler will call the appropriate method.
        //For certain commands, the command handler will ensure that there is somebody logged in before proceeding.
        switch (command.toLowerCase()){
            case "create user":
                createUser();
                break;

            case "create account": //login only function
                if (loggedIn){
                createAccountHandler();}
                else {console.notLoggedInError();}
                break;

            case "logout":
                if (loggedIn){ //login only function
                logout();}
                else {console.notLoggedInError();}
                break;

            case "login":
                login();
                break;

            case "withdraw":
                if (loggedIn){ //login only function
                withdrawHandler();}
                else {console.notLoggedInError();}
                break;

            case "deposit":
                if (loggedIn){ //login only function
                depositHandler();}
                else {console.notLoggedInError();}
                break;


            case "transfer":
                if (loggedIn){
                transferHandler();}
                else {console.notLoggedInError();}
                break;

            case "transaction history":
                if (loggedIn){
                transactionHistoryHandler();}
                else {console.notLoggedInError();}
                break;

            case "check balance":
                if (loggedIn){
                checkBalanceHandler();}
                else {console.notLoggedInError();}
                break;


            case "close account":
                if (loggedIn){
                    closeAccountHandler();}
                else {console.notLoggedInError();}
                break;

            case "exit":
                isOn = false;
                break;

            default:
                console.println("You have selected an invalid command. Please select a command from the given list.");
                break;




        }

        }



    private void createUser(){
        String name = console.getStringInput("Please enter a username.");
        String password = console.getStringInput("Please enter a password.");
        User newUser = new User(name, password);
        users.add(newUser);
        currentUser = newUser;
        console.println("New User created. You are now logged in as " + name);
    }

    private void logout(){
        currentUser = null;
        // with no currentUser, we won't be able to access any of the functions that require
        // somebody to be logged in until we log in again.
        // (deposit, withdraw, etc.)
        console.println("You are now logged out.");
    }

    private void login(){
        String username = console.getStringInput("Please enter username.");

        User attemptedUser = null; //this is the user we are trying to gain access to.
        for (User u : users) {
            if (u.getName().equals(username)){
                //if we find the desired user in the users ArrayList, store it and break out of this loop.
                attemptedUser = u;
                break;
            }
         }

        if (attemptedUser != null) { // in other words, if a user with that name exists in the ArrayList...
            String password = console.getStringInput("Please enter password");
            if (attemptedUser.authenticate(password)) { // authenticate returns true if the passwords match
                currentUser = attemptedUser;
                //currentUser is now the user that we were trying to access and we are logged in.
                console.println("Login successful. You are now logged in as " + currentUser.getName());
            } else {
                        console.println("Invalid password");
            }
        }
        else{ //If we got here, then the user we were trying to access does not exist.
            console.println("User not found.");
        }

    }

    private String accountSelectHandler(String message) { // this is the code responsible for selecting an account
        boolean validInput = false;
        String acctType = "";
        while (!validInput) { // this loop will repeat until valid input is offered
            console.println("Accounts available:");
            for (Account a : currentUser.getAccounts()) {
                console.println(a.getClass().getTypeName());
            }  // this gives the operator a list of the available accounts for the logged in user
            String acct = console.getStringInput(message);

            switch (acct.toLowerCase()) { // we switch to lowercase just in case the operator types some uppercase letters
                case "checking account":
                    acctType = "checkingaccount"; // we will use "acctType" to look for the appropriate account
                    validInput = true; // we were given valid input, so we want to set validInput to true, breaking the loop
                    break;
                case "savings account":
                    acctType = "savingsaccount";
                    validInput = true;
                    break;
                case "investment account":
                    acctType = "investmentaccount";
                    validInput = true;
                    break;
                default:
                    console.println("You have entered an invalid account type.");
            }
        }
        return acctType;
    }

    private void withdrawHandler() {
        String acctType = accountSelectHandler("Which account would you like to withdraw from?"
                +"\n Please enter 'checking account', 'savings account', or 'investment account'");
            for (Account a : currentUser.getAccounts()) {// search through the current user's accounts.
                    String currentType = a.getClass().getTypeName().toLowerCase(); // this is a string representation of a's class (CheckingAccount, SavingsAccount, or InvestmentAccount);
                if (currentType.equals(acctType.toLowerCase())) { // if the desired account is found...
                    Double amt = console.getDoubleInput("How much would you like to withdraw?");
                    if (amt > a.getBalance()) {
                        console.println("Withdrawal failed. Insufficient funds.");
                    } else {
                        a.withdraw(amt);
                        console.println("Withdrawal successful. Current balance: " + a.getBalance());
                    }
                    return;
                }

            }
        console.println("This user does not have a " + acctType + ".");
    }


    private void depositHandler() {
        String acctType = accountSelectHandler("Which account would you like to deposit to?"
                +"\n Please enter 'checking account', 'savings account', or 'investment account'");
        for (Account a : currentUser.getAccounts()) {
            String currentType = a.getClass().getTypeName().toLowerCase(); // this is a string representation of a's class (CheckingAccount, SavingsAccount, or InvestmentAccount);
            if (currentType.equals(acctType.toLowerCase())) {
                Double amt = console.getDoubleInput("How much would you like to deposit?");
                    a.deposit(amt);
                    console.println("Deposit successful. Current balance: " + a.getBalance());
                    return;
                }
            }
            console.println("This user does not have a " + acctType + ".");
        }


    private void createAccountHandler() {
        Boolean validInput = false;
        while (!validInput) {
            String acctType = console.getStringInput("What type of account would you like to create?"
            +"\n Please enter 'checking account', 'savings account', or 'investment account'");
            switch (acctType.toLowerCase()) {
                case "checking account":
                    currentUser.createAccount("checking account");
                    console.println("New " + acctType.toLowerCase() + " created.");
                    validInput = true;
                    break;

                case "savings account":
                    currentUser.createAccount("savings account");
                    console.println("New " + acctType.toLowerCase() + " created.");
                    validInput = true;
                    break;

                case "investment account":
                    currentUser.createAccount("investment account");
                    console.println("New " + acctType.toLowerCase() + " created.");
                    validInput = true;
                    break;

                default:
                    console.println("You have selected an invalid account type.");

            }
        }

    }


    private void transferHandler() {
        String acctType1 = "";
        String acctType2 = "";
        //get the first account
        acctType1 = accountSelectHandler("Which account would you like to transfer from?");
        for (Account a : currentUser.getAccounts()) {
            if (a.getClass().getTypeName().toLowerCase().equals(acctType1.toLowerCase())) {
                //get the second account
                acctType2 = accountSelectHandler("Which account would you like to transfer to?");
                for (Account b : currentUser.getAccounts()) {
                    if (b.getClass().getTypeName().toLowerCase().equals(acctType2.toLowerCase())) {
                        Double amt = console.getDoubleInput("How much would you like to transfer?");
                        Boolean result = a.transfer(b, amt);
                        if (result) {
                            console.println("Transfer successful.");
                        } else {
                            console.println("Transfer unsuccessful. Insufficient funds.");
                        }
                        return;
                    }
                }
                console.println("This user does not have a " + acctType2);
                return;
            }
            console.println("This user does not have a " + acctType1 + ".");
        }
    }

    public void closeAccountHandler(){
        String acctType = accountSelectHandler("Which account would you like to close?");
        for (Account a : currentUser.getAccounts()) {
            String currentType = a.getClass().getTypeName().toLowerCase(); // this is a string representation of a's class (CheckingAccount, SavingsAccount, or InvestmentAccount);
            if (currentType.equals(acctType.toLowerCase())) {
                a.closeAccount();
                console.println("Account successfully removed.");
                return;
            }
        }
        console.println("This user does not have a " + acctType + ".");
    }


    public void transactionHistoryHandler() {
        String acctType1 = "";
        acctType1 = accountSelectHandler("Which account would you like to get the transaction history of?");
        for (Account a : currentUser.getAccounts()) {
            if (a.getClass().getTypeName().toLowerCase().equals(acctType1.toLowerCase())) {
                console.println(a.printTransactionHistory());
                return;
            }
        }
        console.println("This user does not have a " + acctType1 + ".");
    }

    public void checkBalanceHandler(){
        String acctType1 = "";
        acctType1 = accountSelectHandler("Which account would you like to check the balance of?");
        for (Account a : currentUser.getAccounts()) {
            if (a.getClass().getTypeName().toLowerCase().equals(acctType1.toLowerCase())) {
                String balanceFormatted = currencyFormat.format(a.getBalance());
                console.println("Current balance: " + balanceFormatted);
                return;
            }
        }
        console.println("This user does not have a " + acctType1 + ".");
    }



}

