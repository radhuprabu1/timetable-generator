package com.school.timetable.domain.datastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.timetable.domain.datastructure.model.SlotEntry;
import com.school.timetable.domain.model.common.DayOfWeek;

/**
 * Represents the timetable slots for all days, periods, and classes.
 * This class allows marking and unmarking of occupied slots.
 */
public class SlotMap {
	/**
	 * A map that tracks available slots.
	 * 
	 * Key: Day of the week  
	 * Value: A map where the key is the period number, and the value is a list of SlotEntry objects.
	 */
	private final Map<DayOfWeek, Map<Integer, List<SlotEntry>>> slots;

	/**
	 * Constructs a new SlotMap with an empty internal structure.
	 */
	public SlotMap() {
		this.slots = new HashMap<>();
	}

	/**
	 * Adds a list of SlotEntry objects for a specific day and period.
	 *
	 * @param day         The day of the week.
	 * @param period      The period number.
	 * @param slotEntries The list of SlotEntry objects to add.
	 */
	public void addClassInfo(DayOfWeek day, int period, List<SlotEntry> slotEntries) {
		//List<ClassInfo> classInfo = teacher.getClasses();
		slots.putIfAbsent(day, new HashMap<>());
		slots.get(day).put(period, slotEntries);
	}

	/**
	 * Retrieves the SlotEntry list for a specific day and period.
	 *
	 * @param day    The day of the week.
	 * @param period The period number.
	 * @return The list of SlotEntry objects for the given day and period.
	 */
	public List<SlotEntry> getClassInfo(DayOfWeek day, int period) {
		return slots.getOrDefault(day, new HashMap<>()).get(period);
	}

	/**
	 * Marks a slot as occupied for the specified class, section, day, and period.
	 *
	 * @param classGrade The class grade.
	 * @param section The section.
	 * @param dayOfWeek The day of the week.
	 * @param period The period number.
	 */
	public void markSlotAsOccupied(String classGrade, String section, DayOfWeek dayOfWeek, int period) {
		//slots.get(dayOfWeek).get(period) retrieves the list of SlotEntry objects for the given dayOfWeek and period
		List<SlotEntry> slotEntryList = slots.get(dayOfWeek).get(period);
		//stream().filter(...) filters the list to find the SlotEntry that matches the given classGrade and section
		slotEntryList.stream().filter(slotEntry -> slotEntry.getClassGrade().equals(classGrade) && slotEntry.getSection().equals(section))
		.findFirst()
		// ifPresent() - checks if a matching SlotEntry was found.
		// If a match is found, slotEntry.setOccupied(true) marks the SlotEntry as occupied
		.ifPresent(slotEntry -> slotEntry.setOccupied(true)); 
	}

	/**
	 * Unmarks a slot as occupied for the specified class, section, day, and period.
	 *
	 * @param classGrade The class grade.
	 * @param section The section.
	 * @param dayOfWeek The day of the week.
	 * @param period The period number.
	 */
	public void unmarkSlotAsOccupied(String classGrade, String section, DayOfWeek dayOfWeek, int period) {
		slots.get(dayOfWeek).get(period).stream()
		.filter(slotEntry -> slotEntry.getClassGrade().equals(classGrade) && slotEntry.getSection().equals(section))
		.findFirst()
		.ifPresent(slotEntry -> slotEntry.setOccupied(false)); 
	}

	/**
	 * Checks if a slot is occupied for a given class, section, day, and period.
	 *
	 * @param classGrade The class grade.
	 * @param section The section.
	 * @param dayOfWeek The day of the week.
	 * @param period The period number.
	 * @return True if the slot is occupied, false otherwise.
	 */  
	public boolean isSlotOccupied(String classGrade, String section, DayOfWeek dayOfWeek, int period) {
		// Get the list of SlotEntry objects for the specified day and period
		List<SlotEntry> slotEntries = slots.getOrDefault(dayOfWeek, new HashMap<>()).get(period);
		if (slotEntries == null) {
			return false; // No slots exist for this period
		}

		// Check if the specific slot entry is marked as occupied
		for (SlotEntry slotEntry : slotEntries) {
			if (slotEntry.getClassGrade().equals(classGrade) && 
					slotEntry.getSection().equals(section) && 
					slotEntry.isOccupied()) {
				return true; // Slot is occupied
			}
		}
		return false; // Slot is not occupied
	}


	/**
	 * Retrieves the entire slot structure.
	 *
	 * @return The complete slot map.
	 */
	public Map<DayOfWeek, Map<Integer, List<SlotEntry>>> getAllSlots() {
		return slots;
	}

	@Override
	public String toString() {
		return "Slot{" +
				"slots=" + slots +
				'}';
	}
}
