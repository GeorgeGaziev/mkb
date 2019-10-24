package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

public class SiebelLoginPage extends BasePageObject {

    @FindBy(name = "SWEUserName")
    private WebElement usernameField;
    @FindBy(name = "SWEPassword")
    private WebElement passwordField;
    @FindBy(css = ".siebui-login-btn input")
    private WebElement loginButton;

    public SiebelLoginPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @Step
    public void loginAs(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

}