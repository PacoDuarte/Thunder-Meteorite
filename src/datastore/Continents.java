/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */

package datastore;
/******************************************************************************/
import java.awt.image.BufferedImage;
import java.io.BufferedReader;                                              
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import buyer.BuyerGUI;
/******************************************************************************/


public class Continents 
{
    boolean IsHere, Found;
    char characters[]; 
    int number; float extraPrice, extraPriceFinal;
    
    String line, open;
    
    Scanner keyboard = new Scanner(System.in);
    Formatter Output = new Formatter(System.out);
    
    BufferedReader buffer;
    

    public boolean createContinent(String continente, position location)
    {       
        try 
        {
                    
            BufferedImage continentImage = ImageIO.read(new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/"+continente));
            int[] rgb;
            rgb=getPixelData(continentImage,location);
            
            if((rgb[0]==0) && (rgb[1]==0) && (rgb[2]==0))
            {
                IsHere=false;
            }
            if((rgb[0]==255) && (rgb[1]==255) && (rgb[2]==255))
            {
                IsHere=true;
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(BuyerGUI.class.getName()).log(Level.SEVERE, null, ex);
            IsHere=false;
        }
        return IsHere;    
    }
    
    public static int[] getPixelData(BufferedImage img, position location) 
    {
      
        int argb = img.getRGB(location.x, location.y);

        int rgb[] = new int[] 
        {
            (argb >> 16) & 0xff, //red
            (argb >>  8) & 0xff, //green
            (argb      ) & 0xff  //blue
        };
        System.out.println("rgb: " + rgb[0] + " " + rgb[1] + " " + rgb[2]);
        return rgb;
    }
    
    

    
    public float CheckWhere(int Number) 
    {   
        switch(Number)
        {
            case 1: 
                extraPrice = 100;
                break;
                
            case 2:
                extraPrice = 1000;
                break;
                
            case 3:
                extraPrice = 200;
                break;
                
            case 4:
                extraPrice = 500;
                break;
                
            case 5:
                extraPrice = 100;
                break;
                
            case 6:
                extraPrice = 150;
                break;
                
            case 7:
                extraPrice = 200;
                break;
                
            default:
                extraPrice = 0;
                break;
        }
        return extraPrice; 
    }
    
    
    public int cheakLocation(position location)
    {   
        number = 0;
        
        Found = createContinent("Africa.png", location);
        if (Found)
           number = 1;
        
        Found = createContinent("Antarctica.png", location);
        if (Found)
           number = 2;
           
        Found = createContinent("Asia.png", location);
        if (Found)
           number = 3;
        
        Found = createContinent("Austrailia.png", location);
        if (Found)
           number = 4;
        
        Found = createContinent("Europe.png", location);
        if (Found)
           number = 5;
        
        Found = createContinent("NorthAmerica.png", location);
        if (Found)
           number = 6;
        
        Found = createContinent("SouthAmerica.png", location);
        if (Found)
           number = 7;
        
        return number;
    }
    
    
    /* Allow suser to Change price  */
    /*------------------------------*/
    public float calculateExtraPrice(position location)
    {   
                 number = cheakLocation(location);
        extraPriceFinal = CheckWhere(number);
    
        return extraPriceFinal;
    }
    
    
    /* Computes the distance between a seller and a buyer */
    /*----------------------------------------------------*/
    public float CalculateDistance(location buyer, location seller)
    {
        float lat1, lat2, lng2, lng1;
        
        lat1 = buyer.latitude;
        lat2 = seller.latitude;
        lng1 = buyer.longitude;
        lng2 = seller.longitude;
                
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
        
        return new Float(dist).floatValue();
    }

}