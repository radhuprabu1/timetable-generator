package com.school.timetable.domain.populator;

import com.school.timetable.domain.datastructure.ClassScheduleMap;
import com.school.timetable.domain.datastructure.SlotMap;
import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.SubjectUsageMap;
import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.datastructure.TeacherWorkloadMap;
import com.school.timetable.domain.model.input.TimetableInput;

/**
 * This class is responsible for populating all necessary data structures required 
 * for timetable generation. It initializes various helper structures and ensures 
 * that all input data is transformed into an operational format.
 */
public class TimetableDataPopulator {

	/**
	 * Populates all data structures required for timetable generation.
	 *
	 * @param input The parsed timetable input object containing school configuration, 
	 *              teacher details, subject details, and other necessary inputs.
	 */
	public void populateAll(TimetableInput input) {
		// Initialize data structures to store various mappings.
		TeacherAvailabilityMap availabilityMap = new TeacherAvailabilityMap(); // Stores teacher availability per day and period
		ClassScheduleMap scheduleMap = new ClassScheduleMap(); // Stores pre-assigned class schedules
		SubjectPeriodTracker tracker = new SubjectPeriodTracker(); // Tracks subject period requirements
		TeacherWorkloadMap workloadMap = new TeacherWorkloadMap(); // Stores teacher workload across days
		SubjectUsageMap usageMap = new SubjectUsageMap(); // Tracks subject usage across classes
		SlotMap slotMap = new SlotMap(); // Stores available slots for class scheduling

		// Populate data structures using respective populator classes.
		new TeacherAvailabilityPopulator().populate(input, availabilityMap); // Populate teacher availability per period
		new ClassSchedulePopulator().populate(input, scheduleMap); // Assign first-period schedules for class teachers
		new SubjectPeriodTrackerPopulator().populate(input, tracker); // Track minimum and weekly period requirements
		new TeacherWorkloadPopulator().initialize(input, workloadMap); // Initialize teacher workload tracking
		new SubjectUsagePopulator().populate(input, usageMap); // Track subject usage across all classes
		new SlotMapPopulator().populate(input); // Populate slot map with available slots

		// Log the populated data structures for verification.
		System.out.println("***** Teacher Availability ******: " + availabilityMap.getAvailabilityMap());
		System.out.println("***** Class Schedules **********: " + scheduleMap.getScheduleMap());
		System.out.println("***** Subject Period Tracker ***: " + tracker.getTracker());
		System.out.println("***** Teacher Workload Mapping *: " + workloadMap.getWorkloadMap());
		System.out.println("***** Subject Usage Map *******: " + usageMap.getUsageMap());
		System.out.println("***** Slot Map ****************: " + slotMap.getAllSlots());
	}
}