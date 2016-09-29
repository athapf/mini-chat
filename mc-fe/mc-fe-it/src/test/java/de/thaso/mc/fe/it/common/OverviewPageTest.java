package de.thaso.mc.fe.it.common;

import de.thaso.mc.fe.it.base.SeleniumTest;
import org.glassfish.embeddable.BootstrapProperties;
import org.glassfish.embeddable.CommandResult;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * OverviewPageTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class OverviewPageTest extends SeleniumTest {

    private static GlassFish glassfish;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Opening the container");
        final BootstrapProperties bootstrapProperties = new BootstrapProperties();
        final GlassFishProperties glassFishProperties = new GlassFishProperties();
        //glassFishProperties.setConfigFileURI("file:./src/test/resources/glassfish/domains/domain1/config/domain.xml");
        glassFishProperties.setPort("http-listener",9080);
        glassfish = GlassFishRuntime.bootstrap(bootstrapProperties).newGlassFish(glassFishProperties);
        glassfish.start();

        addDatabasePool();

        deployApp();
    }

    private static void addDatabasePool() throws GlassFishException {
        CommandResult res;
        String poolProperties = "user=mcadmin:password=mcadmin:url=jdbc\\:postgresql\\://localhost\\:5432/minichat";
        res = glassfish.getCommandRunner().run("create-jdbc-connection-pool",
                "--ping", "--restype=javax.sql.ConnectionPoolDataSource", "--datasourceClassname",
                "org.postgresql.ds.PGConnectionPoolDataSource", "--property", poolProperties,
                "MiniChatPool");
        System.out.println(res.getOutput());

        res = glassfish.getCommandRunner().run("create-jdbc-resource",
                "--connectionpoolid", "MiniChatPool", "jdbc/minichatdb");
        System.out.println(res.getOutput());
    }

    private static void deployApp() throws GlassFishException {
        File war = new File("./target/war");
        Deployer deployer = glassfish.getDeployer();
        deployer.deploy(war);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        glassfish.stop();
        System.out.println("Closing the container");
    }

    @Test
    public void testOverviewPage() {
        getDriver().get("http://localhost:9080/mc/minichat.xhtml");
        final WebElement roomElement = getDriver().findElementById("myTable:0:room");
        assertThat(roomElement.getText(),is("test"));
        final WebElement nickElement = getDriver().findElementById("myTable:0:nick");
        assertThat(nickElement.getText(),is("demo"));
    }
}
