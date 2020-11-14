import java.util.ArrayList;

public class ATM {//extends User{

    private ArrayList<User> Users = new ArrayList();
    User currentUser;
    Console console = new Console();
    boolean loggedIn = false;


    public ATM(){ }


  public void addUser(User User) {
      Users.add(User);


  }
    public void logIn(){

       Integer newLoginInfo = console.getNumInput();
       for(User U: Users){
           if(U.equals(newLoginInfo)){
               System.out.println("You are now logged in to your account");
               loggedIn = true;
           }else{
               System.out.println("Account number invalid. Please enter a valid account number");
               console.getNumInput();
               loggedIn = false;
           }
       }
      }

      public void logOut(){
        loggedIn = false;
      }





    }




