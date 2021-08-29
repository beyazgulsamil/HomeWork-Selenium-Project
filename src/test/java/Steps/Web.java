package Steps;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import library.Library;
import library.LocatorLibrary;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.meta.TypeQualifier;
import java.util.ArrayList;
import java.util.List;

import static library.Library.driver;

public class Web {

    Library library = new Library();

    @Before
    public void before(){library.setup("Chrome");}
    @After
    public void after() {
        driver.quit();
    }

    @Then("I go to {}")
    public void goTo(String url) {
        System.out.println("navigating to " + url);
        library.navigate(url);
    }

    @Then("I wait {} seconds")
    public void iWait(int duration) {
        library.wait(duration);
    }
    @And("I press button with the {} text on it")
    public void pressButtonWithText(String buttonText) {
        String locator = "//*[contains(text(), '" + buttonText + "')]";
        System.out.println(locator);
        library.click(library.centerElement(driver.findElement(By.xpath(locator))));
    }
    @Then("Click to {} category")
    public void categorieSelect (String cate){
        WebElement leftPanel = driver.findElement(By.cssSelector("[class=\"left-pannel\"]"));
        String locator = "//*[contains(text(), '" + cate + "')]";
        System.out.println(locator);
        leftPanel.findElement(By.cssSelector(locator)).click();
    }


}
