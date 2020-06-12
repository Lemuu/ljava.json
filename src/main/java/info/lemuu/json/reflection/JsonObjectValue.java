package info.lemuu.json.reflection;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.json.simple.JSONObject;
import java.util.stream.Collectors;
import java.lang.reflect.InvocationTargetException;

public class JsonObjectValue {
	
	private final Object object;
	
	public JsonObjectValue(Object object) {
		this.object = object;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		
		this.collectMethods().forEach(method -> {
			method.setAccessible(true);
			
			try {
				json.put(this.getField(method).getName(), method.invoke(object));
			} catch (IllegalAccessException | IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (InvocationTargetException ex) {
				ex.getTargetException().printStackTrace();
			}
		});
		return json;
	}
	
	private Field getField(Method method) {
		return this.collectFields().stream()
			.filter(field -> method.getName().replaceFirst("get", "").equalsIgnoreCase(field.getName()))
			.findFirst()
			.orElse(null);
	}
	
	private List<Field> collectFields() {
		return Arrays.stream(this.object.getClass().getDeclaredFields()).collect(Collectors.toList());
	}
	
	private List<Method> collectMethods() {
		List<Method> methods = new ArrayList<Method>();
		this.collectFields().forEach(field -> {
			field.setAccessible(true);
			
			methods.addAll(Arrays.stream(this.object.getClass().getDeclaredMethods())
					.filter(method -> method.getName().startsWith("get")
						&& method.getName().length() - 3 == field.getName().length()
						&& method.getName().replaceFirst("get", "").equalsIgnoreCase(field.getName()))
					.collect(Collectors.toList()));
		});
		return methods;
	}

}