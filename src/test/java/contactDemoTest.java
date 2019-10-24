import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;
import pages.ContactCardPage;
import pages.ContactListPage;
import pages.HomePage;
import pages.SiebelLoginPage;
import utils.DriverManager;

import static org.hamcrest.core.Is.is;

public class contactDemoTest extends BasePageObject {

    @Rule
    public ErrorCollector collector = new ErrorCollector();
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        logInCrm("sadmin", "sadmin");
        HomePage homePage = new HomePage();
        homePage.gotoContactView();
    }

    @Test
    public void contactDemoTest() {
        ContactListPage contactListPage = new ContactListPage();
        contactListPage.findRecordOnListApplet("[Last Name] = 'Иванов'");
        String lastNameOnList = contactListPage.getContactListApplet().getControlOnListText("Last Name");
        String firstNameOnList = contactListPage.getContactListApplet().getControlOnListText("First Name");
        String middleNameOnList = contactListPage.getContactListApplet().getControlOnListText("Middle Name");

        contactListPage.drillDownOnControl("Last Name");

        ContactCardPage contactCardPage = new ContactCardPage();
        String lastNameOnCard = contactCardPage.getContactCardFormApplet().getControlText("LastName");
        String firstNameOnCard = contactCardPage.getContactCardFormApplet().getControlText("FirstName");
        String middleNameOnCard = contactCardPage.getContactCardFormApplet().getControlText("MiddleName");

        collector.checkThat("Фамилия на списке не сходится со значением на карточке", lastNameOnList, is(lastNameOnCard));
        collector.checkThat("Имя на списке не сходится со значением на карточке", firstNameOnList, is(firstNameOnCard));
        collector.checkThat("Отчество на списке не сходится со значением на карточке", middleNameOnList, is(middleNameOnCard));
    }

    @Step
    private void logInCrm(String username, String password) {
        driver.get("http://192.168.71.34/sibur/start.swe?SWECmd=AutoOn");
        SiebelLoginPage siebelLoginPage = new SiebelLoginPage();
        siebelLoginPage.loginAs(username, password);
    }

    @After
    public void shutDown() {
        DriverManager.executeScript("SWEClearHistoryGotoURL (\"/sibur/start.swe?SWENeedContext=false&SWECmd=Logoff\");");
        DriverManager.quitDriver();
    }
}
