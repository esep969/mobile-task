package core;

import activity.EmailActivity;
import activity.InboxActivity;
import activity.LoginActivity;
import activity.NewEmailActivity;
import io.appium.java_client.android.AndroidDriver;

import java.util.Objects;

public class ActivityFactory {

    private AndroidDriver driver;
    private LoginActivity loginActivity;
    private InboxActivity inboxActivity;
    private NewEmailActivity newEmailActivity;
    private EmailActivity emailActivity;

    public ActivityFactory(AndroidDriver driver) {
        this.driver = driver;
    }

    public LoginActivity getLoginActivity() {
        if(Objects.isNull(loginActivity)) {
            loginActivity = new LoginActivity(driver);
        }
        return loginActivity;
    }

    public InboxActivity getInboxActivity() {
        if(Objects.isNull(inboxActivity)) {
            inboxActivity = new InboxActivity(driver);
        }
        return inboxActivity;
    }

    public NewEmailActivity getNewEmailActivity() {
        if(Objects.isNull(newEmailActivity)) {
            newEmailActivity = new NewEmailActivity(driver);
        }
        return newEmailActivity;
    }

    public EmailActivity getEmailActivity() {
        if(Objects.isNull(emailActivity)) {
            emailActivity = new EmailActivity(driver);
        }
        return emailActivity;
    }

}
