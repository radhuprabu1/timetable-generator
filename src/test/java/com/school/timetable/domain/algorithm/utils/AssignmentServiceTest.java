package com.school.timetable.domain.algorithm.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.school.timetable.domain.algorithm.model.ClassAssignment;
import com.school.timetable.domain.datastructure.ClassScheduleMap;
import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.SubjectUsageMap;
import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.datastructure.TeacherWorkloadMap;
import com.school.timetable.domain.datastructure.model.SlotEntry;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.model.parser.JsonInputParser;
import com.school.timetable.domain.populator.ClassSchedulePopulator;
import com.school.timetable.domain.populator.SlotMapPopulator;
import com.school.timetable.domain.populator.SubjectPeriodTrackerPopulator;
import com.school.timetable.domain.populator.SubjectUsagePopulator;
import com.school.timetable.domain.populator.TeacherAvailabilityPopulator;
import com.school.timetable.domain.populator.TeacherWorkloadPopulator;

/**
 * Integrated Test of the algorithm utils package Classes.
 */
public class AssignmentServiceTest {

	@Test
	public void testAssignmentServices() throws IOException {
		JsonInputParser parser = new JsonInputParser();
		TimetableInput input = parser.parseInput("testdata/FullTimeTable.json");

		// Initialize the datastructures
		TeacherAvailabilityMap availabilityMap = new TeacherAvailabilityMap();
		ClassScheduleMap scheduleMap = new ClassScheduleMap();
		SubjectPeriodTracker tracker = new SubjectPeriodTracker();
		TeacherWorkloadMap workloadMap = new TeacherWorkloadMap();
		SubjectUsageMap usageMap = new SubjectUsageMap();
		//SlotMap slotMap = new SlotMap();


		// Populate data structures
		new TeacherAvailabilityPopulator().populate(input, availabilityMap);
		new ClassSchedulePopulator().populate(input, scheduleMap);
		new SubjectPeriodTrackerPopulator().populate(input, tracker);
		new TeacherWorkloadPopulator().initialize(input, workloadMap);
		new SubjectUsagePopulator().populate(input, usageMap);
		SlotMapPopulator slotPopulator = new SlotMapPopulator();
		slotPopulator.populate(input);

		PeriodSelector selectedPeriod = new PeriodSelector(availabilityMap, tracker, workloadMap,usageMap, 8 );
		Map.Entry<DayOfWeek, Integer> mostConstrained = selectedPeriod.selectNextVariable();
		int period = mostConstrained.getValue();
		DayOfWeek day = mostConstrained.getKey();

		// Initialize Assignment Services
		SlotAvailabilityService possibleSlots = new SlotAvailabilityService(slotPopulator.getPopulatedSlot());
		TeacherAvailabilityService possibleTeachers = new TeacherAvailabilityService(availabilityMap);
		SubjectAvailabilityService possibleSubjectsList = new SubjectAvailabilityService(tracker);
		ClassAssignmentService classAssignmentList = new ClassAssignmentService();
		List<SlotEntry> slotEntryList = possibleSlots.getAvailableSlots(day, period);
		for(SlotEntry slot : slotEntryList) {
			System.out.println("********Slot Entry******"+ slot);
			List<Teacher> availableTeachers = possibleTeachers.getAvailableTeachers(day, period, slot);
			for(Teacher availableTeacher : availableTeachers) {
				System.out.println("********Available Teacher for Each Slot********" + availableTeacher);
				List<String> possibleSubjects = possibleSubjectsList.getPossibleSubjectsForTeacher(availableTeacher);
				for (String subject : possibleSubjects) {
					List<ClassAssignment> possibleAssignmentsList = classAssignmentList
							.getPossibleAssignments(availableTeacher, subject, slot);
					System.out.println("PossibleAssignments List: " + possibleAssignmentsList);
				}
			}
		}
	}
}
