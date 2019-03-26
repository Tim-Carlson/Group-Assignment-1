package contents;
/**
 * converts an XML file to string, and then takes any reading object it can find and adds it to the objects we have
 * 
 * @Robert
 * @version0.1
 */

  

public class Feature3 {
/**
 * Parameters: A string, alpha, that is a fragment of a XML file that covers an entire reading object.
 * 
 * Returns: A new reading object from that XML file 
 * 
 */
public static Reading TurnToReading(String alpha)
{ 
   
    String ReadType= "";
    String SiteNum = " ";
    String ReadID= " ";
    String ValueGet= " ";
    /*
     * Initialized so that if something goes wrong, the program at least doesn't fail
     */
    
   
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
  
   ValueGet=alpha.substring(x,y);

     Reading X = new Reading(ValueGet, "  Imported " , ReadID , ReadType );
     
     return X;
}
/**
 * Param: A string, alpha, that is a segment of an XML file that covers an entire reading object
 * 
 * Returns: A new site Object
 * 
 */
public static Site TurnToSite (String alpha)
{
    int x =alpha.indexOf("<Site>")+6;
    int y=alpha.indexOf("</Site");
    
    Site X=new Site(alpha.substring(x,y)+"(I)");
    return X; 
}

}