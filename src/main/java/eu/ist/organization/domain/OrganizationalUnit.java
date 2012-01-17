package eu.ist.organization.domain;

public class OrganizationalUnit extends OrganizationalUnit_Base {

  public OrganizationalUnit(String name) {
    setName(name);
  }
  
  public String toString() {
    return "Organizational Unit: "+getName();
  }
  
}
