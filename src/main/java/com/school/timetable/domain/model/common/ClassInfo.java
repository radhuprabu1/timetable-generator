package com.school.timetable.domain.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents basic class information, including grade, section, and subject.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected fields
public class ClassInfo {
	/**
	 * The grade of the class (e.g., "10"). Mapped from the JSON property "class".
	 */
	@JsonProperty("class")
	private String classGrade;

	/**
	 * The section of the class (e.g., "A").
	 */
	private String section;

	/**
	 * The subject being taught in the class (e.g., "Mathematics").
	 */
	private String subject;


	// Overriding setter methods to store values in lower case
    public void setClassGrade(String classGrade) {
        this.classGrade = (classGrade != null) ? classGrade.toLowerCase() : null;
    }

    public void setSection(String section) {
        this.section = (section != null) ? section.toLowerCase() : null;
    }

    public void setSubject(String subject) {
        this.subject = (subject != null) ? subject.toLowerCase() : null;
    }

	@Override
	public String toString() {
		return "ClassInfo(classGrade=" + classGrade +
				", section=" + section +
				", subject=" + subject + ")";
	}

}