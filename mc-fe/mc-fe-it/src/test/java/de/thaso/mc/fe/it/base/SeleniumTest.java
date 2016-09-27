package de.thaso.mc.fe.it.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

/**
 * SeleniumTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class SeleniumTest {

    private FirefoxDriver driver;

    @Rule
    public ScreenShotRule screenShotRule = new ScreenShotRule();

    @Before
    public void setUpSeleniumDriver() {
        final FirefoxBinary binary = new FirefoxBinary(new File("/usr/bin/firefox"));
        binary.setEnvironmentProperty("DISPLAY", ":20");
        driver = new FirefoxDriver(binary, null);
        screenShotRule.setBrowser(driver);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);

    }

    @After
    public void tearDownSeleniumDriver() {
        driver.close();
    }

    protected FirefoxDriver getDriver() {
        return driver;
    }
}
