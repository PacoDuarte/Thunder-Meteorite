/* ------------------------------------------------------------------------- *
*      Group: ThunderdTeam                                                   *
*    Authors: Miguel Obiang, Francisco Martinez                              *
*     Course: CSE 1325                                                       *
* Assignment: Meteorite Project Phase NO.2                                   *
*       Date: November 4, 2013                                               *
*                                                                            *      
*   Purpose:                                                                 * 
*           o   With the utilization of introductory material of the Java    *
*               languageto load, read/write, analysis, and save data files.  *
*                                                                            *   
*           o   This is accomplished by utilizing                            *
*               -   basic data types                                         *
*               -   controls                                                 *
*               -   simple concrete classes                                  *
*               -   exception handling                                       *
*               -   simple collection types                                  *
*  Synopsis:                                                                 *
*           o   This Program is part of a Two-Design Project for a Meteorite *
*               Market Place.                                                *
*               -   [Part-1] Meteorite Selling Tool                          *
*               -   [Part-2] Meteorite Buying Tool.                          *
*                                                                            *
*           o These built-in object oriented programs will have a tool that  *
*               -   displays the world map, marking the location of specific *
*                   meteorites.                                              * 
* -------------------------------------------------------------------------- */

package buyer;
/*****************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
/*****************************************************************************/


public class Buyer
{ 
    Scanner keyboard = new Scanner(System.in);
    Formatter output = new Formatter(System.out);
    ControllerBuyerUsers  listBuyers = new ControllerBuyerUsers();
    String buyerName, buyerLastName;
    int longitudeBuyer, latitudeBuyer, rowBuyer, colBuyer, menuOption, buyerID, counter = 0;
    boolean empty; 
     

    public void BuyerUserMenu() 
    {   
        int SelectionMenu;
        boolean ExitMenu = false;
        
        String MainTitle = "Thunder-Team Meteorite Market";
        String MainTitleStr = "Buyer User Menu";
        while (!ExitMenu) 
        {
            output.format("\n\n%39s\n", MainTitle);
            output.format("======================================================\n");
            output.format("%30s\n", MainTitleStr);
            output.format("======================================================\n"
            			+ " 1. Add Buyer\n"
            			+ " 2. Delete Buyer\n"
            			+ " 3. Update Buyer\n"
            			+ " 4. Save Buyer\n"
            			+ " 5. Show all the Buyers\n"
            			+ " 6. Exit from Buyer Users Menu\n"
            			+ "======================================================\n"
            			+ "Enter your option: ");
            
            while (!keyboard.hasNextInt()) 
            {
                System.out.print("Enter a valid option: ");
                keyboard.next(); 
            }
            SelectionMenu = keyboard.nextInt();
            
            switch(SelectionMenu)
            {
                case 1: 
                    AddBuyer();
                    break;
                    
                case 2:
                    DeleteBuyer();
                    break;
                    
                case 3:
                    UpdateBuyer();
                    break;
                    
                case 4:
                    SaveBuyer();
                    break;
                    
                case 5:
                    ShowAllBuyer();
                    break;
                    
                case 6:
                    ExitMenu = true;
                    break;
                    
                default:
                    System.out.println("\n==> Report:\n===============================================\n" 
                    				 + "The option selected is not available.");
                    break;
            }
        }
    }
    
