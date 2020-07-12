package test.filters;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.main.BookingMainPage;
import page.main.BookingMainStaysPage;
import page.search.BookingFacilitiesPopUpPage;
import page.search.BookingSearchResultsPage;
import test.BaseTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingFilters extends BaseTest {

    public BookingFilters() {
        super();
    }

    /**
     *
     */
    @Test (priority = 1)
    public void testSaunaFilterOptionFunctionality(){
        String filterOption = "Sauna";
        String hotelWithSauna = "Limerick Strand Hotel";
        String hotelWithoutSauna = "George Limerick Hotel";
        // Navigate to 'booking' main page
        BookingMainPage main = new BookingMainPage(mDriver);
        main.goToBooking();
        // Fill form
        BookingMainStaysPage mainStays = new BookingMainStaysPage(mDriver);
        mainStays.declineCookieWarning();
        mainStays.setLocation("Limerick");
        mainStays.openCloseCalendar();
        mainStays.setCheckIn(getAdjustedDateFromNow(0,3,0));
        mainStays.setCheckOut(getAdjustedDateFromNow(0,3,1));
        mainStays.openCloseGuests();
        mainStays.setAdults(2);
        mainStays.setRooms(1);
        // Navigate to search results
        BookingSearchResultsPage searchResults = mainStays.search();
        // Trigger 'Facilities' pop-up
        BookingFacilitiesPopUpPage facilitiesPopUp = searchResults.triggerFacilitiesPopUp();
        facilitiesPopUp.searchFilterOption(filterOption);
        facilitiesPopUp.triggerFirstFilterOption();
        facilitiesPopUp.clearSearchField();
        // Assert 'Sauna' filter option checkbox checked
        Assert.assertTrue(facilitiesPopUp.isChecked(filterOption));
        // Close pop-up -> back to search results page, get results
        facilitiesPopUp.applyFilters();
        // Assert results against expected -> test switch on functionality
        Assert.assertTrue(searchResults.isHotelPresent(hotelWithSauna),
                "Expected behavior failed. '" + hotelWithSauna +
                        "' has sauna facilities and did not appear in the search results.");
        Assert.assertFalse(searchResults.isHotelPresent(hotelWithoutSauna),
                "Expected behavior failed. '" + hotelWithoutSauna +
                        "' does not have sauna facilities but did appear in the search results.");
        // Trigger 'Sauna' filter off -> test switch off functionality
        searchResults.triggerRecentFilterOff(filterOption);
        Assert.assertTrue(searchResults.isHotelPresent(hotelWithoutSauna),
                "Expected behavior failed. 'Sauna' filter was switched off - '" + hotelWithSauna +
                        "' which has no sauna facilities did not appear in the search results.");
    }

    /**
     *
     */
    @Test (priority = 2)
    public void testStarFilterOptionFunctionality(){
        int stars = 5;
        String hotel5Star = "The Savoy Hotel";
        String hotelNon5Star = "George Limerick Hotel";
        // Navigate to 'booking' main page
        BookingMainPage main = new BookingMainPage(mDriver);
        main.goToBooking();
        // Fill form
        BookingMainStaysPage mainStays = new BookingMainStaysPage(mDriver);
        mainStays.declineCookieWarning();
        mainStays.setLocation("Limerick");
        mainStays.openCloseCalendar();
        mainStays.setCheckIn(getAdjustedDateFromNow(0,3,0));
        mainStays.setCheckOut(getAdjustedDateFromNow(0,3,1));
        mainStays.openCloseGuests();
        mainStays.setAdults(2);
        mainStays.setRooms(1);
        // Navigate to search results
        BookingSearchResultsPage searchResults = mainStays.search();
        // Trigger 'N Star' filter -> test switch on functionality
        searchResults.triggerStarRating(stars);
        // Assert results against expected
        Assert.assertTrue(searchResults.isHotelPresent(hotel5Star),
                "Expected behavior failed. '" + hotel5Star +
                        "' is '" + stars + " stars' hotel and did not show up in the search results.");
        Assert.assertFalse(searchResults.isHotelPresent(hotelNon5Star),
                "Expected behavior failed. '" + hotelNon5Star +
                        "' is not '" + stars + " stars' hotel but did show up in the search results.");
        // Trigger off 'N Star' filter -> test filter switch off functionality
        // Assert non N-star hotel appears in the search results
        searchResults.triggerStarRating(stars);
        Assert.assertTrue(searchResults.isHotelPresent(hotelNon5Star),
                "Expected behavior failed. '" + stars + " stars' filter was triggered off. '" +
                        hotelNon5Star + "' should appear in the search results.");
    }

    /**
     * Returns desired date in format yyyy-MM-dd. Calculates future date from today
     *
     * @param years int -> number of years to add
     * @param months int -> number of months to add
     * @param days int -> number of days to add
     * @return String -> formatted date
     */
    private String getAdjustedDateFromNow(int years, int months, int days){
        LocalDateTime adjustedDate = LocalDateTime.now().plusYears(years).plusMonths(months).plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(adjustedDate);
    }
}
