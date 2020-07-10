package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import provider.Driver;
import provider.ParameterReader;

public class BaseTest {

    protected ParameterReader reader;
    protected WebDriver mDriver;

    public BaseTest(){
    }

    @BeforeSuite
    public void setUpTest(){
        reader = new ParameterReader();
        Driver driver = new Driver(reader);
        mDriver = driver.getDriver();
    }

    @AfterSuite
    public void finishTest(){
        mDriver.close();
    }
}
