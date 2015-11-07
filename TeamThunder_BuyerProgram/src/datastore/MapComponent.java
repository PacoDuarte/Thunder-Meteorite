/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */


package datastore;
/****************************************************************************/
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JComponent;
/****************************************************************************/


public class MapComponent extends JComponent 
{
    int pinLoop = 0, pinMax = 0;
    private static final long serialVersionUID = 1L;
    
    BufferedImage image;
    Pin thisPin;
    ArrayList <Pin> thesePins;

    
    public MapComponent(BufferedImage img)
    {
        image = img;
        thesePins = new ArrayList<>();
    }

    
    @Override
    public void paint(Graphics g) 
    {
        System.out.println("Map Component Paint");
        g.drawImage(image, 0, 0, 800, 400, null);
        pinMax = thesePins.size();
        System.out.println(pinMax);
        
        for (pinLoop = 0; pinLoop<pinMax; pinLoop++)
        {
            thisPin = thesePins.get(pinLoop);
            System.out.println(thisPin.pos.x);
            System.out.println(thisPin.pos.y);
            System.out.println(thisPin.getIcon());
            g.drawImage(thisPin.getIcon(), thisPin.pos.x, thisPin.pos.y, null);	
        }
    }
    
    
    @Override
    public Dimension getPreferredSize() 
    {
        if (image == null)
            return new Dimension(800,400);
	else 
            return new Dimension(image.getWidth(null), image.getHeight(null));
    }

    
    public void addPin(Pin myPin)
    {
        thesePins.add(myPin);
    }    
}
	
	
	
	
