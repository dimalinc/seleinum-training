
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MyFirstTest {

    final static String linksCsvFilePath = "D:\\Java\\Cart.Bil_parse_files\\linksList.csv";
    final static String carMakeModelSubmodelCsvFilePath = "";
    Csv.Writer csvWriter = new Csv.Writer(linksCsvFilePath).delimiter(';');


    private WebDriver driver;
    private WebDriverWait wait;

    private int delay = 5000;

    static ArrayList<String> linksList = new ArrayList<>();
    static ArrayList<String> carMakeModelSubmodelList = new ArrayList<>();

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

        driver.manage().timeouts().implicitlyWait(5, SECONDS);

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
        bad_sleep(2500);


        for (int i = 0; i < 1; i++) {
            driver.findElement(By.id("engineSelector-year")).sendKeys(Keys.DOWN, Keys.DOWN);
        }
        System.out.println(" - - - - - " + driver.findElement(By.id("engineSelector-year")).getTagName() + " - - - - - ");

        bad_sleep(1500);

        driver.findElement(By.id("engineSelector-make")).sendKeys("R"/*, Keys.DOWN, Keys.DOWN, Keys.DOWN*/);
        //   System.out.println(" - - - - - " + driver.findElement(By.id("engineSelector-make")).getTagName() + " - - - - - ");

        bad_sleep(2500);

       /* //ждем пока прогрузится model
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("engineSelector-model")));*/

        bad_sleep(1500);

        System.out.println("- - -MODEL -");
        driver.findElement(By.id("engineSelector-model")).sendKeys(/*"3",*/ Keys.DOWN);
        //  System.out.println(" - - - - - " + driver.findElement(By.id("engineSelector-model")).getTagName() + " - - - - - ");
        bad_sleep(1500);

       /* //ждем пока прогрузится submodel
        dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("engineSelector-submodel")));
*/

        bad_sleep(1500);

        System.out.println("- -SUBMODEL- -");
        // определяем длину списка - -SUBMODEL- -
        String[] stringArrayListSUBMODEL = driver.findElement(By.id("engineSelector-submodel")).getText().split("\n");
        int listLengthSUBMODEL = stringArrayListSUBMODEL.length - 1;
        //  System.out.println("Длина списка " + By.id("inlineDrop-0") + " = " + listLengthSUBMODEL);

        // TODO: убрать следующую строку после отладки выбора не всех inlineDrop-2
        // driver.findElement(By.id("engineSelector-submodel")).sendKeys("R"/*Keys.DOWN*/);

        for (int i = 0; i < /*listLengthSUBMODEL*/1; i++) {
            driver.findElement(By.id("engineSelector-submodel")).sendKeys(Keys.DOWN);
            bad_sleep(1500);
            dropdown_inlineDrop_0_go(driver/*,By.id("inlineDrop-0")*/);
            driver.findElement(By.id("engineSelector-submodel")).sendKeys(Keys.DOWN);
        }


    }

    public void dropdown_inlineDrop_0_go(WebDriver driver/*, By locator*/) {

        if (isElementPresent(driver, By.id("inlineDrop-0"))) {

            // определяем длину списка 0
            String[] stringArrayList0 = driver.findElement(By.id("inlineDrop-0")).getText().split("\n");
            int listLength0 = stringArrayList0.length - 1;
            //  System.out.println("Длина списка " + By.id("inlineDrop-0") + " = " + listLength0);

            // проходим по списку 0
            for (int i = 0; i < listLength0; i++) {

                driver.findElement(By.id("inlineDrop-0")).sendKeys(Keys.DOWN);

                // определяем длину списка 1
                //    bad_sleep(1500);

                if (isElementPresent(driver, By.id("inlineDrop-1"))) {
                    String[] stringArrayList1 = driver.findElement(By.id("inlineDrop-1")).getText().split("\n");
                    int listLength1 = stringArrayList1.length - 1;
                    //    System.out.println("Длина списка " + By.id("inlineDrop-1") + " = " + listLength1);

                    // проходим по списку 1
                    for (int j = 0; j < listLength1; j++) {

                        driver.findElement(By.id("inlineDrop-1")).sendKeys(Keys.DOWN);

                        dropdown_inlineDrop2_go(driver);

                    }
                }

            }


        } else findNowClick();
    }

    public void dropdown_inlineDrop2_go(WebDriver driver) {

        if (isElementPresent(driver, By.id("inlineDrop-2"))) {

            // определяем длину списка
            String[] stringArrayList = driver.findElement(By.id("inlineDrop-2")).getText().split("\n");
            int listLength = stringArrayList.length - 1;
            //  System.out.println("Длина списка " + locator + " = " + listLength);

            // проходим по списку
            for (int i = 0; i < listLength; i++) {
                driver.findElement(By.id("inlineDrop-2")).sendKeys(Keys.DOWN);

                System.out.println(" FIND_NOW clicked from _-*dropdown_inlineDrop2_go*-_");
                findNowClick();
            }

            System.out.println(" * * * *  END  of inlineDrop-2 * * * * ");
            linksList.add(" * * * *  END  of inlineDrop-2 * * * * ");
            carMakeModelSubmodelList.add(" * * * *  END  of inlineDrop-2 * * * * ");
        }


    }

    public void findNowClick() {
        if (isElementPresent(driver,
                By.xpath("//div[contains(@id,'inlineRow')]/div[@id='inlineBtn']/a[@id='fyvCartBtn']"))) {
            driver.findElement(By.xpath("//div[contains(@id,'inlineRow')]/div[@id='inlineBtn']/a[@id='fyvCartBtn']")).click();

            bad_sleep(1500);

            String currentUrl = driver.getCurrentUrl();

            System.out.println(currentUrl);
            linksList.add(currentUrl);
            writePageToCsv();

            // partNumbersParseFromUrl(currentUrl);

            carMakeModelSubmodelList.add(driver.findElement(By.xpath
                    ("//div[@id='dnn_ctr459_ModuleContent']/div[@id='ProductResults']/h1[@class='searchFeedback']")).getTagName());

            System.out.println("- - FIND_NOW clicked - -");

        }
    }

    public void partNumbersParseFromUrl(String pageUrl) {
        Document doc = null;
        try {
            doc = Jsoup.connect(pageUrl).get();
        } catch (IOException e) {
        }

        Elements partsNums = doc.getElementsByClass("descBox");


    }


    public void bad_sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
        }
    }

    public void writePageToCsv() {


        csvWriter.value(driver.findElement(
                By.xpath("//div[@id='dnn_ctr459_ModuleContent']/div[@id='ProductResults']/h1[@class='searchFeedback']"))
                .getText());

        csvWriter.value(driver.getCurrentUrl());

        List<WebElement> descBoxElements = driver.findElements(By.className("descBox"));

        for (WebElement webElement : descBoxElements) {
            csvWriter.value(webElement.getText());
        }

        csvWriter.newLine().flush();
    }

    public void writeToTXT() {
// Записываем строку в текстовый файл в двух кодировках (Cp866 и Cp1251)
        OutputStream os, os2;
        try {
            os = new FileOutputStream("D:/Java/Cart.Bil_parse_files/linksList.txt"); // класс записи байтов в файл
// Записываем строку в кодировке Cp866
            for (String string : linksList) {
                os.write(string.getBytes("Cp866"));
                os.write("\n".getBytes("Cp866"));
// Записываем строку в кодировке Cp1251
                //  os.write( string.getBytes("Cp1251") );
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            os2 = new FileOutputStream("D:/Java/Cart.Bil_parse_files/carMakeModelSubmodelList.txt"); // класс записи байтов в файл
// Записываем строку в кодировке Cp866
            for (String string : carMakeModelSubmodelList) {
                os2.write(string.getBytes("Cp866"));
                os2.write("\n".getBytes("Cp866"));
// Записываем строку в кодировке Cp1251
                //  os.write( string.getBytes("Cp1251") );
            }
            os2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @After
    public void stop() {

        System.out.println("--------FINALLY  writing linksList to txt --------");

        System.out.println("-------carMakeModelSubmodelList to txt--------------");

        writeToTXT();

        csvWriter.close();

       /* driver.quit();
        driver = null;*/
    }
}
