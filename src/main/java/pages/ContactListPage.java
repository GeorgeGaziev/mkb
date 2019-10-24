package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Applet;
import utils.DriverManager;

/**
 * Страница "Мои контакты"
 */
public class ContactListPage extends HomePage {

    //@FindBy(xpath = "//*[@title=\"Контакты моих компаний Аплет списка\"]//*[contains(@class,\"siebui-icon-newquery\")]")
    @FindBy(xpath = "//*[@rn=\"Contacts Company List Applet\"]//*[contains(@class,\"siebui-icon-newquery\")]")
    private WebElement newQueryButton;
    //@FindBy(xpath = "//*[@title=\"Контакты моих компаний Аплет списка\"]//*[contains(@class,\"siebui-icon-exec\")]")
    @FindBy(xpath = "//*[@rn=\"Contacts Company List Applet\"]//*[contains(@class,\"siebui-icon-exec\")]")
    private WebElement executeQueryButton;
    //@FindBy(xpath = "//*[@title=\"Контакты моих компаний Аплет списка\"]//input[contains(@class,\"siebui-ctrl-input\")]")
    @FindBy(xpath = "//*[@rn=\"Contacts Company List Applet\"]//input[contains(@class,\"siebui-ctrl-input\")]")
    private WebElement appletQueryControl;
    private Applet contactListApplet;

    public ContactListPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @Step
    public void findRecordOnListApplet(String query) {
        click(newQueryButton);
        sendKeys(appletQueryControl, query);
        click(executeQueryButton);
    }

    @Step
    public void drillDownOnControl(String controlRn) {
        waitElementToBeClickable(newQueryButton);
        contactListApplet = getContactListApplet();
        contactListApplet.onDrillDown(controlRn);
    }

    public Applet getContactListApplet() {
        waitUntilPageLoaded();
        if (contactListApplet == null) {
            return new Applet("Contacts Company List Applet");
        } else return contactListApplet;

    }
}
