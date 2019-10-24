package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver = null;

    private static Wait<WebDriver> wait;

    private static JavascriptExecutor jsExecutor;

    private static int delay = 40;
    private static int sleepTime = 250;


    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "webdrivers\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.addArguments(Arrays.asList("--incognito",
                "--allow-file-access-from-files",
                "--disable-file-cookies",
                "--disable-web-security",
                "--disable-notifications",
                "--allow-insecure-localhost",
                "--allow-running-insecure-content",
                "--ignore-urlfetcher-cert-requests",
                "--disable-gpu"
        ));
        option.setAcceptInsecureCerts(true);
        option.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        driver = new ChromeDriver(option);
        generalDriverSetting();
        return driver;
    }

    private static void generalDriverSetting() {
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, delay, sleepTime);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static Object executeScript(String script) {
        return jsExecutor.executeScript(script);
    }
}
