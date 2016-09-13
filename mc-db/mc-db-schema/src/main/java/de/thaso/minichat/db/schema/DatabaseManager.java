package de.thaso.minichat.db.schema;

import org.flywaydb.core.Flyway;

import java.util.Properties;

/**
 * DatabaseManager
 *
 * @author thaler
 * @since 13.09.16
 */
public class DatabaseManager {

    private static final String FLYWAY_SCRIPT_PATH = "db/local";
    private static final String JDBC_URL = "javax.persistence.jdbc.url";
    private static final String JDBC_USER = "javax.persistence.jdbc.user";
    private static final String JDBC_PASSWORD = "javax.persistence.jdbc.password";

    public void createDatabase(final Properties properties) {
        Flyway flyway = initFlyway(properties);
        flyway.clean();
        flyway.migrate();
    }

    public void migrateDatabase(final Properties properties) {
        Flyway flyway = initFlyway(properties);
        flyway.migrate();
    }

    private Flyway initFlyway(final Properties properties) {
        Flyway flyway = new Flyway();
        flyway.setDataSource( properties.getProperty(JDBC_URL),
                properties.getProperty(JDBC_USER),
                properties.getProperty(JDBC_PASSWORD));
        flyway.setLocations(FLYWAY_SCRIPT_PATH);
        return flyway;
    }
}
