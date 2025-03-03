package com.school.timetable.domain.populator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.timetable.domain.datastructure.SlotMap;
import com.school.timetable.domain.datastructure.model.SlotEntry;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.SubjectDetails;
import com.school.timetable.domain.model.input.TimetableInput;

/**
 * Populates the {@link SlotMap} with structured timetable data.
 * Ensures every class is mapped correctly for all days and periods.
 */
public class SlotMapPopulator {

	private final SlotMap slotMap;

	/**
	 * Initializes a new instance of {@code SlotMapPopulator} and creates an empty {@code SlotMap}.
	 */
	public SlotMapPopulator() {
		this.slotMap = new SlotMap();
	}

	/**
	 * Populates the {@link SlotMap} with class details for all periods across all days.
	 *
	 * @param input The {@link TimetableInput} object containing class and subject details.
	 */
	public void populate(TimetableInput input) {
		int totalPeriodsPerDay = input.getSchoolConfiguration().getTotalPeriodsPerDay();

		// Step 1: Track unique SlotEntry objects
		HashMap<String, SlotEntry> uniqueSlotEntries = new HashMap<>();
		// Extract subject details and create unique SlotEntry objects
		Map<String, List<SubjectDetails>> subjectDetails = input.getSubjectDetails();
		for (String subject : subjectDetails.keySet()) {
			List<SubjectDetails> detailsList = subjectDetails.get(subject);

			for (SubjectDetails details : detailsList) {
				String key = details.getClassGrade() + "-" + details.getSection();
				if (!uniqueSlotEntries.containsKey(key)) {
					uniqueSlotEntries.put(key, new SlotEntry(details.getClassGrade(), details.getSection(),null, null));
				}
			}
		}
		/*        input.getSubjectDetails().forEach((subject, detailsList) -> {
        for (var details : detailsList) {
          String key = details.getClassGrade() + "-" + details.getSection();
          if (!uniqueSlotEntries.containsKey(key)) {
            uniqueSlotEntries.put(key, new SlotEntry(details.getClassGrade(), details.getSection()));
          }
        }
    }); */

		// Step 2: Sort the SlotEntry objects
		List<SlotEntry> sortedEntries = new ArrayList<>(uniqueSlotEntries.values());
		Collections.sort(sortedEntries, (entry1, entry2) -> {
			int gradeComparison = entry1.getClassGrade().compareTo(entry2.getClassGrade());
			if (gradeComparison != 0) {
				return gradeComparison;
			}
			return entry1.getSection().compareTo(entry2.getSection());
		});



		// Step 3: Populate slotMaps for each day and period
		for (DayOfWeek day : DayOfWeek.values()) {
			for (int period = 1; period <= totalPeriodsPerDay; period++) {
				// Add all unique classes to the slotMap for the current day and period
				slotMap.addClassInfo(day, period, new ArrayList<>(sortedEntries));
			}
		}
	}

	/**
	 * Retrieves the populated {@link SlotMap}.
	 *
	 * @return The populated {@link SlotMap} object.
	 */
	public SlotMap getPopulatedSlot() {
		return slotMap;
	}
}
