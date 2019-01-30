package contents;

public class Site {

	
	private int site_id; //decided to add site_id to the site object, seemed like a good idea
	private boolean collection_open; //made default of == true
	private Reading humidity, temperature, barometric, particulate;
	
	
	
	public Site(int site_id) {//site class auto-creates four reading classes, assigns them reading_type
		this.site_id = site_id;
		this.collection_open = true;
		
		this.humidity = new Reading("humidity");
		this.temperature = new Reading("temp");
		this.barometric = new Reading("bar_press");
		this.particulate = new Reading("particulate");
		
	}


	public int getSite_id() {
		return site_id;
	}


	public boolean isCollection_open() {
		return collection_open;
	}

	public void closeCollection() {
		this.collection_open = false;
	}
	
	public void openCollection() {
		this.collection_open = true;
	}

	public Reading getHumidity() {
		return humidity;
	}

	public void setHumidity(long reading_date, double reading_value, String reading_id) {
		humidity.setReading_date(reading_date);
		humidity.setReading_value(reading_value);
		humidity.setReading_id(reading_id);	
	}

	public Reading getTemperature() {
		return temperature;
	}
	
	public void setTemperature(long reading_date, double reading_value, String reading_id) {
		temperature.setReading_date(reading_date);
		temperature.setReading_value(reading_value);
		temperature.setReading_id(reading_id);	
	}

	public Reading getBarometric() {
		return barometric;
	}

	public void setBarometric(long reading_date, double reading_value, String reading_id) {
		barometric.setReading_date(reading_date);
		barometric.setReading_value(reading_value);
		barometric.setReading_id(reading_id);	
	}
	
	public Reading getParticulate() {
		return particulate;
	}

	public void setParticulate(long reading_date, double reading_value, String reading_id) {
		particulate.setReading_date(reading_date);
		particulate.setReading_value(reading_value);
		particulate.setReading_id(reading_id);	
	}


	@Override
	public String toString() {
		String openClose;
		if(collection_open) { openClose = "Open";}
		else { openClose = "Closed";}
		
		return "Site_readings for site_id:" + site_id + "\n\n" + humidity.toString() + "\n" + 
	particulate.toString() + "\n" + temperature.toString() + "\n" + barometric.toString() + "Site Collection is: " 
	+ openClose;
	}
	
	
	
}
