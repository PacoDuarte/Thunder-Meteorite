/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */


package datastore;
/*****************************************************************************/


public class StonyIron extends AsteroidalAchondrite 
{   
    public float BasePriceStonyIron;

    public StonyIron(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean statusDefault)
    {
        super(iD, type, price, longitudefloat, latitudefloat, row, col, statusDefault);
        
        this.BasePriceStonyIron = 500;
                      this.Iron = 50;
                     this.Stone = 50;
                   this.Crystal = 0;
    }  
}
