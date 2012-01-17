package eu.ist.organization.domain;

import java.util.Set;

import eu.ist.organization.util.AccountabilityConfiguration;

public class AccountabilityType extends AccountabilityType_Base {

  public AccountabilityType(String name, Set<Role> roleSet) {
    setName(name);
    for(Role role : roleSet) {
      addRole(role);
    }
  }
  
  /**
   * Creates a new accountability with the given role plays starting on a specific timestamp.
   * @param accountabilityConfiguration the configuration of the accountability defining the mapping between the roles and their respective players
   * @param startTime the starting time the accountability is effective, i.e. active
   * @return the create accountability
   */
  public Accountability createNewAccountability(AccountabilityConfiguration accountabilityConfiguration) {
    Accountability accountability = new Accountability();
    for(Party party : accountabilityConfiguration.getPartyRoleMap().keySet()) {
      RolePlay rolePlay = new RolePlay(party, accountabilityConfiguration.getPartyRoleMap().get(party));
      accountability.addRolePlay(rolePlay);
    }
    EffectivePeriod effectivePeriod = new EffectivePeriod(accountabilityConfiguration.getStartTime());
    accountability.addEffectivePeriod(effectivePeriod);
    addAccountability(accountability);
    return accountability;
  }
  
}
