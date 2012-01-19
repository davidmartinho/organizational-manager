package eu.ist.organization.mapper;

import java.util.Set;

import eu.ist.organization.domain.Accountability;
import eu.ist.organization.domain.OrganizationalUnit;
import eu.ist.organization.domain.Party;
import eu.ist.organization.domain.Person;
import eu.ist.organization.domain.RolePlay;

public class JsonMapper implements Mapper {
  
  public String externalizePersonSet(Set<Person> personSet) {
    String result = "[";
    for(Person person : personSet) {
      result += externalize(person)+", ";
    }
    return result.substring(0, result.length()-2)+"]";
  }
  
  public String externalize(Person person) {
    return "{ \"oid\": "+person.getOid()+", \"type\": \"person\", \"name\": \""+person.getName()+"\" }";
  }

  public String externalize(OrganizationalUnit organizationalUnit) {
    return "{ \"oid\": "+organizationalUnit.getOid()+", \"type\": \"organizational-unit\", \"name\": \""+organizationalUnit.getName()+"\" }";
  }

  @Override
  public String externalize(Accountability accountability) {
    return "{ \"oid\": "+accountability.getOid()+", \"type\": \""+accountability.getType().getName()+"\", \"rolePlays\": \""+externalizeRolePlaySet(accountability.getRolePlaySet())+"\" }";
  }

  private String externalizeRolePlaySet(Set<RolePlay> rolePlaySet) {
    String result = "[";
    for(RolePlay rolePlay : rolePlaySet) {
      result += externalize(rolePlay)+", ";
    }
    return result.substring(0, result.length()-2)+"]";
  }

  private String externalize(RolePlay rolePlay) {
    return "{ \"oid\": "+rolePlay.getOid()+", \"role\": \""+rolePlay.getRole().getName()+"\", \"player\": \""+externalize(rolePlay.getPlayer())+"\" }";
  }

  private String externalize(Party player) {
    if(player instanceof Person)
      return externalize((Person)player);
    else
      return externalize((OrganizationalUnit)player);
  }

  @Override
  public String externalizeAccountabilitySet(Set<Accountability> accountabilitySet) {
    String result = "[";
    for(Accountability accountability : accountabilitySet) {
      result += externalize(accountability)+", ";
    }
    return result.substring(0, result.length()-2)+"]";
  }
  
}
