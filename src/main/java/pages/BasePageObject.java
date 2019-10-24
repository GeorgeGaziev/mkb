package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public abstract class BasePageObject {

    private static By siebelLoaderLocator = By.cssSelector(".siebui-busy");
    private WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 30);
    private ExpectedCondition<Boolean> documentReadyState = new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            boolean ready = false;
            try {
                DriverManager.executeScript("return SiebelApp.S_App.GetActiveView().GetName()");
                ready = true;
            } catch (WebDriverException e) {
                //страница пока не загрузилась
            }
            return ready;
        }
    };


    public BasePageObject() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    void click(WebElement element) {
        waitElementToBeClickable(element);
        element.click();
    }

    void sendKeys(WebElement element, String charSequence) {
        waitElementToBeClickable(element);
        element.sendKeys(charSequence);
    }

    //ожидание доступности элемента
    void waitElementToBeClickable(WebElement element) {
        wait.until(documentReadyState);
        wait.until(elementToBeClickable(element));
        wait.until(invisibilityOfElementLocated(siebelLoaderLocator));
    }
}