package eu.ist.organization.util;

import java.util.Map;

import org.joda.time.DateTime;

import eu.ist.organization.domain.AccountabilityType;
import eu.ist.organization.domain.Party;
import eu.ist.organization.domain.Role;
import eu.ist.organization.domain.exception.RoleBelongsToDifferentAccountabilityTypeException;

public class AccountabilityConfiguration {

  private AccountabilityType accountabilityType;
  private Map<Party,Role> partyRoleMap;
  private DateTime startTime;
  private DateTime endTime;
  
  public AccountabilityConfiguration(AccountabilityType accountabilityType, Map<Party,Role> partyRoleMap, DateTime startTime) {
    this(accountabilityType, partyRoleMap, startTime, null);
  }
  
  public AccountabilityConfiguration(AccountabilityType accountabilityType, Map<Party,Role> partyRoleMap, DateTime startTime, DateTime endTime) {
    this.accountabilityType = accountabilityType;
    this.partyRoleMap = partyRoleMap;
    this.startTime = startTime;
    this.endTime = endTime;
  }
  
  public void validate() throws RoleBelongsToDifferentAccountabilityTypeException {
    AccountabilityType type = partyRoleMap.values().iterator().next().getAccountabilityType();
    for(Role role : partyRoleMap.values()) {
      if(!role.getAccountabilityType().equals(type)) {
        throw new RoleBelongsToDifferentAccountabilityTypeException(this);
      }
    }
  }
  
  public AccountabilityType getAccountabilityType() {
    return accountabilityType;
  }
  
  public Map<Party,Role> getPartyRoleMap() {
    return partyRoleMap;
  }
  
  public DateTime getStartTime() {
    return startTime;
  }
  
  public DateTime getEndTime() {
    return endTime;
  }
}
