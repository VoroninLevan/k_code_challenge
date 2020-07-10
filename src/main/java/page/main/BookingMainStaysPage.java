package page.main;

import org.openqa.selenium.WebDriver;
import page.search.BookingSearchResultsPage;

public class BookingMainStaysPage extends BookingMainPage{

    public BookingMainStaysPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens/Closes calendar widget
     */
    public void openCloseCalendar(){
        clickByXPath("//div[@class='xp__dates-inner']");
    }

    /**
     * Opens/Closes guests/rooms widget
     */
    public void openCloseGuests(){
        clickByXPath("//div[@class='xp__input-group xp__guests']");
    }

    /**
     * Clicks 'Search' button
     *
     * @return BookingSearchResultsPage -> page object
     */
    public BookingSearchResultsPage search(){
        clickByXPath("//button[@class='sb-searchbox__button ']");
        return new BookingSearchResultsPage(mDriver);
    }

    /**
     * Sets desired amount of adults
     *
     * @param amount int -> amount of adults
     */
    public void setAdults(int amount){
        int currentAdults = Integer.parseInt(getElementText(BookingMainStaysPage.XPATH,
                "//div[@class='sb-group__field sb-group__field-adults']" +
                        "//span[@class='bui-stepper__display']"));
        if (currentAdults > amount){
            while (currentAdults > amount){
                clickByXPath("//button[@aria-label='Decrease number of Adults']");
                currentAdults--;
            }
        } else if (currentAdults < amount){
            while (currentAdults < amount){
                clickByXPath("//button[@aria-label='Increase number of Adults']");
                currentAdults++;
            }
        }
    }

    /**
     * Sets check-in date
     *
     * @param date String -> format date yyyy/mm/dd
     */
    public void setCheckIn(String date){
        String dateXpath = "//td[@data-date='" + date + "']";
        while (!isElementPresent(BookingMainStaysPage.XPATH, dateXpath)){
            switchMonthNext();
        }
        clickByXPath(dateXpath);
    }

    /**
     * Sets check-out date
     *
     * @param date String -> format date yyyy/mm/dd
     */
    public void setCheckOut(String date){
        String dateXpath = "//td[@data-date='" + date + "']";
        while (!isElementPresent(BookingMainStaysPage.XPATH, dateXpath)){
            switchMonthNext();
        }
        clickByXPath(dateXpath);
    }

    /**
     * Sets desired Location
     *
     * @param location String -> Location
     */
    public void setLocation(String location){
        setText(BookingMainStaysPage.XPATH,
                "//input[@data-component='search/destination/input-placeholder']",
                location);
    }

    /**
     * Sets desired room amount
     *
     * @param amount int -> room amount
     */
    public void setRooms(int amount){
        int currentRooms = Integer.parseInt(getElementText(BookingMainStaysPage.XPATH,
                "//div[@class='sb-group__field sb-group__field-rooms']" +
                        "//span[@class='bui-stepper__display']"));
        if (currentRooms > amount){
            while (currentRooms > amount){
                clickByXPath("//button[@aria-label='Decrease number of Rooms']");
                currentRooms--;
            }
        } else if (currentRooms < amount){
            while (currentRooms < amount){
                clickByXPath("//button[@aria-label='Increase number of Rooms']");
                currentRooms++;
            }
        }
    }

    /**
     * Switches month forward in calendar widget
     */
    private void switchMonthNext(){
        clickByXPath("//div[@data-bui-ref='calendar-next']");
    }
}
