package com.school.timetable.domain.model.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.timetable.domain.model.input.TimetableInput;

import java.io.IOException;
import java.io.InputStream;

/**
 * Parses input JSON data and converts it into a {@link TimetableInput} object.
 */
public class JsonInputParser {
	/**
	 * Jackson ObjectMapper instance for JSON parsing.
	 */
	private final ObjectMapper objectMapper;

	/**
	 * Initializes a new parser with a default ObjectMapper.
	 */
	public JsonInputParser() {
		this.objectMapper = new ObjectMapper();
	}
	/*
	 * 	ObjectMapper: Jackson class for json parsing.
		readValue: Converts JSON into the specified Java object [TimetableInput.class]
		File: Represents the JSON file provided by the user.
	 */

	/**
	 * Reads and parses a JSON file from the classpath and converts it into a {@link TimetableInput} object.
	 *
	 * @param resourcePath The path to the JSON file.
	 * @return A {@link TimetableInput} object populated with parsed data.
	 * @throws IOException If the file cannot be found or read.
	 */
	public TimetableInput parseInput(String resourcePath) throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
		if (inputStream == null) {
			throw new IOException("File not found in classpath: " + resourcePath);
		}
		return objectMapper.readValue(inputStream, TimetableInput.class);
	}
}