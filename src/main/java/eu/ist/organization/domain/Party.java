package eu.ist.organization.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class Party extends Party_Base {
  
  public Set<Accountability> getActiveAccountabilitySetForRole(Role role) {
    Set<Accountability> activeAccountabilitySet = new HashSet<Accountability>();
    for(RolePlay rolePlay : getRolePlaySet()) {
      Accountability accountability = rolePlay.getAccountability();
      if(accountability.isActive()) {
        activeAccountabilitySet.add(accountability);
      }
    }
    return activeAccountabilitySet;
  }
  
}
