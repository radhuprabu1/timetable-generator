package com.school.timetable.domain.algorithm.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.timetable.domain.datastructure.ClassScheduleMap;
import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.SubjectUsageMap;
import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.datastructure.TeacherWorkloadMap;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.TimetableInput;
import com.school.timetable.domain.populator.ClassSchedulePopulator;
import com.school.timetable.domain.populator.SubjectPeriodTrackerPopulator;
import com.school.timetable.domain.populator.SubjectUsagePopulator;
import com.school.timetable.domain.populator.TeacherAvailabilityPopulator;
import com.school.timetable.domain.populator.TeacherWorkloadPopulator;

/**
 * Tests the VariableSelector class for selecting variables.
 */
public class VariableSelectorTest {

    private TeacherAvailabilityMap availabilityMap;
    private ClassScheduleMap scheduleMap;
    private SubjectPeriodTracker periodTracker;
    private TeacherWorkloadMap workloadMap;
    private SubjectUsageMap usageMap;

    private PeriodSelector variableSelector;

    @BeforeEach
    public void setUp() throws Exception {
        // Parse the JSON file into TimetableInput
        ObjectMapper objectMapper = new ObjectMapper();
        File inputFile = new File("src/main/resources/ForbiddenPeriodsScenario.json"); // Replace with actual path
        TimetableInput timetableInput = objectMapper.readValue(inputFile, TimetableInput.class);

        // Initialize data structures
        availabilityMap = new TeacherAvailabilityMap();
        scheduleMap = new ClassScheduleMap();
        periodTracker = new SubjectPeriodTracker();
        workloadMap = new TeacherWorkloadMap();
        usageMap = new SubjectUsageMap();

        // Populate data structures
        new TeacherAvailabilityPopulator().populate(timetableInput, availabilityMap);
        new ClassSchedulePopulator().populate(timetableInput, scheduleMap);
        new SubjectPeriodTrackerPopulator().populate(timetableInput, periodTracker);
        new TeacherWorkloadPopulator().initialize(timetableInput, workloadMap);
        new SubjectUsagePopulator().populate(timetableInput, usageMap);

        // Initialize VariableSelector
        variableSelector = new PeriodSelector(availabilityMap, periodTracker, workloadMap, usageMap, 8);
    }

    @Test
    public void testSelectNextVariable() {
        // Act: Select the next variable
        Map.Entry<DayOfWeek, Integer> selectedVariable = variableSelector.selectNextVariable();

        // Assert: Ensure a valid day and period are returned
        assertNotNull(selectedVariable, "Selected variable should not be null");
        assertNotNull(selectedVariable.getKey(), "Day of the week should not be null");
        assertTrue(selectedVariable.getValue() > 0 && selectedVariable.getValue() <= 8, 
            "Period should be within valid range (1 to 8)");

        // Log the result (optional)
        System.out.println("Selected Variable: Day=" + selectedVariable.getKey() + ", Period=" + selectedVariable.getValue());
    }
}
