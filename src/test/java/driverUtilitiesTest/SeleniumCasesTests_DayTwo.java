package driverUtilitiesTest;

import driverUtilities.DriverUtilities;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class SeleniumCasesTests_DayTwo {

    WebDriver driver;

    @Before
    public void init() {
        DriverUtilities myDriverUtilities = new DriverUtilities();
        driver = myDriverUtilities.getDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void fillUserFormAnSubmitTest() throws IOException{

        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        WebElement userNameField = driver.findElement(By.id("email_create"));
        userNameField.sendKeys("try@try.try");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\dimko\\screenShots\\[C]fillForm.png"));
        userNameField.clear();
        srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\dimko\\screenShots\\[C]clearForm.png"));
        userNameField.sendKeys("Proper@email.com");
        WebElement submitButton = driver.findElement(By.name("SubmitCreate"));
        submitButton.click();

    }

    @Test
    public void checkElementExistence() {
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        WebElement logo = driver.findElement(By.id("header_logo"));
        Assert.assertTrue(logo.isDisplayed());
        Assert.assertFalse(logo.isSelected());
    }

    @Test
    public void selectDropDownItemByIndex() throws InterruptedException, IOException {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        WebElement userNameField = driver.findElement(By.id("email_create"));
        userNameField.sendKeys("try@try.try");
        WebElement submitButton = driver.findElement(By.name("SubmitCreate"));
        submitButton.click();

        Duration timeoutDuration = Duration.ofSeconds(30);
        Duration pollingDuration = Duration.ofSeconds(7);

        Wait<WebDriver> waitTimer = new FluentWait<>(driver)
                .withTimeout(timeoutDuration)
                .pollingEvery(pollingDuration)
                .ignoring(NoSuchElementException.class);


        Thread.sleep(7000);
        Select myNewSelectedElement = new Select(
                waitTimer.until(new Function<WebDriver, WebElement>(){
                    public WebElement apply(WebDriver driver){
                        return driver.findElement(By.name("months"));
                    }
                }));

        myNewSelectedElement.selectByIndex(3);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\dimko\\screenShots\\[D]grabMonthChange.png"));

    }


    @Test
    public void grabInfoOnTag(){

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(7000);
        driver.quit();
    }
}
