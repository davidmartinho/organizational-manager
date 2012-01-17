package eu.ist.organization.domain;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.joda.time.DateTime;

import eu.ist.organization.domain.exception.EffectivePeriodAlreadyTerminatedException;
import eu.ist.organization.domain.exception.EffectivePeriodStartedAfterTerminationTimeException;

public class Accountability extends Accountability_Base {

  
  public boolean isActive(DateTime dateTime) {
    for(EffectivePeriod effectivePeriod : getEffectivePeriodSet()) {
      if(effectivePeriod.isActive(dateTime)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isActive() {
    return isActive(new DateTime());
  }
  
  public void addNewEffectivePeriodStartingOn(DateTime startTime) {
    addEffectivePeriod(new EffectivePeriod(startTime));
  }

  public boolean hasConfiguration(Map<Party, Role> partyRoleMap) {
    for(RolePlay rolePlay : getRolePlaySet()) {
      Party player = rolePlay.getPlayer();
      Role role = rolePlay.getRole();
      if(!partyRoleMap.get(player).equals(role)) {
        return false;
      }
    }
    return true;
  }
  
  public void terminate(DateTime endTime) throws EffectivePeriodAlreadyTerminatedException, EffectivePeriodStartedAfterTerminationTimeException {
    for(EffectivePeriod effectivePeriod : getEffectivePeriodSet()) {
      if(effectivePeriod.isActive()) {
        effectivePeriod.terminate(endTime);
        return;
      }
    }
  }
  
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy k:m:s");
    String result = "On accountability "+super.toString()+":\n";
    for(RolePlay rolePlay : getRolePlaySet()) {
      Party p = rolePlay.getPlayer();
      if(p instanceof Person) {
        result += p+" plays role \""+rolePlay.getRole().getName()+"\"\n";
      } else if(p instanceof OrganizationalUnit) {
        result += p+" plays role \""+rolePlay.getRole().getName()+"\"\n";
      }
    }
    result += "This accountability is valid on:\n";
    for(EffectivePeriod effectivePeriod : getEffectivePeriodSet()) {
      result += "Starting on "+sdf.format(effectivePeriod.getStartTime().toDate());
      if(effectivePeriod.getEndTime()!=null) {
        result += " and ending on "+sdf.format(effectivePeriod.getEndTime().toDate())+"\n";
      }
    }
    return result;
  }
  
}
