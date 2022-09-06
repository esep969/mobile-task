package mobile;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static core.Utils.acceptAlert;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GmailTest extends BaseTest {

    @Test
    public void testLoginAndSendEmail() {
        String subject = UUID.randomUUID().toString();
        String text = UUID.randomUUID().toString();
        acceptAlert();
        factory.getLoginActivity().addGoogleAccount("LOGIN", "PASSWORD");
        factory.getInboxActivity().openNewEmailActivity();
        factory.getNewEmailActivity().sendEmail("LOGIN@gmail.com", subject, text);
        factory.getInboxActivity().openEmail(subject);
        assertEquals(text, factory.getEmailActivity().getEmailText());
    }


}
