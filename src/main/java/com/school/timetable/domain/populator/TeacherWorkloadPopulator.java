package com.school.timetable.domain.populator;

import com.school.timetable.domain.datastructure.TeacherWorkloadMap;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;
import com.school.timetable.domain.model.input.TimetableInput;

/**
 * Populates and updates the {@link TeacherWorkloadMap} with workload data.
 */
public class TeacherWorkloadPopulator {

	/**
	 * Initializes teacher workloads by setting the initial workload to zero.
	 *
	 * @param input The {@link TimetableInput} containing teacher details.
	 * @param workloadMap The {@link TeacherWorkloadMap} to store workloads.
	 */
	public void initialize(TimetableInput input, TeacherWorkloadMap workloadMap) {
		for (Teacher teacher : input.getTeachers()) {
			for (DayOfWeek day : DayOfWeek.values()) {
				workloadMap.initializeWorkload(teacher.getName(), day); // Initialize workload to 0
			}
		}
	}
	/**
	 * Updates the workload for a specific teacher on a given day.
	 *
	 * @param teacherName The name of the teacher.
	 * @param day The {@link DayOfWeek} for which the workload is updated.
	 * @param workloadMap The {@link TeacherWorkloadMap} storing workload data.
	 */
	public void updateWorkload(String teacherName, DayOfWeek day, TeacherWorkloadMap workloadMap) {
		workloadMap.addWorkload(teacherName, day); // Increment workload as subjects are assigned
	}

}
