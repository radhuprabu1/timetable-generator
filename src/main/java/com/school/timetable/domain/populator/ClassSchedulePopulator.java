package com.school.timetable.domain.populator;

import com.school.timetable.domain.datastructure.ClassScheduleMap;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.model.output.Timetable.ScheduleEntry;

/**
 * Populates the {@link ClassScheduleMap} with pre-assigned schedules for class teachers.
 * The first period of each day is assigned to the respective class teachers.
 */
public class ClassSchedulePopulator {

	/**
	 * Populates the {@link ClassScheduleMap} with the first-period assignments 
	 * for class teachers.
	 *
	 * @param input The {@link TimetableInput} containing teacher and class information.
	 * @param scheduleMap The {@link ClassScheduleMap} to be populated.
	 */
	public void populate(TimetableInput input, ClassScheduleMap scheduleMap) {
		for (Teacher teacher : input.getTeachers()) {
			if (teacher.getClassTeacherOf().getClassGrade() != null) {
				ClassInfo classInfo = teacher.getClassTeacherOf();
				String classKey = classInfo.getClassGrade() + classInfo.getSection(); // Combine classGrade and section
				for (DayOfWeek day : DayOfWeek.values()) {
					ScheduleEntry entry = new ScheduleEntry(1, classInfo); // Assign first period
					scheduleMap.addSchedule(classKey, day, entry);
				}
			}
		}
	}

}
