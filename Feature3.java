
/**
 * converts an XML file to string, and then takes any reading object it can find and adds it to the objects we have
 * 
 * @Robert
 * @version0.1
 */
import java.lang.StringBuilder;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Feature3 {
public static void main(String[] args)
{
    /**
     * This allows the user to browse for and select a XML file. 
     */
    final JFileChooser opendoor = new JFileChooser();
    FileFilter XMLOnly = new FileNameExtensionFilter( "Only XML files", "xml");
    opendoor.setFileFilter(XMLOnly);
    int returnVal = opendoor.showOpenDialog(null);
    File testsub= null;
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
int y=test.indexOf("\"", x+1); //These are so we can get the location of the first set of quotation marks. 
String StudyID = test.substring(x+1,y);
// System.out.println( "Study ID is!" + StudyID); 
//Test statement to see if it properly extracts Study ID. 
int ViolentDelight = 0;
int ViolentEnds = 0;
while (ViolentEnds != -1)
{
ViolentDelight = test.lastIndexOf("<Reading type=" );
ViolentEnds = test.lastIndexOf("</Reading>");

String brick = test.substring(ViolentDelight, ViolentEnds+10); //+14 if I want to strip everything from the start. 
//System.out.println(brick);
//Test statement to see if it correctly grabbed the individual objects
 test=test.substring(0,ViolentDelight);

Reading yoyo=TurnToReading(brick);
ViolentEnds = test.lastIndexOf("<Reading type=" );
}
}catch(Exception e){
System.out.println("Could not get file");
}
}

public static Reading TurnToReading(String alpha)
{ 
   
    String ReadType= "";
    String SiteNum = " ";
    String ReadID= " ";
    String ValueGet= " ";
    
   
   boolean ValueUnit = alpha.contains ( "Value unit"); //We need to make sure we grab only the values and not the coding around it in the XML files. 
  
   
    int x=alpha.indexOf("\"");
    int y = alpha.indexOf("\"",x+1); 
    
    ReadType = alpha.substring(x+1,y);
    x =alpha.indexOf("\"",y+1);
    y = alpha.indexOf("\"",x+1); 
    ReadID = alpha.substring(x+1,y);
    if (!ValueUnit)
    { x=alpha.indexOf("V")+6;
      
    }else { x=alpha.indexOf("\"",y+1)+1;
    x=alpha.indexOf("\"",x+1)+2;
    } //We need to make sure that we start the substring from the right place
   y=alpha.indexOf("</V");
   System.out.println(x);
   ValueGet=alpha.substring(x,y);
   //System.out.println ( "Test: Value  " + ValueGet + "   Reading ID   " + ReadID + "  Reading Type  " + ReadType);
     //Test statement to make sure its working. 
     Reading X = new Reading(ValueGet, "  Imported " , ReadID , ReadType );
     
     return X;
}


}