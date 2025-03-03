package com.school.timetable.domain.algorithm.utils;

import java.util.ArrayList;
import java.util.List;

import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.datastructure.model.SlotEntry;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;

/**
 * Service responsible for managing teacher availability during timetable generation.
 * This service helps identify which teachers are available for a given day and period
 * while considering their assigned classes and scheduling constraints.
 */
public class TeacherAvailabilityService {

	private final TeacherAvailabilityMap availabilityMap;

	/**
	 * Constructs a TeacherAvailabilityService with a given availability map.
	 *
	 * @param availabilityMap The data structure that stores teacher availability information.
	 */
	public TeacherAvailabilityService(TeacherAvailabilityMap availabilityMap) {
		this.availabilityMap = availabilityMap;
	}

	/**
	 * Retrieves a list of teachers who are available for a specific day, period, and slot.
	 *
	 * @param day       The day of the week for which availability is being checked.
	 * @param period    The period number within the timetable.
	 * @param slotEntry The slot for which a teacher is needed, containing class and section details.
	 * @return A list of teachers who are available for the specified slot.
	 */
	public List<Teacher> getAvailableTeachers(DayOfWeek day, int period, SlotEntry slotEntry) {
		List<Teacher> availableTeachers = new ArrayList<>();
		// Fetch all teachers available for this day and period

		List<Teacher> allTeachersForPeriod = availabilityMap.getAvailableTeachers(day, period);
		if (allTeachersForPeriod == null) {
			return availableTeachers; // means no teachers available for this period
		}
		String classKey = slotEntry.getClassGrade()+slotEntry.getSection();
		//availableTeachers = availabilityMap.getAvailableTeachersForClass(allTeachersForPeriod, day, period, classKey);

		// Iterate through the list of available teachers and check additional constraints
		for (Teacher teacher : allTeachersForPeriod) {
			if (isTeacherAvailable(allTeachersForPeriod, teacher, day, period, classKey)) {
				availableTeachers.add(teacher);
			}
		}
		System.out.println("Available Teachers List: "+ availableTeachers);
		return availableTeachers;
	}

	/**
	 * Checks whether a teacher is available for a given day, period, and class.
	 * The method ensures the teacher is assigned to the specified class and is not restricted
	 * by any forbidden periods or availability constraints.
	 *
	 * @param teacherList The list of all teachers available for the period.
	 * @param teacher     The teacher to check.
	 * @param day         The day of the week.
	 * @param period      The period being checked.
	 * @param classKey    A unique identifier for the class (combination of grade and section).
	 * @return {@code true} if the teacher is available for the specified slot, otherwise {@code false}.
	 */
	private boolean isTeacherAvailable(List<Teacher> teacherList, Teacher teacher, DayOfWeek day, int period, String classKey) {
		// Check forbidden periods
		//		if (teacher.getForbiddenPeriods().containsKey(day)) {
		//			List<Integer> forbiddenPeriods = teacher.getForbiddenPeriods().get(day);
		//			if (forbiddenPeriods.contains(period)) {
		//				return false; // Teacher is not available for this period
		//			}
		//		}

		// Check availOnly periods
		//		if (!teacher.getAvailablePeriods().isEmpty()) {
		//			if (teacher.getAvailablePeriods().containsKey(day)) {
		//				List<Integer> availableOnlyPeriods = teacher.getAvailablePeriods().get(day);
		//				if (!availableOnlyPeriods.contains(period)) {
		//					return false; // Teacher is not available for this period
		//				}
		//			} else {
		//				return false; // Teacher has availOnly defined but no periods for this day
		//			}
		//		}
		// Check if the teacher is assigned to the requested class
		if(teacher.getClasses()!=null) {
			List<ClassInfo> classInfoList = teacher.getClasses();
			for(ClassInfo classInfo:classInfoList) {
				String teacherClassKey = classInfo.getClassGrade()+classInfo.getSection();
				if(classKey.contains(teacherClassKey)) {
					return true;  // Teacher is available for this class
				}
			}
		}
		return false; // Teacher is not available for this class or period
	}
}
