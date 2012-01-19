package eu.ist.organization.mapper;

import java.util.Set;

import eu.ist.organization.domain.Accountability;
import eu.ist.organization.domain.OrganizationalUnit;
import eu.ist.organization.domain.Person;

public interface Mapper {

  String externalize(Person person);
  
  String externalizePersonSet(Set<Person> personSet);
  
  String externalize(OrganizationalUnit organizationalUnit);
  
  String externalize(Accountability accountability);

  String externalizeAccountabilitySet(Set<Accountability> accountabilitySet);

}
