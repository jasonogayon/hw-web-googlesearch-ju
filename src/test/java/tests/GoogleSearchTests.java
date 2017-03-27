import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.openqa.selenium.WebDriver;

public class GoogleSearchTests {

    Browser browser;
    GoogleSearch google;
    private WebDriver driver = null;

    @Before
    public void startGoogleSearch() {
        this.browser = new Browser();
        this.driver = this.browser.open();
        this.google = new GoogleSearch(this.driver);
    }

    @After
    public void closeGoogleSearch() {
        this.driver.close();
        this.driver.quit();
    }


    @Test
    public void searchForSomething() {

        // Define a Keyword to Search
        String keyword = "onesoftwaretester";

        // Search for that Keyword
        google.open();
        assertThat(driver.getTitle(), is("Google"));
        List<String> results = google.search(keyword);

        // Check if Search Results Contain the OneSoftwareTester Wordpress blog
        int count = 0;
        for (String result : results) { if (result.contains("onesoftwaretester.wordpress.com")) count++; }
        assertThat(count, greaterThan(0));
        System.out.println("OneSoftwareTester Wordpress blog link found in the first page of Google's search results for the 'onesoftwaretester' keyword");
    }

}
