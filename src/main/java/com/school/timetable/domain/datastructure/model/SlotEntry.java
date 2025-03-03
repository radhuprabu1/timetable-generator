package com.school.timetable.domain.datastructure.model;

/**
 * Represents an entry in a timetable slot, containing details of the assigned class,
 * section, teacher, and subject.
 */
public class SlotEntry {

	private String classGrade;
	private String section;
	private String teacherName;
	private String subjectName;
	private boolean isOccupied;

	/**
	 * Constructs a SlotEntry object with the specified class, section, teacher, and subject.
	 * 
	 * @param classGrade  The class grade (e.g., "10").
	 * @param section     The section (e.g., "A").
	 * @param teacherName The teacher's name.
	 * @param subjectName The subject name.
	 */
	public SlotEntry(String classGrade, String section, String teacherName, String subjectName) {
		this.classGrade = classGrade;
		this.section = section;
		this.teacherName = teacherName; // Default to null
		this.subjectName = subjectName; // Default to null

	}

	// Getters and Setters
	public String getClassGrade() {
		return classGrade;
	}

	public void setClassGrade(String classGrade) {
		this.classGrade = classGrade;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean occupied) {
		this.isOccupied = occupied;
	}

	@Override
	public String toString() {
		return "SlotEntry{" +
				"classGrade='" + classGrade + '\'' +
				", section='" + section + '\'' +
				", teacherName='" + teacherName + '\'' +
				", subjectName='" + subjectName + '\'' +
				'}';
	}
}
