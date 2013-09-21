package epam.cdp.spring.task3.dao.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.Resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class DaoUtils {

	public static JsonElement parseJsonElement(Resource resource)
			throws FileNotFoundException, IOException {
		JsonParser parser = new JsonParser();
		JsonReader reader = new JsonReader(new FileReader(resource.getFile()));
		return parser.parse(reader);
	}

}
