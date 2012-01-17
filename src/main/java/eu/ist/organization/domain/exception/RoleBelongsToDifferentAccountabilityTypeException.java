package eu.ist.organization.domain.exception;

import eu.ist.organization.util.AccountabilityConfiguration;

public class RoleBelongsToDifferentAccountabilityTypeException extends Exception {

  private static final long serialVersionUID = 3250330445515888282L;
  
  private AccountabilityConfiguration invalidConfiguration;
  
  public RoleBelongsToDifferentAccountabilityTypeException(AccountabilityConfiguration invalidConfiguration) {
    setInvalidConfiguration(invalidConfiguration);
  }
  
  public AccountabilityConfiguration getInvalidConfiguration() {
    return invalidConfiguration;
  }
  
  public void setInvalidConfiguration(AccountabilityConfiguration invalidConfiguration) {
    this.invalidConfiguration = invalidConfiguration;
  }
}
