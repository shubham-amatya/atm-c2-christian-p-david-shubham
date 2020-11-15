import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    private Integer userID;
    private String passWord;
    private Integer accountType;
    private String stringInput;
    private Integer numInput;
    private Integer preferredTransaction;
    private Double numInputDouble;
    private Account currentAcc;

    ATM atm = new ATM();


    public Console() {
    }

    Scanner mainMenu = new Scanner(System.in);


    public void welcomeInput() {
        boolean validFiveDig;

        do {
            System.out.println(" ** Welcome to the Zip Code Federal Credit Union ** \n" +
                    "Please enter your 5 Digit User ID ");
            userID = getNumInput();
            if (userID.toString().length() != 5) {
                validFiveDig = false;
                System.out.println("Please enter a valid 5 Digit User ID");
                userID = getNumInput();
            }else {
                validFiveDig = true;

                System.out.println("Please enter your password: ");
                passWord = getStringInput();
            }
        } while (!validFiveDig);
        User user1 = new User(userID, passWord);
        atm.addUser(user1);
        atm.currentUser = user1;
    }

    public Integer getNumInput(){
        boolean isNumber;

        do
    {
        if (mainMenu.hasNextInt()) {
            numInput = mainMenu.nextInt();
            isNumber = true;
        } else {
            System.out.println("Please enter a valid number : ");
            isNumber = false;
            mainMenu.next();
        }
    } while(!(isNumber));
            return numInput;

    }

    public Double getNumInputDouble(){
        boolean isNumber;

        do
        {
            if (mainMenu.hasNextInt()) {
                numInputDouble = mainMenu.nextDouble();
                isNumber = true;
            } else {
                System.out.println("Please enter a valid number : ");
                isNumber = false;
                mainMenu.next();
            }
        } while(!(isNumber));
        return numInputDouble;

    }




    public String getStringInput() {
        boolean isString;
        do {
            if (mainMenu.hasNext()) {
                stringInput = mainMenu.next();
                isString = true;
            } else {
                System.out.println("Incorrect password. Please enter a valid password");
                isString = false;
                mainMenu.next();
            }
        } while (!(isString));

        return stringInput;
    }

public String accTypeMenuOptions(){
       String menuOptions = "Press 1 for Checking\n" +
                "Press 2 for Saving and\n" +
                "Press 3 for Investment";

       return menuOptions;
    }

    public void accountTypeMenu() {

        boolean validAccType = false;


        System.out.println("Which account would you like to access today?\n+" + accTypeMenuOptions());


        do {
            if (mainMenu.hasNextInt()) {
                accountType = mainMenu.nextInt();
                if (accountType == 1 || accountType == 2 || accountType == 3) {
                    validAccType = true;
                } else {
                    System.out.println("Please enter a valid account type\n" +
                            accTypeMenuOptions());
                    validAccType = false;
                    System.out.println("Account invalid. Please enter a valid account type.+" +
                            accTypeMenuOptions());
                    mainMenu.next();
                }
            }
        }while (!(validAccType)) ;
        User currentUser = atm.getCurrentUser();
        ArrayList<Account> accts = currentUser.getAccounts();


            switch (accountType) {

                case 1:

                    for (Account a : accts){
                        if (a instanceof CheckingAccount){
                            currentAcc = a;
                        }
                    }
                    optionsMenu ();
                    break;
                case 2:

                    for (Account a : accts){
                        if (a instanceof SavingsAccount){
                            currentAcc = a;
                        }
                    }
                    optionsMenu ();

                    break;
                case 3:
                    for (Account a : accts){
                        if (a instanceof InvestmentAccount){
                            currentAcc = a;

                        }
                    }
                    optionsMenu ();
                    break;

            }
        }

            public void optionsMenu () {

                System.out.println("What would you like to do today? \n" +
                        "To withdraw from account:     Press 1\n" +
                        "To deposit to account:        Press 2\n" +
                        "To open a new account:        Press 3\n" +
                        "To close an account:          Press 4\n" +
                        "To print transaction history: Press 5\n" +
                        "To check balance:             Press 6\n ");


                   preferredTransaction = getNumInput();

                Double amountInput;
                amountInput = getNumInputDouble();
               switch (preferredTransaction){

                   case 1: currentAcc.withdraw (amountInput);
                           break;
                   case 2: currentAcc.deposit(amountInput);
                           break;
                   case 3: atm.getCurrentUser().createAccount();
                           break;
                   case 4: currentAcc.closeAccount();
                           break;

                   case 5: currentAcc.printTransactionHistory();
                            break;
                   case 6: currentAcc.getBalance();
                            break;


                   default: System.out.println("Please enter a number from the options menu");
                       getNumInput();
               }

            }
            

        }




