package com.school.timetable.domain.algorithm.utils;

import java.util.ArrayList;
import java.util.List;

import com.school.timetable.domain.algorithm.model.ClassAssignment;
import com.school.timetable.domain.datastructure.model.SlotEntry;
import com.school.timetable.domain.model.input.Teacher;

/**
 * Generates possible assignments for teachers, subjects, and classes.
 */
public class ClassAssignmentService {


	/**
	 * Default Constructor
	 */
	public ClassAssignmentService() {}

	/**
	 * Generates possible assignments for a given teacher-subject combo.
	 * @param availableTeacher The list of teachers available for the period.
	 * @param subject           Subject taken by the teacher
	 * @param slot         SlotEntry.
	 * @return A list of possible assignments.
	 */
	public List<ClassAssignment> getPossibleAssignments(Teacher availableTeacher, String subject, SlotEntry slot) {
		List<ClassAssignment> possibleAssignmentsList = new ArrayList<>();
		if (availableTeacher.getSubjects().contains(subject)) {
			ClassAssignment possibleAssignment = new ClassAssignment(availableTeacher, subject, slot.getClassGrade(), slot.getSection()); 
			// Create a new assignment for the teacher, subject, and slot
			possibleAssignmentsList.add(possibleAssignment);
		}
		return possibleAssignmentsList;
	}
}
