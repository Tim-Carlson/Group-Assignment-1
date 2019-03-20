package contents;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

/*
The JsonWriter class is a class that writes a java object to a JSON file. 
It's constructor will accepts a study object and a string type name that will be the name of the file.
*/
public class JsonWriter {
	private Gson gson;
	private Study studies;
	private String fileName;

	/**
	 * Accepts a Study object and an intended file name.  
	 * @param study
	 * @param fileName
	 * 
	 */
	public JsonWriter(Study study, String fileName) {
		gson = new Gson();
		this.studies = study;
		this.fileName = fileName;
	}
/**
 * The write method will perform wring to a file. 
 * @throws IOException
 */
	public void Writer() throws IOException {

		try (FileWriter fileWriter = new FileWriter(fileName+".json", true)) {
			fileWriter.write(gson.toJson(studies));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
