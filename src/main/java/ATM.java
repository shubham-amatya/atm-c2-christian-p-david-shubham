import java.util.ArrayList;
// features left to add:
// -can only have one of each account type per user
// -transfer
// -transaction history
// -check balance
public class ATM {
    ArrayList<User> users;
    User currentUser;
    Console console;
    Boolean isOn;

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
                    " | Transaction History" );
            String command = console.getStringInput("What would you like to do?");
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

                /*
            case "transfer":
                if (loggedIn){
                transferHandler();}
                else {console.notLoggedInError();}
                break;

            case "transaction history":
                if (loggedIn){
                printTransactionHistory();}
                else {console.notLoggedInError();}
                break;

            case "check balance":
                if (loggedIn){
                checkBalanceHandler();}
                else {console.notLoggedInError();}
                break;
                */




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

    private void withdrawHandler() {
        boolean validInput = false;
        String acctType = "";
        while (!validInput) { // this loop will repeat until valid input is offered
            console.println("Accounts available:");
            for (Account a : currentUser.getAccounts()) {
                console.println(a.getClass().getTypeName());
            }  // this gives the operator a list of the available accounts for the logged in user
            String acct = console.getStringInput("Which account would you like to withdraw from?"
                    +"\n Please enter 'checking account', 'savings account', or 'investment account'");

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
            for (Account a : currentUser.getAccounts()) {// search through the current user's accounts.
                    String currentType = a.getClass().getTypeName().toLowerCase(); // this is a string representation of a's class (CheckingAccount, SavingsAccount, or InvestmentAccount);
                if (currentType.equals(acctType.toLowerCase())) { // if the desired account is found...
                    Double amt = console.getDoubleInput("How much would you like to withdraw?");
                    if (amt > a.getBalance()) {
                        console.println("Withdrawal failed. Insufficient funds.");
                        return;
                    } else {
                        a.withdraw(amt);
                        console.println("Withdrawal successful. Current balance: " + a.getBalance());
                        return;
                    }
                }

            }
        console.println("This user does not have a " + acctType + ".");
        }


    private void depositHandler() {
        boolean validInput = false;
        String acctType = "";
        while (!validInput) {
            console.println("Accounts available:");
            for (Account a : currentUser.getAccounts()) {
                console.println(a.getClass().getTypeName());
            }
            String acct = console.getStringInput("Which account would you like to deposit to?"
                    +"\n Please enter 'checking account', 'savings account', or 'investment account'");

            switch (acct.toLowerCase()) {
                case "checking account":
                    acctType = "checkingaccount";
                    validInput = true;
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

        /*
    private void transferHandler() {
        boolean validInput = false;
        String acctType1 = "";
        while (!validInput) {
            console.println("Accounts available:");
            for (Account a : currentUser.getAccounts()) {
                console.println(a.getClass().getTypeName());
            }
            String acct = console.getStringInput("Which account would you like to transfer from?"
                    +"\n Please enter 'checking account', 'savings account', or 'investment account'");

            switch (acct.toLowerCase()) {
                case "checking account":
                    acctType1 = "checkingaccount";
                    validInput = true;
                    break;
                case "savings account":
                    acctType1 = "savingsaccount";
                    validInput = true;
                    break;
                case "investment account":
                    acctType1 = "investmentaccount";
                    validInput = true;
                    break;
                default:
                    console.println("You have entered an invalid account type.");
            }
        }
        for (Account a : currentUser.getAccounts()) {
            if (a.getClass().getTypeName().toLowerCase().equals(acctType1.toLowerCase())) {
                Double amt = console.getDoubleInput("How much would you like to deposit?");
                a.deposit(amt);
                console.println("Deposit successful. Current balance: " + a.getBalance());
                return;
            }
        }
        console.println("This user does not have a " + acctType + ".");
    }
    */




    private void createAccountHandler() {
        Boolean validInput = false;
        while (!validInput) {
            String acctType = console.getStringInput("What type of account would you like to create?"
            +"\n Please enter 'checking account', 'savings account', or 'investment account'");
            switch (acctType.toLowerCase()) {
                case "checking account":
                    currentUser.createAccount("checking account");
                    validInput = true;
                    break;

                case "savings account":
                    currentUser.createAccount("savings account");
                    validInput = true;
                    break;

                case "investment account":
                    currentUser.createAccount("investment account");
                    validInput = true;
                    break;

                default:
                    console.println("You have selected an invalid account type.");

            }
        }

    }






}

