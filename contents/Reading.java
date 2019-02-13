package contents;


public class Reading {

	private double reading_value;
	private String reading_date;//This is OK being a String for now
	private String reading_id;
	private String reading_type;
	
	public Reading(double reading_value, String reading_date, String reading_id, String reading_type) {
		this.reading_value = reading_value;
		this.reading_date = reading_date;
		this.reading_id = reading_id;
		this.reading_type = reading_type;
		
	}

	//Most of these getter/setter classes shouldn't be used because we are using the constructor to set data,
	//and using the tostring method to display
	public String getReading_date() {
		return reading_date;
	}

	public void setReading_date(String reading_date) {
		this.reading_date = reading_date;
	}

	public double getReading_value() {
		return reading_value;
	}

	public void setReading_value(double reading_value) {
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
		return "reading_type=" + reading_type + "\nreading_value=" + reading_value + "\nreading_id="
				+ reading_id + "\nreading_date=" + reading_date + "\n";
	}


	
}
