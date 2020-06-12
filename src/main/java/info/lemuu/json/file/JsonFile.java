package info.lemuu.json.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class JsonFile {
	
	private final String name;
	private final File file;
	
	public JsonFile(String name, File directory) {
		this.name = name.matches(".*(?i).json$") ? name : name.concat(".json");
		if (!directory.exists()) {
			directory.mkdir();
		}
		this.file = new File(directory, this.name);
	}
	
	public JsonFile(String name) {
		this.name = name.matches(".*(?i).json$") ? name : name.concat(".json");
		this.file = new File(this.name);
	}
	
	public boolean createFile() throws IOException {
		 if (!this.file.exists()) {
             this.file.createNewFile();
             return true;
         }
		 return false;
	}
	
	public JSONObject read() {
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(this.file));
			return (JSONObject) object;
		} catch (IOException | ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void write(JSONObject object) throws IOException {
		FileWriter file = new FileWriter(this.file);
		
		file.write(JsonFormat.format(object));
		file.flush();
		file.close();
	}
	
}