
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

//
// I G N O R E
//

// Test the expected Account class from ATM.
public class AccountTest {


    @Test
    public void testA0() {
        Account a = new Account(0.0);
        assertEquals(0.0, a.getBalance(), 0.0001);
    }

    @Test
    public void testA00() {
        Account a = new Account(10.0);
        assertEquals(10.0, a.getBalance(), 0.0001);
    }

    /*
    @Test
    public void testA01() {
        Account a = new Account(0.0);
        assertEquals(true, a.closeAccount());
    }

    @Test
    public void testA02() {
        Account a = new Account(10.0);
        assertEquals(false, a.closeAccount());
    }
    */


    @Test
    public void testA1() {
        Account a = new Account(0.0);
        a.deposit(100.0);
        assertEquals(100.0, a.getBalance(), 0.0001);
    }

    @Test
    public void testA2() {
        Account a = new Account(10.0);
        a.deposit(100.0);
        assertEquals(110.0, a.getBalance(), 0.0001);
    }

    @Test
    public void testA3() {
        Account a = new Account(200.0);
        Double actual = a.withdraw(100.0);
        assertEquals(100.0, actual, 0.0001);
    }

    @Test
    public void testA4() {
        Account a = new Account(0.0);
        Double actual = a.withdraw(1.0);
        assertEquals(0.0, actual, 0.0001);
    }

    @Test
    public void testA5() {
        Account a = new Account(10.0);
        Account b = new Account(0.0);
        a.transfer(b, 10.0);
        assertEquals(0.0, a.getBalance(), 0.0001);
        assertEquals(10.0, b.getBalance(), 0.0001);
    }

    @Test
    public void testA6() {
        Account a = new Account(10.0);
        Account b = new Account(0.0);
        a.transfer(b, 100.0); // nothing should happen
        assertEquals(10.0, a.getBalance(), 0.0001);
        assertEquals(0.0, b.getBalance(), 0.0001);
    }


}
