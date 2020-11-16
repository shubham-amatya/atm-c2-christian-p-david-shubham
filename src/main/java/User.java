

import java.util.ArrayList;
public class User {
    private String name;
    private String password;
    private ArrayList <Account> accounts = new ArrayList<Account>();
    private String User;

    public User( String name, String password) {
        this.name = name;
        this.password = password;
        this.accounts = new ArrayList<Account>();
    }
    public String getName(){
        return name;
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


    public void createAccount(String Account){


        switch (Account)
        {
            case "checking account":
                CheckingAccount first = new CheckingAccount(0.0);
                first.setOwner(this);
                accounts.add(first);
                break;
            case "savings account":
                SavingsAccount second = new SavingsAccount(0.0);
                second.setOwner(this);
                accounts.add(second);
                break;
            case "investment account":
                InvestmentAccount third = new InvestmentAccount(0.0);
                third.setOwner(this);
                accounts.add(third);
                break;
        }
    }

    }


