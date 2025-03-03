package com.school.timetable.domain.populator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.school.timetable.domain.datastructure.SlotMap;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.model.parser.JsonInputParser;
import com.school.timetable.domain.populator.SlotMapPopulator;

/**
 * Tests the SlotPopulator class.
 */
public class SlotPopulatorTest {

	private SlotMapPopulator populator;

	@BeforeEach
	public void setUp() throws Exception {
		populator = new SlotMapPopulator();

		// Parse sample JSON input into TimetableInput
		JsonInputParser parser = new JsonInputParser();
		TimetableInput input = parser.parseInput("ComboScenario.json");

		// Populate the Slot object
		populator.populate(input);
	}

	@Test
	public void testSlotPopulation() {
		SlotMap slotMap = populator.getPopulatedSlot();

		System.out.println(slotMap);

		// Validate that slots are populated
		assertNotNull(slotMap, "Slot object should not be null");

		// Check data for a specific day and period
		assertNotNull(slotMap.getClassInfo(DayOfWeek.Monday, 1), "Slot for Monday, Period 1 should not be null");
		assertFalse(slotMap.getClassInfo(DayOfWeek.Monday, 1).isEmpty(), "Slot for Monday, Period 1 should have class data");

	}
}
