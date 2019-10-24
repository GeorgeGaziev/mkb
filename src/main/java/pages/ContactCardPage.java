package pages;

import utils.Applet;

/**
 * Страница карточки контакта
 */
public class ContactCardPage extends HomePage {

    private Applet contactCardFormApplet;

    public Applet getContactCardFormApplet() {
        waitUntilPageLoaded();
        if (contactCardFormApplet == null) {
            contactCardFormApplet = new Applet("Contact Form Applet");
        }
        return contactCardFormApplet;
    }
}
