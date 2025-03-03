package com.school.timetable.domain.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.school.timetable.domain.datastructure.TeacherAvailabilityMap;
import com.school.timetable.domain.model.common.DayOfWeek;
import com.school.timetable.domain.model.input.Teacher;

public class TeacherAvailabilityMapTest {

	@Test
	public void testAddAndRetrieveAvailability() {
		TeacherAvailabilityMap map = new TeacherAvailabilityMap();
		Teacher teacher = new Teacher("Radhu", List.of("Mathematics"), null, null, null, 0, 0, 0, null);

		map.addAvailability(DayOfWeek.Monday, 1, teacher);

		List<Teacher> teachers = map.getAvailableTeachers(DayOfWeek.Monday, 1);

		assertNotNull(teachers);
	}

}