    public void AddBuyer()
    {
        /*
          o  Prompts user to input integer numbers for meteorite: 
                - int ID
                - int longitude coordinate
                - int latitude coordinate
                - double price.
        
          o  Utilizes some of these data to compute and map the location
             of the targeted meteorite. 
        */
        System.out.println("----------------------------------------------------");          
        System.out.println("\nHere you'll enter your Buyer New User information. ");
        System.out.println("Please, make sure to enter the correct format type.");
        System.out.println("----------------------------------------------------");

        /* Prompt user to enter a meteorite ID number */
        //System.out.print(" o   Enter Buyer UserID: ");
        //buyerID = keyboard.nextInt();
        /*--------------------------------------------*/
        /*    Generates userID for the new buyer      */
                buyerID = randomGenerator();
        /*--------------------------------------------*/
        
        System.out.print(" o   Enter Buyer's Name: ");
        buyerName = keyboard.next();
        buyerName = Character.toUpperCase(buyerName.charAt(0)) + buyerName.substring(1);
        
        System.out.print(" o   Enter Buyer's Lastname: ");
        buyerLastName = keyboard.next();
        buyerLastName = Character.toUpperCase(buyerLastName.charAt(0)) + buyerLastName.substring(1);
        

        /* 
           -  PROCESS and ERROR CHECK user's input data for longitude
           -  Computes the parameter col that maps the longitude coordinate
        /*--------------------------------------------------------------------*/
        System.out.print("\n    *  Enter the buyer's longitude location within range [-180, 180]: ");
        while (!keyboard.hasNextInt())                  /* ERROR CHECK none int-type inputs for longitude */
        {
            System.out.print("       => Report -----------------------------------------------------\n"
            			   + "       The longitude loaction you have entered is invalid.\n"
                           + "       In this section, you must enter integers only.\n"
            			   + "       ---------------------------------------------------------------\n\n"
            			   + "       Please, enter a valid longitude location within range  [-180, 180]: ");
            keyboard.next(); 
        }
        longitudeBuyer = keyboard.nextInt();

        while ( longitudeBuyer < -180 || longitudeBuyer > 180 )   /* ERROR CHECK int-inputs falling out of range [-180, 180] */
        {
            System.out.print("        => Report -----------------------------------------------------\n"
            			   + "       The longitude location you have entered is out of range.\n"
            			   + "       You must enter a longitude location that's between -180 & 180\n"
            		 	   + "       ----------------------------------------------------------------\n\n"
            		 	   + "       Please, enter a longitude location that's within range [-180, 180]: ");

            while (!keyboard.hasNextInt())              /* ERROR CHECK for none int-type inputs */
            {
                System.out.print(" 		 => Report --------------------------------------------------\n"
                			   + "       The longitude location you have entered is invalid.\n"
                			   + "       In this section, you must enter integers only.\n"
                			   + "       ------------------------------------------------------------\n\n"
                			   + "       Please, enter a valid longitude location within range  [-180, 180]: ");
                keyboard.next(); 
            }
            longitudeBuyer = keyboard.nextInt();
        }
        rowBuyer = (int)(longitudeBuyer*(60.0/360.0)+30.0);       
        
        /*
           -  computes the "col" OR y-coordinate
           -  PROCESS and CHECK input data to compute latitude coordinate 
        /*---------------------------------------------------------------*/
        System.out.print("\n    *  Enter the buyer's latitude  location within range   [-90, 90]: ");
        while (!keyboard.hasNextInt())                  /* ERROR CHECK for none int-type inputs */
        {
            System.out.print("       => Report ------------------------------------------------------\n"
            			   + "       The latitude location you have entered is invalid.\n"
            			   + "       In this section, you must enter integers only.\n"
            			   + "       ----------------------------------------------------------------\n\n"
            			   + "       Please, enter a valid latitude location within range [-90, 90]: ");                        
            keyboard.next(); 
        }
        latitudeBuyer = keyboard.nextInt();  
        
        /*    Proceeds to computing the latitude   */
        /*-----------------------------------------*/
        while ( latitudeBuyer < -90 || latitudeBuyer > 90 )       /* ERROR CHECK for int-inputs falling out of range [-90, 90] */
        {
            System.out.print("       => Report ------------------------------------------------------\n"
            			   + "       The latitude location you have entered is out of range.\n"
            			   + "       You must enter a latitude location that's between -90 & 90.\n"
            			   + "       ----------------------------------------------------------------\n\n"
            			   + "       Please, enter a latitude location that's within range [-90, 90]: ");
            while (!keyboard.hasNextInt())
            {
                System.out.print("       => Report ----------------------------------------------------\n"
                			   + "       The latitude location you have entered is invalid.\n"
                			   + "       In this section, you must enter integers only.\n"
                			   + "       --------------------------------------------------------------\n\n"
                			   + "       Please, enter a valid latitude location within range  [-90, 90]: ");
                keyboard.next(); 
            }
            latitudeBuyer = keyboard.nextInt();
        }
        colBuyer = (int)(latitudeBuyer * (-20.0/180.0) + 10.0);

        /*  The function addMeteorite stores all the user's input data
            needed to add a meteorite and takes in:
            *     (int) rowBuyer, colBuyer, longitudeBuyer, latitudeBuyer, Code
            *   (float) price
            *  (String) status
        */
        listBuyers.addBuyerUser(buyerID, buyerName, buyerLastName, longitudeBuyer, latitudeBuyer, rowBuyer, colBuyer);
        System.out.print("    \n==> Report:\n==================================================\n");
        System.out.print("    The user(buyer) has been successfully added\n"); 
    }
    
    
    
