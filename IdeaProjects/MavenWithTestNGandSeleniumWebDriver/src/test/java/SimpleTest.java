import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SimpleTest {
    @Test

    public void navigateToRozetkaWebsiteAndSearchForIphone()
    {
        WebDriver driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua");

        WebElement searchField = driver.findElement(By.cssSelector(".rz-header-search-input-text"));
        searchField.clear();
        searchField.sendKeys("iphone 6");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement searchButton = driver.findElement(By.cssSelector(".btn-link-i"));
        searchButton.click();
    }
}
