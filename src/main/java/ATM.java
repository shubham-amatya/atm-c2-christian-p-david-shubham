import java.util.ArrayList;

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
        console.print("Welcome to the ATM.");

        while (isOn){
            String command = console.getStringInput("What would you like to do?");
            handleCommand(command);
        }

    }

    public void handleCommand(String command){
        switch (command.toLowerCase()){
            case "create user":
                createUser();
                break;

            case "create account":
                createAccountHandler();
                break;

            case "logout":
                logout();
                break;

            case "login":
                login();
                break;

            case "withdraw":
                withdrawHandler();
                break;

                /*
            case "deposit":
                depositHandler();
                break;

            case "transfer":
                transferHandler();
                break;

            case "transaction history":
                printTransactionHistory();

            case "check balance":
                checkBalanceHandler();
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
        console.println("You are now logged out.");
    }

    private void login(){
        String username = console.getStringInput("Please enter username.");
        User attemptedUser = null;

        for (User u : users) {
            System.out.println(u.getName());
            if (u.getName().equals(username)){
                attemptedUser = u;
                break;
            }
         }

        if (attemptedUser != null) {
            String password = console.getStringInput("Please enter password");
            if (attemptedUser.authenticate(password)) {
                currentUser = attemptedUser;
                console.println("Login successful. You are now logged in as " + currentUser.getName());
            } else {
                        console.println("Invalid password");
            }
        }
        else{
            console.println("User not found.");
        }

    }

    private void withdrawHandler() {
        String acct = console.getStringInput("Which account would you like to withdraw from?");
        for (Account a : currentUser.getAccounts()) {
            System.out.println(a.getClass().toString());
        }
        switch (acct.toLowerCase()) {

            case "checking account":
                for (Account a : currentUser.getAccounts()) {
                    if (a instanceof CheckingAccount) {
                        Double amt = console.getDoubleInput("How much would you like to withdraw?");
                        if (amt > a.getBalance()) {
                            console.println("Withdrawal failed. Insufficient funds.");
                            break;
                        } else {
                            a.withdraw(amt);
                            console.println("Withdrawal successful. Current balance: " + a.getBalance());
                            break;
                        }
                    }
                    console.println("This user does not have a " + acct + ".");
                }
                break;

            case "savings account":
                for (Account a : currentUser.getAccounts()) {
                    if (a instanceof SavingsAccount) {
                        Double amt = console.getDoubleInput("How much would you like to withdraw?");
                        if (amt > a.getBalance()) {
                            console.println("Withdrawal failed. Insufficient funds.");
                            break;
                        } else {
                            a.withdraw(amt);
                            console.println("Withdrawal successful. Current balance: " + a.getBalance());
                            break;
                        }
                    }
                    console.println("This user does not have a " + acct + ".");
                }
                break;

            case "Investment account":
                for (Account a : currentUser.getAccounts()) {
                    if (a instanceof InvestmentAccount) {
                        Double amt = console.getDoubleInput("How much would you like to withdraw?");
                        if (amt > a.getBalance()) {
                            console.println("Withdrawal failed. Insufficient funds.");
                            break;
                        } else {
                            a.withdraw(amt);
                            console.println("Withdrawal successful. Current balance: " + a.getBalance());
                            break;
                        }
                    }
                    console.println("This user does not have a " + acct + ".");
                }
                break;

            default:
                console.println("You have entered an invalid account type.");
        }

    }

    private void createAccountHandler() {
        Boolean validInput = false;
        while (!validInput) {
            String acctType = console.getStringInput("What type of account would you like to create?");
            switch (acctType.toLowerCase()) {
                case "checking":
                    currentUser.createAccount("checking account");
                    validInput = true;
                    break;

                case "savings":
                    currentUser.createAccount("savings account");
                    validInput = true;
                    break;

                case "investment":
                    currentUser.createAccount("investment account");
                    validInput = true;
                    break;

                default:
                    console.println("You have selected an invalid account type.");

            }
        }
    }





}
