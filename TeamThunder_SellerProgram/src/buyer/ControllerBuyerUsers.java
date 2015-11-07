
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
import java.util.ArrayList;
/*****************************************************************************/


public class ControllerBuyerUsers
{
    ArrayList <BuyerUser> BuyersList;
    
    public ControllerBuyerUsers() 
    {
	BuyersList = new ArrayList<>();
    }
    
    public void addBuyerUser(int iD, String name, String last, int longitude, int latitude, int row, int col) 
    {
	BuyerUser temp;
	temp = new BuyerUser(iD, name, last, longitude, latitude, row, col);
	BuyersList.add(temp);
    }  
}
