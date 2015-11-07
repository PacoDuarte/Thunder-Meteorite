/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */


package datastore;
/****************************************************************************/
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/****************************************************************************/


public class Pin 
{    
    /*      Image Files      */
    /*-----------------------*/
    Image soldImage;
    Image unsoldImage;

    ImageIcon soldIcon;
    ImageIcon unsoldIcon;
    
    BufferedImage sold_image;
    BufferedImage unsold_image;
    

    /*    Position_1 on the Map   */
    /*----------------------------*/
    public Position_1 pos;

    
    /*     Sold or Unsold     */
    /*------------------------*/
    public boolean sold;


    public Pin(int id,int x,int y,boolean status)
    {
        File soldFile = null;
        File unsoldFile = null;
        sold_image = null;
        unsold_image = null;
        sold = status;  
        pos = new Position_1(x,y);
            
        
        
        
        soldIcon = new ImageIcon("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Icons/Sold.png");
        unsoldIcon = new ImageIcon("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Icons/Unsold.png");
        
        
        
        ImageFilter filter = new RGBImageFilter() 
        {
            int transparentColor = Color.white.getRGB() | 0xFF000000;
            public final int filterRGB(int x, int y, int rgb) 
            {
                if ((rgb | 0xFF000000) == transparentColor)
                   return 0x00FFFFFF & rgb;

                else 
                   return rgb;
            }
        };

        ImageProducer filteredImgProd = new FilteredImageSource(soldIcon.getImage().getSource(), filter);
        soldImage = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
        filteredImgProd = new FilteredImageSource(unsoldIcon.getImage().getSource(), filter);
        unsoldImage = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

        try
        {
            soldFile = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Icons/Sold.png");
            sold_image = ImageIO.read(soldFile);

            unsoldFile = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Icons/Unsold.png");
            unsold_image = ImageIO.read(unsoldFile);

        }catch(IOException e){}
    }


    public Image getIcon()
    {
        if (sold)
            return soldImage;
        else
            return unsoldImage;
    }
    
    
    public BufferedImage getImage()
    {
        if (sold)
            return sold_image;
        
        else
            return unsold_image;
    }
}