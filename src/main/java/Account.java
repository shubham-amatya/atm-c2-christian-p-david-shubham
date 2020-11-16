import java.util.ArrayList;

public class Account {
    private Double balance;
    private ArrayList<String> history;
    private User owner; // uncomment this when User is present

    public Account(double balance){
        this.balance = balance;
        history = new ArrayList<String>();
    }

    public void setOwner(User owner){
        this.owner = owner;
    }

    public User getOwner(){
        return owner;
    }

    public Double withdraw(Double amt){
        if (amt <= balance){
            balance -= amt;
            addToHistory("Withdrawal", amt);
        }
        return balance;
    }

    public void deposit(Double amt){
        balance += amt;
        addToHistory("Deposit", amt);
    }

    public boolean transfer(Account acc, Double amt){
        if (amt <= balance){
            balance -= amt;
            acc.deposit(amt);
            addToHistory("Transfer", amt);
            return true;
        }
        else return false;
    }

    public String printTransactionHistory(){
        StringBuilder sb = new StringBuilder("Transaction history:");
        for (String s : history) {
            sb.append("\n" + s);
        }
        return sb.toString();
    }

    public boolean closeAccount(){
        if (balance == 0){
            owner.getAccounts().remove(this);
            return true;
        }
        return false;
    }

    public double getBalance(){
        return balance;
    }

    public void addToHistory(String type, Double amt){
        String entry = String.format("%s of $" + "%.2f", type, amt);
        history.add(entry);
    }


}