    /*  Allows the user(buyer) to remove current buyer  */
    /*--------------------------------------------------*/
    public void DeleteBuyer()
    {
    	empty = ShowAllBuyer();
        if (empty != true)
        {
        	try
        	{
	            System.out.print("\n Enter the ID of the buyer you wish to delete: ");
	            /*while (!keyboard.hasNextInt()) 
	            { 
	                System.out.print("\n==> Report:\n    "
	                               + "======================================================\n    ");
	                System.out.print("This ID is not assigned to any buyer in the list or is invalid.\n    ");
	                System.out.print("Please, enter a valid ID that's assigned to a buyer in the list.\n\n    ");
	                System.out.print("Enter ID: "); 
	                keyboard.nextInt();
	            }*/
	            
	            buyerID = keyboard.nextInt();
	            for (int i = 0; i < listBuyers.BuyersList.size(); i++)
	            {
	                if (listBuyers.BuyersList.get(i).iD == buyerID)
	                {
	                    System.out.print("\nAre you sure you want to delete this buyer from the list?\n"
	                    + "------------------------------------------------------------\n"
	                    + "o   Press 1: To continue to delete the buyer from the list.\n"
	                    + "o   Press 2: To cancel the deletion and return to Buyer menu.\n"
	                    + "------------------------------------------------------------\n"
	                    + " Select your option: ");
	                    
	                    while (!keyboard.hasNextInt()) 
	                    {
	                        System.out.print(" Please, enter a valid option: ");
	                        keyboard.next(); 
	                    }
	                    menuOption = keyboard.nextInt();
	                    
	                    switch (menuOption)
	                    {
	                        case 1:
	                            listBuyers.BuyersList.remove(i);
	                            System.out.print("\n==> Report:\n    "
	                                           + "==================================================\n    "
	                            			   + "\"" + buyerName +" "+ buyerLastName + "\" has been deleted from the list\n    "
	                                           + " of buyers successfully.\n\n");
	                            counter++;
	                            break;
	                            
	                        case 2:
	                            counter++;
	                            break;
	                            
	                        default:
	                            System.out.print("\n==> Report:\n    "
	                                           + "==================================================\n    "
	                            			   + "The option selected is not available.\n\n");
	                            counter++;
	                            break;
	                    }                                                                     
	                }
	            }
	            
	            if (counter == 0)
	            {
	                System.out.print("\n==> Report:\n    "
	                               + "======================================================\n    "
	                			   + "The ID entered is not assigned to any buyer in the list.\n    "
	                               + "You may try again later.\n");                
	            }
        	}
        	catch(Exception ex){
        		System.out.print("\n==> Report:\n    "
                        	   + "================================================================\n    "
        					   + "This ID is not assigned to any buyer in the list or is invalid.\n    "
        					   + "Next time, please just follow directions and enter a valid ID.\n\n    ");
        	}
        }
    }
    
//        ShowAllBuyers();
//
//        /* ERROR CHECK none int-type inputs 
//           -------------------------------- */
//        System.out.print("\n o   Enter the ID of the Buyer you wish to remove: ");
//        while (!keyboard.hasNextInt()) 
//        {
//            System.out.println("    \n==> Report:\n======================================================\n");
//            System.out.print("    The ID number entered is not assigned to any buyer in the list or is invalid.");
//            System. out.println("    Please, enter a valid ID number that's assigned to any buyer in the list. ");
//            System.out.print("\n     ID: "); 
//            keyboard.nextInt(); 
//        }
//        buyerID = keyboard.nextInt();
//
//
//        /* ERROR CHECK
//           -  the existance of the user's ID number
//           -  The validity of the user's ID number  
//           ----------------------------------------
//        */
//        for (int i = 0; i < listBuyers.BuyersList.size(); i++)
//        {
//            if (listBuyers.BuyersList.get(i).iD == buyerID)
//            {
//                System.out.println("Press 1 to Remove");
//                System.out.println("Press 2 to Quit\n");
//                System.out.print("Select option number: ");
//
//                while (!keyboard.hasNextInt()) 
//                {
//                    System.out.print("Enter a valid option number: ");
//                    keyboard.next(); 
//                }
//                menuOption = keyboard.nextInt();
//                
//                switch (menuOption)
//                {
//                    case 1:
//                        listBuyers.BuyersList.remove(i);
//                        System.out.println("\n==> Report:\n===============================================\n"); 
//                        System.out.print("The Buyer User was removed successfully");
//                        counter++;
//                        break;
//
//                    case 2:
//                        counter++;
//                        break;
//
//                    default:
//                        System.out.println("\n==> Report:\n===============================================\n"); 
//                        System.out.print("The option selected is not available");
//                        counter++;
//                        break;
//                }                                                                     
//            }
//        }
//        
//        /* Notifies the user whether or not the ID entered exist */
//        /*-------------------------------------------------------*/
//        if (counter == 0) {
//            System.out.println("\n==> Report:\n===============================================\n"); 
//            output.format("The User Buyer with ID %d does not exist. Try again later.\n",buyerID );
//        }
//    }
    

