package mobile;

import core.ActivityFactory;
import core.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    private AndroidDriver driver;
    protected ActivityFactory factory;

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getDriver();
        factory = new ActivityFactory(driver);
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.saveVideo();
        DriverFactory.closeDriver();
    }
}
