/*
   Group Members: Alison Zhang, Kevin Huang, Shairagavi Selvachandran, Sunny Lin
   Group Name: Iconic Cinema Studios (ICS)
   Description: the manager controls
*/

import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TheatreRunner {
   public static void main (String[] args) {
   
      final String accessCode = "123"; // master login code
      
      TheatreManager theatre = new TheatreManager(); // initialize TheatreManager object
      
      Date date = new Date(1,1,2019);
      
      String checkNull = "";
      
   
      boolean quit = false;
      while (!quit) {
         // choose options
         String[] button = {"Customer","Staff","Manager","Quit"};
         int option = TheatreGUI.choiceDialog("Welcome to Iconic Cinema Studios (ICS)\nLogin as:", "Login", button);
                  
         if (option == 1) {
            System.out.println("Login customer");
            String[] button1 = {"Login as Customer","Create Account"};
            int choice = TheatreGUI.choiceDialog("Options:", "Customer", button1);
            if (choice == 1) { //Login
               String[] prompts = {"Username:", "Password:"};
               boolean validInput = false;
               while(!validInput){
                  String [] information = TheatreGUI.multipleInputs(prompts);
                  while (information[0]!=null && (information[0].equals("") || information[1].equals(""))) {
                     TheatreGUI.showText("Invalid input, please try again!");
                     ///////////////////////////////////////////////////////////////////////////////////////////
                     TheatreManager.logAccountHistory("manager", "Invalid Customer login");
                     ///////////////////////////////////////////////////////////////////////////////////////////
                     information = TheatreGUI.multipleInputs(prompts);
                  }
                  validInput = true;
                  if (information[0]!=null){
                     Person customer = theatre.find(information[0], information[1]);
                     if (customer instanceof Customer && customer != null) {
                        System.out.println(customer);
                        TheatreGUI.showText("Logged In!");
                        ///////////////////////////////////////////////
                        TheatreManager.logAccountHistory(information[0], "Customer login: " + information[0]);
                        TheatreManager.logAccountHistory(information[0], "Log in date: " + Date.getCurrentDate().toString()); 
                        TheatreManager.logAccountHistory("manager", "Customer login: " + information[0]); 
                        TheatreManager.logAccountHistory("manager", "Log in date: " + Date.getCurrentDate().toString()); 
                        ///////////////////////////////////////////////
                        //code 
                        boolean login = true;
                        while(login) {
                           String[] button2 = {"Buy Ticket","Buy Snacks", "View Account History", "Log out"};
                           int choice1 = TheatreGUI.choiceDialog("Options:", "Customer", button2);
                           if (choice1 == 1) {
                              System.out.println("ticket");
                              String[] movies = theatre.movieTitles();
                              String[] images = theatre.imageAddresses();
                              String[] dates = new String[7];
                              for (int i = 0; i < dates.length; i++) {
                                 Date temp = date.getCurrentDate();
                                 temp.addDay(i);
                                 dates[i] = temp.toString();
                              }
                              String[] sessions = {"Morning","Noon","Afternoon","Evening","Midnight"};
                           
                              String[] responses = TheatreGUI.buyTickets(movies,images,dates,sessions,theatre.showtimes); 
                              Movie selectedMovie = theatre.findMovie(responses[0]);
                              Date selectedDate = new Date(responses[1]);
                              Session selectedSession = new Session(responses[2]);
                              
                              Showtime[] matched = theatre.matchShowtimes(selectedMovie, selectedDate, selectedSession);
                              
                              if (matched[0] != null) {
                                 System.out.println("WE GOT MOVIES");
                                 TheatreManager.logAccountHistory(information[0], "Movie selected: " + responses[0]);
                                 TheatreManager.logAccountHistory(information[0], "Date selected: " + responses[1]);
                                 TheatreManager.logAccountHistory(information[0], "Session selected: " + responses[2]); 
                                 String chosen = TheatreGUI.selectShowtime(matched);
                                 Showtime selectedShowtime = theatre.findShowtime(chosen);
                              //                                  System.out.println(selectedShowtime);
                                 boolean[][] spots = selectedShowtime.getAuditorium().getSpots();
                                 
                                 boolean[][] oldSpots = new boolean[spots.length][spots[0].length];
                                 
                                 for(int i=0;i<spots.length;i++){
                                    for(int j=0;j<spots[0].length;j++){
                                       oldSpots[i][j] = spots[i][j];  
                                    }
                                 }
                                 
                                 boolean[][] selectedSeats = TheatreGUI.selectSeats(spots);
                                 selectedShowtime.getAuditorium().setSeats(selectedSeats);
                                 selectedShowtime.getAuditorium().printSeats();
                                 TheatreManager.logAccountHistory(information[0], "Showtime selected: " + selectedShowtime); 
                                 TheatreManager.logAccountHistory("manager", "Showtime " + selectedShowtime + " selected by: " + information[0]);
                                 TheatreManager.logAccountHistory(information[0], "Seat selected: " + TheatreManager.getNewSelectedSeating (oldSpots, spots)); 
                                 TheatreManager.logAccountHistory("manager", "Seat selected by Customer: " + information[0]);
                              
                              } else {
                                 TheatreGUI.showText("No showtimes available for those parameters.");
                                 TheatreManager.logAccountHistory(information[0], "Showtime failed to select");
                              }
                              
                                                      
                              // for(int i = 0;i<selectedSeats.length;i++){
                           //                                  for(int j = 0;j<selectedSeats[i].length;j++){
                           //                                     if(selectedSeats[i][j]){
                           //                                        System.out.print("X");//taken
                           //                                     }else{
                           //                                        System.out.print("O");//empty
                           //                                     }
                           //                                  }
                           //                                  System.out.println();
                           //                               }
                           }
                           else if (choice1 == 2) {
                              System.out.println("Snacks");
                              String[] snacks = {"Burger", "Candy", "ChickenWing", "Drink", "Fries", "HotDog", "Nachos", "Pizza", "Popcorn", "Tacos"};
                              String selectedSnack = TheatreGUI.buySnacks(snacks, false); 
                              Snack[] empty = new Snack[0];
                              TheatreGUI.checkOutSnacks(information[0], snacks, false, empty, selectedSnack);
                              //while(selectedSnack==null){}
                              TheatreGUI.showText("Snack Bought!"); 
                              ///////////////////////////////////////////////////////////////////////////////////////////
                              TheatreManager.logAccountHistory("manager", "Snack purchased by: " + information[0]);
                              ///////////////////////////////////////////////////////////////////////////////////////////     
                              System.out.println(selectedSnack);
                           //Snacks code
                           }
                           else if (choice1 == 3) {
                              System.out.println("View History");
                              /////////////////////////////////////////////////////////////////////////////////////
                              TheatreGUI.showText(TheatreManager.loadAccountHistory(information[0]));
                              TheatreManager.logAccountHistory(information[0], "Viewed account history"); 
                              TheatreManager.logAccountHistory("manager", "Account history viewed by: " + information[0]); 
                              ///////////////////////////////////////////////////////////////////////////////////////
                           }
                           else{
                              login = false;
                              ///////////////////////////////////////////////////////////////////////////////////////////
                              TheatreManager.logAccountHistory(information[0], "Logged out" );
                              TheatreManager.logAccountHistory("manager", "Customer logged out: " + information[0]);
                              ///////////////////////////////////////////////////////////////////////////////////////////
                           }
                        }
                     }else {
                        TheatreGUI.showText("Incorrect username/password!");
                        validInput = false;
                        ///////////////////////////////////////////////////////////////////////////////////////////
                        TheatreManager.logAccountHistory("manager", "Incorrect username/password entry");
                        ///////////////////////////////////////////////////////////////////////////////////////////
                     }
                  }
               }
            } else if (choice == 2) { //Create Account
               boolean validInput = false;
               while (!validInput){
                  String[] prompts = {"First name:","Last name:","Username:","Password:", "Birth date (DD/MM/YYYY)"};
                  String[] responses = TheatreGUI.multipleInputs(prompts);
                  if(responses[0]==null){
                     validInput = true;
                  }
               
                  if(!validInput){
                  // check if already exists
                     if (theatre.addCustomer(responses)){
                        validInput = true;
                        ///////////////////////////////////////////////////////////////////////////////////////////
                        TheatreManager.logAccountHistory(responses[2], "Account created");
                        TheatreManager.logAccountHistory("manager", "Customer account created: " + responses[2]); 
                        ///////////////////////////////////////////////////////////////////////////////////////////
                     }else {
                        TheatreGUI.showText("Username taken");
                        ///////////////////////////////////////////////////////////////////////////////////////////
                        TheatreManager.logAccountHistory("manager", "Customer account failed to create");
                        ///////////////////////////////////////////////////////////////////////////////////////////
                     }
                  }
               }
            
            }
         }else if (option == 2){
            String[] prompts = {"Username:", "Password:"};
            boolean validInput = false;
            while(!validInput){
               String [] information = TheatreGUI.multipleInputs(prompts);
               while (information[0]!=null && (information[0].equals("") || information[1].equals(""))) {
                  TheatreGUI.showText("Invalid input, please try again!");
                  ///////////////////////////////////////////////////////////////////////////////////////////
                  TheatreManager.logAccountHistory("manager", "Staff login failed");
                  ///////////////////////////////////////////////////////////////////////////////////////////
                  information = TheatreGUI.multipleInputs(prompts);
               }
               validInput = true;
               if (information[0]!=null){
                  Person staff = theatre.find(information[0], information[1]);
                  if (staff instanceof Staff && staff != null) {
                     System.out.println(staff);
                     TheatreGUI.showText("Logged In!");
                     ///////////////////////////////////////////////////////////////////////////////////////////
                     TheatreManager.logAccountHistory(information[0], "Staff login: " + information[0]);
                     TheatreManager.logAccountHistory(information[0], "Log in date: " + Date.getCurrentDate().toString()); 
                     TheatreManager.logAccountHistory("manager", "Staff login: " + information[0]);
                     TheatreManager.logAccountHistory("manager", "Log in date: " + Date.getCurrentDate().toString());
                     ///////////////////////////////////////////////////////////////////////////////////////////
                  
                        //code 
                     boolean login = true;
                     while(login) {
                        String[] button2 = {"Add Movie","Add Showtime", "Add Auditorium", "Remove Movie", "View History", "Log out"};
                        int choice1 = TheatreGUI.choiceDialog("Options:", "Staff", button2);
                        if (choice1 == 1) {
                           boolean validMovie = false;
                           while(!validMovie){
                              System.out.println("movie");
                              String[] options = {"Movie Title:", "Rating:", "Director:", "Actors/Actresses:", "Image Address:"};
                              String [] info = TheatreGUI.multipleInputs(options);
                              if(info[0] == null){
                                 validMovie = true;
                              }else if(!theatre.addMovie(info)){
                                 TheatreGUI.showText("Invalid input!");
                              }else{
                                 TheatreGUI.showText(info[0] + " has been added as a movie");
                                 ///////////////////////////////////////////////////////////////////////////////////////////
                                 TheatreManager.logAccountHistory(information[0], "Movie added: " + info[0]);
                                 TheatreManager.logAccountHistory("manager", "Movie " + info[0] + " added by Staff: " + information[0]);
                                 ///////////////////////////////////////////////////////////////////////////////////////////
                                 validMovie = true;
                              }
                           }
                        }else if (choice1 == 2) {
                           System.out.println("showtime");
                           String[] movies = theatre.movieTitles();
                           String[] images = theatre.imageAddresses();
                           String[] dates = new String[7];
                           for (int i = 0; i < dates.length; i++) {
                              Date temp = date.getCurrentDate();
                              temp.addDay(i);
                              dates[i] = temp.toString();
                           }
                           String[] sessions = {"Morning","Noon","Afternoon","Evening","Midnight"};
                           String[] auditoriums = theatre.auditoriumNumbers();
                           String[] showTypes = {"Regular", "3D", "IMAX"};
                           
                           String[] responses = TheatreGUI.createShowtime(movies, images, dates, sessions, auditoriums, showTypes);
                           ///////////////////////////////////////////////////////////////////////////////
                           String showtimeInfo = responses[0]; 
                           for (int i = 1; i < responses.length; i++) {
                              showtimeInfo += responses[i]; 
                           }
                           ///////////////////////////////////////////////////////////////////////////////
                           if (theatre.addShowtime(responses)) {
                              TheatreGUI.showText("Showtime was successfully added."); 
                              ///////////////////////////////////////////////////////////////////////////////////////////
                              TheatreManager.logAccountHistory(information[0], "Showtime added: " + showtimeInfo);
                              TheatreManager.logAccountHistory("manager", "Showtime " + showtimeInfo + " added by Staff: " + information[0]);
                              ///////////////////////////////////////////////////////////////////////////////////////////
                           }
                           else {
                              TheatreGUI.showText("Showtime unable to be added.");
                              ///////////////////////////////////////////////////////////////////////////////////////////
                              TheatreManager.logAccountHistory(information[0], "Showtime failed to add: " + showtimeInfo);
                              TheatreManager.logAccountHistory("manager", "Showtime " + showtimeInfo + " failed to add by Staff: " + information[0]);
                              ///////////////////////////////////////////////////////////////////////////////////////////
                           }
                           //showtime code
                        }else if (choice1 == 3) {
                           System.out.println("Auditorium");
                           int num = theatre.getNextAuditoriumNum(); 
                           int add = TheatreGUI.addAuditorium(num);
                           
                           if (add == 1) {
                              theatre.addAuditorium(num);
                           }
                        
                           //Auditorium code
                           ////???????????????????????????????????????????????????????????????????????????????????????????????
                           TheatreManager.logAccountHistory(information[0], "Auditorium added: ");
                           TheatreManager.logAccountHistory("manager", "Auditorium added");
                           ///////////////////////////////////////////////////////////////////////////////////////////
                           //Auditorium code
                        }else if (choice1 == 4) {
                           System.out.println("Remove Movie");
                           String[] movies = theatre.movieTitles();
                           String movie = TheatreGUI.removeMovie(movies);
                           if (theatre.removeMovie(movie)) {
                              TheatreGUI.showText(movie + " was successfully removed.");
                              ///////////////////////////////////////////////////////////////////////////////////////////
                              TheatreManager.logAccountHistory(information[0], "Movie removed: " + movie);
                              TheatreManager.logAccountHistory("manager", "Movie " + movie + " removed by: " + information[0]);
                              ///////////////////////////////////////////////////////////////////////////////////////////
                           } 
                           else {
                              TheatreGUI.showText(movie + " was not found.");
                              ///////////////////////////////////////////////////////////////////////////////////////////
                              TheatreManager.logAccountHistory(information[0], "Movie falied to remove: " + movie);
                              TheatreManager.logAccountHistory("manager", "Movie " + movie + " failed to remove by: " + information[0]);
                              ///////////////////////////////////////////////////////////////////////////////////////////
                           }
                           // Remove movie code
                        }else if (choice1 == 5) {
                           System.out.println("View History"); 
                              /////////////////////////////////////////////////////////////////////////////////////
                           TheatreGUI.showText(TheatreManager.loadAccountHistory(information[0]));
                           TheatreManager.logAccountHistory(information[0], "Viewed account history"); 
                           TheatreManager.logAccountHistory("manager", "Account history viewed by: " + information[0]); 
                              ///////////////////////////////////////////////////////////////////////////////////////
                        }
                        else{
                           login = false;
                           ///////////////////////////////////////////////////////////////////////////////////////////
                           TheatreManager.logAccountHistory(information[0], "Logged out");
                           TheatreManager.logAccountHistory("manager", "Staff logged out: " + information[0]);
                           ///////////////////////////////////////////////////////////////////////////////////////////
                        }
                     
                     }
                  }else {
                     TheatreGUI.showText("Incorrect username/password!");
                     //////////////////////////////////////////////////////////////////////////////////
                     TheatreManager.logAccountHistory("manager", "Incorrect username/password for Staff login");
                     ////////////////////////////////////////////////////////////////////////////////////
                     validInput = false;
                  }
               }
            }
            
            
         }else if (option == 3){
            checkNull = TheatreGUI.accessCode();
            if(checkNull!= null && checkNull.equals(accessCode)){ // manager access
               //////////////////////////////////////////////////////////////////////////////////
               TheatreManager.logAccountHistory("manager", "Manager access confirmed");
               ////////////////////////////////////////////////////////////////////////////////////
               // choose which type of person to add
               int choice = TheatreGUI.createPersonDialog();
            
               if (choice == 1){ // Staff
                  boolean usernameTaken = false;
                  boolean cancel = false;
                  while (!usernameTaken && !cancel){
                     usernameTaken = false;
                     String[] prompts = {"First name:","Last name:","Username:","Password:"};
                     String[] responses = TheatreGUI.multipleInputs(prompts);
                     
                     System.out.println("aaaaa"+responses[0]);
                                         
                     while (responses[0] != null && (responses[0].equals("") || responses[1].equals("") || responses[2].equals("") || responses[3].equals("")) ) {
                        TheatreGUI.showText("Invalid input, please try again!");
                           ///////////////////////////////////////////////////////////////////////////////////////////
                        TheatreManager.logAccountHistory("manager", "Invalid Customer login");
                           ///////////////////////////////////////////////////////////////////////////////////////////
                        responses = TheatreGUI.multipleInputs(prompts);
                     }
                        
                     if(responses[0] == null){
                        cancel = true;
                     }else{
                          // check if already exists
                        if (theatre.addStaff(responses)){
                           usernameTaken = true;
                                 //////////////////////////////////////////////////////////////////////////////////
                           TheatreManager.logAccountHistory("manager", "Staff added: " + prompts[2]);
                                 ////////////////////////////////////////////////////////////////////////////////////
                        }else {
                           TheatreGUI.showText("Username taken");
                        }
                     }
                  }
               
               }else if(choice == 2){ // Customer
                  boolean validInput = false;
                  while (!validInput){
                     String[] prompts = {"First name:","Last name:","Username:","Password:", "Birth date (DD/MM/YYYY)"};
                     String[] responses = TheatreGUI.multipleInputs(prompts);
                     if(responses[0]==null){ // cancel
                        validInput = true;
                     }
                     if(!validInput){
                     // check if already exists
                        if (theatre.addCustomer(responses)){
                        //////////////////////////////////////////////////////////////////////////////////
                           TheatreManager.logAccountHistory("manager", "Customer added: " + prompts[2]);
                        ////////////////////////////////////////////////////////////////////////////////////
                        }else {
                           validInput = false;
                           TheatreGUI.showText("Username taken");
                        }
                     }
                  }
               
               }
            }else{
               if(checkNull!=null){
                  TheatreGUI.showText("Wrong access code");
               //////////////////////////////////////////////////////////////////////////////////
                  TheatreManager.logAccountHistory("manager", "Incorrect access code for manager access");
               ////////////////////////////////////////////////////////////////////////////////////
               }
            }
         }else if (option == 4){
            quit = true;
            //////////////////////////////////////////////////////////////////////////////////
            TheatreManager.logAccountHistory("manager", "Logged out");
            ////////////////////////////////////////////////////////////////////////////////////
            theatre.writeToFile();
            System.exit(0);
         }else{
            TheatreGUI.showText("Invalid Choice. Try again.");
         }
         
      }
   }
}