import org.testng.Assert;
import org.testng.annotations.Test;

public class testRunner extends baseTest {

    @Test(priority = 0)
    public void testZero()
    {
        System.out.println("Passed test case");
        logger.info("Test Zero pAssed");


    }
    @Test(priority = 1)
    public void testOne()
    {
        System.out.println("Failed test case");
        logger.info("Test one Failed");
        Assert.fail();
    }
    @Test(priority = 2)
    public void testTwo()
    {
        System.out.println("Passed test case");
        logger.info("testTwo pAssed");
    }
    @Test(priority = 3)
    public void testThree()
    {
        System.out.println("Passed test case");
        logger.info("testThree passed");
    }
    @Test(priority = 4,dataProvider = "Login",dataProviderClass = dataProviderDemo.class)
    public void testFour(String user, String password,String desc )
    {
        System.out.println(user +" "+password+" "+desc);
        logger.info(user +" "+password+" "+desc);
        System.out.println("Passed test case");
        logger.info("Test four Passed");

    }
}
