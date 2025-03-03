package com.school.timetable.domain.model.input;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.common.DayOfWeek;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the {@link Teachers} objects in the input json and their assigned classes, subjects, and other details of this object.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected field
public class Teacher {

	/**
	 * The name of the teacher. Mapped from the JSON property "teacherName".
	 */
	@JsonProperty("teacherName")
	private String name;

	/**
	 * The list of subjects the teacher teaches.
	 */
	private List<String> subjects;

	/**
	 * The list of classes the teacher is assigned to.
	 */
	private List<ClassInfo> classes;

	/**
	 * The forbidden periods during which the teacher is unavailable.
	 */
	private Map<DayOfWeek, List<Integer>> forbiddenPeriods;

	/**
	 * The available periods of the teacher mapped from the JSON property "availOnly".
	 */
	@JsonProperty("availOnly")
	private Map<DayOfWeek, List<Integer>> availablePeriods;

	/**
	 * The maximum number of consecutive periods the teacher can teach.
	 */
	private int maxConsecutivePeriods;

	/**
	 * The minimum number of periods the teacher must teach per day.
	 */
	private int minPeriodsPerDay;

	/**
	 * The total number of periods assigned to the teacher per week.
	 */
	private int periodsPerWeek;

	/**
	 * The class for which the teacher serves as the class teacher.
	 */
	private ClassInfo classTeacherOf;

	//	@Override
	//	public String toString() {
	//		return "Teacher(name=" + name +
	//				", subjects=" + subjects +
	//				", forbiddenPeriods=" + forbiddenPeriods +
	//				", availablePeriods=" + availablePeriods +
	//				", maxConsecutivePeriods=" + maxConsecutivePeriods +
	//				", minPeriodsPerDay=" + minPeriodsPerDay +
	//				", periodsPerWeek=" + periodsPerWeek +
	//				", classTeacherOf=" + classTeacherOf + ")";
	//	}

}