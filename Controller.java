
import java.util.ArrayList;
/**
 * Robert here. I hope you guys don't mind if I make some  additions to Controller; I felt it was the best way to incorporate this into the code. 
 * I'll comment when I add a method and such so that people know what I added.  Also, any imports after this are mine as well.
 */
import java.lang.StringBuilder;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Controller {

    private ArrayList<Study> studyList;
    private Study notApplicable;
    private JsonReader jRead;
    private JsonWriter jWrite;
    private String memoryLocation;

    public Controller() {
        studyList = new ArrayList<Study>();
        notApplicable = new Study("Not Applicable", "404");
        studyList.add(notApplicable);
        jRead = new JsonReader();
        jWrite = new JsonWriter();
        memoryLocation = "./src/contents/memory.json";
        restoreMemory();
    }

    public static void main(String[]args) {
        Gui gui = new Gui();
        gui.setVisible(true);
    }

    private void restoreMemory() {
        if(readJson(memoryLocation));
        else System.out.println("Was unable to load from memory file");
    }

    public boolean saveToMemory() {
        return writeJson(memoryLocation);
    }

    public boolean readJson(String location) {
        return jRead.read(location, notApplicable);
    }

    public boolean writeJson(String location) {
        return jWrite.write(location, studyList);
    }

    public void addEntry(String site, String id, String key, String val, String date) {
        Site buf = notApplicable.getOrMakeSite(site);
        if (buf.isCollection_open())
            buf.addReading(val, "" + date, id, key);
        else System.out.println("Site " + buf.getSite_id() + " is closed.");
    }

    public void setSiteOpen(String siteID) {
        int size = studyList.size();

        for(int i = 0; i < size; i++) {
            if(studyList.get(i).getSite(siteID) == null);
            else {
                studyList.get(i).getSite(siteID).openCollection();
            }
        }
    }

    public void setSiteClosed(String siteID) {
        int size = studyList.size();

        for(int i = 0; i < size; i++) {
            if(studyList.get(i).getSite(siteID) == null);
            else {
                studyList.get(i).getSite(siteID).closeCollection();
            }
        }
    }

    /**
     * Searches through all studies for sites with matching siteIDs to display
     * @param siteID
     * @return
     */
    public String displaySite(String siteID) {
        int size = studyList.size();
        String output = "";

        for(int i = 0; i < size; i++) {
            if(studyList.get(i).getSite(siteID) == null);
            else {
                output += studyList.get(i).getSite(siteID).toString();
            }
        }

        return output;
    }

    public Study getStudy(String studyID) {
        Study temp = null;
        int size = studyList.size();
        for(int i = 0; i < size; i++) {
            if(studyList.get(i).getStudyID().equals(studyID)) {
                temp = studyList.get(i);
            }
        }

        return temp;
    }

    /**
     * Robert here with his stuff! 
     * 
     * 
     */
    
    
  
    public Boolean XMLImporter()

    {

        /**
         * This allows the user to browse for and select a XML file. 
         */
        final JFileChooser opendoor = new JFileChooser();
        FileFilter XMLOnly = new FileNameExtensionFilter( "Only XML files", "xml");
        opendoor.setFileFilter(XMLOnly);
        int returnVal = opendoor.showOpenDialog(null);
        File testsub= null;
        Study studyIndex;
        Site siteIndex;
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            testsub=opendoor.getSelectedFile();
        }
        /**
         *  If file exists, this translates it to a string so info can be gleamed from it. 
         */
        try {
            BufferedReader reader =new BufferedReader(new FileReader(testsub));
            StringBuilder tesla = new StringBuilder();
            String line=null;
            while ((line = reader.readLine()) != null)
            {
                tesla.append(line);
            }
            reader.close();
            String test=tesla.toString();
            int x = test.indexOf("\"");
            int y=test.indexOf("\"", x+1); //These are so we can get the location of the first set of quotation marks. You'll see this a lot in the program. 
            String StudyID = test.substring(x+1,y);
            String StudyName=test.substring(y+2, test.indexOf("</St"));
         StudyID=StudyID+ "(I)";
         studyList.add(new Study(StudyName,StudyID));
         studyIndex=getStudy(StudyID);
         
        
            int ViolentDelight = 0;
            int ViolentEnds = 0;
            Boolean HasSite=false; 
            while (ViolentEnds != -1)
            {
                ViolentDelight = test.lastIndexOf("<Reading type=" );
                ViolentEnds = test.lastIndexOf("</Reading>");

                String brick = test.substring(ViolentDelight, ViolentEnds+10);  
                HasSite=brick.contains("Site");

                if (HasSite)
                {
                    Site Placeholder=Feature3.TurnToSite(brick);
                    
                    siteIndex=studyIndex.getOrMakeSite(Placeholder.getSite_id() + "(I)");
                    
                   
                }
                else {
                    
                    siteIndex=studyIndex.getOrMakeSite("Null(I)");
                    
                }

                test=test.substring(0,ViolentDelight); //Trims the last entry (AKA the one just read) from the string so we can see the next one. 
              

                Reading yoyo=Feature3.TurnToReading(brick);
                siteIndex.addReading(yoyo);
                
                ViolentEnds = test.lastIndexOf("<Reading type=" );
                
                
                
            }
        }catch(Exception e){
            System.out.println("Could not get file");
            return false;
        }

        return true; 
    }
/**
 * And I'm out- Robert
 */

}