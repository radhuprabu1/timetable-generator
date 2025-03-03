package com.school.timetable.domain.model.common;

/**
 * Enum representing the days of the week.
 */
public enum DayOfWeek {
	Monday, Tuesday, Wednesday, Thursday, Friday;

  /**
   * Returns the next day of the week.
   * If it is Friday, returns null.
   *
   * @return The next {@link DayOfWeek} or null if it is the last day.
   */
	public DayOfWeek next() {
		// Returns the position of the current enum value in the declaration order (0 for Monday, 1 for Tuesday, etc.).
		int ordinal = this.ordinal();

		int nextOrdinal = ordinal + 1;
		// DayOfWeek.values(): Provides an array of all enum constants
		/**
		 * Logic:
		 * Increment the ordinal to get the next day.
		 * Return the corresponding enum constant.
		 * Return null if the current day is the last day (Friday)
		 */
		if (nextOrdinal < DayOfWeek.values().length) {
			return DayOfWeek.values()[nextOrdinal];
		} else {
			return null; // Return null if it’s the last day
		}
	}
}
