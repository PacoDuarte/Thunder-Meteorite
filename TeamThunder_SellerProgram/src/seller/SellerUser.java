/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *
*                                                                            *                    
* -------------------------------------------------------------------------- */


package seller;
/******************************************************************************/
import datastore.position;
/******************************************************************************/


public class SellerUser
{
    public String name, last;
    public int userID;
    position addposition;
        
    public SellerUser(int iD, String name, String last, float longitude, float latitude, int row, int col)
    {
        addposition = new position(longitude, latitude, row, col);
        this.userID = iD;
          this.name = name;
          this.last = last;
    }     
}