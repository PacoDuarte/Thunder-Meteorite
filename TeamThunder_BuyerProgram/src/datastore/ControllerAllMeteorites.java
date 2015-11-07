/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *             
* -------------------------------------------------------------------------- */

package datastore;
/*****************************************************************************/
import java.util.ArrayList;
/*****************************************************************************/


public class ControllerAllMeteorites 
{
    public ArrayList <Achondrite> achondriteList;
    public ArrayList <AsteroidalAchondrite> asteroidalachondriteList;
    public ArrayList <Chondrite> chondriteList;
    public ArrayList <Iron> ironList;
    public ArrayList <LunarAchondrite> lunarachondriteList;
    public ArrayList <MartianAchondrite> martianachondriteList;
    public ArrayList <Pallasite> pallasiteList;
    public ArrayList <SpaceJunk> spaceJunkList;
    public ArrayList <Stony> stonyList;
    public ArrayList <StonyIron> stonyironList;
    public ArrayList <SuperMeteorite> superMeteoriteList;
    
    public ControllerAllMeteorites() 
    {
                  achondriteList = new ArrayList<>();
        asteroidalachondriteList = new ArrayList<>();
                   chondriteList = new ArrayList<>();
                        ironList = new ArrayList<>();
             lunarachondriteList = new ArrayList<>();
           martianachondriteList = new ArrayList<>();
                   pallasiteList = new ArrayList<>();
                   spaceJunkList = new ArrayList<>();
                       stonyList = new ArrayList<>();
                   stonyironList = new ArrayList<>();
              superMeteoriteList = new ArrayList<>();
    }
    
    public void addAchondrite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	Achondrite temp;
		temp = new Achondrite(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
		superMeteoriteList.add(temp);
        achondriteList.add(temp);
    } 
    
    public void addAsteroidalAchondrite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	AsteroidalAchondrite temp;
    	temp = new AsteroidalAchondrite(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        asteroidalachondriteList.add(temp);
    }
    
    public void addChondrite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	Chondrite temp;
    	temp = new Chondrite(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        chondriteList.add(temp);
    }
    
    public void addIron(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	Iron temp;
    	temp = new Iron(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        ironList.add(temp);
    }
    
    public void addLunarAchondrite(int iD,String type,float price,float longitudefloat,float latitudefloat,int row,int col, boolean StatusDefault)
    {
    	LunarAchondrite temp;
    	temp = new LunarAchondrite(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
		superMeteoriteList.add(temp);
        lunarachondriteList.add(temp);
    }
    
    public void addMartianAchondrite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	MartianAchondrite temp;
    	temp = new MartianAchondrite(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        martianachondriteList.add(temp);
    }
    
    public void addPallasite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	Pallasite temp;
    	temp = new Pallasite(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        pallasiteList.add(temp);
    }
    
    public void addSpaceJunk(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	SpaceJunk temp;
    	temp = new SpaceJunk(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        spaceJunkList.add(temp);
    }
    
    public void addStony(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	Stony temp;
    	temp = new Stony(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        stonyList.add(temp);
    }
    
    public void addStonyIron(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	StonyIron temp;
    	temp = new StonyIron(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
        stonyironList.add(temp);
    }
    
    public void addSuperMeteorite(int iD, String type, float price, float longitudefloat, float latitudefloat, int row, int col, boolean StatusDefault)
    {
    	SuperMeteorite temp;
    	temp = new SuperMeteorite(iD, type, price, longitudefloat, latitudefloat, row, col, StatusDefault);
    	superMeteoriteList.add(temp);
    }
}