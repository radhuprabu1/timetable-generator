package com.school.timetable.domain.model.input;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the complete timetable input, including school configuration,
 * teachers, and subject details.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected fields
public class TimetableInput {
	/**
	 * Represent {@link SchoolConfiguration} object from the input json
	 * This object has details such as total periods per day.
	 */
	private SchoolConfiguration schoolConfiguration;

	/**
	 * The list of teachers available for scheduling.
	 */
	private List<Teacher> teachers;

	/**
	 * The mapping of subjects to their respective details, deserialized using a custom deserializer.
	 */
	@JsonDeserialize(using = SubjectDetailsDeserializer.class)
	private Map<String, List<SubjectDetails>> subjectDetails;
}
