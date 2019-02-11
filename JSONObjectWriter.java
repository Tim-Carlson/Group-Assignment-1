
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONObjectWriter {

	private String reading_type;
	private double reading_value;
	private String reading_id;
	private String reading_date;
	JSONObject obj;

	public JSONObjectWriter(Reading readingObject) {

		this.reading_type = readingObject.getReading_type();
		this.reading_date = readingObject.getReading_date();
		this.reading_id = readingObject.getReading_id();
		this.reading_value = readingObject.getReading_value();
	}

	public void write() {
		

		try (FileWriter write = new FileWriter("SiteFile.json")) {

			obj = new JSONObject();
			obj.put("readingType", reading_type);
			obj.put("readingValue", new Double(reading_value));
			obj.put("readingId", reading_id);
			obj.put("readingDate", reading_date);
			
		

			write.write(obj.toString());
			write.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
