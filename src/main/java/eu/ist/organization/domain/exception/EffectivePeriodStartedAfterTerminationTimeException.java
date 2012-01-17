package eu.ist.organization.domain.exception;

import eu.ist.organization.domain.EffectivePeriod;

public class EffectivePeriodStartedAfterTerminationTimeException extends Exception {

  private static final long serialVersionUID = -6026181278088463388L;
  
  private EffectivePeriod effectivePeriod;
  
  public EffectivePeriodStartedAfterTerminationTimeException(EffectivePeriod effectivePeriod) {
    setEffectivePeriod(effectivePeriod);
  }
  
  public void setEffectivePeriod(EffectivePeriod effectivePeriod) {
    this.effectivePeriod = effectivePeriod;
  }
  
  public EffectivePeriod getEffectivePeriod() {
    return effectivePeriod;
  }
}
