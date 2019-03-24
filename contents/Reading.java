package contents;


public class Reading {

	private String reading_value;
	private String reading_date;
	private String reading_id;
	private String reading_type;
	
	public Reading(String reading_value, String reading_date, String reading_id, String reading_type) {
		this.reading_value = reading_value;
		this.reading_date = reading_date;
		this.reading_id = reading_id;
		this.reading_type = reading_type;
		
	}

	public String getReading_date() {
		return reading_date;
	}

	public void setReading_date(String reading_date) {
		this.reading_date = reading_date;
	}

	public String getReading_value() {
		return reading_value;
	}

	public void setReading_value(String reading_value) {
		this.reading_value = reading_value;
	}

	public String getReading_id() {
		return reading_id;
	}

	public void setReading_id(String reading_id) {
		this.reading_id = reading_id;
	}

	public String getReading_type() {
		return reading_type;
	}

	@Override
	public String toString() {
		
		return "\treading_type=   " + reading_type + "\n\treading_value= " + reading_value + "\n\treading_id=      "
				+ reading_id + "\n\treading_date=  " + reading_date + "\n";
		
	}


	
}
