package com.school.timetable.domain.datastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.output.Timetable.ScheduleEntry;


/**
 * Maintains the schedule for each class-grade and section by tracking 
 * the assigned subjects and teachers for each period and day.
 */
public class ClassScheduleMap {

	/** 
	 * A map that stores the class schedules.
	 * 
	 * Key: Class grade + section (e.g., "10A")  
	 * Value: A map where the key is a day of the week, and the value is a list of ScheduleEntry objects.
	 */
	private final Map<String, Map<DayOfWeek, List<ScheduleEntry>>> scheduleMap;

	/**
	 * Constructs a new ClassScheduleMap with an empty internal map.
	 */
	public ClassScheduleMap() {
		this.scheduleMap = new HashMap<>();
	}

	/**
	 * Adds a schedule entry for a given class and day.
	 * If the class or day does not already exist in the schedule, 
	 * it initializes new entries before adding the schedule data.
	 *
	 * @param classGrade The class grade (e.g., "10").
	 * @param day The day of the week.
	 * @param entry The ScheduleEntry object to be added.
	 */
	public void addSchedule(String classGrade, DayOfWeek day, ScheduleEntry entry) {
		scheduleMap.putIfAbsent(classGrade, new HashMap<>());
		scheduleMap.get(classGrade).putIfAbsent(day, new java.util.ArrayList<>());
		scheduleMap.get(classGrade).get(day).add(entry);
	}


	/**
	 * Retrieves the list of schedule entries for a specified day and class.
	 *
	 * @param classGrade The class grade (e.g., "10").
	 * @param day The day of the week.
	 * @return A list of ScheduleEntry objects assigned to the given class and day.
	 *         Returns an empty list if no schedule exists for the given class and day.
	 */
	public List<ScheduleEntry> getSchedule(String classGrade, DayOfWeek day) {
		return scheduleMap.getOrDefault(classGrade, new HashMap<>()).getOrDefault(day, List.of());
	}

	/**
	 * Retrieves the entire schedule map.
	 * 
	 * @return The internal map containing all class schedules.
	 */
	public Map<String, Map<DayOfWeek, List<ScheduleEntry>>> getScheduleMap() {
		return scheduleMap;
	}
}
