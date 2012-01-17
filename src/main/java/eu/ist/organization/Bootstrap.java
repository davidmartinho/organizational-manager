package eu.ist.organization;

import eu.ist.organization.domain.OrganizationalManager;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

public class Bootstrap {

  public static void init() {
    FenixFramework.initialize(new Config() {{
      domainModelPath = PropertiesManager.getProperty("dml.filename");
      dbAlias = PropertiesManager.getProperty("sql.alias");
      dbUsername = PropertiesManager.getProperty("sql.username");
      dbPassword = PropertiesManager.getProperty("sql.password");
      rootClass = OrganizationalManager.class;
    }});
  }
}