    /* Allows the user to modify his/her information */
    /*-----------------------------------------------*/
    public void UpdateBuyer()
    {
    	try
    	{
	        empty = ShowAllBuyer();
	        if (!empty)
	        {
	            System.out.print(" Enter the buyer's ID you want to edit: ");
	            while (!keyboard.hasNextInt())
	            {
	                System.out.print(" Please, enter a valid ID number: ");
	                keyboard.next(); 
	            }          
	            
	            buyerID = keyboard.nextInt();
	            for (int i = 0; i < listBuyers.BuyersList.size(); i++)
	            {    
	                if (listBuyers.BuyersList.get(i).iD == buyerID)
	                {
	                    output.format("\n\n=============================================================================\n");
	                    output.format("%10s%15s%15s%15s%15s\n","ID","name","lastname","longitude","latitude");
	                    output.format("=============================================================================\n");
	                    output.format("%10d%15s%15s%15s%15s\n", listBuyers.BuyersList.get(i).iD, listBuyers.BuyersList.get(i).name, listBuyers.BuyersList.get(i).last, listBuyers.BuyersList.get(i).addposition.longitude, listBuyers.BuyersList.get(i).addposition.latitude);
	                    output.format("=============================================================================\n\n");
	                    
	                    System.out.print("Are you sure you want to preoceed with the update?\n"
	                                   + "----------------------------------------------------\n"
	                    			   + "o  Press 1 to start updating the user's information.\n"
	                    			   + "o  Press 2 to cancell the update and return to menu.\n"
	                    			   + "----------------------------------------------------\n"
	                    			   + " Select your option: ");  
	                    
	                    menuOption = keyboard.nextInt();
	                    switch (menuOption)
	                    {
	                        case 1:
	                            System.out.print("\n==> Input Directions:\n    "
	                            			   + "-------------------------------------------------\n    "
	                            			   + " Enter new information for the buyer user below.\n    "
	                                           + " Please, remember to follow directions next time.\n    "
	                            			   + "-------------------------------------------------\n    ");
	                            
	                            System.out.print("*  Enter a new name for buyer: ");
	                            buyerName = keyboard.next();
	                            buyerName = Character.toUpperCase(buyerName.charAt(0)) + buyerName.substring(1);
	                            listBuyers.BuyersList.get(i).name = buyerName;
	                            	                            
	                            System.out.print("\n    *  Enter a new last name for buyer: ");
	                            buyerLastName = keyboard.next();
	                            buyerLastName = Character.toUpperCase(buyerLastName.charAt(0)) + buyerLastName.substring(1);
	                            listBuyers.BuyersList.get(i).last = buyerLastName;
	                            
	                            /* 
	                               -  PROCESS and ERROR CHECK user's input data for longitude
	                               -  Computes the parameter col that maps the longitude coordinate
	                            /*--------------------------------------------------------------------*/
	                            System.out.print("\n    *  Enter a new longitude location for buyer in range [-180, 180]: ");
	                            while (!keyboard.hasNextInt())                  /* ERROR CHECK none int-type inputs for longitude */
	                            {
	                                System.out.print("       => Report ------------------------------------------------------\n"
	                                			   + "       The new longitude loaction you have entered is invalid.\n"
	                                               + "       In this section, you must enter integers only.\n"
	                                			   + "       ----------------------------------------------------------------\n\n"
	                                			   + "       Please, enter a valid longitude location within range [-180, 180]: ");
	                                keyboard.next(); 
	                            }
	                            longitudeBuyer = keyboard.nextInt();

	                            while ( longitudeBuyer < -180 || longitudeBuyer > 180 )   /* ERROR CHECK int-inputs falling out of range [-180, 180] */
	                            {
	                                System.out.print("       => Report ------------------------------------------------------\n"
	                                			   + "       The new longitude location you have entered is out of range.\n"
	                                			   + "       You must enter a latitude location that's between -180 & 180.\n"
	                                			   + "       ----------------------------------------------------------------\n\n"
	                                			   + "       Please, enter a longitude location that's within range [-180, 180]: ");

	                                while (!keyboard.hasNextInt())              /* ERROR CHECK for none int-type inputs */
	                                {
	                                    System.out.print("       => Report ------------------------------------------------------\n"
	                                    			   + "       The longitude location you have entered is invalid.\n"
	                                    			   + "       In this section, you must enter integers only.\n"
	                                    			   + "       --------------------------------------------------------------\n\n"
	                                    			   + "       Please, enter a valid longitude location within range [-180, 180]: ");
	                                    keyboard.next(); 
	                                }
	                                longitudeBuyer = keyboard.nextInt();
	                            } 
	                            listBuyers.BuyersList.get(i).addposition.longitude = longitudeBuyer;
	                            rowBuyer = (int)(longitudeBuyer * (60.0/360.0)) + 30;
	                            listBuyers.BuyersList.get(i).addposition.x = rowBuyer;
	                            
	                            /*
	                               o  computes the "col" OR y-coordinate
	                               o  PROCESS and CHECK input data to compute latitude coordinate 
	                            /*---------------------------------------------------------------*/
	                            System.out.print("\n    *  Enter a new latitude  location for buyer within range   [-90, 90]: ");
	                            while (!keyboard.hasNextInt())                  /* ERROR CHECK for none int-type inputs */
	                            {
	                                System.out.print("       => Report ------------------------------------------------------\n"
	                                			   + "       The new latitude location you have entered is invalid.\n"
	                                			   + "       Please, you must only enter integers in this section.\n"
	                                			   + "       --------------------------------------------------------------\n\n"
	                                			   + "       Please, enter a valid latitude location within range [-90, 90]: ");                        
	                                keyboard.next(); 
	                            }
	                            latitudeBuyer = keyboard.nextInt();  
	                            
	                            /*    Proceeds to computing the latitude   */
	                            /*-----------------------------------------*/
	                            while ( latitudeBuyer < -90 || latitudeBuyer > 90 )       /* ERROR CHECK for int-inputs falling out of range [-90, 90] */
	                            {
	                                System.out.print("       => Report ------------------------------------------------------\n"
	                                			   + "       The new latitude location you have entered is out of range.\n"
	                                			   + "       You must enter a latitude loaction that's between -90 & 90.\n"
	                                			   + "       --------------------------------------------------------------\n\n"
	                                			   + "       Please, enter a latitude location that's within range [-90, 90]: ");
	                                
	                                while (!keyboard.hasNextInt())
	                                {
	                                    System.out.print("       => Report ------------------------------------------------------\n"
	                                    			   + "       The latitude location you have entered is invalid.\n"
	                                    			   + "       Please, you must only enter integers in this section.\n"
	                                    			   + "       --------------------------------------------------------------\n"
	                                    			   + "       Please, enter a valid latitude location within range [-90, 90]: ");
	                                    keyboard.next(); 
	                                }
	                                latitudeBuyer = keyboard.nextInt();
	                            }
	                            listBuyers.BuyersList.get(i).addposition.latitude = latitudeBuyer;
	                            colBuyer = (int)(latitudeBuyer* (-20.0/180.0) + 10.0);
	                            listBuyers.BuyersList.get(i).addposition.y = colBuyer;
	                            
	                            System.out.print("\n==> Report:\n    "
	                                           + "======================================================\n    ");
	                            output.format("The information of the buyer with ID \"%d\" has been\n    "
	                                        + "successfully updated.\n\n", buyerID);
	                            counter++;
	                            break;
	
	                        case 2:
	                            counter++;
	                            break;
	                            
	                        default:
	                            System.out.print("\n==> Report:\n    "
	                                           + "==================================================\n    "
	                            			   + "The option selected is not available.\n");
	                            counter++;
	                            break;
	                    }
	                }
	            }
	        }
	        
	        /*
	           ERROR CHECK
	           o  For cases in which the user may enter a valid ID that
	              has not yet been listed 
	        */
	        else{
	            System.out.print("\n==> Report:\n    "
	                           + "==================================================\n    ");
	            output.format("The buyer with the ID \"%d\" does not exist.\n    "
	                        + "Please, try again later.\n\n", buyerID);
	        }
	    }
    	catch(Exception ex)
    	{
            System.out.print("\n==> Report:\n    "
                    	   + "======================================================\n    "
            			   + "There is not such ID. Try following directions next time\n");
    	}
    }  
    
    
    
