package contents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class XMLReader {
	
	/**
	 * read method returns user messages in String format, errors are reported in console
	 * currently ignores value units
	 * @param location
	 * @param studyList
	 * @return String
	 */
	public String read(String location,  ArrayList<Study> studyList) {
		
		String response = "Success";
		String studyID = "";
		String nameOfStudy = ""; 
			
        
        try {
        	//parse file
			File file = new File(location);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document document = dBuilder.parse(file);
	        document.getDocumentElement().normalize();
	        
	        //Make sure the first document element is <ReadingSet>
	        if((document.getDocumentElement().getNodeName()).equals("ReadingSet")) {
	        	NodeList xmlStudyList = document.getElementsByTagName("Study");
	        	Node xmlStudyNode = xmlStudyList.item(0);
	        	Study studyPlaceholder = null;
	        	Site sitePlaceholder = null;
	        	Element element = null;
	        	
	        	//Read Study info, and create or find applicable study
	        	if (xmlStudyNode.getNodeType() == Node.ELEMENT_NODE) {
	        		element = (Element) xmlStudyNode;
	        		studyID = element.getAttribute("id");
	        		nameOfStudy = element.getTextContent();
	        		
	        		//look for look for the study, or create one
	        		for (Study study : studyList) {
						if(study.getStudyID() == studyID) studyPlaceholder = study;
					}
	        		if (studyPlaceholder == null) {
	        			studyPlaceholder = new Study(nameOfStudy, studyID);
	        			studyList.add(studyPlaceholder);
	        		}
	        	} else {
        			System.err.println("Node Conversion Failure");
        			return "Failure";
        		}
	        	
	        	//get all the readings, and order them in a node list
	        	NodeList xmlReadingList = document.getElementsByTagName("Reading");
	        	Node xmlReadingNode = null;
	        	int rLength = xmlReadingList.getLength();
	        	int skippedReadings = 0;
	        	element = null;
	        	
	        	//iterate through the node list, looking at one reading element at a time
	        	for (int i = 0; i < rLength; i++) {
	        		xmlReadingNode = xmlReadingList.item(i);
	        		
	        		
	        		if (xmlReadingNode.getNodeType() == Node.ELEMENT_NODE) {
	        			element = (Element) xmlReadingNode;
	        			
	        			if (element.hasAttribute("type") && element.hasAttribute("id") && 
	        					(element.getElementsByTagName("Value").getLength() != 0) && 
	        						(element.getElementsByTagName("Site").getLength() != 0)){
	        				sitePlaceholder = studyPlaceholder.getOrMakeSite(element.getElementsByTagName("Site").item(0).getTextContent());
	        				
	        				if (sitePlaceholder.isCollection_open()) {
	        					sitePlaceholder.addReading(element.getElementsByTagName("Value").item(0).getTextContent(),
	        							String.valueOf(System.currentTimeMillis()),
	        								element.getAttribute("id"), element.getAttribute("type"));
	        				} else skippedReadings++;
	        			} else skippedReadings++;
	        			
	        		} else {
	        			System.err.println("Node Conversion Failure");
	        			return "Failure";
	        		}
	        	
	        	}
	        	
	        	if (skippedReadings > 0) response = "There was(were) " + skippedReadings + " skipped reading(s)";
	        	
	     
	        } else response = "ReadingSet not found";
	        
	        
		} catch (NullPointerException e) {
			System.err.println("NullPointerException-XMLReader");
			response = "Failure";
		} catch (IOException e) {
			System.err.println("IOException-XMLReader");
			response = "Failure";
		} catch (SAXException e) {
			System.err.println("SAXException-XMLReader");
			response = "Failure";
		} catch (IllegalArgumentException e) {
			System.err.println("IllegalArgumentException-XMLReader");
			response = "Failure";
		} catch (ParserConfigurationException e) {
			System.err.println("ParserConfigurationException-XMLReader");
			response = "Failure";
		} 
		
		return response;
	}
}