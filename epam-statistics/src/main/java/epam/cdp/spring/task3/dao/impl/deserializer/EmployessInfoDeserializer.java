package epam.cdp.spring.task3.dao.impl.deserializer;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import epam.cdp.spring.task3.bean.EmployeesInfo;

public class EmployessInfoDeserializer implements
		JsonDeserializer<List<EmployeesInfo>> {

	@Override
	public List<EmployeesInfo> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
