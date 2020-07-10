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
     *
     * @param stars
     */
    public void selectStarRating(int stars){
        clickByXPath("//a[@data-id='class-" + stars + "']");
    }

    /**
     *
     */
    public void selectSauna(){
        List<WebElement> popActivities = findElements(BasePage.XPATH,
                "//a[contains(@data-id, 'popular_activities')]");
        for (WebElement currentActivity : popActivities){
            String activity = currentActivity.findElement
                    (By.xpath(".//span[contains(@class, 'filter_label')]")).getText();
            System.out.println(activity);
            if (activity.contains("Sauna")) currentActivity.click();
        }
    }
}
