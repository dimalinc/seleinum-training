
import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private int delay = 5000;

    public boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    @Before
    public void start() {

        //  ChromeOptions options = new ChromeOptions();
        //  options.addArguments("start-fullscreen");
        driver = new FirefoxDriver();

        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, SECONDS);

        driver.manage().timeouts().implicitlyWait(15, SECONDS);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehavior", "dismiss");
        // driver = new ChromeDriver(caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        // driver.get("https://bilsteinlifts.com/");
        //  driver.findElement(By.id("s")).sendKeys( "2000" +  Keys.ENTER);
        // driver.findElement(By.name("btnK")).click();


        driver.get("https://cart.bilsteinus.com/");


        for (int i = 0; i < 1; i++) {
            driver.findElement(By.id("engineSelector-year")).sendKeys(Keys.DOWN);
        }
        System.out.println(" - - - - - " + driver.findElement(By.id("engineSelector-year")).getTagName() + " - - - - - ");

        bad_sleep(2500);

        driver.findElement(By.id("engineSelector-make")).sendKeys("R"/*, Keys.DOWN, Keys.DOWN, Keys.DOWN*/);
        System.out.println(" - - - - - " + driver.findElement(By.id("engineSelector-make")).getTagName() + " - - - - - ");

        bad_sleep(2500);

       /* //ждем пока прогрузится model
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("engineSelector-model")));*/

        bad_sleep(2500);

        System.out.println("- - -MODEL -");
        driver.findElement(By.id("engineSelector-model")).sendKeys(/*"3",*/ Keys.DOWN);
        System.out.println(" - - - - - " + driver.findElement(By.id("engineSelector-model")).getTagName() + " - - - - - ");
        bad_sleep(2500);

       /* //ждем пока прогрузится submodel
        dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("engineSelector-submodel")));
*/

        bad_sleep(1500);

        System.out.println("- -SUBMODEL- -");
        driver.findElement(By.id("engineSelector-submodel")).sendKeys(Keys.DOWN);
        System.out.println(" *******" + driver.findElement(By.id("engineSelector-submodel")).getTagName() + " ******* ");
        System.out.println("- - - - - - -");

        bad_sleep(2500);


        dropdown_go (driver/*,By.id("inlineDrop-0")*/);



    }

    public void dropdown_go(WebDriver driver/*, By locator*/) {

        if (isElementPresent(driver,By.id("inlineDrop-0"))) {

            // определяем длину списка 0
            String[] stringArrayList0 = driver.findElement(By.id("inlineDrop-0")).getText().split("\n");
            int listLength0 = stringArrayList0.length-1;
            System.out.println("Длина списка " + By.id("inlineDrop-0") + " = " + listLength0);

            // проходим по списку 0
            for (int i = 0; i < listLength0; i++) {

                driver.findElement(By.id("inlineDrop-0")).sendKeys(Keys.DOWN);

                // определяем длину списка 1
            //    bad_sleep(1500);

                if (isElementPresent(driver, By.id("inlineDrop-1"))) {
                    String[] stringArrayList1 = driver.findElement(By.id("inlineDrop-1")).getText().split("\n");
                    int listLength1 = stringArrayList1.length - 1;
                    System.out.println("Длина списка " + By.id("inlineDrop-1") + " = " + listLength1);

                    // проходим по списку 1
                    for (int j = 0; j < listLength1; j++) {

                        driver.findElement(By.id("inlineDrop-1")).sendKeys(Keys.DOWN);

                        //  TODO: заменить на переменную
                        dropdown_go_click(driver, By.id("inlineDrop-2"));
                    }


                }
            }

            System.out.println(" * * * *  END  * * * * * ");


        }
    }

    public void dropdown_go_click (WebDriver driver, By locator) {

        if (isElementPresent(driver,locator)) {

            // определяем длину списка
            String[] stringArrayList = driver.findElement(locator).getText().split("\n");
            int listLength = stringArrayList.length-1;
            System.out.println("Длина списка " + locator + " = " + listLength);

            // проходим по списку
            for (int i = 0; i < listLength; i++) {
                driver.findElement(locator).sendKeys(Keys.DOWN);

               findNowClick();
            }

            System.out.println("- - - - - - -");


        }
    }

    public void findNowClick() {
        driver.findElement(By.xpath("//div[contains(@id,'inlineRow')]/div[@id='inlineBtn']/a[@id='fyvCartBtn']")).click();

       bad_sleep(1500);

            System.out.println(driver.getCurrentUrl());
    }


    public void bad_sleep(int delay) {    try {
        Thread.sleep(delay);
    } catch (Exception e) {}}

    @After
    public void stop() {
       /* driver.quit();
        driver = null;*/
    }
}
