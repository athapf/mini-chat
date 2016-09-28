package de.thaso.mc.fe.it.common;

import de.thaso.mc.fe.it.base.SeleniumTest;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * OverviewPageTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class OverviewPageTest extends SeleniumTest {

    @Test
    public void testOverviewPage() {
        getDriver().get("http://localhost:9080/mc/minichat.xhtml");
        final WebElement roomElement = getDriver().findElementById("myTable:0:room");
        assertThat(roomElement.getText(),is("test"));
        final WebElement nickElement = getDriver().findElementById("myTable:0:nick");
        assertThat(nickElement.getText(),is("demo"));
    }
}
