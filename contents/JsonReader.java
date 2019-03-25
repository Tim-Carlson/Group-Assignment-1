package contents;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonReader {

	private JSONParser parser;
	private Site sitePlaceholder;

	public JsonReader() {
		parser = new JSONParser();

	}

	@SuppressWarnings("unchecked")
	public boolean read(String location, ArrayList<Study> studyPlaceholder) {
		String siteId, readingValue, readingDate, readingId, readingType, studyName, studyID;
		boolean ret = true;

		try {
			int i, studyPlaceholderIndex = -1;
			Object obj = parser.parse(new FileReader(location));
			JSONObject jObj = (JSONObject) obj;

			JSONArray siteArray = (JSONArray) jObj.get("site_readings");
			Iterator<String> siteScan = siteArray.iterator();

			// Add notApplicable first, so it can be the default.
			studyPlaceholder.add(new Study("notApplicable", "-1"));

			while (siteScan.hasNext()) {
				i = 0;
				Object readings = siteScan.next();
				JSONObject jReadings = (JSONObject) readings;

				// Read in site data.
				siteId = String.valueOf(jReadings.get("site_id"));
				readingValue = String.valueOf(jReadings.get("reading_value"));
				readingDate = String.valueOf(jReadings.get("reading_date"));
				readingId = String.valueOf(jReadings.get("reading_id"));
				readingType = String.valueOf(jReadings.get("reading_type"));

				// Sort out study information, e.g. where to put it & if study already exists
				if (jReadings.containsKey("study_name") && jReadings.containsKey("study_id")) {
					studyName = String.valueOf(jReadings.get("study_name"));
					studyID = String.valueOf(jReadings.get("study_id"));

					// Check if the study in this entry exists.
					while (i < studyPlaceholder.size() && studyPlaceholderIndex == -1) {
						if (studyPlaceholder.get(i).getNameOfStudy().compareToIgnoreCase(studyName) == 0
								&& studyPlaceholder.get(i).getStudyID().compareToIgnoreCase(studyID) == 0)
							studyPlaceholderIndex = i;
						i++;
					}

					// Add the study if it doesn't exist and make sure studyPlaceholderIndex points
					// to it
					if (studyPlaceholderIndex == -1) {
						// This will be the index after we add the new study
						studyPlaceholderIndex = studyPlaceholder.size();
						studyPlaceholder.add(new Study(studyName, studyID));
					}

					// After all that, if the collection is open, add the data.
					if (studyPlaceholder.get(studyPlaceholderIndex).isCollectionOpen(siteId))
						studyPlaceholder.get(studyPlaceholderIndex).getOrMakeSite(siteId).addReading(readingValue,
								readingDate, readingId, readingType);

				} else {
					// Default is the notApplicable study. Add data if collection for site is open.
					if (studyPlaceholder.get(0).isCollectionOpen(siteId))
						studyPlaceholder.get(0).getOrMakeSite(siteId).addReading(readingValue, readingDate, readingId,
								readingType);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return ret;
	}

}
