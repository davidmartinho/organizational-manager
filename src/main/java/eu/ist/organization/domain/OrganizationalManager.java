package eu.ist.organization.domain;

import java.util.HashSet;
import java.util.Set;
import eu.ist.organization.domain.exception.ActiveAccountabilityExistsException;
import eu.ist.organization.util.AccountabilityConfiguration;

public class OrganizationalManager extends OrganizationalManager_Base {
  
  public OrganizationalUnit createNewOrganizationalUnit(String name) {
    OrganizationalUnit organizationalUnit = new OrganizationalUnit(name);
    addOrganizationalUnit(organizationalUnit);
    return organizationalUnit;
  }
  
  public Person createNewPerson(String name) {
    Person person = new Person(name);
    addPerson(person);
    return person;
  }
  
  public Role createNewRole(String roleName) {
    Role role = new Role(roleName);
    addRole(role);
    return role;
  }
  
  public AccountabilityType createNewAccountabilityType(String name, Set<Role> roleSet) {
    AccountabilityType accountabilityType = new AccountabilityType(name, roleSet);
    addAccountabilityType(accountabilityType);
    return accountabilityType;
  }
  
  public Accountability createNewAccountability(AccountabilityConfiguration accountabilityConfiguration) throws ActiveAccountabilityExistsException {
    Accountability accountability = findAccontabilityWithConfiguration(accountabilityConfiguration);
    if(accountability==null) {
      accountability = accountabilityConfiguration.getAccountabilityType().createNewAccountability(accountabilityConfiguration);
    } else {
      if(accountability.isActive()) {
        throw new ActiveAccountabilityExistsException(accountability);
      } else {
        accountability.addNewEffectivePeriodStartingOn(accountabilityConfiguration.getStartTime());
      }
    }
    return accountability;
  }
  
  public Set<Party> getActivePlayerSetOfRole(Role role) {
    Set<Party> activePlayerSet = new HashSet<Party>();
    for(RolePlay rolePlay : role.getRolePlaySet()) {
      if(rolePlay.getAccountability().isActive()) {
        activePlayerSet.add(rolePlay.getPlayer());
      }
    }
    return activePlayerSet;
  }
  
  public Role getRoleByName(String roleName) {
    for(Role role : getRoleSet()) {
      if(role.getName().equals(roleName)) {
        return role;
      } 
    }
    return null;
  }
  
  public Accountability findAccontabilityWithConfiguration(AccountabilityConfiguration accountabilityConfiguration) {
    for(Accountability accountability : accountabilityConfiguration.getAccountabilityType().getAccountabilitySet()) {
      if(accountability.hasConfiguration(accountabilityConfiguration.getPartyRoleMap())) {
        return accountability;
      }
    }
    return null;
  }

  public Set<Accountability> getActiveAccountabilitySet(AccountabilityType accountabilityType, Role roleA, Role roleB, Party playerA, Party playerB) {
    Set<Accountability> activeAccountabilitySet = new HashSet<Accountability>();
    for(Accountability accountability : accountabilityType.getAccountabilitySet()) {
      if(accountability.isActive()) {
        activeAccountabilitySet.add(accountability);
      }
    }
    return activeAccountabilitySet;
  }

}
