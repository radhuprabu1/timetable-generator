package com.school.timetable.domain.algorithm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.school.timetable.domain.datastructure.SubjectPeriodTracker;
import com.school.timetable.domain.datastructure.model.PeriodRequirement;
import com.school.timetable.domain.model.common.ClassInfo;
import com.school.timetable.domain.model.input.Teacher;

/**
 * Helper class for determining possible subjects for each available teacher.
 */
public class SubjectAvailabilityService {

	private final SubjectPeriodTracker periodTracker;

	/**
	 * Constructor to initialize SubjectAssignmentHelper with the necessary data structures.
	 *
	 * @param periodTracker The tracker containing subject period requirements.
	 */
	public SubjectAvailabilityService(SubjectPeriodTracker periodTracker) {
		this.periodTracker = periodTracker;
	}

	/**
	 * Retrieves a list of possible subjects that a teacher can teach for a given period.
	 * Iterates through the teacher’s subjects.
	 * Calls 'canTeachSubject' for each subject to determine if it satisfies the constraints.
	 * @param teacher The teacher being evaluated.
	 * @return A list of subjects that the teacher can teach.
	 */
	public List<String> getPossibleSubjectsForTeacher(Teacher availableTeacher) {
		List<String> possibleSubjects = new ArrayList<>();

		// Iterate through the teacher's subjects
		for (String subject : availableTeacher.getSubjects()) {
			// Check if the subject satisfies requirements for any assigned class
			if (canTeachSubject(availableTeacher, subject)) {
				possibleSubjects.add(subject);
			}
		}

		return possibleSubjects;
	}

	/**
	 * Checks if a teacher can teach a given subject based on remaining period requirements.
	 * Checks the SubjectPeriodTracker to ensure the subject has:
				Remaining periods available (hasRemainingPeriods) &
				 Sufficient remainingPeriodsPerDay and remainingPeriodsPerWeek
	 * @param teacher The teacher being evaluated.
	 * @param subject The subject being checked.
	 * @return True if the teacher can teach the subject, otherwise false.
	 */
	private boolean canTeachSubject(Teacher teacher, String subject) {

		Map<String, PeriodRequirement> classPeriods = periodTracker.getPeriodRequirementsForSubject(subject);
		// Retrieve the class keys for the teacher
		List<String> assignedClassKeys = getAssignedClassKeys(teacher);

		// Iterate through the teacher's assigned classes
		for (String classKey : assignedClassKeys) {
			if (classPeriods.containsKey(classKey)) {
				PeriodRequirement requirement = classPeriods.get(classKey);

				// Check remaining periods and constraints
				if (requirement.hasRemainingPeriods()) {
					if (requirement.getRemainingPeriodsPerDay() > 0 && requirement.getRemainingPeriodsPerWeek() > 0) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Retrieves the class keys for all classes assigned to a teacher.
	 *
	 * @param teacher The teacher whose class keys are being retrieved.
	 * @return A list of class keys (e.g., "10A", "11B").
	 */
	public List<String> getAssignedClassKeys(Teacher teacher) {
		List<String> classKeys = new ArrayList<>();

		for (ClassInfo classInfo : teacher.getClasses()) {
			String classKey = classInfo.getClassGrade() + classInfo.getSection(); // Example: "10A"
			classKeys.add(classKey);
		}

		return classKeys;
	}
}
