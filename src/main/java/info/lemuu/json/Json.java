package info.lemuu.json;

import java.io.File;
import info.lemuu.json.file.JsonFile;

public class Json extends JsonFile {

	public Json(String name, File directory) {
		super(name, directory);
	}
	
	public Json(String name) {
		super(name);
	}
	
}