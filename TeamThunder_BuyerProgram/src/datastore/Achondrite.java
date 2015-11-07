/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */

package datastore;
/******************************************************************************/


public class Achondrite extends SuperMeteorite 
{   
    public float BasePriceAchondrite;
    
    public Achondrite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean statusDefault)
    {
        super(iD, type, price, longitudefloat, latitudefloat, row, col, statusDefault);
        this.BasePriceAchondrite = 150;
    }  
}