package tests.explore;

import tests.BaseTest;

public class TestExplicitWait extends BaseTest {
    private static final long WAIT_TIME = 10L;
    @Test
    public void locatedBy(){
        // Will throw TimeOut
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("CD")));
    }
    @Test
    public void locatedByElement(){
        // Will throw NoSuchElement
        new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME)).until(ExpectedConditions.visibilityOf(
                getDriver().findElement(AppiumBy.accessibilityId("CD"))));
    }
}
