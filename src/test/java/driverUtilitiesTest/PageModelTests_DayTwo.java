package driverUtilitiesTest;

import driverUtilities.DriverUtilities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageModels.PageProperties;
import pageModels.Product;

public class PageModelTests_DayTwo {

    WebDriver driver;
    Product pageProduct;

    @Before
    public void init() {
        DriverUtilities myDriverUtilities = new DriverUtilities();
        driver = myDriverUtilities.getDriver();
        driver.get(PageProperties.BLOUSE_LINK.toString());
        driver.manage().window().maximize();
        pageProduct = new Product();

    }

    @Test
    public void testIfPageModelIsSuccessfullyInstantiated(){
        Assert.assertNotNull(pageProduct);
    }

    @Test
    public void testIfCanPopulateTheProductWithTheProperName(){
        WebDriverWait waitTimer = new WebDriverWait(driver,30);
        WebElement blouse = waitTimer.until(ExpectedConditions.visibilityOf(driver.findElement(By
                .tagName(PageProperties.TAG_H1.toString()))));
        pageProduct.setName(blouse.getText());
        Assert.assertEquals(PageProperties.BLOUSE.toString(),pageProduct.getName());
    }

    @Test
    public void testIfCanPopulateTheProductWithTheProperReference(){
        WebElement blouse = driver.findElement(By.id(PageProperties.PAGE_PRODUCT_REFERENCE.toString()));
        pageProduct.setReference(blouse.getText());
        Assert.assertEquals(PageProperties.REFERENCE.toString(),pageProduct.getReference());
    }

    @Test
    public void testIfCanPopulateTheProductWithTheProperCondition(){
        WebElement blouse = driver.findElement(By.id(PageProperties.PAGE_PRODUCT_CONDITION.toString()));
        pageProduct.setCondition(blouse.getText());
        Assert.assertEquals(PageProperties.CONDITION.toString(),pageProduct.getCondition());
    }

    @Test
    public void testIfCanPopulateTheProductWithTheProperDescription(){

        WebElement blouse = driver.findElement(By.id(PageProperties.PAGE_PRODUCT_DESCRIPTION.toString()));
        pageProduct.setDescription(blouse.getText());
        Assert.assertEquals(PageProperties.DESCRIPTION.toString(),pageProduct.getDescription());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
