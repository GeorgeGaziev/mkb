package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

public class HomePage extends BasePageObject {

    @FindBy(xpath = "//*[@rn=\"Contacts Screen\"]//a")
    private WebElement contactTab;

    @FindBy(css = ".siebui-logo")
    private WebElement logo;

    public HomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void gotoContactView() {
        click(contactTab);
    }

    void waitUntilPageLoaded() {
        waitElementToBeClickable(logo);
    }
}
