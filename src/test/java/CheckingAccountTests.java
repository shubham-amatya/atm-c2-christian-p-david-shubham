import org.junit.Assert;
import org.junit.Test;
// Thoughts:
// -is it necessary to have a check for loggedIn each time that we do one of these operations?
// -what if we just had a single check in the ATM class for when the user is selecting their action?
// -if they're not logged in, they shouldn't even be able to get access to the accounts, anyway
//-Should we print error messages at the Account level or at the ATM level?
// - Why doesn't it matter whether Account is abstract or not?

public class CheckingAccountTests {

    @Test
    public void testConstructor(){
        //Given
        Double expectedAmount = 3.0;
        CheckingAccount acc = new CheckingAccount(expectedAmount);

        //When
        Double actualAmount = acc.getBalance();

        //Then
        Assert.assertEquals(expectedAmount, actualAmount);

    }

    @Test
    public void testWithdraw(){
        //Given
        CheckingAccount acc = new CheckingAccount(5.0);
        Double expectedWithdrawal = 4.0;
        Double currentAmount = acc.getBalance();
        Double expectedAmount = currentAmount - expectedWithdrawal;

        //When
        acc.withdraw(4.0);
        Double actualAmount = acc.getBalance();

        //Then
        Assert.assertEquals(expectedAmount, actualAmount);
    }

    public void testWithdraw2(){ //try to take out money that's not there
        CheckingAccount acc = new CheckingAccount(0.0);
        Double result = acc.withdraw(5.0);
        Double expected = 0.0;
        Assert.assertEquals(expected, result);

    }

    @Test
    public void testDeposit(){
        //Given
        CheckingAccount acc = new CheckingAccount(0.0);
        Double expectedAmount = 6.0;

        //When
        acc.deposit(6.0);

    }

    @Test
    public void testTransfer(){
        //Given
        CheckingAccount acc1 = new CheckingAccount(5.0);
        SavingsAccount acc2 = new SavingsAccount(0.0);
        Double expectedTransfer = 3.0;


        //When
        acc1.transfer(acc2, expectedTransfer);
        Double actualTransfer = acc2.getBalance();

        //Then
        Assert.assertEquals(expectedTransfer, actualTransfer);



    }

    @Test
    public void testGetTransactionHistory(){
        //Given
        CheckingAccount acc = new CheckingAccount(5.0);
        String expectedTransactionHistory = "Transaction history:\n" + "Withdrawal of $5.00\n" + "Deposit of $6.00";
        acc.withdraw(5.0);
        acc.deposit(6.0);

        //When
        String actualTransactionHistory = acc.printTransactionHistory();

        //Then
        Assert.assertEquals(expectedTransactionHistory, actualTransactionHistory);

    }

    //public void test



}