    /* Saves user(buyer)'s information */
    public void SaveBuyer()
    {
        String SaveFileUsers = "Buyers";
        
        if ( listBuyers.BuyersList.isEmpty() )
        {
            System.out.println("\n==> Report:\n===============================================\n"); 
            System.out.print("Sorry, you have no information to save.");
        }
        
        else
        {
            File outputFile = new File("/Users/Paco/Desktop/TeamThunder_Project_Final/Files/" + SaveFileUsers + ".txt");

            try 
            {
                Formatter saveFile = new Formatter(outputFile); 
                for (int i = 0; i < listBuyers.BuyersList.size(); i++) 
                {  
                    saveFile.format("%s,%s,%s,%s\n", listBuyers.BuyersList.get(i).name, listBuyers.BuyersList.get(i).last, listBuyers.BuyersList.get(i).addposition.longitude, listBuyers.BuyersList.get(i).addposition.latitude);
                }
                
                System.out.println("\n==> Report:\n======================================================\n");
                System.out.println("The file has been saved at: " + outputFile.getAbsolutePath());
                output.format("======================================================\n");
                saveFile.flush();
                saveFile.close();
            }catch(FileNotFoundException ErrorWritingFile){}	
        }
    }
    
    
    /* Displays the list of current latitudeBuyer */
    /*--------------------------------------*/
    public boolean ShowAllBuyer()
    {
    	boolean flag;
    	
        if (listBuyers.BuyersList.isEmpty())
        {
            System.out.print("\n==> Report:\n    "
                           + "==================================================\n    "
            			   + "The seller list is empty.\n\n");
            flag = true;
        }
        
        else
        {
            //output.format("\n\n=============================================================================\n");
            output.format("\n\n %50s\n", "*** List of Buyer Users ***");
            output.format("=============================================================================\n");
            output.format("%6s%15s%19s%18s%18s\n","ID","Name","Lastname","Longitude","Latitude");
            output.format("=============================================================================\n");

            for (int i = 0; i < listBuyers.BuyersList.size(); i++)
            {
                output.format("%6d%17s%16s%17s%17s\n", listBuyers.BuyersList.get(i).iD, listBuyers.BuyersList.get(i).name, listBuyers.BuyersList.get(i).last, listBuyers.BuyersList.get(i).addposition.longitude, listBuyers.BuyersList.get(i).addposition.latitude);
            }
            output.format("=============================================================================\n");
            flag = false;
        }
        return flag;
    }


    
    /*  Generates random values for the meteorite types  */
    /*---------------------------------------------------*/
    private int randomGenerator()
    {
        Random randomGenerator = new Random();
        int n = randomGenerator.nextInt(100) + 1;
        
        return n;
    }
}