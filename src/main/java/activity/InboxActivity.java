package activity;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static core.Utils.DEFAULT_TIMEOUT;
import static core.Utils.clickWithWait;
import static core.Utils.swipe;
import static core.Utils.waitForElementBeVisible;

public class InboxActivity extends BaseActivity {

    @AndroidFindBy(id = "com.google.android.gm:id/next_button")
    private WebElement gotItPopupButton;

    @AndroidFindBy(id = "com.google.android.gm:id/compose_button")
    private WebElement newEmailButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id = \"com.google.android.gm:id/viewified_conversation_item_view\"]/android.widget.TextView[@resource-id =\"com.google.android.gm:id/subject\"]")
    private List<WebElement> emailSubjects;

    public InboxActivity(AndroidDriver driver) {
        super(driver);
    }

    public void openNewEmailActivity() {
        clickWithWait(gotItPopupButton, DEFAULT_TIMEOUT);
        clickWithWait(newEmailButton, DEFAULT_TIMEOUT);
    }

    public void openEmail(String subject) {
        clickWithWait(getReceivedEmailBySubject(subject), DEFAULT_TIMEOUT);
    }

    private WebElement getReceivedEmailBySubject(String subject) {
        List<WebElement> elements = null;
        for (int i = 0; i < 10; i++) {
            waitForElementBeVisible(newEmailButton, DEFAULT_TIMEOUT);
            elements = emailSubjects.stream().filter(element -> subject.equals(element.getText())).collect(Collectors.toList());
            if (!elements.isEmpty()) {
                swipe(500, 1500);
                break;
            }
        }
        return elements.get(0);
    }

}
