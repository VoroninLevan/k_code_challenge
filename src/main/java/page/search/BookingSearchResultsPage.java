package page.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.BasePage;

import java.util.List;

public class BookingSearchResultsPage extends BasePage {

    public BookingSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks is desired hotel present in the search results
     * NOTE: For future use better to create separate class for storing results
     *
     * @param hotelName String -> hotel name
     * @return boolean -> true if hotel present
     */
    public boolean isHotelPresent(String hotelName){
        List<WebElement> elements = findElements(BasePage.XPATH,
                "//div[@id='search_results_table']//div[@id='hotellist_inner']" +
                        "/div[contains(@class, 'sr_item')]");
        for (WebElement currentResult : elements){
            String name = currentResult.findElement
                    (By.xpath(".//span[contains(@class, 'sr-hotel__name')]")).getText();
            if (name.contains(hotelName)){
                return true;
            }
        }
        return false;
    }

    /**
     * Selects desired star rating
     *
     * @param stars int -> rating, e.g. 5
     */
    public void triggerStarRating(int stars){
        clickByXPath("//a[@data-id='class-" + stars + "']");
        waitForVisibilityOfElementByXpath("//div[@class='sr-usp-overlay sr-usp-overlay--wide']");
        waitForInvisibilityOfElementByXpath("//div[@class='sr-usp-overlay sr-usp-overlay--wide']");
    }

    /**
     * Triggers desired recent filter off
     *
     * @param option String -> filter option name
     */
    public void triggerRecentFilterOff(String option){
        List<WebElement> recentList = findElements(BasePage.XPATH,
                "//div[@id='filter_recent']//a");
        for (WebElement currentFilter : recentList){
            String filterName = currentFilter.findElement
                    (By.xpath(".//span[contains(@class, 'filter_label')]")).getText();
            if (filterName.contains("Sauna")) currentFilter.findElement(By.xpath(".//label")).click();
        }
        waitForInvisibilityOfElementByXpath("//div[@class='sr-usp-overlay sr-usp-overlay--wide']");
    }

    public BookingFacilitiesPopUpPage triggerFacilitiesPopUp(){
        clickByXPath("//div[@id='filter_facilities']//button");
        return new BookingFacilitiesPopUpPage(mDriver);
    }
}
