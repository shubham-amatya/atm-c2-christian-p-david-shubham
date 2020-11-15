import java.util.ArrayList;

public class User {

    private String name;
    private String password;
    private ArrayList<Account> accounts;
    private boolean loggedIn;

    public User (String name, String password){
        this.name = name;
        this.password = password;
        accounts = new ArrayList<Account>();
        loggedIn = false;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public boolean authenticate(String password){
        if (password.equals(this.password)){
            return true;
        }
        return false;
    }

    public void createAccount(String accountType){
        switch (accountType.toLowerCase()) {
            case "checking account":
                accounts.add(new CheckingAccount(0.0));
                break;

            case "savings account":
                accounts.add(new SavingsAccount(0.0));
                break;

            case "investment account":
                accounts.add(new InvestmentAccount(0.0));
                break;
        }
    }

    public boolean getLoggedIn(){
        return loggedIn;
    }

}
