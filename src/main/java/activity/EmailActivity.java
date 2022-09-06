package activity;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static core.Utils.DEFAULT_TIMEOUT;
import static core.Utils.waitForElementBeVisible;

public class EmailActivity extends BaseActivity {

    @AndroidFindBy(id = "//android.widget.TextView[@resource-id=\"com.google.android.gm:id/subject_and_folder_view\"]")
    private WebElement subjectText;

    @AndroidFindBy(xpath = "//android.webkit.WebView/android.view.View/android.widget.TextView")
    private WebElement emailText;

    public EmailActivity(AndroidDriver driver) {
        super(driver);
    }

    public String getEmailText() {
        waitForElementBeVisible(emailText, DEFAULT_TIMEOUT);
        return emailText.getText();
    }

    public String getSubjectText() {
        waitForElementBeVisible(subjectText, DEFAULT_TIMEOUT);
        return subjectText.getText();
    }
}
