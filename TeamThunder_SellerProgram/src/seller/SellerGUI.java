package seller;

/******************************************************************************/
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Random;
import javax.swing.JTextField;
import datastore.*;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.*;
/******************************************************************************/



public class SellerGUI extends JFrame implements ActionListener 
{
    /* ----------------------------------------------------------------- */
    
    ArrayList<String> worldDocumentLines = new ArrayList<>();
    ArrayList<String> userDocumentUpload = new ArrayList<>();
    
    ControllerAllMeteorites allKindMeteorites = new ControllerAllMeteorites();
    ControllerSeller listSellers = new ControllerSeller();
    
    private static final long serialVersionUID = 1L;
    
    char mapDisplay[][] = new char[60][20];
    char mapBackup[][] = new char[60][20];

    int x, y, newX, newY, longitude, latitude, counter = 0,i=0;
    int menuOption, option, sellerID, meteoriteID, returnVal;
    
    float price, basePrice, finalprice, longitudefloat, latitudefloat, addBase, addLocation, distance;

    boolean statusBoolean, Flag, empty, loadedFileBoolean;
    
    location buyerLocation = new location(longitude, latitude);
    location sellerLocation = new location(longitude, latitude);
    position location = new position(longitude, latitude, x, y);

    String buffer, sellerName, landOcean, type, statusString;
    String temp1, temp2, temp3, temp4, temp5, temp6, line, tempID;
    String titleUser = "Thunder-Team Meteorite Market";
    
    Scanner keyboard = new Scanner(System.in);
    Formatter output = new Formatter(System.out);
    
    Component frame;
    
    JPanel tPanelGlobal = new JPanel();
    
    MapComponent temp;

    
    Continents continent = new Continents();
    
    JTextField field0 = new JTextField(); 
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField(); 
    JTextField field5 = new JTextField(); 
    JTextField field6 = new JTextField();
    
    Pin myPin = null;
    BufferedImage worldmapImage;
    File worldmapFile;
    

