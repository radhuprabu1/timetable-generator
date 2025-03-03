package com.school.timetable.domain.populator;

import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.model.parser.JsonInputParser;

/**
 * This class serves as the temporary entry point for running the timetable data population process.
 * It reads the input JSON file, processes the data, and populates the necessary data structures.
 */
public class TimetableDataPopulatorRunner {

	/**
	 * Main method to execute the timetable data population process.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		timetableRunnerWithJson();
	}

	/**
	 * Reads the input JSON file, parses it into a TimetableInput object, and 
	 * triggers the population of data structures required for timetable generation.
	 */
	private static void timetableRunnerWithJson() {
		try {
			// Initialize JSON parser
			JsonInputParser parser = new JsonInputParser();

			// Parse the input JSON file into a TimetableInput object
			TimetableInput input = parser.parseInput("ComboScenario.json");

			// Initialize the data populator
			TimetableDataPopulator populator = new TimetableDataPopulator();

			// Populate all necessary data structures for timetable generation
			populator.populateAll(input);

		} catch (Exception e) {
			// Print stack trace if any exception occurs during execution
			e.printStackTrace();
		}
	}
}
