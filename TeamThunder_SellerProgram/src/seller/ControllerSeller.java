/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */

package seller;
/******************************************************************************/
import java.util.ArrayList;
/******************************************************************************/

public class ControllerSeller
{
    ArrayList <SellerUser> SellersList;
    
    public ControllerSeller() 
    {
	SellersList = new ArrayList<>();
    }
    
    public void addSellerUser(int iD, String name, String last, float longitude, float latitude, int row, int col) 
    {
	SellerUser temp;
	temp = new SellerUser(iD, name, last, longitude, latitude, row, col);
	SellersList.add(temp);
    }  
}