    /* ----------------------------------------------------------------- */
    /*  The main  of the Program  */
    /*----------------------------*/
    public static void main(String[] args) 
    {
        {
            SellerGUI StartWindow = new SellerGUI();
            StartWindow.CreateMainWindow();   
        }
    }


    

    
    /*  Display the main window   */
    /*----------------------------*/
    public void CreateMainWindow() 
    {    
        loadName();
        this.setTitle(titleUser);
        JMenuBar BarMenu = new JMenuBar();
        this.setJMenuBar(BarMenu);
        
        /* About Menu  */
        /* ----------------------------------------------------------- */
        JMenu aboutMenu = new JMenu("About");
        BarMenu.add(aboutMenu);//.setForeground(Color.red);
        JMenuItem menuItemAbout = new JMenuItem("About This Program");
        menuItemAbout.addActionListener(this);
        aboutMenu.add(menuItemAbout);
        
        /*  File Menu  */
        /* ----------------------------------------------------------- */
        JMenu FileMenu = new JMenu("File");
        BarMenu.add(FileMenu);
       
        JMenu LoadFilesMenu = new JMenu("Load Files");
        FileMenu.add(LoadFilesMenu);
        //JMenuItem menuItemLoadAllFiles = new JMenuItem("Load all the Files");
        //menuItemLoadAllFiles.addActionListener(this);
        JMenuItem menuItemLoadWorldMAp = new JMenuItem("Load The World Map");
        JMenuItem menuItemLoadSeller = new JMenuItem("Load Seller");
        JMenuItem menuItemLoadMeteorite = new JMenuItem("Load Meteorites");
        //JMenuItem menuItemLoadOtherFiles = new JMenuItem("Load Other File");
        menuItemLoadWorldMAp.addActionListener(this);
        menuItemLoadSeller.addActionListener(this);
        menuItemLoadMeteorite.addActionListener(this);
        //menuItemLoadOtherFiles.addActionListener(this);
        //LoadFilesMenu.add(menuItemLoadAllFiles);
        LoadFilesMenu.addSeparator();
        LoadFilesMenu.add(menuItemLoadWorldMAp);
        LoadFilesMenu.addSeparator();
        LoadFilesMenu.add(menuItemLoadSeller);
        LoadFilesMenu.addSeparator();
        LoadFilesMenu.add(menuItemLoadMeteorite);
        LoadFilesMenu.addSeparator();
        //LoadFilesMenu.add(menuItemLoadOtherFiles);
        
        JMenu SaveFilesMenu = new JMenu("Save Files");
        FileMenu.add(SaveFilesMenu);
        FileMenu.addSeparator();
        JMenuItem menuItemSaveSeller = new JMenuItem("Save Seller");
        JMenuItem menuItemSaveMeteorite = new JMenuItem("Save Meteorites");
        menuItemSaveSeller.addActionListener(this);
        menuItemSaveMeteorite.addActionListener(this);
        SaveFilesMenu.addSeparator();
        SaveFilesMenu.add(menuItemSaveSeller);
        SaveFilesMenu.addSeparator();
        SaveFilesMenu.add(menuItemSaveMeteorite);
        SaveFilesMenu.addSeparator();
        
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(this);
        FileMenu.add(menuItemExit);

        
        /* Seller Menu */
        /* ----------------------------------------------------------- */
        JMenu SellerMenu = new JMenu("Seller");
        BarMenu.add(SellerMenu);
        JMenuItem menuItemAddSeller = new JMenuItem("Add Seller");
        menuItemAddSeller.addActionListener(this);
        JMenuItem menuItemDeleteSeller = new JMenuItem("Delete Seller");
        menuItemDeleteSeller.addActionListener(this);
        JMenuItem menuItemUpdateSeller = new JMenuItem("Update Seller");
        menuItemUpdateSeller.addActionListener(this);
        JMenuItem menuItemShowSellers = new JMenuItem("Show Sellers in the System");
        menuItemShowSellers.addActionListener(this);
        
        SellerMenu.add(menuItemAddSeller);
        SellerMenu.add(menuItemDeleteSeller);
        SellerMenu.add(menuItemUpdateSeller);
        SellerMenu.addSeparator();
        SellerMenu.add(menuItemShowSellers);

        
        /* MEteorites Menu */
        /* ----------------------------------------------------------- */
        JMenu MeteoriteMenu = new JMenu("Handle Meteorites");
        BarMenu.add(MeteoriteMenu);
        JMenuItem menuItemAddMeteorite_Regular = new JMenuItem("Add Meteorite Regular");
        menuItemAddMeteorite_Regular.addActionListener(this);
        JMenuItem menuItemAddMeteorite_OneLine = new JMenuItem("Add Meteorite OneLine");
        menuItemAddMeteorite_OneLine.addActionListener(this);
        JMenuItem menuItemDeleteMeteorite = new JMenuItem("Delete Meteorite");
        menuItemDeleteMeteorite.addActionListener(this);
        JMenuItem menuItemUpdateMeteorite = new JMenuItem("Update Meteorite");
        menuItemUpdateMeteorite.addActionListener(this);
        JMenuItem menuItemShowMeteorites = new JMenuItem("Show All Meteorites");
        menuItemShowMeteorites.addActionListener(this);
        
        MeteoriteMenu.add(menuItemAddMeteorite_Regular);
        MeteoriteMenu.add(menuItemAddMeteorite_OneLine);
        MeteoriteMenu.addSeparator();
        MeteoriteMenu.add(menuItemDeleteMeteorite);
        MeteoriteMenu.addSeparator();
        MeteoriteMenu.add(menuItemUpdateMeteorite);
        MeteoriteMenu.addSeparator();
        MeteoriteMenu.add(menuItemShowMeteorites);

        
        /*    Tool Menu    */
        /* ----------------------------------------------------------- */
        JMenu ToolsMenu = new JMenu("Tools");
        BarMenu.add(ToolsMenu);
        JMenuItem menuItemCustomize = new JMenuItem("Customize Market Place");
        menuItemCustomize.addActionListener(this);
        JMenuItem menuItemShowWorldMap = new JMenuItem("Show The World Map With Meteorites");
        menuItemShowWorldMap.addActionListener(this);
        JMenuItem menuItemScrambleMeteorites = new JMenuItem("Scramble Meteorites Position");
        menuItemScrambleMeteorites.addActionListener(this);
        JMenuItem menuItemLoadSellerBuyerPos = new JMenuItem("Enter Locations of Seller and Buyer");
        menuItemLoadSellerBuyerPos.addActionListener(this);
        JMenuItem menuItemCalculateDistance = new JMenuItem("Calculate Distance Between Seller and Buyer");
        menuItemCalculateDistance.addActionListener(this);
        
        ToolsMenu.add(menuItemCustomize);
        ToolsMenu.addSeparator();
        ToolsMenu.add(menuItemShowWorldMap);
        ToolsMenu.add(menuItemScrambleMeteorites);
        ToolsMenu.addSeparator();
        ToolsMenu.add(menuItemLoadSellerBuyerPos);
        ToolsMenu.add(menuItemCalculateDistance);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(tPanelGlobal);
        this.pack();
        this.setSize(new Dimension(810,460));
        centreWindow(this);
        this.setVisible(true);
        
        
   
    }

    
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        String action = ae.getActionCommand();
        switch (action) 
        {
            case "Load all the Files":
                JFileChooser fileOpenAll = new JFileChooser("/Users/Paco/Desktop/TeamThunderFinalProject/Data/");
                if (fileOpenAll.showOpenDialog(tPanelGlobal) == JFileChooser.APPROVE_OPTION) {
                    
                    String fileName = fileOpenAll.getSelectedFile().getName();
                    FileCheker("AllFiles.txt");
                }
                break;
                
            case "Load Other File":
                JFileChooser fileOpen = new JFileChooser("/Users/Paco/Desktop/TeamThunderFinalProject/Data/");
                if (fileOpen.showOpenDialog(tPanelGlobal) == JFileChooser.APPROVE_OPTION) {
                    
                    String fileName = fileOpen.getSelectedFile().getName();
                    FileCheker(fileName);
                }
                break;
                
           case "Save Other Files":
                JFileChooser SaveOpen = new JFileChooser("/Users/Paco/Desktop/TeamThunderFinalProject/Data/");
                if (SaveOpen.showOpenDialog(tPanelGlobal) == JFileChooser.APPROVE_OPTION) {
                    
                    String fileName = SaveOpen.getSelectedFile().getName();
                    FileCheker(fileName);
                }
                break;
                
            case "Load The World Map":
                fileOpen("WorldMapSimple.png");
                break;
                
            case "Show The World Map With Meteorites":
                pinIt();
                break;
                
            case "Load Seller":
                loadSellerLocation();
                successfullMessage();
                
                break;
            
            case "Load Meteorites":
                LoadMeteoriteFile();
                break;

            case "Save Meteorites":
                SaveMeteorites();
                break;
             
            case "Save Seller":
                 SaveSeller();
                break;
               

            case "Exit":
                System.exit(0);
                break;

            case "Add Seller":
                addSeller();
                break;

            case "Delete Seller":
                DeleteSeller();
                break;

            case "Update Seller":
                UpdateSeller();
                break;

            case "Show Sellers in the System":
                ShowSystemSeller();
                break;

            case "Add Meteorite Regular":
                addMeteoriteRegular();
                break;

            case "Add Meteorite OneLine":
                addMeteoriteOneLine();
                break;

            case "Delete Meteorite":
                RemoveMeteorite();
                break;

            case "Update Meteorite":
                UpdateMeteorite();
                break;

            case "Show All Meteorites":
                ShowAllMeteorites();
                break;

            case "About This Program":
                aboutThisProgram();
                break;

            case "Customize Market Place":
                Customizename();
                break;

            case "Scramble Meteorites Position":

                ScrambleMeteorites();
                break;
            
            case "Enter Locations of Seller and Buyer":
                addInformation();
                break;

            case "Calculate Distance Between Seller and Buyer":
                DistanceCal();
                break;
        }
    }
    
    
    public void FileCheker(String file) 
    {
        switch (file) 
        {
            case "meteorites.txt":
                LoadMeteoriteFile();
                break;
            
                
            case "Sellers.txt":
                loadSellerLocation();
                successfullMessage();
                break;    

            case "WorldMapSimple.png":
                fileOpen(file);
                break;

            case "Asia.png":
                fileOpen(file);
                break;

            case "Africa.png":
                fileOpen(file);
                break;

            case "Europe.png":
                fileOpen(file);
                break;

            case "NorthAmerica.png":
                fileOpen(file);
                break;

            case "SouthAmerica.png":
                fileOpen(file);
                break;

            case "Antarctica.png":
                fileOpen(file);
                break;

            case "Austrailia.png":
                fileOpen(file);
                break;
            
            case "AllFiles.txt":
                successfullMessage();
                break;

            default:
                JOptionPane.showMessageDialog(tPanelGlobal, "\n    Report:\n    "
                        + "==================================================\n    "
                        + "Sorry, you will need the administrator's permision to open  \"" + file + "\".\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                break;
        }
    }

    
    public void fileOpen(String Name) 
    {

        try 
        {
            tPanelGlobal.removeAll();
            worldmapFile = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/"+ Name );
            worldmapImage = ImageIO.read(worldmapFile);
            
            temp = new MapComponent(worldmapImage);
            tPanelGlobal.add(temp);
            tPanelGlobal.validate();
            tPanelGlobal.repaint();
        } 
        catch (IOException e) 
        {
        }
    }
    
    
    public void successfullMessage() 
    {
        JOptionPane.showMessageDialog(tPanelGlobal, "\n    Report:\n    "
                + "================================\n    "
                + "All the files were loaded succesfully.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
    }
    
    
    public void centreWindow(Window frame) 
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    
    
    public void aboutThisProgram() 
    {
        JOptionPane.showMessageDialog(tPanelGlobal, 
              " --------------------------------------- \n"
            + "            Group:  ThunderdTeam         \n"
            + "         Authors:  Miguel Obiang\n"
            + "                        Francisco Martinez    \n"
            + "          Course:  CSE 1325              \n"
            + "   Assignment:  Meteorite Project Phase NO.3\n"
            + " --------------------------------------- \n",
            "About this program", JOptionPane.PLAIN_MESSAGE);
    }
    
    
    public void addSeller()
    {  
        try{
            Object[] message = 
            {  
                    " Name", field1,  
                " Lastname", field2,  
               " Longitude [-180, 180]", field3,  
                " Latitude [-90, 90]", field4,   
            };
            
            option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Add a new seller", JOptionPane.OK_CANCEL_OPTION);  
            if (option == JOptionPane.OK_OPTION)  
            {   
                longitudefloat = Float.parseFloat( field3.getText() );
                latitudefloat = Float.parseFloat( field4.getText() );                
            
                if (!field1.getText().isEmpty() && !field2.getText().isEmpty() && 
                    !field3.getText().isEmpty() && !field4.getText().isEmpty() &&
                              (longitudefloat >= -180 && longitudefloat <= 180) &&
                                (latitudefloat >= -90 && latitudefloat <= 90))
                {
                    sellerID = randomGenerator();
                    x = (int)(longitudefloat*(60.0/360.0)+30.0);
                    y = (int)(latitudefloat * (-20.0/180.0) + 10.0);
                    
                    listSellers.addSellerUser(sellerID, field1.getText(), field2.getText(), longitudefloat, latitudefloat, x, y);
                    JOptionPane.showMessageDialog( null, "    The  new  seller  has  been  added  successfully.\n"
                                                       + "    Welcome  aboard (Mr./Mrs./Miss.)  " + field1.getText()+" "+ field2.getText(),
                                                         "Message", JOptionPane.PLAIN_MESSAGE );
                }
                else
                    JOptionPane.showMessageDialog( null, "Either your latitude or longituded is out or range.",
                                                 "Attention", JOptionPane.PLAIN_MESSAGE ); 
            }
        }
        catch(HeadlessException | NumberFormatException ex ){
            JOptionPane.showMessageDialog( null, "One  Of  Your  Input  Is  Not  Valid ",
                                                 "Attention", JOptionPane.PLAIN_MESSAGE ); 
        }
    } 
    
    
    /*     Delete a seller user      */
    /*-------------------------------*/
    public void DeleteSeller() 
    {
        try
        {
            counter=0;
            empty = ShowSystemSeller();

            if (empty != true)
            {
                
            }    
            
            else
            {
                try
                {
                        Object[] message = 
                        {  
                                " Enter the Seller ID to delete", field1,  
                        };

                        option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Delete a meteorite", JOptionPane.OK_CANCEL_OPTION);  
                        if (option == JOptionPane.OK_OPTION)  
                        {   
                            if (!field1.getText().isEmpty())
                            {
                                sellerID = Integer.parseInt( field1.getText() );
                                for (i = 0; i < listSellers.SellersList.size(); i++) 
                                {
                                    if (listSellers.SellersList.get(i).userID == sellerID)
                                    {
                                        int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
                                        int dialogResult = JOptionPane.showConfirmDialog(frame, "really want to delete the seller", "Title on Box",dialogButton);
                                        if(dialogResult==0)
                                        {
                                                listSellers.SellersList.remove(i);
                                                counter++;
                                                JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                                      + "==============================\n    "
                                                      + "The seller was removed successfully\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        if(dialogResult==1)
                                        {
                                            counter++;
                                            JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                                  + "==============================\n    "
                                                  + "No seller removed\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                        }
                                                   
                                        if (dialogResult==2)
                                        {
                                            counter++;
                                            JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                                  + "==============================\n    "
                                                  + "Operation canceled\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }
                                }
                                if (counter == 0) 
                                {                                   
                                    JOptionPane.showMessageDialog(null, "\nReport:\n"
                                            + "===================================\n"
                                            + "The Seller with ID " + sellerID + " does not exist.\n"
                                            + "Next time follow directions.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                }

                            }
                            else
                                JOptionPane.showMessageDialog( null, "Either your input is empty or invalid",
                                                             "Attention", JOptionPane.PLAIN_MESSAGE ); 
                        }
                }
                catch(HeadlessException | NumberFormatException ex){
                    JOptionPane.showMessageDialog( null, "One  Of  Your  Input  Is  Not  Valid ",
                                                         "Attention", JOptionPane.PLAIN_MESSAGE ); 
                }
            }            
       }
       catch (HeadlessException ex)
       {
            JOptionPane.showMessageDialog( null, "\nReport:\n    "
                                       + "==================================================\n    "
                                       + "Sorry, at least one your parameters is invalid.\n    "
                                       + "Please, follow instructions next time.\n\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
       }
    }
    

    /* 
     -  Prompts user to input integer numbers for meteorite ID, longitude 
     and latitude coordinates and price. 
     -  Utilizes some of these data to compute and map the location 
     of the targeted meteorite. 
     /*--------------------------------------------------------------------*/

    public void addMeteoriteRegular() 
    {         
        try{
            Object[] message = {
                                    "\n    ==> Input Directions:\n    "
                                  + "-------------------------------------------------\n    "
                                  + " Here you will enter your meteorite information.\n    "
                                  + " Please, make sure to enter integers when needed.\n    "
                                  + "-------------------------------------------------\n"
                                  + " Longitude [-180, 180]", field3, " Latitude [-90, 90]", field4   
            };
            
            option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Add a meteorite regular", JOptionPane.OK_CANCEL_OPTION);  
            if (option == JOptionPane.OK_OPTION)  
            {   
                longitudefloat = Float.parseFloat( field3.getText() );
                latitudefloat = Float.parseFloat( field4.getText() );

                if (!field3.getText().isEmpty() && !field4.getText().isEmpty() &&
                              (longitudefloat >= -180 && longitudefloat <= 180) &&
                                (latitudefloat >= -90 && latitudefloat <= 90))
                    
                {
                    
                    if (ask())
                    {
                        meteoriteID = randomGenerator();
                        x = (int) (longitudefloat * (799.0 / 360.0) + 400.0);
                        y = (int) (latitudefloat * (-399.0 / 180.0) + 200.0);
                        statusBoolean = true;
                        changePrice(true);
                        selectType(type); 
                        field3.setText(null);
                        field4.setText(null);
                    }
                    
                                      
                }

                else
                {
                    JOptionPane.showMessageDialog( null, "Either your latitude or longituded is out or range.",
                                                 "Attention", JOptionPane.PLAIN_MESSAGE );
                }   
            }
        }catch(HeadlessException | NumberFormatException ex){
            JOptionPane.showMessageDialog( null, "One  Of  Your  Input  Is  Not  Valid ",
                                                 "Attention", JOptionPane.PLAIN_MESSAGE );
        }
    }
 
    
    @SuppressWarnings({"unchecked", "unchecked"})
    public boolean ask()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Your Meteorite"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("< none >");
        model.addElement("Chondrite");
        model.addElement("Achondrite");
        model.addElement("Lunar Achondrite");
        model.addElement("Martian Achondrite");
        model.addElement("Asteroidal Achondrite");
        model.addElement("Iron");
        model.addElement("Stony");
        model.addElement("Stony-Iron");
        model.addElement("Pallasite");
        model.addElement("SpaceJunk");
        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);

        int okButton = JOptionPane.showConfirmDialog(null, panel, "Meteorite Type", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (okButton == JOptionPane.OK_OPTION)
        {
            String txt = (String) comboBox.getSelectedItem();
            switch (txt) 
            {
                case "Chondrite":
                    type = "1.0";
                    Flag=true;
                    break;

                case "Achondrite":
                    type = "2.0";
                    Flag=true;
                    break;

                case "Lunar Achondrite":
                    type = "2.1";
                    Flag=true;
                    break;

                case "Martian Achondrite":
                    type = "2.2";
                    Flag=true;
                    break;

                case "Asteroidal Achondrite":
                    type = "2.3";
                    Flag=true;
                    break;

                case "Iron":
                    type = "2.3.1";
                    Flag=true;
                    break;

                case "Stony":
                    type = "2.3.2";
                    Flag=true;
                    break;

                case "Stony-Iron":
                    type = "2.3.3";
                    Flag=true;
                    break;

                case "Pallasite":
                    type = "2.3.3.1";
                    Flag=true;
                    break;

                case "SpaceJunk":
                    type = "3.0";
                    Flag=true;
                    break;
            }
        } 
        else if(okButton == JOptionPane.CANCEL_OPTION)
        {
            JOptionPane.showMessageDialog( null, "\nReport:\n    "
                                                  + "==============================\n    "
                                                  + "Sorry, you can't continue without select a meteorite type\n    "
                                                  + "Please, try again thenext time.\n\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
         
            Flag=false;
        }
        return Flag;
    }    
    
    
    
    /*  
         This prompts the user(seller/buyer) an alternative
        manner to input meteorite informtion in the database 
      -------------------------------------------------------  
    */
    public void addMeteoriteOneLine() 
    {
        try {
              Object[] message = {   
                                      "                     [ Available Meteorite Types]        \n"
                                    + "==================================\n"
                                    + "Chondrite ............................................................   1.0\n"
                                    + "Achondrite ..........................................................   2.0\n"
                                    + "Lunar Achondrite ................................................   2.1\n"
                                    + "Martian Achondrite .............................................   2.2\n"
                                    + "Asteroidal Achondrite .........................................   2.3\n"
                                    + "Iron ...................................................................   2.3.1\n"
                                    + "Stony ................................................................   2.3.2\n"
                                    + "Stony-Iron ........................................................   2.3.3\n"
                                    + "Pallasite .........................................................   2.3.3.1\n"
                                    + "SpaceJunk ............................................................   3.0\n"
                                    + "==================================\n\n"
                                    + " Directions:\n "
                                    + "-----------------------------------------------------\n "
                                    + " In one line, with not spaces, using \",\" as a separator, you will enter\n "
                                    + " the following information:\n "
                                    + " *     The longitude Position in the range of [-180,180]\n "
                                    + " *     The latitude  Position in the range of   [-90,90]\n "
                                    + " *     The corresponding number of the meteorite you wish to add\n "
                                    + " An example format would be like [180,-90,2.3.3]; no brackets.\n "
                                    + "-----------------------------------------------------\n\n "
                                    + " Enter your meteorite information here: ", field0
                                 }; 
              
            JOptionPane.showMessageDialog( null, message, "Message", JOptionPane.PLAIN_MESSAGE);
            String str = field0.getText();
            StringTokenizer SplitText = new StringTokenizer(str, ",");

            while (SplitText.hasMoreTokens())
            {
                temp1 = SplitText.nextToken();
                temp2 = SplitText.nextToken();
                temp3 = SplitText.nextToken();
            }

            meteoriteID = randomGenerator();
            longitudefloat = Float.parseFloat(temp1); 
            latitudefloat = Float.parseFloat(temp2); 
            type = temp3;
            x = (int) (longitudefloat * (799.0 / 360.0) + 400.0);
            y = (int) (latitudefloat * (-399.0 / 180.0) + 200.0);

            if ((longitudefloat < -180 || longitudefloat > 180) || (latitudefloat < -90 || latitudefloat > 90)) 
            {
                JOptionPane.showMessageDialog( null, "\nReport:\n    "
                                                  + "==============================\n    "
                                                  + "Sorry, at least one your parameters is incorrect.\n    "
                                                  + "Please, enter correct parameters next time.\n\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
            } 
            
            else {
                String optionSelect = type;
                statusBoolean = true;
                switch (optionSelect) {
                    case "1.0":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.0":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.1":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.2":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.3":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.3.1":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.3.2":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.3.3":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "2.3.3.1":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    case "3.0":
                        type = optionSelect;
                        changePrice(true);
                        selectType(type);
                        break;

                    default:
                        type = "3.0";
                        
                        JOptionPane.showMessageDialog( null, "\n    Report:\n    "
                                                           + "=============================\n    "
                                                           + "Sorry, there is not such meteorite type in the list.\n    "
                                                           + "The meteoryte will be create with space junk as default.\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
                        changePrice(true);
                        selectType(type);
                        break;
                }
            }
            
        } 
        catch (NumberFormatException ex) 
        {
            /* ERROR CHECK none int-type inputs for longitude */
            /*------------------------------------------------*/
            JOptionPane.showMessageDialog( null, "\n    Report:\n    "
                                               + "=============================\n    "
                                               + "Sorry, at least one your parameters is invalid.\n    "
                                               + "Please, follow directions next time you try. \n\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    

    /*   Allows the user(seller) to update a meteorite   */
    /*--------------------------------------------------*/
    public void UpdateMeteorite() 
    {
        try
        {
            counter=0;
            empty = ShowAllMeteorites();
            if (empty != true)
            {
            }
            else
            {
                Object[] message = 
                {  
                    " Enter the Meteorite ID to update", field1,  
                };

                option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Update a meteorite", JOptionPane.OK_CANCEL_OPTION);  
                if (option == JOptionPane.OK_OPTION)  
                {   
                    if (!field1.getText().isEmpty())
                    {
                        meteoriteID = Integer.parseInt( field1.getText() );
                        for (i = 0; i < allKindMeteorites.superMeteoriteList.size(); i++) 
                        {
                            if (allKindMeteorites.superMeteoriteList.get(i).meteoriteID == meteoriteID)
                            {
                                int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(frame, "really want to update the meteorite", "Update meteorite",dialogButton);
                                if(dialogResult==0)
                                {
                                    counter++;
                                    try
                                    {
                                        Object[] updatemeteorite =  
                                        {
                                                                "\n Input Directions:\n  "
                                                                + "-------------------------------------------------\n "
                                                                + " Here you will enter the coordinates of Seller & Buyer.\n "
                                                                + " Please, make sure to provide valid information.\n "
                                                                + "-------------------------------------------------\n "
                                                                + " New longitude [-180, 180]: ", field1, 
                                                                  " New latitude [-90, 90]: " , field2,   
                                                                  " New price : ", field3, 
                                        };

                                        option = JOptionPane.showConfirmDialog(tPanelGlobal, updatemeteorite, "Add new information for meteorite", JOptionPane.OK_CANCEL_OPTION);  
                                        if (option == JOptionPane.OK_OPTION)  
                                        {   
                                            //float price, basePrice, finalprice, longitudefloat, latitudefloat;
                                            longitudefloat = Float.parseFloat( field1.getText() );
                                            latitudefloat = Float.parseFloat( field2.getText() );
                                            price = Float.parseFloat( field3.getText() );
                                            

                                            if (!field1.getText().isEmpty() && 
                                                !field2.getText().isEmpty() &&
                                                !field3.getText().isEmpty() &&
                                                (longitudefloat >= -180 && longitudefloat <= 180)&&
                                                (latitudefloat >= -90 && latitudefloat <= 90))

                                            {
                                                allKindMeteorites.superMeteoriteList.get(i).addposition.longitude = (int)longitudefloat;
                                                allKindMeteorites.superMeteoriteList.get(i).addposition.latitude = (int)latitudefloat;
                                                allKindMeteorites.superMeteoriteList.get(i).price = price;

                                                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                                           + "=============================\n    "
                                                           + " The changes were added successfully.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                            }

                                            else
                                            {
                                                JOptionPane.showMessageDialog( null, "Either your latitude or longituded is out or range.",
                                                                                     "Attention", JOptionPane.PLAIN_MESSAGE );
                                            }
                                        }
                                    }
                                    catch(HeadlessException | NumberFormatException ex)
                                    {
                                        JOptionPane.showMessageDialog( null, "One of Your input is not valid",
                                                                             "Attention", JOptionPane.PLAIN_MESSAGE );
                                    }
                               
                                }
                                
                                if(dialogResult==1)
                                {
                                    counter++;
                                    JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                          + "==============================\n    "
                                          + "no.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                }

                                if (dialogResult==2)
                                {
                                    counter++;
                                    JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                          + "==============================\n    "
                                          + "cancel.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                }
                            }
                        }
                        if (counter == 0) 
                        {                                   
                            JOptionPane.showMessageDialog(null, "\nReport:\n"
                                    + "===================================\n"
                                    + "The meteorite with ID " + meteoriteID + " does not exist.\n"
                                    + "Next time follow directions.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                        }
                   }
                   else
                   {
                        JOptionPane.showMessageDialog( null, "Either your input is empty or invalid",
                                                             "Attention", JOptionPane.PLAIN_MESSAGE );
                   }
                }
            }
        }
       catch (HeadlessException ex)
       {
            JOptionPane.showMessageDialog( null, "\nReport:\n    "
                                       + "==================================================\n    "
                                       + "Sorry, at least one your parameters is invalid.\n    "
                                       + "Please, follow instructions next time.\n\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
       }
    }
 
    public void RemoveMeteorite() 
    {    
       try{
           counter=0;
            empty = ShowAllMeteorites();

            if (empty != true)
                JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                                  + "==============================\n    "
                                                  + "The meteorite list is empty.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
            else
            {
                try
                {
                        Object[] message = 
                        {  
                                " Enter the Meteorite ID to delete", field1,  
                        };

                        option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Delete a meteorite", JOptionPane.OK_CANCEL_OPTION);  
                        if (option == JOptionPane.OK_OPTION)  
                        {   
                            if (!field1.getText().isEmpty())
                            {
                                meteoriteID = Integer.parseInt( field1.getText() );
                                for (i = 0; i < allKindMeteorites.superMeteoriteList.size(); i++) 
                                {
                                    if (allKindMeteorites.superMeteoriteList.get(i).meteoriteID == meteoriteID)
                                    {
                                        int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
                                        int dialogResult = JOptionPane.showConfirmDialog(frame, "really want to delete the meteorite", "Title on Box",dialogButton);
                                        if(dialogResult==0)
                                        {
                                                allKindMeteorites.superMeteoriteList.remove(i);
                                                counter++;
                                                JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                                      + "==============================\n    "
                                                      + "The meteorite was removed successfully\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        if(dialogResult==1)
                                        {
                                            counter++;
                                            JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                                  + "==============================\n    "
                                                  + "The meteorite was not removed\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                        }
                                                   
                                        if (dialogResult==2)
                                        {
                                            counter++;
                                            JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                                  + "==============================\n    "
                                                  + "The operation was canceled.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }
                                }
                                if (counter == 0) 
                                {                                   
                                    JOptionPane.showMessageDialog(null, "\nReport:\n"
                                            + "===================================\n"
                                            + "The meteorite with ID " + meteoriteID + " does not exist.\n"
                                            + "Next time follow directions.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                }

                            }
                            else
                                JOptionPane.showMessageDialog( null, "Either your input is empty or invalid",
                                                             "Attention", JOptionPane.PLAIN_MESSAGE ); 
                        }
                }
                catch(HeadlessException | NumberFormatException ex){
                    JOptionPane.showMessageDialog( null, "One  Of  Your  Input  Is  Not  Valid ",
                                                         "Attention", JOptionPane.PLAIN_MESSAGE ); 
                }
            }            
       }
       catch (HeadlessException ex)
       {
            JOptionPane.showMessageDialog( null, "\nReport:\n    "
                                       + "==================================================\n    "
                                       + "Sorry, at least one your parameters is invalid.\n    "
                                       + "Please, follow instructions next time.\n\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
       }
 }
        
    
    
    /*  Allows user to load Meteorite files  */
    /*---------------------------------------*/
    public void LoadMeteoriteFile() 
    {
        try{
            FileReader OpenFile = new FileReader("/Users/Paco/Desktop/TeamThunderFinalProject/Data/meteorites.txt");
            BufferedReader bufferReader;
            bufferReader = new BufferedReader(OpenFile);

            while ((line = bufferReader.readLine()) != null) {
                userDocumentUpload.add(line);
            }
            bufferReader.close();
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "==========================\n    "
                    + "Unable  to  Load  File.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "===========================\n    "
                    + "Error  Reading  File.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        }
        
        /* Writes the meteorite information into a file */
        /*----------------------------------------------*/
        for (i = 0; i < userDocumentUpload.size(); i++) {
            line = userDocumentUpload.get(i);
            StringTokenizer SplitText = new StringTokenizer(line, ",");

            while (SplitText.hasMoreTokens()) {
                temp1 = SplitText.nextToken();
                temp2 = SplitText.nextToken();
                type = SplitText.nextToken();
                temp4 = SplitText.nextToken();
                temp5 = SplitText.nextToken();

                meteoriteID = randomGenerator();
                longitudefloat = Float.parseFloat(temp1);
                latitudefloat = Float.parseFloat(temp2);
                price = Float.parseFloat(temp4);
                x = (int) (longitudefloat * (799.0 / 360.0) + 400.0);
                y = (int) (latitudefloat * (-399.0 / 180.0) + 200.0);

                if (temp5.equals("available"))
                    statusBoolean = true;

                if (temp5.equals("sold")) 
                    statusBoolean = false;
                
                selectType(type);
                   
                
            }
            loadedFileBoolean = true;
        }

        if (loadedFileBoolean == true) 
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "============================================\n    "
                    + "The meteorite file was loaded successfuly.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
    }

    
    /*  Save the meteorites into a file  */
    /*-----------------------------------*/
    public void SaveMeteorites() {
        /*
         - Allows the user to save his session information into a file
         o  Prompt the user to enter the name document file he wants to save
         o  The program saves users's session information in a predisigned(fixed) format
                
         - Utilizes <try and catch> to ERROR CHECK the file's readability
         ---------------------------------------------------------------------------
         */

        if (allKindMeteorites.superMeteoriteList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "=========================================\n    "
                    + "Sorry, you have no information to save.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        } else {
            File outputFile = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/meteorites.txt");
            File outputFilename = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Filename.txt");

            try {
                Formatter saveFilename;
                try (Formatter saveFile = new Formatter(outputFile)) {
                    saveFilename = new Formatter(outputFilename);
                    saveFilename.format("%s", titleUser);
                    for (i = 0; i < allKindMeteorites.superMeteoriteList.size(); i++) {
                        type = ConvertType(allKindMeteorites.superMeteoriteList.get(i).type);
                        
                        if (allKindMeteorites.superMeteoriteList.get(i).status == true) {
                            statusString = "available";
                        }
                        
                        if (allKindMeteorites.superMeteoriteList.get(i).status == false) {
                            statusString = "sold";
                        }
                        
                        saveFile.format("%.2f,%.2f,%s,%.2f,%s\n", allKindMeteorites.superMeteoriteList.get(i).addposition.longitude, allKindMeteorites.superMeteoriteList.get(i).addposition.latitude, type, allKindMeteorites.superMeteoriteList.get(i).price, statusString);
                    }   
                    JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                                           + "=========================================\n    "
                                                           + "The file is located at the address below:\n -> <" + outputFile.getAbsolutePath() + ">\n", "Message", JOptionPane.PLAIN_MESSAGE);                saveFile.flush();
                    saveFilename.flush();
                }
                saveFilename.close();

            } catch (FileNotFoundException ErrorWritingFile) {}
        }
    }
    
    
    /*   Displays the current meteorites   */
    /*-------------------------------------*/
    public boolean ShowAllMeteorites() {
        
        /* Notifies the user if the file is empty */
        /*----------------------------------------*/
        if (allKindMeteorites.superMeteoriteList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                               + "=====================================\n    "
                                               + "No meteorite has been uploaded yet.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
            Flag = false;
        }
        
        else {
            try 
            {
                for (i = 0; i < allKindMeteorites.superMeteoriteList.size(); i++)
                {    
                    int x=i+1;
                    if (allKindMeteorites.superMeteoriteList.get(i).status == true)
                        statusString = "available";

                    if (allKindMeteorites.superMeteoriteList.get(i).status == false) 
                        statusString = "sold";
                    
                    JOptionPane.showMessageDialog(frame,    "  Meteorite # "+x+" in the System "
                                                          + "\n====================="
                                                          + "\n          Type: "  + allKindMeteorites.superMeteoriteList.get(i).type + " "
                                                          + "\n          ID: "  + allKindMeteorites.superMeteoriteList.get(i).meteoriteID + " "
                                                          + "\nLongitude: "  + allKindMeteorites.superMeteoriteList.get(i).addposition.longitude + " "
                                                          + "\n  Latitude: "  + allKindMeteorites.superMeteoriteList.get(i).addposition.latitude  + " "
                                                          + "\n      Price: "  + allKindMeteorites.superMeteoriteList.get(i).price
                                                          + "\n=====================\n", "Meteorite Information", JOptionPane.PLAIN_MESSAGE);
                }
                Flag = true;

            } catch (HeadlessException ex) {
                
                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                                   + "======================================\n    "
                                                   + "ERROR!!\nYou may have entered incorrect values.\n", "Message", JOptionPane.PLAIN_MESSAGE);
                Flag = false;
            }
        }
        return Flag;
    }
    
    /*  Sramble meteorite's position  */
    /*---------------------------------*/
    public void ScrambleMeteorites() {
        int newLongitude, newLatitude, check;

        if (allKindMeteorites.superMeteoriteList.isEmpty()) 
        {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "==================================\n    "
                    + "There  is  No  Meteorites to  Scramble  Yet.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        } 
        else 
        {
            for (i = 0; i < allKindMeteorites.superMeteoriteList.size(); i++) 
            {
                newLongitude = ScrambleLongitude();
                newLatitude = ScrambleLatitude();

                newX = (int) (newLongitude * (799.0 / 360.0) + 400.0);
                newY = (int) (newLatitude * (-399.0 / 180.0) + 200.0);

                location.longitude = newLongitude;
                location.latitude = newLatitude;

                location.x = newX;
                location.y = newY;
                check = continent.cheakLocation(location);

                while (check == 0) 
                {
                    newLongitude = ScrambleLongitude();
                    newLatitude = ScrambleLatitude();

                    newX = (int) (newLongitude * (799.0 / 360.0) + 400.0);
                    newY = (int) (newLatitude * (-399.0 / 180.0) + 200.0);

                    location.longitude = newLongitude;
                    location.latitude = newLatitude;

                    location.x = newX;
                    location.y = newY;

                    check = continent.cheakLocation(location);
                }
                
                allKindMeteorites.superMeteoriteList.get(i).addposition.longitude = location.longitude;
                allKindMeteorites.superMeteoriteList.get(i).addposition.latitude = location.latitude;
                allKindMeteorites.superMeteoriteList.get(i).addposition.x = location.x;
                allKindMeteorites.superMeteoriteList.get(i).addposition.y = location.y;
            }
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "====================\n    "
                    + " Scramble successfull.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    

    /*   Provide the user with directions    */
    /*---------------------------------------*/
    public void addInformation() {
        
        try{
            Object[] message =  {
                                    "\n Input Directions:\n  "
                                    + "-------------------------------------------------\n "
                                    + " Here you will enter the coordinates of Seller & Buyer.\n "
                                    + " Please, make sure to provide valid information.\n "
                                    + "-------------------------------------------------\n "
                                    + " Longitude for Seller [-180, 180]: ", field1, 
                                      " Latitude for Seller [-90, 90]: " , field2,   
                                      " Longitude for Buyer [-180, 180]: ", field3, 
                                      " Latitude for Buyer [-90, 90]: ", field4,   
                                };
            
            option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Add Locations For Seller and Buyer", JOptionPane.OK_CANCEL_OPTION);  
            if (option == JOptionPane.OK_OPTION)  
            {   
                float longitudeSeller = Float.parseFloat( field1.getText() );
                float latitudeSeller = Float.parseFloat( field2.getText() );
                float longitudeBuyer = Float.parseFloat( field3.getText() );
                float latitudeBuyer = Float.parseFloat( field4.getText() );

                if (!field1.getText().isEmpty() && !field2.getText().isEmpty() &&
                    !field3.getText().isEmpty() && !field4.getText().isEmpty() &&
                              (longitudeSeller >= -180 && longitudeSeller <= 180) &&
                               (longitudeBuyer >= -180 && longitudeBuyer <= 180) &&
                                (latitudeSeller >= -90 && latitudeSeller <= 90) &&
                                 (latitudeBuyer >= -90 && latitudeBuyer <= 90))
                    
                {
                        buyerLocation.longitude = longitudeBuyer;
                        buyerLocation.latitude = latitudeBuyer;
                        sellerLocation.longitude = longitudeSeller;
                        sellerLocation.latitude = latitudeSeller;
                        
                        JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                                           + "=============================\n    "
                                                           + " The parameters were added successfully.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                }
            
                else
                {
                    JOptionPane.showMessageDialog( null, "Either your latitude or longituded is out or range.",
                                                         "Attention", JOptionPane.PLAIN_MESSAGE );
                }
            }
        }catch(HeadlessException | NumberFormatException ex){
            JOptionPane.showMessageDialog( null, "One of Your input is not valid",
                                                 "Attention", JOptionPane.PLAIN_MESSAGE );
        }
    }

    
    /* Load inf regarding the buyer's location */
    /*-----------------------------------------*/
    public boolean loadSellerLocation() {
        boolean flagSeller = false;

        try{
            FileReader OpenFile = new FileReader("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Sellers.txt");
            BufferedReader bufferReader;
            bufferReader = new BufferedReader(OpenFile);

            while ((line = bufferReader.readLine()) != null) {
                StringTokenizer SplitText = new StringTokenizer(line, ",");
                while (SplitText.hasMoreTokens()) {
                    temp1 = SplitText.nextToken();
                    temp2 = SplitText.nextToken();
                    temp3 = SplitText.nextToken();
                    temp4 = SplitText.nextToken();

                    longitudefloat = Float.parseFloat(temp3);
                    latitudefloat = Float.parseFloat(temp4);   
        
                    sellerLocation.longitude = longitudefloat;
                    sellerLocation.latitude = latitudefloat;
                    sellerID = randomGenerator();
                    listSellers.addSellerUser(sellerID, temp1, temp2, longitudefloat, latitudefloat, x, y);
                    
                    flagSeller = true;
                }
            }
            bufferReader.close();

        } catch (IOException | NumberFormatException ex) {
            flagSeller = false;
        }
        return flagSeller;
    }
    
    
    
    /* Load inf regarding the buyer's location */
    /*-----------------------------------------*/
    public boolean loadBuyerLocation() {
        boolean flagBuyer = false;

        try {
            FileReader OpenFile = new FileReader("/Users/obifenix/Dropbox/School/Fall 2013/CSE 1325/NetBeansProjects/TeamThunderFinalProject/Data/Buyers.txt");

            BufferedReader bufferReader;
            bufferReader = new BufferedReader(OpenFile);
            while ((line = bufferReader.readLine()) != null) {
                StringTokenizer SplitText = new StringTokenizer(line, ",");
                while (SplitText.hasMoreTokens()) {
                    temp1 = SplitText.nextToken();
                    temp2 = SplitText.nextToken();
                    temp3 = SplitText.nextToken();
                    temp4 = SplitText.nextToken();

                    longitudefloat = Float.parseFloat(temp3);
                    latitudefloat = Float.parseFloat(temp4);

                    buyerLocation.longitude = longitudefloat;
                    buyerLocation.latitude = latitudefloat;

                    flagBuyer = true;
                }
            }
            bufferReader.close();
            
        } catch (IOException | NumberFormatException ex) {
            flagBuyer = false;
        }
        
        return flagBuyer;
    }
    
    /*  Calculate the distance between seller and buuyer  */
    /*----------------------------------------------------*/
    public void DistanceCal() {
        try{
            distance = continent.CalculateDistance(buyerLocation, sellerLocation);

            if (distance == 0) 
                JOptionPane.showMessageDialog(frame, "\n    Solution:\n    "
                        + "==============================\n    "
                        + "The resultant distance between a seller and a buyer\n    "
                        + "located at the same position is equal to " + distance + " miles\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
            
            else if (distance > 0) 
                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                        + "==============================\n    "
                        + " The distance between the seller and the buyer\n    "
                        + " is \"" + distance + "\" miles.", "Message", JOptionPane.PLAIN_MESSAGE);
                
            else
                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                        + "==============================\n    "
                        + " Sorry, your calculation could not be completed.    "
                        + " Please check your parameters and try again later.\n\n.", "Message", JOptionPane.PLAIN_MESSAGE);
                
        }catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                        + "==============================\n    "
                        + "Sorry! Your caculation could not be completed.      "
                        + "Please, check your parameters and try again later.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        }
    }

    
    
    /*  Scrumble coordinates of Longitude  */
    /*-------------------------------------*/
    private int ScrambleLongitude() {
        int newLongitude;
        newLongitude = -177 + (int) (Math.random() * ((177 - (-177)) + 1));
        return newLongitude;
    }

    
    
    /*  Scrumble coordinates of Latitude  */
    /*------------------------------------*/
    private int ScrambleLatitude() {
        int newLatitude;
        newLatitude = -87 + (int) (Math.random() * ((87 - (-87)) + 1));
        return newLatitude;
    }

    
    
    /*    get parameter from user     */
    /*--------------------------------*/
    public void LoadParameter() {
        try
        {
            if (loadBuyerLocation() == true && loadSellerLocation() == true)
                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                        + "===================================\n    "
                        + "The buyer and seller were loaded successfully.\n", "Message", JOptionPane.PLAIN_MESSAGE);


        } catch (HeadlessException ex){

            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "=======================================\n    "
                    + "Unable to open the files carrying the seller's position\n    and the buyer's position "
                    + "in order to calculate the\n    distance between them.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    
    
    private int randomGenerator()
    {
        Random randomGenerator = new Random();
        int n = randomGenerator.nextInt(1000) + 1;
        
        return n;
    }
    
    
    
    /*   Update seller's information   */
    /*---------------------------------*/
    public void UpdateSeller()
    {
        try
        {
            counter=0;
            empty = ShowSystemSeller();
            if (empty != true)
            {
            }
            else
            {
                Object[] message = 
                {  
                    " Enter the Seller ID to update", field1,  
                };

                option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Update a seller", JOptionPane.OK_CANCEL_OPTION);  
                if (option == JOptionPane.OK_OPTION)  
                {   
                    if (!field1.getText().isEmpty())
                    {
                        sellerID = Integer.parseInt( field1.getText() );
                        for (i = 0; i < listSellers.SellersList.size(); i++) 
                        {
                            if (listSellers.SellersList.get(i).userID == sellerID)
                            {
                                int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(frame, "really want to update the seller", "Update seller",dialogButton);
                                if(dialogResult==0)
                                {
                                    counter++;
                                    try
                                    {
                                        Object[] updatemeteorite =  
                                        {
                                                                "\n Input Directions:\n  "
                                                                + "-------------------------------------------------\n "
                                                                + " Here you will enter the new information for the user.\n "
                                                                + " Please, make sure to provide valid information.\n "
                                                                + "-------------------------------------------------\n "
                                                                +   " New name : ", field1,
                                                                    " New lastname : ", field2,
                                                                    " New longitude [-180, 180]: ", field3, 
                                                                    " New latitude [-90, 90]: " , field4,   
                                        };

                                        option = JOptionPane.showConfirmDialog(tPanelGlobal, updatemeteorite, "Add new information for seller", JOptionPane.OK_CANCEL_OPTION);  
                                        if (option == JOptionPane.OK_OPTION)  
                                        {   
                            
                                            longitudefloat = Float.parseFloat( field3.getText() );
                                            latitudefloat = Float.parseFloat( field4.getText() );
                                            
                                            

                                            if (!field1.getText().isEmpty() && 
                                                !field2.getText().isEmpty() &&
                                                !field3.getText().isEmpty() &&
                                                !field4.getText().isEmpty() &&
                                                (longitudefloat >= -180 && longitudefloat <= 180)&&
                                                (latitudefloat >= -90 && latitudefloat <= 90))

                                            {
                                                listSellers.SellersList.get(i).name=field1.getText();
                                                listSellers.SellersList.get(i).last=field2.getText();
                                                listSellers.SellersList.get(i).addposition.longitude = longitudefloat;
                                                listSellers.SellersList.get(i).addposition.latitude = latitudefloat;
                                                

                                                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                                           + "=============================\n    "
                                                           + " The changes were added successfully.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                            }

                                            else
                                            {
                                                JOptionPane.showMessageDialog( null, "Either your latitude or longituded is out or range.",
                                                                                     "Attention", JOptionPane.PLAIN_MESSAGE );
                                            }
                                        }
                                    }
                                    catch(HeadlessException | NumberFormatException ex)
                                    {
                                        JOptionPane.showMessageDialog( null, "One of Your input is not valid",
                                                                             "Attention", JOptionPane.PLAIN_MESSAGE );
                                    }
                               
                                }
                                
                                if(dialogResult==1)
                                {
                                    counter++;
                                    JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                          + "==============================\n    "
                                          + "No changes made on the user.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                }

                                if (dialogResult==2)
                                {
                                    counter++;
                                    JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                                          + "==============================\n    "
                                          + "Operation canceled.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                                }
                            }
                        }
                        if (counter == 0) 
                        {                                   
                            JOptionPane.showMessageDialog(null, "\nReport:\n"
                                    + "===================================\n"
                                    + "The Seller with ID " + sellerID + " does not exist.\n"
                                    + "Next time follow directions.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                        }
                   }
                   else
                   {
                        JOptionPane.showMessageDialog( null, "Either your input is empty or invalid",
                                                             "Attention", JOptionPane.PLAIN_MESSAGE );
                   }
                }
            }
       }
       catch (HeadlessException ex)
       {
            JOptionPane.showMessageDialog( null, "\nReport:\n    "
                                       + "==================================================\n    "
                                       + "Sorry, at least one your parameters is invalid.\n    "
                                       + "Please, follow instructions next time.\n\n    ", "Message", JOptionPane.PLAIN_MESSAGE);
       }
    }

    /*  Save seller's information to a file  */
    /*---------------------------------------*/
    public void SaveSeller() {
        
        if (listSellers.SellersList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                    + "=======================================\n    "
                    + "No seller in the system to save.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        }
        
        else {
            File openFile = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Sellers.txt");
            
            try {
                Formatter saveFile;
                saveFile = new Formatter(openFile);
                for (i = 0; i < listSellers.SellersList.size(); i++) {
                    saveFile.format("%s,%s,%s,%s\n", listSellers.SellersList.get(i).name, listSellers.SellersList.get(i).last, listSellers.SellersList.get(i).addposition.longitude, listSellers.SellersList.get(i).addposition.latitude);
                }
                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                                   + "=======================================\n    "
                                                   + "The file is located at the address below:\n -> <" + openFile.getAbsolutePath() + ">\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
                saveFile.flush();
                saveFile.close();

            } catch (FileNotFoundException ErrorWritingFile) {}
        }
    }
    
    
    /* Displays the list of current sellers */
    /*--------------------------------------*/
    public boolean ShowSystemSeller()
    {
        boolean flagy;


        if (listSellers.SellersList.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                               + "==============================\n    "
                                               + "The  seller  list  is  empty.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
            flagy=false;
        }
        else
        {
            for (int k = 0; k < listSellers.SellersList.size(); k++)
            {
                JOptionPane.showMessageDialog(frame,  "\n Current Seller in The System "
                                                    + "\n=====================    "
                                                    + "\n      Name: "  + listSellers.SellersList.get(k).name +" " + listSellers.SellersList.get(k).last
                                                    + "\n            ID: "  + listSellers.SellersList.get(k).userID + " "
                                                    + "\nLongitude: "  + listSellers.SellersList.get(k).addposition.longitude + " "
                                                    + "\n   Latitude: "  + listSellers.SellersList.get(k).addposition.latitude  + " "
                                                    + "\n=====================", "Seller User", JOptionPane.PLAIN_MESSAGE);
            }
            
            flagy = true;
        }
        return flagy;
    }
    
    
    public void saveMap() 
    {
        String sb = "TEST CONTENT";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/Users/obifenix/Dropbox/School/Fall 2013/CSE 1325/"
                                           + "NetBeansProjects/TeamThunderFinalProject/Data"));
        
        int retrival = chooser.showSaveDialog(null);
        
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
                fw.write(sb.toString());
            } catch (IOException ex) {
            }
        }
    }
    
     /*
        Creates relations of the user's meteorite number
        with the name of the meteorite to the program
       --------------------------------------------------
    */
    private String ConvertType(String type) {
        switch (type) {
            case "Chondrite":
                buffer = "1.0";
                break;

            case "Achondrite":
                buffer = "2.0";
                break;

            case "Lunar Achondrite":
                buffer = "2.1";
                break;

            case "Martian Achondrite":
                buffer = "2.2";
                break;

            case "Asteroidal Achondrite":
                buffer = "2.3";
                break;

            case "Iron":
                buffer = "2.3.1";
                break;

            case "Stony":
                buffer = "2.3.2";
                break;

            case "Stony-Iron":
                buffer = "2.3.3";
                break;

            case "Pallasite":
                buffer = "2.3.3.1";
                break;

            case "SpaceJunk":
                buffer = "3.0";
                break;

            default:
                JOptionPane.showMessageDialog(frame, "\n    Report:\n    "
                                                   + "================================\n    "
                                                   + "Sorry, there  is  not  such  type.\n", "Message", JOptionPane.PLAIN_MESSAGE);
                break;
        }
        return buffer;

    }

    
    /*  Allows the user(seller) to select a meteorite type  */
    /*------------------------------------------------------*/
    private void selectType(String type) {
        switch (type) {
            case "1.0":
                allKindMeteorites.addChondrite(meteoriteID, "Chondrite", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.0":
                allKindMeteorites.addAchondrite(meteoriteID, "Achondrite", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.1":
                allKindMeteorites.addLunarAchondrite(meteoriteID, "Lunar Achondrite", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.2":
                allKindMeteorites.addMartianAchondrite(meteoriteID, "Martian Achondrite", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.3":
                allKindMeteorites.addAsteroidalAchondrite(meteoriteID, "Asteroidal Achondrite", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.3.1":
                allKindMeteorites.addIron(meteoriteID, "Iron", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.3.2":
                allKindMeteorites.addStony(meteoriteID, "Stony", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.3.3":
                allKindMeteorites.addStonyIron(meteoriteID, "Stony-Iron", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "2.3.3.1":
                allKindMeteorites.addPallasite(meteoriteID, "Pallasite", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;

            case "3.0":
                allKindMeteorites.addSpaceJunk(meteoriteID, "SpaceJunk", price, longitudefloat, latitudefloat, x, y, statusBoolean);
                break;
        }
    }

    
    
    /*   Loads the files that contains the list of names  */
    /*----------------------------------------------------*/
    public void loadName() {
        File file = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Filename.txt");
        try {
            try (Scanner scan = new Scanner(file)) {
                while (scan.hasNextLine()) {
                    titleUser = scan.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
        }
    }
    
    

    /*  Allows the user to customize the name of seller   */
    /*----------------------------------------------------*/
    public void Customizename()
    {
        try{
            Object[] message = {
                                    "\n    ==> Input Directions:\n    "
                                  + "-------------------------------------------------\n    "
                                  + " Here you will enter your new name for the market.\n    "
                                  + " Please, make sure to enter the correct format when needed.\n    "
                                  + "-------------------------------------------------\n"
                                  + " New name ", field1,   
            };
            
            option = JOptionPane.showConfirmDialog(tPanelGlobal, message, "Customize name", JOptionPane.OK_CANCEL_OPTION);  
            if (option == JOptionPane.OK_OPTION)  
            {     
                if (!field1.getText().isEmpty())
                {
                    titleUser= field1.getText();
                    this.setTitle(titleUser);
                    File outputFilename = new File("/Users/Paco/Desktop/TeamThunderFinalProject/Data/Filename.txt");
                    Formatter saveFilename;
                    try 
                    {
                        saveFilename = new Formatter(outputFilename);
                        saveFilename.format("%s", titleUser);
                        saveFilename.flush();
                        saveFilename.close();
                    } 
                    catch (FileNotFoundException ex) 
                    {
                        Logger.getLogger(SellerGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    JOptionPane.showMessageDialog( null, "    The  new  market name  has  been  added  successfully.\n",
                                                         "Message", JOptionPane.PLAIN_MESSAGE );
                }
                
                else
                {
                    JOptionPane.showMessageDialog( null, "No name detected",
                                                 "Attention", JOptionPane.PLAIN_MESSAGE );
                }
                
            }
        }catch(HeadlessException | NumberFormatException ex){
            JOptionPane.showMessageDialog( null, "The input is not valid ",
                                                 "Attention", JOptionPane.PLAIN_MESSAGE );
        }
    }
    
    

    /*   Determine the fixed prices according to the continents   */
    /*------------------------------------------------------------*/
    private float basePrice(String type) {
        switch (type) {
            case "1.0":
                basePrice = 50;
                break;

            case "2.0":
                basePrice = 150;
                break;

            case "2.1":
                basePrice = 1000;
                break;

            case "2.2":
                basePrice = 1000;
                break;
            case "2.3":
                basePrice = 250;
                break;

            case "2.3.1":
                basePrice = 500;
                break;

            case "2.3.2":
                basePrice = 500;
                break;

            case "2.3.3":
                basePrice = 500;
                break;

            case "2.3.3.1":
                basePrice = 1000;
                break;

            case "3.0":
                basePrice = 10;
                break;

            default:
                basePrice = 0;
                break;
        }
        return basePrice;
    }
            
         
    
    /*   Allows user(seller) to change the meteorie fixed price  */
    /*-----------------------------------------------------------*/
    public void changePrice(boolean confirmation)
    {   
        float newPrice;
        try
        {
            if (confirmation != true) 
            {
            }

            else 
            {
                addLocation = continent.calculateExtraPrice(location);
                addBase = basePrice(type);
                finalprice = addLocation + addBase;
                
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(frame, "The suggested price : $" + finalprice +"\nDo you want to change the suggested price?", "Change price confirmation",dialogButton);
                if(dialogResult==0)
                {
                     Object[] message1 = {"\n    Enter your price: ", field1};
                        option = JOptionPane.showConfirmDialog(tPanelGlobal, message1, "Change Price", JOptionPane.OK_CANCEL_OPTION);  
                        if (option == JOptionPane.OK_OPTION)
                        {
                            float p = Float.parseFloat( field1.getText() );
                            price = p;
                            JOptionPane.showMessageDialog( null, "    The  new  meteorite  has  been  added  successfully with your new price.\n",                                                     
                                                                 "Message", JOptionPane.PLAIN_MESSAGE );
                        }
                }
                if(dialogResult==1)
                {
                    price = finalprice;
                    JOptionPane.showMessageDialog( null, "    The  new  meteorite  has  been  added  successfully with the suggested price.\n",                                                     
                                                         "Message", JOptionPane.PLAIN_MESSAGE );
                }
            }
            
        }
        catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(frame, "\nReport:\n    "
                        + "===============================================\n    "
                        + "Check with the administrador of this program.\n    "
                        + "There seems to be a bug.\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    public void pinIt()
    {   
        if (allKindMeteorites.superMeteoriteList.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "\n    Report:\n    "
                    + "==============================\n    "
                    + "No meteorites to show\n\n", "Message", JOptionPane.PLAIN_MESSAGE);
            
        }
        else
            
        {
            for (i = 0; i < allKindMeteorites.superMeteoriteList.size(); i++)
            {
                myPin = new Pin(allKindMeteorites.superMeteoriteList.get(i).meteoriteID,allKindMeteorites.superMeteoriteList.get(i).addposition.x, allKindMeteorites.superMeteoriteList.get(i).addposition.y, allKindMeteorites.superMeteoriteList.get(i).status);
                temp.addPin(myPin);
            }
            tPanelGlobal.repaint();
            tPanelGlobal.validate();
        }
    } 
}

