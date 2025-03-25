package com.school.timetable.domain.model.output;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for output json for the field - {@link SubjectsUsage}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected fields
@Builder
public class SubjectUsage {
	/**
	 * The name of the subject.
	 */
	private String subjectName;

	/**
	 * List of classes where the subject is taught, including period count.
	 */
	private List<ClassPeriodCount> classesTaughtIn;

	@JsonSetter("subject")
	public void setSubjectName(String subjectName) {
		this.subjectName = (subjectName != null) ? subjectName.toLowerCase() : null;
	}

	/**
	 * Represents the count of periods a subject is taught in a specific class.
	 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ClassPeriodCount {
		private String classGrade;
		private String section;

		/**
		 * The total number of periods allocated for this subject in the class.
		 */
		private int totalPeriods;

		// Overriding setter methods to store values in lowercase
	    public void setClassGrade(String classGrade) {
	        this.classGrade = (classGrade != null) ? classGrade.toLowerCase() : null;
	    }

	    public void setSection(String section) {
	        this.section = (section != null) ? section.toLowerCase() : null;
	    }
	    
		@Override
		public String toString() {
			return "ClassPeriodCount(classGrade=" + classGrade +
					", section=" + section +
					", totalPeriods=" + totalPeriods + ")";
		}

	}

}
