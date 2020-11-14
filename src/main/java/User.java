import java.util.ArrayList;

public class User {

    private String ID;
    private String password;
    ArrayList <Account> Accounts = new ArrayList<Account>();
    private boolean loggedin;


    public User(String ID, String password) {
        this.ID = ID;
        this.password = password;
        this.Accounts = new ArrayList<Account>();


    }
    public String getID(){
     return ID;

    }
    public ArrayList<Account> getAccount(){
       return Accounts;

    }
    public boolean authenticate(String password){
        if (password.equals(this.password)){
            return true;
        }
        return false;
   }

    public void   createAccount(Integer Account){
        switch (Account)
        {
            case 1:

                Accounts.add(new CheckingAccount(0.0));
                break;
            case 2:

                Accounts.add(new SavingsAccount(0.0));
                break;
            case 3:

                Accounts.add(new InvestmentAccount(0.0));
                break;
        }

    }


    public boolean getloggedin(){
    return loggedin;
    }
}