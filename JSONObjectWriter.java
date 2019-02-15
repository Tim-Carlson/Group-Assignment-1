import java.io.FileWriter;
import com.google.gson.Gson;

public class JSONObjectWriter {

	public JSONObjectWriter(SiteCollection site, String fileName) {
		Gson gson = new Gson();
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			fileWriter.write(gson.toJson(site));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
