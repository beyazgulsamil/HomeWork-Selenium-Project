package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import library.Library;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

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
        library.click(driver.findElement(By.xpath(locator)));
    }
    @Then("Click to {} category")
    public void categorieSelect (String cate){
        WebElement leftPanel = driver.findElement(By.cssSelector("[class=\"left-pannel\"]"));
        String locator = "//*[contains(text(), '" + cate + "')]";
        System.out.println(locator);
        library.click(leftPanel.findElement(By.xpath(locator)));
    }
    @Then("Full name is {}")
    public void fullName (String name){driver.findElement(By.cssSelector("[id=\"userName\"]")).sendKeys(name);}
    @Then("E-mail is {}")
    public void email(String mail){
        driver.findElement(By.cssSelector("[id=\"userEmail\"]")).sendKeys(mail);
    }
    @Then("Current Address is {}")
    public void cAdress(String cad){
        driver.findElement(By.cssSelector("[id=\"currentAddress\"]")).sendKeys(cad);
    }
    @Then("Permanent Address is {}")
    public void pAdress(String pad){
        driver.findElement(By.cssSelector("[id=\"permanentAddress\"]")).sendKeys(pad);
    }
    @Then("Verification name {}")
    public void vName(String inputName){
        WebElement output = driver.findElement(By.cssSelector("[id=\"output\"]"));
        String nameOutput = output.findElement(By.cssSelector("[id=\"name\"]")).getText();
        Assert.assertTrue(nameOutput.contains(inputName));
        System.out.println("Verified Succes Name");
    }
    @Then("Verification email {}")
    public void vMail(String inputMail){
        WebElement output = driver.findElement(By.cssSelector("[id=\"output\"]"));
        String nameOutput = output.findElement(By.cssSelector("[id=\"email\"]")).getText();
        Assert.assertTrue(nameOutput.contains(inputMail));
        System.out.println("Verified Succes Mail");
    }
    @Then("Verification Current Address {}")
    public void vCAd(String inputCAddress){
        WebElement output = driver.findElement(By.cssSelector("[id=\"output\"]"));
        String nameOutput = output.findElement(By.cssSelector("[id=\"currentAddress\"]")).getText();
        Assert.assertTrue(nameOutput.contains(inputCAddress));
        System.out.println("Verified Succes Current Address");
    }
    @Then("Verification Permanent Address {}")
    public void vPAd(String inputPAddress){
        WebElement output = driver.findElement(By.cssSelector("[id=\"output\"]"));
        String nameOutput = output.findElement(By.cssSelector("[id=\"permanentAddress\"]")).getText();
        Assert.assertTrue(nameOutput.contains(inputPAddress));
        System.out.println("Verified Succes Permanenet Address");
    }
    @Then("Drag {} to {}")
    public void dragDrop (String from,String to){
        Actions actions =new Actions(driver);
        List<WebElement> nums =driver.findElements(By.cssSelector("[class=\"list-group-item list-group-item-action\"]"));
        for (WebElement groupItem: nums ) {
            if (groupItem.getText().contains(from)){
                for (WebElement groupItemTo:nums) {
                    if (groupItemTo.getText().contains(to)){
                        int toX = groupItemTo.getLocation().getX();
                        int toY = groupItemTo.getLocation().getY();
                        int X = groupItem.getLocation().getX();
                        int Y = groupItem.getLocation().getY();
                        int difX = toX-X;
                        int difY = toY-Y;
                        actions.moveToElement(groupItem,0,0)
                                .clickAndHold()
                                .dragAndDropBy(groupItem,difX,difY)
                                .build()
                                .perform();

                    }

                }

            }


        }
    }
    @Then("Menu {} in mouse hover")
    public void mouseHover(String menu1){
        Actions actions = new Actions(driver);
        List<WebElement> menus = driver.findElements(By.cssSelector("[class=\"nav-menu-container\"]"));
        for (WebElement groupMenu:menus) {
            groupMenu.getText().contains(menu1);
            actions.moveToElement(groupMenu).perform();
        }
    }
    @And("I hover button with the {} text on it")
    public void hoverButtonWithText(String hoverText) {
        Actions actions = new Actions(driver);
        String locator = "//*[contains(text(), '" + hoverText + "')]";
        WebElement hlocate =driver.findElement(By.xpath(locator));
        actions.moveToElement(library.centerElement(hlocate)).perform();
    }
}
