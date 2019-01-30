package contents;


public class Reading {

	
	private long reading_date;
	private double reading_value;
	private String reading_id;
	private String reading_type;
	
	public Reading(String reading_type) {
		this.reading_date = 0;
		this.reading_value = 0;
		this.reading_id = null;
		this.reading_type = reading_type;
		
	}

	public long getReading_date() {
		return reading_date;
	}

	public void setReading_date(long reading_date) {
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
