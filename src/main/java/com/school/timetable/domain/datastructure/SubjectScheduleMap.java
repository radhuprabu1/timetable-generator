package com.school.timetable.domain.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.timetable.domain.model.common.DayOfWeek;

/**
 * Manages and tracks subject schedules by organizing class-section assignments per day.
 * This class ensures that subjects are scheduled properly and allows verification of scheduling constraints.
 */
public class SubjectScheduleMap {

	/**
	 * A map that tracks subject schedules.
	 * 
	 * Key: Subject name (e.g., "Mathematics")  
	 * Value: A map where the key is a day of the week, and the value is a list of assigned class-sections.
	 */
	private final Map<String, Map<DayOfWeek, List<String>>> scheduleMap = 
			new HashMap<>();     // Map of subject name -> Day of the week -> List of class-sections


	/**
	 * Adds a class-section to the schedule of a specific subject for a given day.
	 * If the subject is not already in the schedule, it initializes a new entry.
	 *
	 * @param subjectName  The name of the subject.
	 * @param day          The day of the week when the subject is scheduled.
	 * @param classSection The class-section (e.g., "10A") to which the subject is assigned.
	 */
	public void addClassSection(String subjectName, DayOfWeek day, String classSection) {
		scheduleMap.putIfAbsent(subjectName, new HashMap<>());
		scheduleMap.get(subjectName).putIfAbsent(day, new ArrayList<>());
		scheduleMap.get(subjectName).get(day).add(classSection);
	}

	/**
	 * Retrieves the schedule of a specific subject on a particular day.
	 *
	 * @param subjectName The name of the subject.
	 * @param day         The day of the week.
	 * @return A list of class-sections where the subject is scheduled on the given day.
	 *         Returns an empty list if no schedule exists for the specified subject and day.
	 */
	public List<String> getScheduleForDay(String subjectName, DayOfWeek day) {
		return scheduleMap.getOrDefault(subjectName, Map.of())
				.getOrDefault(day, List.of());
	}

	/**
	 * Checks if a subject exceeds the allowed maximum periods per week.
	 * This helps ensure that subjects do not have an excessive number of scheduled periods.
	 *
	 * @param subjectName       The name of the subject.
	 * @param maxPeriodsPerWeek The maximum number of periods allowed per week for the subject.
	 * @return True if the subject exceeds the allowed periods, false otherwise.
	 */
	public boolean exceedsMaxPeriodsPerWeek(String subjectName, int maxPeriodsPerWeek) {
		return scheduleMap.getOrDefault(subjectName, Map.of())
				.values()
				.stream()
				.mapToInt(List::size)
				.sum() > maxPeriodsPerWeek;
	}
}
