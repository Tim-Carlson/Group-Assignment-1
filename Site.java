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


	public Reading getTemperature() {
		return temperature;
	}


	public Reading getBarometric() {
		return barometric;
	}


	public Reading getParticulate() {
		return particulate;
	}

	
	
	
	
}
