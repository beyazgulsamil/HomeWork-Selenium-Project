package library;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Library {

    public static WebDriver driver;

    public WebDriver setup(String driverName){
        switch (driverName.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("disable-notifications");
                chromeOptions.addArguments("--no-sandbox");
                System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                Assert.fail("No such driver was identified");
                return null;

        }

        return driver;
    }

    public void navigate(String url){

        driver.get("https://"+url);

    }

    public void click(WebElement element){

        centerElement(element).click();

    }

    public void wait(int duration){
        try {
            Thread.sleep(duration*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void clearInputField(WebElement element){
        int textLength = element.getAttribute("value").length();
        for(int i = 0; i < textLength; i++){
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
    public LocalDateTime getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    public void loopAndClick(List<WebElement> list,String buttonName,String buttonLocator){
        for (WebElement item:list) {
            if (item.getText().contains(buttonName)){
                click(item.findElement(By.cssSelector(buttonLocator)));
                return;
            }
        }
        Assert.fail("No such category was found...");


    }

    public void advancedLoopAndClick(List<WebElement> list,String attributeValue,String attributeType){
        for (WebElement item:list) {
            if (item.getAttribute(attributeType).contains(attributeValue)){
                click(item);
                return;
            }
        }
        Assert.fail("No such category was found...");

    }
    public WebElement centerElement(WebElement element){

        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollScript, element);

        wait(1);

        return element;
    }
}

