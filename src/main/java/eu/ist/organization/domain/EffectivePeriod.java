package eu.ist.organization.domain;

import org.joda.time.DateTime;

import eu.ist.organization.domain.exception.EffectivePeriodAlreadyTerminatedException;
import eu.ist.organization.domain.exception.EffectivePeriodStartedAfterTerminationTimeException;

public class EffectivePeriod extends EffectivePeriod_Base {

  public EffectivePeriod(DateTime startTime) {
    setStartTime(startTime);
    setEndTime(null);
  }

  /**
   * Checks if the effective period is active or not, i.e. if it has not been terminated yet.
   * @return true if the effective period has not been terminated, false otherwise
   */
  public boolean isActive() {
    return isActive(new DateTime());
  }
  
  public boolean isActive(DateTime dateTime) {
    return getStartTime().isBefore(dateTime) && (getEndTime()==null || getEndTime().isAfter(dateTime));
  }
  
  /**
   * Terminates the effective period on a given time.
   * @param endTime the timestamp for when the effective period ends 
   * @throws EffectivePeriodAlreadyTerminatedException when the effective period has already been terminated
   * @throws EffectivePeriodStartedAfterTerminationTimeException when the given end time is before the start time of the effective period
   */
  public void terminate(DateTime endTime) throws EffectivePeriodAlreadyTerminatedException, EffectivePeriodStartedAfterTerminationTimeException {
    if(getStartTime().isAfter(endTime)) {
      throw new EffectivePeriodStartedAfterTerminationTimeException(this);
    }
    if(getEndTime() == null) {
      setEndTime(endTime);
    } else {
      throw new EffectivePeriodAlreadyTerminatedException(this);
    }
  }
  
  /**
   * Terminates the effective period on the current time.
   * @throws EffectivePeriodAlreadyTerminatedException when the effective period has already been terminated
   * @throws EffectivePeriodStartedAfterTerminationTimeException when the given end time is before the start time of the effective period
   */
  public void terminate() throws EffectivePeriodAlreadyTerminatedException, EffectivePeriodStartedAfterTerminationTimeException {
    terminate(new DateTime());
  }
  
}
