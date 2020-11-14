import java.util.ArrayList;

public class User {

    private Integer name;
    private String password;
    ArrayList <Account> Accounts = new ArrayList<Account>();
    private boolean loggedin;



    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.Accounts = new ArrayList<Account>();


    }
    public String getName(){
     return name;

    }
    public String getAccount(){
       return Account;

    }
    public String authenticate(String name, String password){

    }

    public String createAccount(){

        return Account;
    }

    public String closeAccount(){
        return Account;
    }

    public getloggedin(){

    }
}