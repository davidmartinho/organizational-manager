package eu.ist.organization.domain.exception;

import eu.ist.organization.domain.Accountability;

public class ActiveAccountabilityExistsException extends Exception {
  
  private static final long serialVersionUID = 4147463750579884152L;
  
  private Accountability activeAccountability;
  
  public ActiveAccountabilityExistsException(Accountability activeAccountability) {
    setActiveAccountability(activeAccountability);
  }
  
  public void setActiveAccountability(Accountability activeAccountability) {
    this.activeAccountability = activeAccountability;
  }
  
  public Accountability getActiveAccountability() {
    return activeAccountability;
  }
}
