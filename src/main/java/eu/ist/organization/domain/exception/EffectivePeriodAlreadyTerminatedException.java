package eu.ist.organization.domain.exception;

import eu.ist.organization.domain.EffectivePeriod;

public class EffectivePeriodAlreadyTerminatedException extends Exception {

  private static final long serialVersionUID = 1600087673405469472L;
  
  private EffectivePeriod alreadyTerminatedEffectivePeriod;
  
  public EffectivePeriodAlreadyTerminatedException(EffectivePeriod alreadyTerminatedEffectivePeriod) {
    setAlreadyTerminatedEffectivePeriod(alreadyTerminatedEffectivePeriod);
  }
  
  public void setAlreadyTerminatedEffectivePeriod(
      EffectivePeriod alreadyTerminatedEffectivePeriod) {
    this.alreadyTerminatedEffectivePeriod = alreadyTerminatedEffectivePeriod;
  }
  
  public EffectivePeriod getAlreadyTerminatedEffectivePeriod() {
    return alreadyTerminatedEffectivePeriod;
  }
}
