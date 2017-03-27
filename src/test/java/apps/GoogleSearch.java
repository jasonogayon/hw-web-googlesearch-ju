import java.util.*;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class GoogleSearch {

    private WebDriver browser;
    private WebDriverWait wait;

    private String GOOGLE_SEARCH = "https://www.google.com/";
    private String TEXTFIELD_QUERY = "q";
    private String LINKS_RESULTS = "//div[@class='g']//cite";

    public GoogleSearch(WebDriver browser) {
        this.browser = browser;
        this.wait = new WebDriverWait(this.browser, 30);
    }

    protected void open() {
        browser.navigate().to(GOOGLE_SEARCH);
    }

    protected List<String> search(String keyword) {

        // Search for a Keyword
        WebElement query = wait.until(visibilityOf(browser.findElement(By.name(TEXTFIELD_QUERY))));
        query.sendKeys(keyword);
        query.sendKeys(Keys.ENTER);

        // Wait for Results to Appear
        int count = 0;
        int maxTries = 5;
        List<WebElement> locators = new ArrayList<WebElement>();
        while(locators.size() == 0) {
            try {
                locators = browser.findElements(By.xpath(LINKS_RESULTS));
            } catch (Exception e) {
                if (++count == maxTries) fail("Results did not appear after some time. Try again later.");
            }
        }

        // Get Data from Results
        List<String> results = new ArrayList<String>();
        for (WebElement locator : locators) { results.add(locator.getText()); }
        return results;
    }

}
