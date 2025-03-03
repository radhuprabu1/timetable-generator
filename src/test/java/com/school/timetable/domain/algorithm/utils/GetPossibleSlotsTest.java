/**
 * 
 */
package com.school.timetable.domain.algorithm.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.school.timetable.domain.datastructure.model.SlotEntry;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.model.parser.JsonInputParser;
import com.school.timetable.domain.populator.SlotMapPopulator;
/**
 * 
 */
public class GetPossibleSlotsTest {


	private SlotAvailabilityService getPossibleSlots;

	@BeforeEach
	public void setUp() throws Exception {
		// Parse sample JSON input into TimetableInput
		JsonInputParser parser = new JsonInputParser();
		TimetableInput input = parser.parseInput("ComboScenario.json");


		//Populate the Slot object
		SlotMapPopulator populator = new SlotMapPopulator();
		populator.populate(input);

		// Initialize SlotHelper
		getPossibleSlots = new SlotAvailabilityService(populator.getPopulatedSlot());
	}

	@Test
	public void testGetAvailableSlots() {
		// Retrieve available slots for Monday, Period 1
		List<SlotEntry> availableSlots = getPossibleSlots.getAvailableSlots(DayOfWeek.Monday, 1);

		// Validate the result
		assertNotNull(availableSlots, "Available slots should not be null");
		assertFalse(availableSlots.isEmpty(), "Available slots should not be empty");

		// Check that all returned slots have teacherName and subjectName as null
		for (SlotEntry entry : availableSlots) {
			assertNull(entry.getTeacherName(), "Teacher name should be null for available slots");
			assertNull(entry.getSubjectName(), "Subject name should be null for available slots");
		}

		// Log the available slots for verification
		System.out.println("Available Slots for Monday, Period 1: " + availableSlots);
	}

}
