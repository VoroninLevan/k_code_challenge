package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import provider.Driver;
import provider.ParameterReader;

public class BaseTest {

    protected WebDriver mDriver;

    public BaseTest(){
    }

    @BeforeSuite
    protected void beforeSuite(){
        ParameterReader reader = new ParameterReader();
        Driver driver = new Driver(reader);
        mDriver = driver.getDriver();
    }

    @AfterSuite
    protected void afterSuite(){
        mDriver.close();
    }
}
