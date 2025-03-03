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


	@Override
	public String toString() {
		return "ClassInfo(classGrade=" + classGrade +
				", section=" + section +
				", subject=" + subject + ")";
	}

}