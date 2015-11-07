/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */

package buyer;
/******************************************************************************/
import java.util.ArrayList;
/******************************************************************************/

public class ControllerBuyer
{
    ArrayList <BuyerUser> SellersList;
    
    public ControllerBuyer() 
    {
	SellersList = new ArrayList<>();
    }
    
    public void addSellerUser(int iD, String name, String last, float longitude, float latitude, int row, int col) 
    {
	BuyerUser temp;
	temp = new BuyerUser(iD, name, last, longitude, latitude, row, col);
	SellersList.add(temp);
    }  
}
