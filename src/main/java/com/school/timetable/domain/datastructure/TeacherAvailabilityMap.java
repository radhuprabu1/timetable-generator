package com.school.timetable.domain.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;
/**
 * Manages teacher availability for scheduling purposes. 
 * Tracks when teachers are always available, conditionally available, 
 * and dynamically updates availability based on constraints such as 
 * forbidden periods, max consecutive periods, and specific availabilities.
 */
public class TeacherAvailabilityMap {
	private final Map<DayOfWeek, Map<Integer, List<Teacher>>> alwaysAvailable = new HashMap<>();
	private final Map<DayOfWeek, Map<Integer, List<Teacher>>> conditionallyAvailable = new HashMap<>();
	private final Map<DayOfWeek, Map<Integer, List<Teacher>>> availabilityMap;

	/**
	 * Initializes the teacher availability tracking structure.
	 */	
	public TeacherAvailabilityMap() {
		this.availabilityMap = new HashMap<>();
	}

	/**
	 * Adds a teacher to the always-available list for a specific day and period.
	 * This means the teacher is available for scheduling during that period unless explicitly restricted.
	 *
	 * @param day     The day of the week.
	 * @param period  The period number.
	 * @param teacher The teacher who is always available.
	 */
	public void addAlwaysAvailable(DayOfWeek day, int period, Teacher teacher) {
		alwaysAvailable.putIfAbsent(day, new HashMap<>());
		alwaysAvailable.get(day).putIfAbsent(period, new ArrayList<>());
		alwaysAvailable.get(day).get(period).add(teacher);
	}


	/**
	 * Adds a teacher to the conditionally-available list for a specific day and period.
	 * These teachers have specific availability constraints.
	 *
	 * @param day     The day of the week.
	 * @param period  The period number.
	 * @param teacher The teacher with conditional availability.
	 */
	public void addConditionallyAvailable(DayOfWeek day, int period, Teacher teacher) {
		conditionallyAvailable.putIfAbsent(day, new HashMap<>());
		conditionallyAvailable.get(day).putIfAbsent(period, new ArrayList<>());
		conditionallyAvailable.get(day).get(period).add(teacher);
	}

	/**
	 * Retrieves the list of teachers who are always available for a specific day and period.
	 *
	 * @param day    The day of the week.
	 * @param period The period number.
	 * @return List of always available teachers.
	 */
	public List<Teacher> getAlwaysAvailable(DayOfWeek day, int period) {
		return alwaysAvailable.getOrDefault(day, Map.of()).getOrDefault(period, List.of());
	}

	/**
	 * Retrieves the list of teachers who are conditionally available for a specific day and period.
	 *
	 * @param day    The day of the week.
	 * @param period The period number.
	 * @return List of conditionally available teachers.
	 */
	public List<Teacher> getConditionallyAvailable(DayOfWeek day, int period) {
		return conditionallyAvailable.getOrDefault(day, Map.of()).getOrDefault(period, List.of());
	}

	/**
	 * Adds a teacher to the general availability map for a specific day and period.
	 *
	 * @param day     The day of the week.
	 * @param period  The period number.
	 * @param teacher The teacher to be marked as available.
	 */
	public void addAvailability(DayOfWeek day, int period, Teacher teacher) {
		availabilityMap.putIfAbsent(day, new HashMap<>());
		availabilityMap.get(day).putIfAbsent(period, new java.util.ArrayList<>());
		availabilityMap.get(day).get(period).add(teacher);
	}

	/**
	 * Retrieves a list of teachers available for a given day and period.
	 * If it is the first period of the day, only class teachers are considered.
	 *
	 * @param day    The day of the week.
	 * @param period The period number.
	 * @return List of available teachers.
	 */
	public List<Teacher> getAvailableTeachers(DayOfWeek day, int period) {
		List<Teacher> teacherList = availabilityMap.getOrDefault(day, new HashMap<>()).getOrDefault(period, List.of());
		// Retrieve only the Class Teachers for 1st period of all days.
		if(period==1) {
			return teacherList.stream().filter(teacher -> period == 1 && teacher.getClassTeacherOf() != null)
					.collect(Collectors.toList());
		}

		return teacherList;
	}

	/**
	 * Filters the list of available teachers based on a specific class.
	 *
	 * @param teacherList The list of teachers to filter.
	 * @param day         The day of the week.
	 * @param period      The period number.
	 * @param classKey    The class identifier (e.g., "10A").
	 * @return List of teachers available for the specified class.
	 */
	public List<Teacher> getAvailableTeachersForClass(List<Teacher> teacherList, DayOfWeek day, int period, String classKey) {
		return teacherList.stream().filter(teacher -> teacher.getClasses().stream()
				.anyMatch(classInfo -> classKey.contains(classInfo.getClassGrade() + classInfo.getSection())))
				.collect(Collectors.toList());
	}

	/**
	 * Retrieves the full teacher availability map.
	 *
	 * @return The availability map.
	 */
	public Map<DayOfWeek, Map<Integer, List<Teacher>>> getAvailabilityMap() {
		return availabilityMap;
	}

	/**
	 * Updates a teacher's availability by removing them from a specific day's period.
	 *
	 * @param teacher The teacher to remove.
	 * @param day     The day of the week.
	 * @param period  The period number.
	 */
	public void updateTeacherAvailability(Teacher teacher, DayOfWeek day, int period) {
		// Remove the teacher from the availability map for the specified day and period
		List<Teacher> availableTeachers = availabilityMap.getOrDefault(day, new HashMap<>()).getOrDefault(period, new ArrayList<>());
		availableTeachers.remove(teacher);

		// Update the map
		if (availableTeachers.isEmpty()) {
			availabilityMap.get(day).remove(period);
		} else {
			availabilityMap.get(day).put(period, availableTeachers);
		}
	}
}
