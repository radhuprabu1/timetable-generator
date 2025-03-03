package com.school.timetable.domain.model.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Custom deserializer for {@link SubjectDetails} objects.
 * Converts JSON input into a map where the key is the subject name
 * and the value is a list of {@link SubjectDetails} objects.
 */
public class SubjectDetailsDeserializer extends StdDeserializer<Map<String, List<SubjectDetails>>> {

	private static final long serialVersionUID = -8011566612850951344L;

	/**
	 * Default constructor.
	 */
	public SubjectDetailsDeserializer() {
		this(null);
	}

	public SubjectDetailsDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Map<String, List<SubjectDetails>> deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		Map<String, List<SubjectDetails>> subjectDetailsMap = new HashMap<>();
		JsonNode rootNode = parser.getCodec().readTree(parser);

		// Iterate through each subject in the JSON
		Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String subjectName = field.getKey(); // The key, e.g., "Mathematics"

			List<SubjectDetails> detailsList = new ArrayList<>();
			for (JsonNode detailNode : field.getValue()) {
				SubjectDetails detail = new SubjectDetails();
				detail.setSubject(subjectName); // Set the subject name
				detail.setClassGrade(detailNode.get("class").asText());
				detail.setSection(detailNode.get("section").asText());
				detail.setMinPeriodsPerDay(detailNode.get("minPeriodsPerDay").asInt());
				detail.setPeriodsPerWeek(detailNode.get("periodsPerWeek").asInt());
				detailsList.add(detail);
			}
			subjectDetailsMap.put(subjectName, detailsList);
		}
		return subjectDetailsMap;
	}
}
