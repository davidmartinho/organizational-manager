package eu.ist.organization.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jvstm.Atomic;

import pt.ist.fenixframework.FenixFramework;

import eu.ist.organization.Bootstrap;
import eu.ist.organization.domain.OrganizationalManager;
import eu.ist.organization.domain.Person;
import eu.ist.organization.domain.Role;
import eu.ist.organization.domain.RolePlay;

@Path("accountability")
public class AccountabilityTypeResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{accountabilityId}")
  public static String getAccountability(@PathParam("accountabilityId") String accountabilityId) {
    Bootstrap.init();
    return fetchData(accountabilityId);
  }
  
  @Atomic
  public static String fetchData(String accountabilityId) {
    OrganizationalManager om = (OrganizationalManager)FenixFramework.getRoot();
    Role master = om.getRoleByName("Apprentice");
    String result = "[";
    for(RolePlay rolePlay : master.getRolePlaySet()) {
      return " { \""+((Person)rolePlay.getPlayer()).getName()+"\": \""+accountabilityId+"\" }";
    }
    result += "]";
    return result;
  }
  
  
}
