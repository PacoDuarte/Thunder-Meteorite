/* -------------------------------------------------------------------------- *
 *      Group: ThunderdTeam                                                   *
 *    Authors: Miguel Obiang, Francisco Martinez                              *
 *     Course: CSE 1325                                                       *
 * Assignment: Meteorite Project Phase NO.2                                   *
 *       Date: November 4, 2013                                               *             
 * -------------------------------------------------------------------------- */


package datastore;
/******************************************************************************/


public class position 
{
    public float longitude, latitude;
    public int x, y;
    
    public position (float longitude, float latitude, int x, int y)
    {
      this.longitude = longitude;
       this.latitude = latitude;
            this.x = x;
            this.y = y;
    }
    
}
