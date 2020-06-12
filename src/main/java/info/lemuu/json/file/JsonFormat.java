package info.lemuu.json.file;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import com.google.gson.GsonBuilder;

public class JsonFormat {
	
	public static String format(JSONObject object) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(object.toJSONString()).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		return gson.toJson(json);
	}

}