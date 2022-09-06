package core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Utils {

    public static final long DEFAULT_TIMEOUT = 10;
    public static final long LONG_TIMEOUT = 30;


    private Utils(){}

    public static void waitForElementBeVisible(WebElement element, long timeout) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.of(timeout, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void clickWithWait(WebElement element, long timeout) {
        waitForElementBeVisible(element, timeout);
        element.click();
    }
    
    public static void waitForAlertPresent(long timeout) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.of(timeout, ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void acceptAlert() {
        waitForAlertPresent(DEFAULT_TIMEOUT);
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.accept();
    }

    public static void swipe(int from, int to) {
        Point source = new Point(500, from);
        Point target = new Point(500, to);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), source.x, source.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),target.x, target.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverFactory.getDriver().perform(Arrays.asList(swipe));
    }
}
