
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

System.out.println(test);

}catch(Exception e){
System.out.println("Could not get file");
}
}
}