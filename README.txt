Hotel-Reservation-System

A command line hotel reservation system developed by Tomasz Zajas, John Walsh, Dean Guilfoyle and Ruairi Gallagher

To run the Reservation System:

1) Open the Comandprompt.

2) Change Current Working Directory to the directory where all the packages and the reservations.csv file is in. They are inside the folder called newSource
   The path should look something like this:
   C:\where the project is saved\ProjectVersion3\ProjectVersion3\newSource

3) When inside the directory compile the java project by running the following command:
   javac Analysis/*.java CsvReaderWriter/*.java DateAndTime/*.java Main/*.java model/customer/*.java rateCalculations/*.java model/menu/*.java model/reservations/*.java model/room/*.java

4) After compilation is complete you can run the program by running the following command:
    java -cp . Main/main

5) When the program runs you will have a numerical options to choose the feature you want to use. Most of the program is done through picking numerical values unless prompted
   otherwise.

6) For the cancelations feature you will be prompted to put in a username and password. The username is: hotelstaff123
                                                                                        The password is: password123
Notes: main.java is where the main method is held. Run this class file. 
Do NOT alter the csv files in anyway doing so will cause the program to malfunction.
The csv file being used during the command line version of the application is inside the newSource file. 
Also make sure csv file is closed when making a reservation
