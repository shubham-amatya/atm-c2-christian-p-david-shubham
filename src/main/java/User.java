

import java.util.ArrayList;
public class User {
    private String name;
    private String password;
    ArrayList <Account> Accounts = new ArrayList<Account>();
    private boolean loggedin;
    public User( String name, String password) {
        this.name = name;
        this.password = password;
        this.Accounts = new ArrayList<Account>();
    }
    public String getName(){
        return name;
    }
    public ArrayList<Account> getAccounts(){
        return Accounts;
    }
  
    public boolean authenticate(String password){
        if (password.equals(this.password)){
            return true;
        }
        return false;
    }

    public void   createAccount(String Account){
        switch (Account)
        {
            case "checking account":
                Accounts.add(new CheckingAccount(0.0));
                break;
            case "savings account":
                Accounts.add(new SavingsAccount(0.0));
                break;
            case "investment account":
                Accounts.add(new InvestmentAccount(0.0));
                break;
        }
    }
    public boolean getloggedin(){
        return loggedin;
    }
}

