package com.school.timetable.domain.datastructure;

import java.util.HashMap;
import java.util.Map;

import com.school.timetable.domain.model.common.DayOfWeek;
/**
 * Manages the workload of teachers by tracking the number of periods assigned per day and week.
 * Ensures that teachers do not exceed their maximum consecutive periods or weekly limits.
 */
public class TeacherWorkloadMap {
	private final Map<String, Map<DayOfWeek, Integer>> workloadMap;

	/**
	 * Initializes an empty workload tracking map.
	 */
	public TeacherWorkloadMap() {
		this.workloadMap = new HashMap<>();
	}

	/**
	 * Initializes the workload entry for a teacher on a given day with zero periods assigned.
	 * 
	 * @param teacherName The name of the teacher.
	 * @param day         The day of the week.
	 */
	public void initializeWorkload(String teacherName, DayOfWeek day) {
		workloadMap.putIfAbsent(teacherName, new HashMap<>());
		workloadMap.get(teacherName).put(day, 0); // Set initial workload as 0
	}

	/**
	 * Increments the workload of a teacher for a specific day by one period.
	 * 
	 * @param teacherName The name of the teacher.
	 * @param day         The day of the week.
	 */
	public void addWorkload(String teacherName, DayOfWeek day) {
		workloadMap.putIfAbsent(teacherName, new HashMap<>());
		workloadMap.get(teacherName).put(day, workloadMap.get(teacherName).getOrDefault(day, 0) + 1);
	}


	/**
	 * Retrieves the workload (number of assigned periods) for a teacher on a specific day.
	 * 
	 * @param teacherName The name of the teacher.
	 * @param day         The day of the week.
	 * @return The number of assigned periods for the teacher on that day.
	 */
	public int getWorkload(String teacherName, DayOfWeek day) {
		return workloadMap.getOrDefault(teacherName, new HashMap<>()).getOrDefault(day, 0);
	}

	/**
	 * Checks whether a teacher's daily workload exceeds the allowed maximum periods per day.
	 * 
	 * @param teacherName     The name of the teacher.
	 * @param day             The day of the week.
	 * @param maxPeriodsPerDay The maximum allowed periods per day.
	 * @return True if the teacher's workload exceeds the limit, otherwise false.
	 */
	public boolean exceedsDailyWorkload(String teacherName, DayOfWeek day, int maxPeriodsPerDay) {
		int currentWorkload = workloadMap.getOrDefault(teacherName, Map.of()).getOrDefault(day, 0);
		return currentWorkload >= maxPeriodsPerDay;
	}

	/**
	 * Checks whether a teacher's weekly workload exceeds the allowed maximum periods per week.
	 * 
	 * @param teacherName      The name of the teacher.
	 * @param maxPeriodsPerWeek The maximum allowed periods per week.
	 * @return True if the teacher's workload exceeds the limit, otherwise false.
	 */
	public boolean exceedsWeeklyWorkload(String teacherName, int maxPeriodsPerWeek) {
		int totalWorkload = workloadMap.getOrDefault(teacherName, Map.of())
				.values()
				.stream()
				.mapToInt(Integer::intValue)
				.sum();
		return totalWorkload >= maxPeriodsPerWeek;
	}

	/**
	 * Retrieves the entire workload map.
	 * 
	 * @return A map containing teacher workload data.
	 */
	public Map<String, Map<DayOfWeek, Integer>> getWorkloadMap() {
		return workloadMap;
	}

}
