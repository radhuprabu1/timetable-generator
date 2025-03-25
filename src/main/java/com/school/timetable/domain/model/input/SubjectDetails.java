package com.school.timetable.domain.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the details of subjects taught in the school.
 * This model class is used in deserializing 'subjectDetails' object from the input JSON.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected field
public class SubjectDetails {
	/**
	 * The class grade to which the subject belongs.
	 * Mapped from the JSON property "class".
	 */
	@JsonProperty("class")
	private String classGrade; // Maps to "class" in JSON

	/**
	 * The section of the class (e.g., "A").
	 */
	private String section; // e.g., "A"

	/**
	 * The name of the subject. 
	 * This field won't be serialised/de-serialised directly from JSON
	 */
	@JsonIgnore
	private String subject;

	/**
	 * The minimum number of periods required for the subject per day.
	 */
	private int minPeriodsPerDay;

	/**
	 * The total number of periods allocated per week for the subject.
	 */
	private int periodsPerWeek;

	// Overriding setter methods to store values in lowercase
	public void setClassGrade(String classGrade) {
		this.classGrade = (classGrade != null) ? classGrade.toLowerCase() : null;
	}

	public void setSection(String section) {
		this.section = (section != null) ? section.toLowerCase() : null;
	}

	/**
	 * Sets the subject name dynamically during deserialization.
	 * 
	 * @param subject The name of the subject.
	 */
	@JsonSetter("subject")
	public void setSubject(String subject) {
		this.subject = (subject != null) ? subject.toLowerCase() : null;
	}

}
