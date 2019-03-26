package contents;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonReader {
	private JSONParser parser;

	public JsonReader() {
		parser = new JSONParser();
	}

	@SuppressWarnings("unchecked")
	public boolean read(String location, ArrayList<Study> studyPlaceholder) {
		String siteId, readingValue, readingDate, readingId, readingType;
		JSONObject jObj = null;
		boolean ret = true;
		int start, end;
		LinkedList<String> studyNames = new LinkedList<String>();

		try {
			// int i, studyPlaceholderIndex = -1;
			Object obj = parser.parse(new FileReader(location));
			jObj = (JSONObject) obj;

		} catch (FileNotFoundException e) {
			ret = false;

		} catch (IOException e) {
			ret = false;

		} catch (ParseException e) {
			ret = false;
		}

		if (ret) {
			// Discover study names
			String json = jObj.toString();

			// Study names will always be before the pattern: ":{"
			for (int i = 0; i < json.length() - 3; i++) {
				if (json.substring(i, i + 4).compareTo("\":{\"") == 0) {
					end = i;
					start = end;
					while (start > 0) {
						start -= 1;
						if (json.charAt(start) == '"') {
							// get substring between start and end, and add it
							// to studyNames
							studyNames.add(json.substring(start + 1, end));
							start = -1;
						}
					}
				}
			}

			for (int i = 0; i < studyNames.size(); i++) {
				// Get the study object
				JSONObject studyArray = (JSONObject) jObj
						.get(studyNames.get(i));
				Study buf;

				// Get every site reading in the study object
				JSONArray siteArray = (JSONArray) studyArray
						.get("site_readings");
				Iterator<String> siteScan = siteArray.iterator();

				// Start prepping our output for this iteration
				buf = new Study(studyNames.get(i), studyNames.get(i));

				while (siteScan.hasNext()) {
					Object readings = siteScan.next();
					JSONObject jReadings = (JSONObject) readings;

					// Read in site data.
					siteId = String.valueOf(jReadings.get("site_id"));
					readingValue = String
							.valueOf(jReadings.get("reading_value"));
					readingDate = String.valueOf(jReadings.get("reading_date"));
					readingId = String.valueOf(jReadings.get("reading_id"));
					readingType = String.valueOf(jReadings.get("reading_type"));

					buf.getOrMakeSite(siteId).addReading(readingValue,
							readingDate, readingId, readingType);
				}

				// Commit site data
				studyPlaceholder.add(buf);
			}

			// Now see if there are any study-less sites.
			JSONArray siteArray = (JSONArray) jObj.get("site_readings");
			if (siteArray != null) {
				// If there are, do the same thing as above, but with
				// notApplicable study.
				studyPlaceholder
						.add(new Study("notApplicable", "notApplicable"));
				Iterator<String> siteScan = siteArray.iterator();
				while (siteScan.hasNext()) {
					Object readings = siteScan.next();
					JSONObject jReadings = (JSONObject) readings;

					siteId = String.valueOf(jReadings.get("site_id"));
					readingValue = String
							.valueOf(jReadings.get("reading_value"));
					readingDate = String.valueOf(jReadings.get("reading_date"));
					readingId = String.valueOf(jReadings.get("reading_id"));
					readingType = String.valueOf(jReadings.get("reading_type"));

					studyPlaceholder.get(studyPlaceholder.size() - 1)
							.getOrMakeSite(siteId).addReading(readingValue,
									readingDate, readingId, readingType);
				}
			}
		} 
		return ret;
	}
}
