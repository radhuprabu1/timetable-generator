/**
 * This package contains operational data structures designed to efficiently store and manage
 * information from deserialized Java objects using populator classes.  
 *
 * The primary data structures included in this package are:
 * <ul>
 *   <li>{@link ClassScheduleMap} - Stores class schedules by tracking subject and teacher assignments
 *       for each class-grade and section per period and day.</li>
 *   <li>{@link SlotMap} - Represents timetable slots for all days, periods, and classes, providing
 *       mechanisms to mark and unmark occupied slots.</li>
 *   <li>{@link SubjectPeriodTracker} - Tracks period requirements for each subject-class combination,
 *       ensuring compliance with scheduling constraints.</li>
 *   <li>{@link SubjectScheduleMap} - Manages subject schedules by tracking class-section assignments
 *       for each subject per day.</li>
 *   <li>{@link com.school.timetable.domain.datastructure.TeacherAvailabilityMap} - Tracks teacher availability across periods.</li>
 *   <li>{@link com.school.timetable.domain.datastructure.TeacherWorkloadMap} - Manages workload constraints per teacher.</li>
 * </ul>
 *
 * These data structures play a crucial role in the timetable generation process, facilitating
 * efficient lookup and modification of schedule-related information.
 */
package com.school.timetable.domain.datastructure;
