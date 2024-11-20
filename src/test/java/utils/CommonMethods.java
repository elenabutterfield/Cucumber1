package utils;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() throws IOException {

        String browser = ConfigReader.read("browser");
        switch (browser) {
                case "Chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                WebDriver driver = new ChromeDriver(options);
                break;

                case "Firefox":
                driver = new FirefoxDriver();
                break;
                case "Edge":
                driver = new EdgeDriver();
                break;
                default:
                throw new RuntimeException("Invalid Browser Name");
        }
        
        //  maximize the window
        driver.manage().window().maximize();
        //  got to the url
        driver.get(ConfigReader.read("url"));
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        initializePageObjects();

    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    public static void sendText(String text, WebElement element){
        element.clear();
        element.sendKeys(text);
    }
    public static WebDriverWait getwait(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        return  wait;
    }
    public static void waitForElementToBeClickable(WebElement element){
        getwait().until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void clickWithExplicitWait(WebElement element){
        waitForElementToBeClickable(element);
        element.click();
    }
    public static void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();
    }

    public  static  void selectFromDropDown(WebElement dropDown,int index){
        Select sel=new Select(dropDown);
        sel.selectByIndex(index);
    }
    public  static  void selectFromDropDown(WebElement dropDown,String visibleText){
        Select sel=new Select(dropDown);
        sel.selectByVisibleText(visibleText);
    }
    public  static  void selectFromDropDown(String value, WebElement dropDown) {
        Select sel = new Select(dropDown);
        sel.selectByValue(value);
    }
    public static byte[] takeScreenshot(String fileName){

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        //it is not going to take another screenshot, instead it will consider picByte
        //i.e array of byte as a source file for transfer
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {//add path to the constants for screenshot yourself to resolve the error in red
            FileUtils.copyFile(sourceFile, new File
                    (Constants.SCREENSHOT_FILEPATH+fileName+
                            " "+ getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }
    public static String getTimeStamp(String pattern) {

        Date date = new Date();
        //yyyy-MM-dd-hh-mm-ss
        //dd-MM-yyyy-mm-hh-ss
        //to get the date in my acceptable format, i need to format it
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();",element);

    }

}

