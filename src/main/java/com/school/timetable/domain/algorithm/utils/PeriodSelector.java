package com.school.timetable.domain.algorithm.utils;

import java.util.Map;

import com.school.timetable.domain.algorithm.model.DailyPeriod;
import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.SubjectUsageMap;
import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.datastructure.TeacherWorkloadMap;
import com.school.timetable.domain.datastructure.model.PeriodRequirement;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;

/**
 * Handles the selection of variables (periods) based on Row-major Order
 * with Most Constrained Period Heuristic.
 */
public class PeriodSelector {

	private final TeacherAvailabilityMap availabilityMap;
	private final SubjectPeriodTracker periodTracker;
	private final TeacherWorkloadMap workloadMap;
	private final SubjectUsageMap usageMap;
	private final int totalPeriodsPerDay;

	/**
	 * All args constructor
	 * @param availabilityMap
	 * @param periodTracker
	 * @param workloadMap
	 * @param usageMap
	 * @param totalPeriodsPerDay
	 */
	public PeriodSelector(
			TeacherAvailabilityMap availabilityMap,
			SubjectPeriodTracker periodTracker,
			TeacherWorkloadMap workloadMap,
			SubjectUsageMap usageMap,
			int totalPeriodsPerDay
			) {
		this.availabilityMap = availabilityMap;
		this.periodTracker = periodTracker;
		this.workloadMap = workloadMap;
		this.usageMap = usageMap;
		this.totalPeriodsPerDay = totalPeriodsPerDay;
	}

	/**
	 * Selects the next period to assign using the Most Constrained Period Heuristic.
	 *
	 * @return A Map.Entry representing the selected day and period
	 */
	public Map.Entry<DayOfWeek, Integer> selectNextVariable() {
		DayOfWeek currentDay = DayOfWeek.Monday;
		int currentPeriod = 1;

		Map.Entry<DayOfWeek, Integer> mostConstrained = null;
		DailyPeriod dayPeriod = new DailyPeriod();

		int fewestValidCombinations = Integer.MAX_VALUE;

		// Iterate through days
		while (currentDay != null) {
			// Iterate through periods
			while (currentPeriod <= totalPeriodsPerDay) {
				// Calculate the number of valid teacher-subject combinations
				int validCombinations = calculateValidCombinations(currentDay, currentPeriod);

				// Update the most constrained period
				if (validCombinations < fewestValidCombinations) {
					fewestValidCombinations = validCombinations;
					mostConstrained = Map.entry(currentDay, currentPeriod);
					DailyPeriod dailyPeriod = new DailyPeriod(currentDay, currentPeriod);
					dailyPeriod.setDayOfWeek(currentDay);
					dailyPeriod.setPeriodNumber(currentPeriod);
				}

				currentPeriod++;
			}

			currentDay = currentDay.next(); // Move to the next day
			currentPeriod = 1;
		}
		dayPeriod.setDayOfWeek(mostConstrained.getKey());
		dayPeriod.setPeriodNumber(mostConstrained.getValue());
		return mostConstrained;
	}

	/**
	 * Retrieves DayOfWeek Key from mostConstrained Variable Map
	 * 
	 * @param mostConstrained Map
	 * @return mostConstrained Selected Day
	 */
	public DayOfWeek getNextVariableDayOfWeek(Map.Entry<DayOfWeek, Integer> mostConstrained) {
		DayOfWeek day = mostConstrained.getKey();
		return day;
	}

	/**
	 * Retrieves Period Value from mostConstrained Variable Map
	 * 
	 * @param mostConstrained Map
	 * @return mostConstrained Period of the Selected Day.
	 */
	public int getNextVariablePeriod(Map.Entry<DayOfWeek, Integer> mostConstrained) {
		return mostConstrained.getValue();
	}
	/**
	 * Calculates the number of valid teacher-subject combinations for a given period.
	 *
	 * @param day The current day.
	 * @param period The current period.
	 * @return The number of valid combinations.
	 */
	private int calculateValidCombinations(DayOfWeek day, int period) {
		int count = 0;

		// Iterate through all teacher-subject combinations
		for (Teacher teacher : availabilityMap.getAvailableTeachers(day, period)) {
			for (String subject : teacher.getSubjects()) {
				if (isValidCombination(teacher, subject, day, period)) {
					count++;
				}
			}
		}

		return count;
	}

	/**
	 * Checks if a teacher-subject combination is valid for a given period.
	 *
	 * @param teacher The teacher to check.
	 * @param subject The subject to check.
	 * @param day The current day.
	 * @param period The current period.
	 * @return True if the combination is valid, otherwise false.
	 */
	private boolean isValidCombination(Teacher teacher, String subject, DayOfWeek day, int period) {
		// Check availability
		if (!availabilityMap.getAvailableTeachers(day, period).contains(teacher)) {
			return false;
		}

		// Iterate through the teacher's classes to match the subject and construct the classKey
		for (ClassInfo classInfo : teacher.getClasses()) {
			if (classInfo.getSubject().equals(subject)) {
				String classKey = classInfo.getClassGrade() + classInfo.getSection(); // Form classKey

				// Get the PeriodRequirement for the subject and classKey
				PeriodRequirement requirement = periodTracker.getPeriodRequirement(subject, classKey);

				// Check if the PeriodRequirement exists and has remaining periods
				if (requirement == null || requirement.getRemainingPeriodsPerDay() <= 0 || requirement.getRemainingPeriodsPerWeek() <= 0) {
					return false;
				}
			}
		}
		// Check subject's remaining periods
		//        if (!periodTracker.getPeriodRequirement(subject, teacher.getClasses().toString()).hasRemainingPeriods()) {
		//            return false;
		//        }

		// Check teacher's workload
		if (workloadMap.exceedsDailyWorkload(teacher.getName(), day, teacher.getMinPeriodsPerDay())) {
			return false;
		}
		if (workloadMap.exceedsWeeklyWorkload(teacher.getName(), teacher.getPeriodsPerWeek())) {
			return false;
		}

		// Check usage limits
		if (usageMap.isSubjectFullyAssigned(subject)) {
			return false;
		}
		return true;
	}
}
