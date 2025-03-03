package com.school.timetable.domain.datastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.timetable.domain.model.output.SubjectUsage.ClassPeriodCount;

/**
 * Tracks the number of class periods assigned for each subject across different classes and sections.
 * This data is used to generate output reports.
 */
public class SubjectUsageMap {

	/** 
	 * A map that tracks subject usage.
	 * 
	 * Key: Subject name  
	 * Value: A list of ClassPeriodCount objects representing period allocations for different class-sections.
	 */
	private final Map<String, List<ClassPeriodCount>> usageMap;

	/**
	 * Constructs a new SubjectUsageMap with an empty internal structure.
	 */
	public SubjectUsageMap() {
		this.usageMap = new HashMap<>();
	}

	/**
	 * Adds usage details for a subject.
	 * If the subject is not already in the usage map, it initializes a new entry.
	 *
	 * @param subject The subject name.
	 * @param count   The class period count details.
	 */
	public void addUsage(String subject, ClassPeriodCount count) {
		usageMap.putIfAbsent(subject, new java.util.ArrayList<>());
		usageMap.get(subject).add(count);
	}

	/**
	 * Retrieves usage details for a subject.
	 *
	 * @param subject The subject name.
	 * @return A list of ClassPeriodCount objects for the subject.
	 */
	public List<ClassPeriodCount> getUsage(String subject) {
		return usageMap.getOrDefault(subject, List.of());
	}

	/**
	 * Retrieves the internal usage map.
	 *
	 * @return The usage map.
	 */
	public Map<String, List<ClassPeriodCount>> getUsageMap() {
		return usageMap;
	}

	/**
	 * Checks if all required periods for a subject have been assigned.
	 *
	 * @param subject The subject name.
	 * @return True if all periods are assigned, otherwise false.
	 */
	public boolean isSubjectFullyAssigned(String subject) {
		List<ClassPeriodCount> periodCounts = getUsage(subject);
		if (periodCounts == null || periodCounts.isEmpty()) {
			return false;
		}

		// Check if all classes and sections for the subject have zero remaining periods
		for (ClassPeriodCount count : periodCounts) {
			if (count.getTotalPeriods() > 0) {
				return false; // Periods still remain
			}
		}
		return true;
	}

	/**
	 * Not implemented this method properly yet.
	 * Used to decrease the subject usage count.
	 * @param subject
	 * @param classGrade
	 * @param section
	 */
	public void decrementSubjectUsage(String subject, String classGrade, String section) {
		List<ClassPeriodCount> periodCounts = usageMap.getOrDefault(subject, List.of());

		//      for (ClassPeriodCount count : periodCounts) {
		//          if (count.getClassGrade().equals(classGrade) && count.getSection().equals(section)) {
		//              count.decrementTotalPeriods(); // Assuming ClassPeriodCount has a decrementTotalPeriods method
		//              break;
		//          }
		//      }
	}

}
