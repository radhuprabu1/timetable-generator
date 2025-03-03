package com.school.timetable.domain.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.school.timetable.domain.datastructure.model.PeriodRequirement;

/**
 * Tracks period requirements for each subject and class combination.
 * This class ensures that subjects are scheduled correctly by keeping track of
 * their period requirements and whether they have been fully scheduled.
 */
public class SubjectPeriodTracker {
	/** 
	 * A map that tracks period requirements for each subject and class.
	 * 
	 * Key: Subject name (e.g., "Mathematics")  
	 * Value: A map where the key is the class key (e.g., "10A"), and the value is the period requirement.
	 */
	private final Map<String, Map<String, PeriodRequirement>> tracker = new HashMap<>();

	/**
	 * A set of subjects that have been fully scheduled.
	 */
	private final Set<String> completedSubjects = new HashSet<>();

	/**
	 * A set of subjects that have not yet been fully scheduled.
	 */
	private final Set<String> incompleteSubjects = new HashSet<>();

	/**
	 * Adds a period requirement for a specific subject and class.
	 * If the subject is not already being tracked, it initializes a new entry.
	 *
	 * @param subject     The subject name.
	 * @param classKey    The class identifier (e.g., "10A").
	 * @param requirement The period requirement that specifies how many periods are needed.
	 */
	public void addPeriodRequirement(String subject, String classKey, PeriodRequirement requirement) {
		tracker.putIfAbsent(subject, new HashMap<>());
		tracker.get(subject).put(classKey, requirement);
		incompleteSubjects.add(subject);
	}

	/**
	 * Checks if a subject has been fully scheduled.
	 *
	 * @param subject The subject to check.
	 * @return True if the subject is complete (i.e., all required periods have been scheduled), false otherwise.
	 */
	public boolean isSubjectComplete(String subject) {
		return completedSubjects.contains(subject);
	}

	/**
	 * Retrieves the period requirement for a specific subject and class.
	 *
	 * @param subject  The subject name.
	 * @param classKey The class identifier (e.g., "10A").
	 * @return The corresponding PeriodRequirement object, or null if no requirement exists.
	 */
	public PeriodRequirement getPeriodRequirement(String subject, String classKey) {
		return tracker.get(subject).get(classKey);
	}

	/**
	 * Getter for the internal tracker map (if needed externally).
	 *
	 * @return The tracker map.
	 */
	public Map<String, Map<String, PeriodRequirement>> getTracker() {
		return tracker;
	}

	/**
	 * Decreases the remaining period count for a subject in a specific class and section.
	 * This method reduces the number of periods available per day and per week.
	 * If a subject has no remaining required periods, it is marked as complete.
	 *
	 * @param subject    The subject name.
	 * @param classGrade The class grade (e.g., "10").
	 * @param section    The section (e.g., "A").
	 * @throws IllegalStateException if no PeriodRequirement is found for the given subject and class.
	 */
	public void decrementRemainingPeriods(String subject, String classGrade, String section) {
		String classKey = classGrade + section;

		// Retrieve the PeriodRequirement for the subject and class
		PeriodRequirement requirement = tracker.getOrDefault(subject, new HashMap<>()).get(classKey);
		if (requirement == null) {
			throw new IllegalStateException("No PeriodRequirement found for subject: " + subject + " and class: " + classKey);
		}

		// Decrement the remaining periods
		requirement.reduceRemainingPeriodsPerDay();
		requirement.reduceRemainingPeriodsPerWeek();

		// Check if the subject is now fully scheduled for the class
		if (!requirement.hasRemainingPeriods()) {
			tracker.get(subject).remove(classKey);
			if (tracker.get(subject).isEmpty()) {
				tracker.remove(subject);
				completedSubjects.add(subject);
				incompleteSubjects.remove(subject);
			}
		}
	}


	/**
	 * Retrieves all period requirements for a specific subject.
	 *
	 * @param subject The subject name.
	 * @return A map of class keys to their period requirements, or an empty map if the subject has no requirements.
	 */
	public Map<String, PeriodRequirement> getPeriodRequirementsForSubject(String subject) {
		return tracker.getOrDefault(subject, Map.of());
	}

}
