package driverUtilitiesTest;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import driverUtilities.DriverUtilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DriverUtilitiesTest_DayOne {

    WebDriver driver;

    @Before
    public void init() {
        DriverUtilities myDriverUtilities = new DriverUtilities();
        driver = myDriverUtilities.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void driverIsNotNull() {
        Assert.assertNotNull(driver);
    }

    @Test
    public void runBasicDriverCommands() {
        driver.get("http://www.bbc.co.uk");
        Assert.assertNotEquals("www.reddit.com", driver.getCurrentUrl());
        driver.navigate().refresh();
        driver.get("http://www.google.co.uk");
        Assert.assertEquals("Google", driver.getTitle());
        driver.navigate().back();
        driver.navigate().forward();
        driver.close();
    }

    @Test
    public void castDriverAsWebDriverGetBrowserName(){
        Assert.assertEquals("firefox", ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
    }

    @Test
    public void castDriverAsWebDriverGetBrowserVersion(){
        Assert.assertEquals("92.0.4515.131", ((RemoteWebDriver) driver).getCapabilities().getVersion());
    }

    @Test
    public void grabScreenShotReturnsScreenshot() throws IOException {
        driver.get("http://www.reddit.com");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\dimko\\screenShots\\[A]firstSnap.png"));
    }

    @Test
    public void grabLogoTagName(){
        driver.get("http://automationpractice.com/index.php");
        WebElement firstNameField = driver.findElement(By.id("header_logo"));
        Assert.assertEquals("div",firstNameField.getTagName());
    }

    @Test
    public void grabSpecificSiteInformation(){
        driver.get("http://automationpractice.com/index.php");
        List<WebElement> results = driver.findElements(By.className("columns-container"));
        /*for (WebElement result: results)
        {
         System.out.println(result.getText());
        }*/
        Assert.assertTrue(results.size()>0);
    }

    @Test
    public void addSomethingOnSearch() throws IOException {
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        WebElement searchField = driver.findElement(By.id("search_query_top"));
        searchField.sendKeys("One Piece T-Shirt");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\dimko\\screenShots\\[B]searchQuerySnap.png"));
    }

    @Test
    public void findLinkAndClickIt() {
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        WebElement contactUs = driver.findElement(By.linkText("Contact us"));
        contactUs.click();
    }

    @Test
    public void findPartialLinkAndClickIt() {
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        WebElement contactUs = driver.findElement(By.partialLinkText("Sign"));
        contactUs.click();
    }

    @Test
    public void getInformationWithAbsolutePath(){
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        WebElement whatNot = driver.findElement(By.xpath("html/body/div/div/div/div/div/div/div/div/div"));
        String expected = "Prev\nNext";
        Assert.assertEquals(expected,whatNot.getText());
    }

    @Test
    public void getInformationWithAbsolutePathSpecifics(){
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        WebElement whatNot = driver.findElement(By.xpath("//div[@id='slider_row']"));
        String expected = "EXCEPTEUR\nOCCAECAT\nLorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Proin tristique in tortor et dignissim. Quisque non tempor leo. Maecenas egestas sem elit" +
                "\nSHOP NOW !\n\n\n\nPrev\nNext";
        Assert.assertEquals(expected,whatNot.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}