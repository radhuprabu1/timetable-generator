package com.school.timetable.domain.algorithm.model;

import com.school.timetable.domain.model.input.Teacher;

/**
 * Represents a possible assignment of a teacher, subject, and class.
 * A simple data structure to hold teacher, subject, and class information for possible assignments.
 */
public class ClassAssignment {
	private Teacher teacher;
	private String subject;
	private String classGrade;
	private String section;

	/**
	 * Constructor to initialize a possible assignment.
	 *
	 * @param teacher   The teacher for the assignment.
	 * @param subject   The subject to be assigned.
	 * @param classInfo The class information.
	 */
	public ClassAssignment(Teacher teacher, String subject, String classGrade, String section) {
		this.teacher = teacher;
		this.subject = subject;
		this.classGrade = classGrade;
		this.section = section;
	}

	// Getters and Setters
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "ClassAssignment{" +
				"teacher=" + teacher.getName() +
				", subject='" + subject + '\'' +
				", classGrade='" + classGrade + '\'' +
				", section='" + section + '\'' +
				'}';
	}

}
