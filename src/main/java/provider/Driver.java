package provider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private String mProfile;
    private final String GECKO_DRIVER = "src/test/resources/drivers/geckodriver.exe";
    private final String CHROME_DRIVER = "src/test/resources/drivers/chromedriver.exe";
    private WebDriver mDriver;

    public Driver(ParameterReader parameterReader){
        mProfile = parameterReader.getProfile();
        setBrowser();
    }

    private void setBrowser(){
        switch (mProfile){
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
                mDriver = new FirefoxDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.gecko.driver", CHROME_DRIVER);
                mDriver = new ChromeDriver();
                break;
        }
    }

    public WebDriver getDriver(){
        return mDriver;
    }
}
