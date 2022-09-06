package activity;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static core.Utils.DEFAULT_TIMEOUT;
import static core.Utils.waitForElementBeVisible;

public class NewEmailActivity extends BaseActivity {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = \"com.google.android.gm:id/peoplekit_autocomplete_chip_group\"]/android.widget.EditText")
    private WebElement toInput;

    @AndroidFindBy(id = "com.google.android.gm:id/subject")
    private WebElement subjectInput;

    @AndroidFindBy(id = "com.google.android.gm:id/wc_body_layout")
    private WebElement textBody;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.google.android.gm:id/wc_body_layout\"]//android.widget.EditText")
    private WebElement textInput;

    @AndroidFindBy(id = "com.google.android.gm:id/send")
    private WebElement sendButton;

    public NewEmailActivity(AndroidDriver driver) {
        super(driver);
    }

    public void sendEmail(String to, String subject, String text) {
        waitForElementBeVisible(toInput, DEFAULT_TIMEOUT);
        subjectInput.sendKeys(subject);
        textBody.click();
        textInput.sendKeys(text);
        toInput.sendKeys(to);
        sendButton.click();
    }
}
