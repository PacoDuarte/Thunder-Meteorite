# Thunder-Meteorite
The meteorite selling tool, and the other will be a meteorite buying tool. These programs are to be built in an object oriented manner. These will have a tool that shows the map of the world, which will be marked with the location of where the meteorite was found. Also, the system shall list out current meteorites for sale. 

1 Objective:
The goal of this project is to cover the introductory material of the Java language, including
 Use of basic data types,
 Controls,
 Simple concrete classes,
 Files,
 Exception handling.
 Simple collection types

2 Problem:
Create two programs, one will be a meteorite selling tool, and the other will be a meteorite buying tool. These programs are to be built in an object oriented manner. These will have a tool that shows the map of the world, which will be marked with the location of where the meteorite was found. Also, the system shall list out current meteorites for sale. In addition, two UML class diagrams, one for each program, will be submitted for grading.
  
3 The Diagrams and Output.
Create a class diagram of each program, showing the aggregation and association between the classes. There should be no inheritance links in this phase of the project. There should be two diagrams: One for Meteorite Seller Tool, and one for Meteorite Buyer Tool. Turn in a printout of the finished map to Blackboard. The map must be populated with a minimum of 12 meteorites.

4 The Meteorite Seller Tool:
Create a menu driven program that has the following options: 1. Load the World Map.
2. Handle a Meteorite.
3. Show the World Map with Meteorites.
4. Save files. 5. Load files.
5.1 Load the World Map
Go to the Blackboard and download the World Map text file "MMarket.txt". This file contains a map of 1200 lines of code designating coordinates for map pieces to make a map of the world 60 columns by 20 rows. Each line of the file starts with the column, the row, and whether the area is land or ocean. Write a function that will load this data set into an ArrayList or Vector of map grid points. Have the grid points of the ma be a class that contains the coordinates and the map type.
5.2 Handle a Meteorite
Meteorites will have certain key properties for the meteorite shop. Write a menu option and a function that will create a meteorite object from a meteorite class. Meteorites should have a location on the map, an identification number, and a price. Also 'they have a Boolean field to indicate if they have been sold.
5.2.1 Create a Meteorite
Have a menu item that will prompt the user for the coordinates, the price, and an identification number. When this is done, add the meteorite to a collection type (ArrayList or Vector preferred). The Location should be twofold, one is the actual longitude and latitude of the meteorite given by a user. The second should be the row and column on the 60x20 map of the world. Write a formula into your program to convert latitude and longitude to the best match in the two dimensional array. You will need to create 12 meteorites. Use your imagination!
      Example:
Longitude is either positive or negative 180 degrees about the earth from east to west, with 0 being the prime meridian and 180 being the opposite side of the globe. Above and below the equator is latitude, with the equator being 0, and the North Pole being positive 90 and the South Pole being negative 90. On a two‐dimensional array that is 60 by 20, the North Pole would be at row 0, and the South Pole would be at row 19. The Prime Meriden would be at column 30. The Equator would be at row 10. Every index of latitude would be a shift of +/‐9 degrees. Every index of latitude would be a shift of +/‐6 degrees. Use this information to work out the correct integer index of the array.
                5.2.2 Update a Meteorite
Have a menu option that shows the user a list of available meteorites and their identification numbers. Then, prompt the user to select a meteorite or to quit the function. If a meteorite is selected, prompt the user for the coordinates and price changes of the meteorite.
5.2.3 Remove a Meteorite
Have a menu option that shows the user a list of available meteorites and their identification numbers. Then, prompt the user to select a meteorite or to quit the function. If a meteorite is selected, remove the meteorite from the list of objects in the program.
5.3 Show the World Map with Meteorites
Write a menu option that takes the collection of map points and the list of meteorites and creates a map showing their original location on the grid. Remember, index of the array is not the same as longitude and latitude. Put an "X" into the two‐dimensional array to show the location of the meteorite if it is unsold. If it has been sold, put in the symbol of a "$"
5.4 Save Files
Give the Meteorite Seller Tool the ability to save the list of meteorites to a file. The file should be comma separated values with one meteorite per line. Each meteorite line should include its original location, its value, and whether or not it has been sold.
5.5 Load Files
Give the Meteorite Seller Tool the ability to load the list of meteorites from a file. The file should be comma separated values with one meteorite per line. Each meteorite line should include its original location, its value, and whether or not it has been sold.
6 The Meteorite Buyer Tool:
Create a menu driven program that has the following options: 1. Load the World Map.
2. Show the World Map with Meteorites.
3. Allows the user to buy a meteorite
4. Save files. 5. Load files.
6.1 Load the World Map
As with the Seller Tool, Go to the Blackboard and download the World Map text file "MMarket.txt". This file contains a map of 1200 lines of code designating coordinates for map pieces to make a map of the world 60 columns by 20 rows. Each line of the file starts with the column, the row, and whether the area is land or ocean. Write a function that will load this data set into an ArrayList or Vector of map grid points. Have the grid points of the ma be a class that contains the coordinates and the map type.
6.2 Show the World Map with Meteorites
Write a menu option that takes the collection of map points and the list of meteorites and creates a map showing their original location on the grid. Remember, index of the array is not the same as longitude and latitude. Put an "X" into the two‐dimensional array to show the location of the meteorite if it is unsold. If it has been sold, put in the symbol of a "$"
6.3 Buy a Meteorite
Write a menu option to allow the user to buy a meteorite. When executed, the program shall list out all the available meteorites that are available for sale, including their ID number. Allow the user to select the meteorite by ID number and ask for confirmation if the buyer really wants to get the space rock. After this prompt, the tool will change the Boolean status of the meteorite to sold and then acknowledge the buyer.
6.4 Save Files
Give the Meteorite Seller Tool the ability to save the list of meteorites to a file. The file should be comma separated values with one meteorite per line. Each meteorite line should include its original location, its value, and whether or not it has been sold.
6.5 Load Files
Give the Meteorite Seller Tool the ability to load the list of meteorites from a file. The file should be comma separated values with one meteorite per line. Each meteorite line should include its original location, its value, and whether or not it has been sold.
