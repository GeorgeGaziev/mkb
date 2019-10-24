package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

public class Applet {

    private String appletId;

    public Applet(String appletName) {
        String setActiveView = "Window.ActiveView = SiebelApp.S_App.GetActiveView()";
        DriverManager.executeScript(setActiveView);

        try {
            String setActiveApplet = "Window.ActiveApplet = Window.ActiveView.GetAppletMap()['" + appletName + "'];";
            DriverManager.executeScript(setActiveApplet);
            this.appletId = DriverManager.executeScript("return Window.ActiveApplet.GetFullId();").toString();
        } catch (WebDriverException wde) {
            System.out.println("Не удалось найти апплет");
        }
    }

    public void onDrillDown(String controlRn) {
        DriverManager.executeScript("Window.ActiveApplet.OnDrillDown('" + controlRn + "')");
    }

    public String getControlOnListText(String controlRn) {
        String idWithRn = controlRn.replaceAll(" ", "_");
        String controlLocator = ("(//*[@id=\"" + appletId + "\"]//*[contains(@id,\"" + idWithRn + "\")])[3]");
        DriverManager.getDriver().findElement(By.xpath(controlLocator)).click();
        controlLocator += "//input";
        return DriverManager.getDriver().findElement(By.xpath(controlLocator)).getAttribute("value");
    }

    public String getControlText(String controlUn) {
        String controlLocator = ("//*[@id=\"" + appletId + "\"]//*[@rn=\"" + controlUn + "\"]");
        return DriverManager.getDriver().findElement(By.xpath(controlLocator)).getAttribute("value");
    }

}
