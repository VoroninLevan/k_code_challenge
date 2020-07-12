package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import provider.Driver;
import provider.ParameterReader;

public class BaseTest {

    protected WebDriver mDriver;

    public BaseTest(){
    }

    @BeforeMethod
    protected void beforeMethod(){
        ParameterReader reader = new ParameterReader();
        Driver driver = new Driver(reader);
        mDriver = driver.getDriver();
    }

    @AfterMethod
    protected void afterMethod(){
        mDriver.close();
    }
}
