package eu.ist.organization.domain;

import java.util.HashSet;
import java.util.Set;

public class Person extends Person_Base {

  public Person(String name) {
    setName(name);
  }
  
  public Set<Role> getActiveRoleSet() {
    Set<Role> activeRoleSet = new HashSet<Role>();
    for(RolePlay rolePlay : getRolePlaySet()) {
      if(rolePlay.getAccountability().isActive()) {
        activeRoleSet.add(rolePlay.getRole());
      }
    }
    return activeRoleSet; 
  }
  
  public String toString() {
    return "Person: "+getName();
  }

  public Set<Accountability> getActiveAccountabilitySet() {
    Set<Accountability> activeAccountabilitySet = new HashSet<Accountability>();
    for(RolePlay rolePlay : getRolePlaySet()) {
      if(rolePlay.getAccountability().isActive()) {
        activeAccountabilitySet.add(rolePlay.getAccountability());
      }
    }
    return activeAccountabilitySet; 
  }
}
