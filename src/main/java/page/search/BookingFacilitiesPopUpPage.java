package page.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.BasePage;

import java.util.List;

public class BookingFacilitiesPopUpPage extends BookingSearchResultsPage {

    public BookingFacilitiesPopUpPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks 'Apply filters' button
     *
     * @return BookingSearchResultsPage -> page object
     */
    public BookingSearchResultsPage applyFilters(){
        clickByXPath("//aside[@id='sr-facility-search-modal']//button[@class='bui-button']");
        return new BookingSearchResultsPage(mDriver);
    }

    /**
     * Clears search field
     */
    public void clearSearchField(){
        clearTextField(BasePage.XPATH,
                "//aside[@id='sr-facility-search-modal']//input[@type='text']");
    }

    /**
     * Checks is desired filter option checkbox triggered
     *
     * @param option String -> filter option
     * @return boolean -> true if triggered
     */
    public boolean isChecked(String option){
        List<WebElement> elements = findElements(BasePage.XPATH,
                "//aside[@id='sr-facility-search-modal']//div[@data-selected='1']");
        for (WebElement currentFilter : elements){
            String name = currentFilter.findElement
                    (By.xpath(".//div//span[1]")).getText();
            if (name.contains(option)){
                return true;
            }
        }
        return false;
    }

    /**
     * Searches filter option by name
     *
     * @param option String -> filter option name
     */
    public void searchFilterOption(String option){
        enterText(BasePage.XPATH,
                "//aside[@id='sr-facility-search-modal']//input[@type='text']",
                option);
    }

    /**
     * Triggers first filter option in 'Facilities' pop-up
     */
    public void triggerFirstFilterOption(){
        String label = "//aside[@id='sr-facility-search-modal']//label";
        clickByXPath(label);
        waitForVisibilityOfElementByXpath(label);
    }
}
