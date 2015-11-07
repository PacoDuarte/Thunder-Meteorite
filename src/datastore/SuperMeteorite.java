/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */


package datastore;
/*****************************************************************************/
/*  
    The super class for the meteorite that will be implemented on
    or inherited from by other classes (subclasses) as needed 
    -------------------------------------------------------------
*/
public class SuperMeteorite
{
    public int meteoriteID;
    public String type;
    public float price;
    public boolean status;
    public position addposition;
    
    public SuperMeteorite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean status)
    {
        meteoriteID = iD;
          this.type = type;
         this.price = price;
        this.status = status;
        addposition = new position(longitudefloat, latitudefloat, row, col);
    }
}

