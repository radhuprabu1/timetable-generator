/**
 * 
 */
package com.school.timetable.domain.algorithm.model;

import com.school.timetable.domain.model.common.DayOfWeek;

/**
 * Encapsulate the DayOfWeek and period together selected from the variables generator based on Row-major Order
 * with Most Constrained Period Heuristic. 
 */
public class DailyPeriod {
	private DayOfWeek dayOfWeek;
	private int periodNumber;
	
	
	public DailyPeriod(DayOfWeek dayOfWeek, int periodNumber) {
		this.dayOfWeek = dayOfWeek;
		this.periodNumber = periodNumber;
	}

	/**
	 * Default Constructor
	 */
	public DailyPeriod() {}

	/**
	 * Retrieves DayOfWeek Key from mostConstrained Variable Map
	 * 
	 * @return mostConstrained Selected Day of the Week
	 */
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Retrieves Period Number of the Day from mostConstrained Variable Map
	 * 
	 * @return mostConstrained Selected Period of the day.
	 */
	public int getPeriodNumber() {
		return periodNumber;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @param periodNumber the periodNumber to set
	 */
	public void setPeriodNumber(int periodNumber) {
		this.periodNumber = periodNumber;
	}


}
