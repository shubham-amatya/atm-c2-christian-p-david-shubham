import org.junit.Assert;
import org.junit.Test;
import sun.text.normalizer.ICUBinary;

import javax.naming.Name;

public class UserTest {

    @Test
    public void testConstructor() {
        //given
        String expectedName = "David";
        User user = new User(expectedName, "whatever");

        //when
        String actualName = user.getName();

        //then
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void setNameTest() {
        // Given
        String expectedName = "Leon";
        User user = new User(expectedName, "whatever");

        // When

        String actualName = user.getName();

        // Then
        Assert.assertEquals(expectedName, actualName);

    }

    @Test

    public void testAuthenicate() {
        //given
        boolean res = true;
        String expectedPassword = "whatever";

        User user  = new User("David",expectedPassword);
        //when
        boolean actualResult = user.authenticate(expectedPassword);


        //then
        Assert.assertEquals(res,actualResult);
    }



    }
   
