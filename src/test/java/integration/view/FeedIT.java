package integration.view;

import ext.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

@Category(IntegrationTest.class)
public class FeedIT {

    @Test
    public void titleTest() {
        WebDriver driver = new HtmlUnitDriver();

//        driver.manage().window().maximize();

//        driver.get("http://localhost:8080/fission/feed");
        driver.get("http://localhost:8080/fission-1.0.0-SNAPSHOT/feed");

        WebElement newFeedBtn = driver.findElement(By.id("new-feed"));
        newFeedBtn.click();

        Select feedTypeSelect = new Select(driver.findElement(By.name("feedType")));
        feedTypeSelect.selectByVisibleText("Grupo");

        driver.findElement(By.name("name")).sendKeys("Novo Feed");
        driver.findElement(By.name("identifier")).sendKeys("identificador");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals("Fission", driver.getTitle());

        driver.close();
    }
}
