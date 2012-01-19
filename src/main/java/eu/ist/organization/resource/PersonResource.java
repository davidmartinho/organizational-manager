package eu.ist.organization.resource;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jvstm.Atomic;

import pt.ist.fenixframework.FenixFramework;

import eu.ist.organization.Bootstrap;
import eu.ist.organization.domain.OrganizationalManager;
import eu.ist.organization.domain.Person;
import eu.ist.organization.mapper.JsonMapper;
import eu.ist.organization.mapper.Mapper;

@Path("person")
public class PersonResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public static String getAllThePersons() {
    Bootstrap.init();
    return fetchAllPersons();
  }
  
  @Atomic
  public static String fetchAllPersons() {
    OrganizationalManager om = (OrganizationalManager)FenixFramework.getRoot();
    Mapper jsonMapper = new JsonMapper();
    return jsonMapper.externalizePersonSet(om.getPersonSet());
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{oid}")
  public static String getPerson(@PathParam("oid") String oid) {
    Bootstrap.init();
    return fetchPerson(oid);
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{oid}/accountabilities")
  public static String getPersonAccountabilities(@PathParam("oid") String oid) {
    Bootstrap.init();
    return fetchPersonAccountabilities(oid);
  }
  
  @Atomic
  public static String fetchPersonAccountabilities(String oid) {
    Person person = (Person)OrganizationalManager.fromOID(Long.parseLong(oid));
    Mapper mapper = new JsonMapper();
    return mapper.externalizeAccountabilitySet(person.getActiveAccountabilitySet());
  }  
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public static String createPerson(@FormParam("name") String name) {
    Bootstrap.init();
    return createPersonOnDomainModel(name);
  }
  
  
  @Atomic
  public static String fetchPerson(String oid) {
    Person person = (Person)OrganizationalManager.fromOID(Long.parseLong(oid));
    Mapper mapper = new JsonMapper();
    return mapper.externalize(person);
  }
  
  @Atomic
  public static String createPersonOnDomainModel(String name) {
    OrganizationalManager om = (OrganizationalManager)FenixFramework.getRoot();
    Person p = om.createNewPerson(name);
    Mapper mapper = new JsonMapper();
    return mapper.externalize(p);
  }
  
  
}
