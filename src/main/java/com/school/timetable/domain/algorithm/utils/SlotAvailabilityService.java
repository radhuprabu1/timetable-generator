/**
 * 
 */
package com.school.timetable.domain.algorithm.utils;

import java.util.ArrayList;
import java.util.List;

import com.school.timetable.domain.datastructure.SlotMap;
import com.school.timetable.domain.datastructure.model.SlotEntry;
import com.school.timetable.domain.model.common.DayOfWeek;

/**
 * Helper class for retrieving available slots for a specific period of a day.
 */
public class SlotAvailabilityService {

	private final SlotMap slotMap;

	/**
	 * Constructor to initialize SlotHelper with the Slot data structure.
	 * @param slot The Slot object representing the timetable structure.
	 */
	public SlotAvailabilityService(SlotMap slotMap) {
		this.slotMap = slotMap;
	}
	/**
	 * Retrieves the list of available slots for a specific day and period.
	 *
	 * @param day    The day of the week.
	 * @param period The period number.
	 * @return A list of available SlotEntry objects for the given day and period.
	 */
	public List<SlotEntry> getAvailableSlots(DayOfWeek day, int period) {
		List<SlotEntry> availableSlots = new ArrayList<>();

		// Retrieve the list of SlotEntries for the given day and period
		List<SlotEntry> slotEntries = slotMap.getClassInfo(day, period);
		if(slotEntries == null) {
			return availableSlots; // No slots available for this period
		}

		// Look for unallocated slots
		for(SlotEntry slotEntry: slotEntries) {
			if(slotEntry.getTeacherName() == null & slotEntry.getSubjectName() == null) {
				availableSlots.add(slotEntry);
			}
		}
		System.out.println("Available Slots: "+ availableSlots);
		return availableSlots;
	}

}
