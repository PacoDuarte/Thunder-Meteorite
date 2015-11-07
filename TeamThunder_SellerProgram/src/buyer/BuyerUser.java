
/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *
*                                                                            *      
*   Purpose:                                                                 * 
*           o   With the utilization of introductory material of the Java    *
*               meteorites.                                                  * 
* -------------------------------------------------------------------------- */

package buyer;
/*****************************************************************************/


import datastore.position;


public class BuyerUser
{
    public String name, last;
    public int iD;
    position addposition;
    
    public BuyerUser(int iD, String name, String last, int longitude, int latitude, int row, int col)
    {
        addposition = new position(longitude, latitude, row, col);
            this.iD = iD;
          this.name = name;
          this.last = last;
    }
        
}