package com.school.timetable.domain.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the school configuration settings used in timetable generation json.
 * This class is used to deserialize school configuration object from the input JSON format.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unexpected fields
public class SchoolConfiguration {
	/**
	 * The total number of periods allowed in a single school day.
	 * Mapped from the JSON property "totalPeriodsPerDay".
	 */
	private int totalPeriodsPerDay;
}
