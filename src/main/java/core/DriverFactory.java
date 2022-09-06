package core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.Objects;

public class DriverFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        if(Objects.isNull(driver)) {
            LOGGER.info("Create driver with capabilities: {}", getDesiredCapabilities());
            try {
                driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), getDesiredCapabilities());
                driver.startRecordingScreen();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            }
            catch (MalformedURLException e){
               LOGGER.error(e.getMessage());
            }
        }
        return driver;
    }

    public static void reopenApp() {
        LOGGER.info("Terminate app");
        driver.terminateApp("com.google.android.gm");
        LOGGER.info("Wait for 5 sec");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Start app");
        driver.activateApp("com.google.android.gm");
    }

    public static void saveVideo() {
        String video = driver.stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(video);
        try {
            FileUtils.writeByteArrayToFile(new File("record.mp4"), decode);
        } catch (IOException e) {
            LOGGER.error("Unable to save video");
        }
    }
    public static void closeDriver(){
        if(!Objects.isNull(driver)) {
            driver.quit();
            driver = null;
        }
    }

    private static DesiredCapabilities getDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("appPackage", "com.google.android.gm");
        capabilities.setCapability("appActivity", "com.google.android.gm.ConversationListActivityGmail");
        return capabilities;
    }

}
