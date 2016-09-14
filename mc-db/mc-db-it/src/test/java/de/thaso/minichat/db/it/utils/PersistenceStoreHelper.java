package de.thaso.minichat.db.it.utils;

import de.thaso.minichat.db.schema.PropertiesManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * PersistenceStoreHelper
 *
 * @author thaler
 * @since 13.09.16
 */
public class PersistenceStoreHelper {

  private static final EntityManagerFactory entityManagerFactory;
  private static final EntityManager entityManagerAud;

  static {
      entityManagerFactory = Persistence.createEntityManagerFactory("testdb", getConnectionProperties());
      entityManagerAud = entityManagerFactory.createEntityManager();
  }

  public static Properties getConnectionProperties() {
      return PropertiesManager.readDevelopProperties();
  }

  public static EntityManager getEntityManager() {
    return entityManagerAud;
  }
}
