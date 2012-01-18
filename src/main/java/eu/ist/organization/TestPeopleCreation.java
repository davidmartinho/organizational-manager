package eu.ist.organization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jvstm.Atomic;

import org.joda.time.DateTime;

import pt.ist.fenixframework.FenixFramework;
import eu.ist.organization.domain.Accountability;
import eu.ist.organization.domain.AccountabilityType;
import eu.ist.organization.domain.OrganizationalManager;
import eu.ist.organization.domain.OrganizationalUnit;
import eu.ist.organization.domain.Party;
import eu.ist.organization.domain.Person;
import eu.ist.organization.domain.Role;
import eu.ist.organization.domain.exception.ActiveAccountabilityExistsException;
import eu.ist.organization.domain.exception.EffectivePeriodAlreadyTerminatedException;
import eu.ist.organization.domain.exception.EffectivePeriodStartedAfterTerminationTimeException;
import eu.ist.organization.util.AccountabilityConfiguration;

public class TestPeopleCreation {

  public static void main(String[] args) throws EffectivePeriodAlreadyTerminatedException, EffectivePeriodStartedAfterTerminationTimeException {
    Bootstrap.init();
    test();
  }
  
  @Atomic
  public static void test() throws EffectivePeriodAlreadyTerminatedException, EffectivePeriodStartedAfterTerminationTimeException {
    OrganizationalManager om = (OrganizationalManager)FenixFramework.getRoot();
    
    Set<Role> roleSet = new HashSet<Role>();
    
    Role studentRole = om.createNewRole("PhD Student");
    Role universityRole = om.createNewRole("University");
    Role supervisorRole = om.createNewRole("PhD Supervisor");
    
    roleSet.add(studentRole);
    roleSet.add(universityRole);
    roleSet.add(supervisorRole);
    AccountabilityType at = om.createNewAccountabilityType("STUDENT_MAKES_PHD_ON_UNIVERSITY_AND_IS_SUPERVISED", roleSet);
    
    OrganizationalUnit ist = om.createNewOrganizationalUnit("IST");
    Person david = om.createNewPerson("David Martinho");
    Person rito = om.createNewPerson("António Rito Silva");
    
    Map<Party,Role> partyRoleMap = new HashMap<Party,Role>();
    partyRoleMap.put(david, studentRole);
    partyRoleMap.put(ist, universityRole);
    partyRoleMap.put(rito, supervisorRole);
       
    try {
      AccountabilityConfiguration ac = new AccountabilityConfiguration(at, partyRoleMap, new DateTime());
      Accountability a = om.createNewAccountability( ac);
      a.terminate(new DateTime().plusMonths(5));
      System.out.println(a);
      
      for(Party p : om.getActivePlayerSetOfRole(om.getRoleByName("PhD Supervisor"))) {
        System.out.println(p);
      }
      
    } catch (ActiveAccountabilityExistsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}
