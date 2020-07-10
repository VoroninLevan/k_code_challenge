package test.filters;

import org.testng.annotations.Test;
import page.main.BookingMainPage;
import page.main.BookingMainStaysPage;
import page.search.BookingSearchResultsPage;
import test.BaseTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingFilters extends BaseTest {

    public BookingFilters() {
        super();
    }

    @Test
    public void test(){
        BookingMainPage main = new BookingMainPage(mDriver);
        main.goToBooking();
        BookingMainStaysPage mainStays = new BookingMainStaysPage(mDriver);
        mainStays.declineCookieWarning();
        mainStays.setLocation("Limerick");
        mainStays.openCloseCalendar();
        mainStays.setCheckIn(getAdjustedDateFromNow(0,0,0));
        mainStays.setCheckOut(getAdjustedDateFromNow(0,0,1));
        mainStays.openCloseGuests();
        mainStays.setAdults(2);
        mainStays.setRooms(1);
        BookingSearchResultsPage searchResults = mainStays.search();
        searchResults.selectStarRating(4);
        searchResults.selectSauna();
        //driver.quit();
    }

    /**
     *
     * @param years
     * @param months
     * @param days
     * @return
     */
    private String getAdjustedDateFromNow(int years, int months, int days){
        LocalDateTime adjustedDate = LocalDateTime.now().plusYears(years).plusMonths(months).plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(adjustedDate);
    }
}
