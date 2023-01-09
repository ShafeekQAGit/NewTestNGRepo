import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Constants;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class baseTest {

    public static WebDriver driver;
    public ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod()
    {
        sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+"AutomationReport.html");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setTheme(Theme.DARK);
        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Automation Tester","Shafeek");
    }

    @BeforeMethod
    public void beforeMethodMethod( Method testMethod)
    {
        logger= extent.createTest(testMethod.getName());
        setUpDriver("chrome");
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethodMethod(ITestResult result)
    {
        if(result.getStatus()==ITestResult.SUCCESS)
        {
            String methodName=result.getMethod().getMethodName();
            String logTest = "Test case : "+methodName+"Passed";
            Markup m= MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
            logger.log(Status.PASS,m);
        }
        else if(result.getStatus()==ITestResult.FAILURE)
        {
            String methodName=result.getMethod().getMethodName();
            String logTest = "Test case : "+methodName+"Failed";
            Markup m= MarkupHelper.createLabel(logTest, ExtentColor.RED);
            logger.log(Status.FAIL,m);

        }
        driver.quit();

    }

    @AfterTest
    public void afterTestMethod()
    {

        extent.flush();
    }

    public void setUpDriver(String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator+"drivers"+File.separator+"chromedriver.exe");
            driver=new ChromeDriver();

        }
        else if (browserName.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ File.separator+"drivers"+File.separator+"geckodriver.exe");
            driver=new FirefoxDriver();
        }
        else
        {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator+"drivers"+File.separator+"chromedriver.exe");
            driver=new ChromeDriver();
        }
    }


}
