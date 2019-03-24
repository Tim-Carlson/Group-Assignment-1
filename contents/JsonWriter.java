package contents;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonWriter {

	// Return true if the JSON file could be written.
	
	@SuppressWarnings("unchecked")
	public boolean write(String location, ArrayList<Study> studyList) {
		
		boolean ret = true;
		String sbuf = "";
		int numReadings = 0;
		
		File f = new File(location);

		JSONObject jbuf;
		Reading rbuf;
		
		
		JSONObject report = new JSONObject();
		JSONArray siteReadings = new JSONArray();
		
		for (int h = 0; h < studyList.size(); h++) {
			for (int i = 0; i < studyList.get(h).size(); i++) {
				for (int j = 0; j < studyList.get(h).getSiteList().get(i).size(); j++) {
					siteReadings.add(new JSONObject());
					jbuf = ((JSONObject) siteReadings.get(numReadings));
					rbuf = studyList.get(h).getSiteList().get(i).getReading(j);
					jbuf.put("site_id", studyList.get(h).getSiteList().get(i).getSite_id());
					jbuf.put("reading_id", rbuf.getReading_id());
					jbuf.put("reading_date", rbuf.getReading_date());
					jbuf.put("reading_type", rbuf.getReading_type());
					jbuf.put("reading_value", rbuf.getReading_value());
					numReadings ++;
				}
			}
		}
		report.put("site_readings", siteReadings);
		sbuf = report.toJSONString();
		
		try {
			f.getParentFile().mkdirs();
			f.createNewFile();
			PrintWriter pw = new PrintWriter(f);
			pw.write(sbuf);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			ret = false;
			System.out.println("File error in File Operator.");
		}
		
		return ret;
	}
}
