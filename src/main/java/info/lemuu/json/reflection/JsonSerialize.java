package info.lemuu.json.reflection;

import org.json.simple.JSONObject;

public interface JsonSerialize {
	
	public default String toStringJson(Object object) {
		return this.serialize(object).toJSONString();
	}
	
	public default JSONObject serialize(Object object) {
		JsonObjectValue jsonObjectValue = new JsonObjectValue(object);
		return jsonObjectValue.toJsonObject();
	}
	
}