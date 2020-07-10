package page.main;

import org.openqa.selenium.WebDriver;
import page.BasePage;

public class BookingMainPage extends BasePage {


    public BookingMainPage(WebDriver driver) {
        super(driver);
    }

    /**
     *
     */
    public void declineCookieWarning(){
        clickByXPath("//a[@class='bui-link bui-link--primary close_warning']");
    }
}
