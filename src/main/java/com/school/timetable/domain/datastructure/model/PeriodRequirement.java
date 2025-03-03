package com.school.timetable.domain.datastructure.model;

/**
 * Represents the period requirements for a subject, tracking the remaining
 * number of periods per day and per week.
 */
public class PeriodRequirement {
	private int remainingPeriodsPerDay;
	private int remainingPeriodsPerWeek;

	/**
	 * Constructs a PeriodRequirement object with specified daily and weekly period limits.
	 * 
	 * @param remainingPeriodsPerDay  The number of periods remaining for the day.
	 * @param remainingPeriodsPerWeek The number of periods remaining for the week.
	 */
	public PeriodRequirement(int remainingPeriodsPerDay, 
			int remainingPeriodsPerWeek) {
		this.remainingPeriodsPerDay = remainingPeriodsPerDay;
		this.remainingPeriodsPerWeek = remainingPeriodsPerWeek;
	}
	/**
	 * Reduces the number of remaining periods for the day by one if possible.
	 */
	public void reduceRemainingPeriodsPerDay() {
		if (remainingPeriodsPerDay > 0) remainingPeriodsPerDay--;
	}
	/**
	 * Reduces the number of remaining periods for the week by one if possible.
	 */
	public void reduceRemainingPeriodsPerWeek() {
		if (remainingPeriodsPerWeek > 0) remainingPeriodsPerWeek--;
	}

	/**
	 * Checks whether any periods remain for the day or week.
	 * 
	 * @return True if there are remaining periods, otherwise false.
	 */
	public boolean hasRemainingPeriods() {
		return remainingPeriodsPerDay > 0 && remainingPeriodsPerWeek > 0;
	}

	// Getters
	public int getRemainingPeriodsPerDay() {
		return remainingPeriodsPerDay;
	}

	public int getRemainingPeriodsPerWeek() {
		return remainingPeriodsPerWeek;
	}

	@Override
	public String toString() {
		return "PeriodRequirement(remainingPeriodsPerDay=" + remainingPeriodsPerDay +
				", remainingPeriodsPerWeek=" + remainingPeriodsPerWeek + ")";
	}

	/**
	 * @param remainingPeriodsPerDay
	 */
	public void setRemainingPeriodsPerDay(int remainingPeriodsPerDay) {
		this.remainingPeriodsPerDay = remainingPeriodsPerDay;
	}

	/**
	 * @param remainingPeriodsPerWeek
	 */
	public void setRemainingPeriodsPerWeek(int remainingPeriodsPerWeek) {
		this.remainingPeriodsPerWeek = remainingPeriodsPerWeek;
	}

}
