package eu.ist.organization.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pt.ist.fenixframework.FenixFramework;
import jvstm.Atomic;
import eu.ist.organization.Bootstrap;
import eu.ist.organization.domain.OrganizationalManager;
import eu.ist.organization.domain.OrganizationalUnit;
import eu.ist.organization.domain.Party;
import eu.ist.organization.domain.Person;
import eu.ist.organization.mapper.JsonMapper;
import eu.ist.organization.mapper.Mapper;

@Path("organizational-unit")
public class OrganizationalUnitResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{oid}")
  public static String getOrganizationalUnit(@PathParam("oid") String oid) {
    Bootstrap.init();
    return getOrganizationalUnitJson(oid);
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public static String createOrganizationalUnit(@FormParam("name") String name) {
    Bootstrap.init();
    return createOrganizationalUnitOnDomainModel(name);
  }
  
  
  @Atomic
  public static String getOrganizationalUnitJson(String oid) {
    OrganizationalUnit organizationalUnit = (OrganizationalUnit)OrganizationalManager.fromOID(Long.parseLong(oid));
    Mapper mapper = new JsonMapper();
    return mapper.externalize(organizationalUnit);
  }
  
  
  @Atomic
  public static String createOrganizationalUnitOnDomainModel(String name) {
    OrganizationalManager om = (OrganizationalManager)FenixFramework.getRoot();
    OrganizationalUnit orgUnit = om.createNewOrganizationalUnit(name);
    Mapper mapper = new JsonMapper();
    return mapper.externalize(orgUnit);
  }
  
  
}
