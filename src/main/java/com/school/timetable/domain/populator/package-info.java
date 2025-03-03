/**
 * This package contains classes responsible for populating various data structures 
 * required for timetable generation. These classes transform deserialized input data 
 * into an operational format for efficient scheduling.
 * 
 * <p>The key components of this package include:</p>
 * <ul>
 *   <li>{@link com.school.timetable.domain.populator.TeacherAvailabilityPopulator} - 
 *       Populates teacher availability based on predefined constraints.</li>
 *   <li>{@link com.school.timetable.domain.populator.ClassSchedulePopulator} - 
 *       Assigns pre-scheduled periods for class teachers.</li>
 *   <li>{@link com.school.timetable.domain.populator.SubjectPeriodTrackerPopulator} - 
 *       Tracks subject-wise period requirements.</li>
 *   <li>{@link com.school.timetable.domain.populator.TeacherWorkloadPopulator} - 
 *       Manages and updates teacher workloads.</li>
 *   <li>{@link com.school.timetable.domain.populator.SubjectUsagePopulator} - 
 *       Tracks subject distribution across different classes.</li>
 *   <li>{@link com.school.timetable.domain.populator.SlotMapPopulator} - 
 *       Prepares available slots for scheduling.</li>
 *   <li>{@link com.school.timetable.domain.populator.TimetableDataPopulator} - 
 *       Orchestrates the population of all required data structures.</li>
 *   <li>{@link com.school.timetable.domain.populator.TimetableDataPopulatorRunner} - 
 *       Serves as the execution entry point for running the data population process.</li>
 * </ul>
 * 
 * <p>This package plays a crucial role in converting parsed timetable data 
 * into structured, optimized mappings for timetable generation.</p>
 */
package com.school.timetable.domain.populator;