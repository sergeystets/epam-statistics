package epam.cdp.spring.task3.dao.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.Resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class ParseUtils {

	public static JsonElement parseJsonElement(Resource resource)
			throws FileNotFoundException, IOException {
		JsonParser parser = new JsonParser();
		JsonReader reader = new JsonReader(new FileReader(resource.getFile()));
		return parser.parse(reader);
	}
	
	/**
	 * Extract year from a string containing year.
	 * 
	 * @param stringWithYear
	 *            string that matches pattern <i>'Year(\d)+'</i>
	 * @return
	 */
	public static Integer parseYear(String stringWithYear) {
		if (!stringWithYear.matches("Year(\\d)+")) {
			throw new IllegalArgumentException("string: " + stringWithYear
					+ " does not match pattern: 'Year(\\d)+'");
		}
		int length = stringWithYear.length();
		return Integer.valueOf(stringWithYear.substring(4, length));
	}

}
