package activity;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static core.Utils.DEFAULT_TIMEOUT;
import static core.Utils.LONG_TIMEOUT;
import static core.Utils.clickWithWait;
import static core.Utils.swipe;
import static core.Utils.waitForElementBeVisible;

public class LoginActivity extends BaseActivity {

    @AndroidFindBy(id = "com.google.android.gm:id/welcome_tour_got_it")
    private WebElement gotItButton;

    @AndroidFindBy(id = "com.google.android.gm:id/setup_addresses_add_another")
    private WebElement addEmailAddressButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Google\")")
    private WebElement googleAccountButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"identifierId\"]")
    private WebElement emailOrPhoneInput;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"password\"]//android.widget.EditText")
    private WebElement passwordInput;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Next\")")
    private WebElement nextButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"signinconsentNext\"]/android.widget.Button")
    private WebElement iAgreeButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Back up to Google Drive\")")
    private WebElement backupToGoogleDriveLabel;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"ACCEPT\")")
    private WebElement acceptButton;

    @AndroidFindBy(id = "com.google.android.gm:id/action_done")
    private WebElement takeMeToGmailButton;

    @AndroidFindBy(id = "com.google.android.gm:id/owner")
    private WebElement accountAddress;
    public LoginActivity(AndroidDriver driver) {
        super(driver);
    }

    public void addGoogleAccount(String login, String password) {
        clickWithWait(gotItButton, DEFAULT_TIMEOUT);
        clickWithWait(addEmailAddressButton, DEFAULT_TIMEOUT);
        clickWithWait(googleAccountButton, DEFAULT_TIMEOUT);
        waitForElementBeVisible(emailOrPhoneInput, LONG_TIMEOUT);
        emailOrPhoneInput.sendKeys(login);
        clickWithWait(nextButton, DEFAULT_TIMEOUT);
        waitForElementBeVisible(passwordInput, DEFAULT_TIMEOUT);
        passwordInput.sendKeys(password);
        clickWithWait(nextButton, DEFAULT_TIMEOUT);
        clickWithWait(iAgreeButton, LONG_TIMEOUT);
        waitForElementBeVisible(backupToGoogleDriveLabel, LONG_TIMEOUT);
        swipe(1500, 500);
        clickWithWait(acceptButton, DEFAULT_TIMEOUT);
        waitForElementBeVisible(accountAddress, LONG_TIMEOUT);
        clickWithWait(takeMeToGmailButton, DEFAULT_TIMEOUT);
    }

}
