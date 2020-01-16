/*
   Group Members: Alison Zhang, Kevin Huang, Shairagavi Selvachandran, Sunny Lin
   Group Name: Iconic Cinema Studios (ICS)
   Description: TheatreManager class
*/

import java.util.*;
import java.io.*;
import java.text.*;

public class TheatreManager {
   private final String personsFileName = "persons.txt";
   private final String moviesFileName = "movies.txt";
   private final String showtimesFileName = "showtimes.txt";
   private final String auditoriumsFileName = "auditoriums.txt";
   private static File movieSeatingFileName = new File("movieSeating.txt");
   ArrayList<Person> persons = new ArrayList<Person>();
   ArrayList<Movie> movies = new ArrayList<Movie>();
   ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
   ArrayList<Auditorium> auditoriums = new ArrayList<Auditorium>();
   
   
   // constructor
   public TheatreManager () {
      // persons
      try{
         BufferedReader in = new BufferedReader (new FileReader (personsFileName));
         String input =  in.readLine();
         while(input != null){
            if(input.equals("Customer")){
               persons.add(new Customer(in.readLine(),in.readLine(),in.readLine(),in.readLine(),Long.parseLong(in.readLine()),in.readLine()));
            }else if (input.equals("Staff")){
               persons.add(new Staff(in.readLine(),in.readLine(),in.readLine(),in.readLine(),Long.parseLong(in.readLine())));
            }
            input = in.readLine();
         }
         in.close();
      } catch (IOException e){
         System.out.println("File error");
      }
      
      // movies
      try{
         BufferedReader in = new BufferedReader (new FileReader (moviesFileName));
         String input =  in.readLine();
         while(input != null){
            movies.add(new Movie(in.readLine(),in.readLine(),in.readLine(),in.readLine(), in.readLine()));
            System.out.println(movies.get(movies.size()-1));
            input = in.readLine();
         }
         in.close();
      } catch (IOException e){
         System.out.println("File error");
      }
      
      // auditoriums
      try{
         BufferedReader in = new BufferedReader (new FileReader (auditoriumsFileName));
         String input =  in.readLine();
         while(input != null){
            auditoriums.add(new Auditorium(Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine())));
            input = in.readLine();
         }
         in.close();
      } catch (IOException e){
         System.out.println("File error");
      } 
      
      // showtimes
      boolean[][] result = new boolean [6][7];
      try {
         BufferedReader in = new BufferedReader (new FileReader (showtimesFileName));
         String input = in.readLine();
         while (input != null) {
            String movieLine = in.readLine();
            int auditoriumNum = Integer.parseInt(in.readLine());
            String dateLine = in.readLine();
            String sessionLine = in.readLine();
            String showTypeLine = in.readLine();
         
            input = in.readLine();
            for (int i = 0; i < result.length; i++) {
               for (int j = 0; j < result[i].length; j++) {
                  if (input.charAt(j) == 'O'){
                     result[i][j] = false;
                  }
                  else {
                     result[i][j] = true;
                  }
               }
               input = in.readLine();
            }
            
            Movie movie = findMovie(movieLine);
            Date date = new Date (dateLine);
            Session session = new Session (sessionLine);
            Auditorium refAuditorium = findAuditorium(auditoriumNum);
            Auditorium auditorium = new Auditorium(refAuditorium.getAuditoriumNum(), refAuditorium.getNumRows(), refAuditorium.getNumColumns());
            String showType = showTypeLine; 
            
            auditorium.setSeats(result);  
            auditorium.printSeats();            
            showtimes.add(new Showtime(movie,auditorium,session,date,showType));
            
         }
         in.close();
      }
      catch (IOException e) {
         System.out.println("Error");
      }
   }   
   // add staff
   public boolean addStaff (String[] responses) {
      if (!this.checkRepeat(responses[2])) {
         Staff staff = new Staff(responses[0], responses[1], responses[2], responses[3], this.nextIdNum());
         persons.add(staff);
         System.out.println(staff);
         TheatreGUI.showText(staff.getFirst() + " " + staff.getLast() + " has been added as a staff\n");
         try{
            BufferedWriter out = new BufferedWriter (new FileWriter (personsFileName,true));
            out.write("Staff");
            out.newLine();
            out.write(staff.getFirst());
            out.newLine();
            out.write(staff.getLast());
            out.newLine();
            out.write(staff.getUser());
            out.newLine();
            out.write(staff.getPass());
            out.newLine();
            out.write("" + staff.getId());
            out.newLine(); 
            out.close();
         } catch (IOException e){
            System.out.println("File error");
         }
         return true;
      }
      return false;
   
   }
   
   // add customer
   public boolean addCustomer (String[] responses) {
   
      if (!this.checkRepeat(responses[2])) {
         Customer customer = new Customer(responses[0], responses[1], responses[2], responses[3], this.nextIdNum(), responses[4]);
         persons.add(customer);
         System.out.println(customer);
         TheatreGUI.showText(customer.getFirst() + " " + customer.getLast() + " has been added as a customer\n");
         try{
            BufferedWriter out = new BufferedWriter (new FileWriter (personsFileName,true));
            out.write("Customer");
            out.newLine();
            out.write(customer.getFirst());
            out.newLine();
            out.write(customer.getLast());
            out.newLine();
            out.write(customer.getUser());
            out.newLine();
            out.write(customer.getPass());
            out.newLine();
            out.write("" + customer.getId());
            out.newLine();
            out.write(customer.getBirthDate().toString()); 
            
           //  out.write(""+customer.getDay());
         //             out.newLine();
         //             out.write(""+customer.getMonth());
         //             out.newLine();
         //             out.write(""+customer.getYear());
            out.newLine();
            out.close();
         } catch (IOException e){
            System.out.println("File error");
         }
         return true;
      }
      else {
         return false;
      }
     
   }
   
   public boolean addMovie (String[] information) {
      for(int i=0;i<information.length;i++){
         if(information[i] == null || information[i].equals("")){
            return false;
         }
      }
      
      if (findMovie(information[0]) == null) {
         movies.add(new Movie(information[0],information[1],information[2],information[3], information[4]));
         try {
            BufferedWriter out = new BufferedWriter (new FileWriter (moviesFileName,false));
            out.write("Movie");
            out.newLine();
            out.write(movies.get(movies.size()-1).getTitle());
            out.newLine();
            out.write(movies.get(movies.size()-1).getRating());
            out.newLine();
            out.write(movies.get(movies.size()-1).getDirector());
            out.newLine();
            out.write(movies.get(movies.size()-1).getActors());
            out.newLine();
            out.write(movies.get(movies.size()-1).getImageAddress());
            out.newLine();
            out.close();
         }
         catch (IOException e) {
         
         }
         return true;
      }
      return false;
   }
   
   public boolean removeMovie (String title) {
      for(int i=0;i<movies.size();i++){
         if(movies.get(i).getTitle().equals(title)) {
            movies.remove(i);
            // MOVIE
            try {
               BufferedWriter out = new BufferedWriter (new FileWriter (moviesFileName,false));
               for (int j = 0; j < movies.size(); j++) {
                  out.write("Movie");
                  out.newLine();
                  out.write(movies.get(j).getTitle());
                  out.newLine();
                  out.write(movies.get(j).getRating());
                  out.newLine();
                  out.write(movies.get(j).getDirector());
                  out.newLine();
                  out.write(movies.get(j).getActors());
                  out.newLine();
                  out.write(movies.get(j).getImageAddress());
                  out.newLine();
               }
               out.close();
            }
            catch (IOException e) {
            
            }
            return true;
         }
      }
      return false;
   }
   
   public void addAuditorium (int num) {     
      auditoriums.add(new Auditorium(num, 6, 7));
      
      // AUDITORIUM
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter (auditoriumsFileName,false));
         out.write("Auditorium");
         out.newLine();
         out.write(String.valueOf(auditoriums.get(auditoriums.size()-1).getAuditoriumNum()));
         out.newLine();
         out.write(String.valueOf(auditoriums.get(auditoriums.size()-1).getNumRows()));
         out.newLine();
         out.write(String.valueOf(auditoriums.get(auditoriums.size()-1).getNumColumns()));
         out.newLine();
         out.close();
      }
      catch (IOException e) {
      
      }
   }
   
   public int getNextAuditoriumNum() {
      System.out.println(auditoriums.size());
      return auditoriums.size() + 1;
   }
   
   public ArrayList getMovieArrayList() {
      return movies;
   }
   
   public boolean addShowtime (String[] information) {
      for(int i=0;i<information.length;i++){
         if(information[i] == null || information[i].equals("")){
            return false;
         }
      }
      
      Movie movie = findMovie(information[0]);
      Date date = new Date (information[1]);
      Session session = new Session (information[2]);
      Auditorium refAuditorium = findAuditorium(Integer.parseInt(information[3]));
      Auditorium auditorium = new Auditorium(refAuditorium.getAuditoriumNum(), refAuditorium.getNumRows(), refAuditorium.getNumColumns());
      String showType = information[4]; 
      
      for (int i = 0; i < showtimes.size(); i++) {
         if (showtimes.get(i).getDate().toString().equals(date.toString()) && showtimes.get(i).getSession().getSession().equals(session.getSession()) && showtimes.get(i).getAuditorium().getAuditoriumNum() == auditorium.getAuditoriumNum()) {
            return false;
         }
      }
                          
      showtimes.add(new Showtime(movie,auditorium,session,date,showType));
      
      // SHOWTIME
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter(showtimesFileName, false));
         out.write("Showtime");
         out.newLine();
         out.write(movie.getTitle());
         out.newLine();
         out.write(String.valueOf(auditorium.getAuditoriumNum()));
         out.newLine();
         out.write(date.toString());
         out.newLine();
         out.write(session.getSession());
         out.newLine();
         out.write(showType);
         out.newLine();
         for (int i = 0; i < auditorium.getNumRows(); i++) {
            for (int j = 0; j < auditorium.getNumColumns(); j++) {
               out.write("O");
            }
            out.newLine();
         }
         out.close();
      }
      catch (IOException e) {
         System.out.println("Error");
      }
      
      for (int r = 0; r < auditorium.getNumRows(); r++) {
         for (int c = 0; c < auditorium.getNumColumns(); c++) {
            auditorium.setSeat(r, c, false);
         }
      }
   
      return true;
   }
   
   // list all persons
   public void listAllPersons () {
      for(int i = 0; i<persons.size(); i++){
         System.out.println(persons.get(i));
      }
   }
   
   // list all staffs
   public void listAllStaffs () {
      for(int i = 0; i<persons.size(); i++){
         if (persons.get(i) instanceof Staff) {
            System.out.println(persons.get(i));
         }
      }
   }
   
   // list all customers
   public void listAllCustomers () {
      for(int i = 0; i<persons.size(); i++){
         if (persons.get(i) instanceof Customer) {
            System.out.println(persons.get(i));
         }
      }
   }
   
   public long currentIdNum () {
      return persons.size();
   }
   
   public long nextIdNum () {
      return this.currentIdNum() + 1;
   }
   
   public void displayMovies () {
      for(int i=0; i<movies.size(); i++){
         System.out.println(movies.get(i) + "\n");
      }
   }
   
   public void displayShowtimes () {
      for(int i=0; i<showtimes.size(); i++){
         System.out.println(showtimes.get(i) + "\n");
      }
   }

   public void displayShowtimes (Date d) {
      System.out.println(d);
   }
    
   public void displayAuditoriums () {
      for(int i=0; i<auditoriums.size(); i++){
         System.out.println(auditoriums.get(i) + "\n");
      }
   }
 
   public boolean checkRepeat (String user) {
      for(int i=0;i<persons.size();i++){
         if(persons.get(i).getUser().equals(user)){
            return true;
         }
      }
      return false;
   }

   public Person find (String user, String pass) {
      Person result = null;
      for(int i=0;i<persons.size();i++){
         if(persons.get(i).getUser().equals(user) && persons.get(i).getPass().equals(pass)){
            result = persons.get(i);
         }
      }
      return result;
   }
   
   public Movie findMovie (String title) {
      Movie result = null;
      for(int i=0;i<movies.size();i++){
         if(movies.get(i).getTitle().equals(title)){
            result = movies.get(i);
         }
      }
      return result;
   }
   
   public Auditorium findAuditorium (int number) {
      Auditorium result = null;
      for(int i=0;i<auditoriums.size();i++){
         if(auditoriums.get(i).getAuditoriumNum() == number){
            result = auditoriums.get(i);
         }
      }
      return result;
   }
   
   public Showtime findShowtime (String showtime) {
      Showtime result = null;
      for(int i=0;i<showtimes.size();i++){
         if(showtimes.get(i).toString().equals(showtime)){
            result = showtimes.get(i);
         }
      }
      return result;
   }
   
   public String[] movieTitles() {
      String[] titles = new String[movies.size()];
      
      for (int i = 0; i < movies.size(); i++) {
         titles[i] = movies.get(i).getTitle();
      }
      return titles;
   }
   
   public String[] imageAddresses() {
      String[] images = new String[movies.size()];
      
      for (int i = 0; i < movies.size(); i++) {
         images[i] = movies.get(i).getImageAddress();
      }
      return images;
   }
   
   public String[] auditoriumNumbers() {
      String[] numbers = new String[auditoriums.size()];
      
      for (int i = 0; i < auditoriums.size(); i++) {
         numbers[i] = String.valueOf(auditoriums.get(i).getAuditoriumNum());
      }
      return numbers;
   }
  
   public Showtime[] matchShowtimes(Movie movie, Date date, Session session) {
      int count = 0;
      Showtime[] result;
      
      for (int i = 0; i < showtimes.size(); i++) {
         if (showtimes.get(i).getMovie().getTitle().equals(movie.getTitle()) && showtimes.get(i).getDate().compareToDays(date) == 0 && showtimes.get(i).getSession().getSession().equals(session.getSession())) {
            count++;
         }
      }
      
      if (count == 0) {
         result = new Showtime[1];
         result[0] = null;
      }
      else {
         result = new Showtime[count];
         int track = 0;
      
         for (int i = 0; i < showtimes.size(); i++)
         {
            if (showtimes.get(i).getMovie().getTitle().equals(movie.getTitle()) && showtimes.get(i).getDate().compareToDays(date) == 0 && showtimes.get(i).getSession().getSession().equals(session.getSession())) { 
               result[track] = showtimes.get(i);
               track++;
            }
         }
      }
      return result;
   } 
   
   public void writeToFile() {
      // MOVIE
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter (moviesFileName,false));
         for (int i = 0; i < movies.size(); i++) {
            out.write("Movie");
            out.newLine();
            out.write(movies.get(i).getTitle());
            out.newLine();
            out.write(movies.get(i).getRating());
            out.newLine();
            out.write(movies.get(i).getDirector());
            out.newLine();
            out.write(movies.get(i).getActors());
            out.newLine();
            out.write(movies.get(i).getImageAddress());
            out.newLine();
         }
         out.close();
      }
      catch (IOException e) {
      
      }
      
      // SHOWTIME
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter(showtimesFileName, false));
         for (int i = 0; i < showtimes.size(); i++) {
            out.write("Showtime");
            out.newLine();
            out.write(showtimes.get(i).getMovie().getTitle());
            out.newLine();
            out.write(String.valueOf(showtimes.get(i).getAuditorium().getAuditoriumNum()));
            out.newLine();
            out.write(showtimes.get(i).getDate().toString());
            out.newLine();
            out.write(showtimes.get(i).getSession().getSession());
            out.newLine();
            out.write(showtimes.get(i).getShowType());
            out.newLine();
            boolean[][] spots = showtimes.get(i).getAuditorium().getSpots(); 
            for (int j = 0; j < showtimes.get(i).getAuditorium().getNumRows(); j++) {
               for (int k = 0; k < showtimes.get(i).getAuditorium().getNumColumns(); k++) {
                  if (spots[j][k]) {
                     out.write("X");
                  } else {
                     out.write("O"); 
                  }
               }
               out.newLine();
            }
         }
         out.close();
      }
      catch (IOException e) {
         System.out.println("Error");
      }
      
      // AUDITORIUM
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter (auditoriumsFileName,false));
         for (int i = 0; i < auditoriums.size(); i++) {
            out.write("Auditorium");
            out.newLine();
            out.write(String.valueOf(auditoriums.get(i).getAuditoriumNum()));
            out.newLine();
            out.write(String.valueOf(auditoriums.get(i).getNumRows()));
            out.newLine();
            out.write(String.valueOf(auditoriums.get(i).getNumColumns()));
            out.newLine();
         }
         out.close();
      }
      catch (IOException e) {
      
      }
   }
   
   public static boolean [][] readSeating (String movie, String date, String session) {
      //first round
      boolean exist = false;
      boolean[][] result = new boolean [6][7];
      try {
         BufferedReader in = new BufferedReader (new FileReader(movieSeatingFileName));
         String movieLine = in.readLine();
         String dateLine = in.readLine();
         String sessionLine = in.readLine();
       
         
         int counter = 0;
         while (movieLine != null && !exist) {
            counter++;
            if (movieLine.equals(movie) && dateLine.equals(date) && sessionLine.equals(session)) {
               exist = true;
               String input = in.readLine();
               for (int i = 0; i < result.length; i++) {
                  for (int j = 0; j < result[i].length; j++) {
                     if (input.charAt(j) == 'O'){
                        result[i][j] = false;
                     }
                     else {
                        result[i][j] = true;
                     }
                  }
                  input = in.readLine();
               }
               movieLine = in.readLine();
               dateLine = in.readLine();
               sessionLine = in.readLine();
            }
            else {
               for (int i = 0; i < result.length; i++) {
                  in.readLine();
               }
               movieLine = in.readLine();
               dateLine = in.readLine();
               sessionLine = in.readLine();
               System.out.println(movieLine);
            }
         }
         in.close();
      }
      catch (IOException e) {
         System.out.println("Error");
      }
      if (exist) {
         return result;
      }
      else {
         try {
            BufferedWriter out = new BufferedWriter (new FileWriter(movieSeatingFileName, true));
            out.write(movie);
            out.newLine();
            out.write(date);
            out.newLine();
            out.write(session);
            out.newLine();
            for (int i = 0; i < result.length; i++) {
               for (int j = 0; j < result[i].length; j++) {
                  out.write("O");
               }
               out.newLine();
            }
            out.close();
         }
         catch (IOException e) {
            System.out.println("Error");
         }
      }
      for (int i = 0; i < result.length; i++) {
         for (int j = 0; j < result[i].length; j++) {
            result[i][j] = false;
         }
      }
      return result;
   }
  
   public static void writeSeating (String movie, String date, String session, boolean[][] spots) {
      boolean exist = false;
      File tempFile = new File("tempFile.txt");
      int counter = 0;
      try {
         BufferedReader in = new BufferedReader (new FileReader(movieSeatingFileName));
         BufferedWriter out = new BufferedWriter (new FileWriter(tempFile));
        
         String movieLine = in.readLine();
         out.write(movieLine);
         out.newLine();
         String dateLine = in.readLine();
         out.write(dateLine);
         out.newLine();
         String sessionLine = in.readLine();
         out.write(sessionLine);
         out.newLine();
         while (movieLine != null) {
            counter++;
            if (movieLine.equals(movie) && dateLine.equals(date) && sessionLine.equals(session)) {
               for (int i = 0; i < spots.length; i++) {
                  in.readLine();
               }
            
               for (int i = 0; i < spots.length; i++) {
                  for (int j = 0; j < spots[i].length; j++) {
                     if (spots[i][j] == false){
                        out.write("O");
                        System.out.print("O");
                     }
                     else {
                        out.write("X");
                        System.out.print("X");
                     }
                  }
                  out.newLine();
                  System.out.println("");
               } 
               movieLine = in.readLine();
               dateLine = in.readLine();
               sessionLine = in.readLine();
               if (movieLine!= null) {
                  out.write(movieLine);
                  out.newLine();
                  out.write(dateLine);
                  out.newLine();
                  out.write(sessionLine);
                  out.newLine();
               }
            }
            else {
               String input;
               for (int i = 0; i < spots.length; i++) {
                  input = in.readLine();
                  out.write(input);
                  out.newLine();
               }
               movieLine = in.readLine();
               dateLine = in.readLine();
               sessionLine = in.readLine();
               if (movieLine != null) {
                  out.write(movieLine);
                  out.newLine();
                  out.write(dateLine);
                  out.newLine();
                  out.write(sessionLine);
                  out.newLine();
               }
            }
         }
         in.close();
         out.close();
         movieSeatingFileName.delete();
         tempFile.renameTo( movieSeatingFileName);
         
      }
      catch (IOException e) {
      }
   }
   
   ////////////////////////////////////////////////////////////////
   public static String getNewSelectedSeating (boolean[][] oldSpots, boolean[][] newSpots) {
      String result = ""; 
      for (int i = 0; i < oldSpots.length; i++) {
         for (int k = 0; k < oldSpots[i].length; k++) {
            if (oldSpots[i][k] != newSpots[i][k]) {
               if (result == null) {
                  result += (char)('A'+ i) + "" + (k+1); 
               } else {
                  result += ", " + (char)('A'+ i) + "" + (k+1); 
               } 
            }
         }
      }
      return result; 
   }
   
   public static void logAccountHistory (String userName, String content) {
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter("."+File.separator+"accountHistory"+File.separator+userName + ".txt", true)); 
         out.write(content); 
         out.newLine(); 
         out.close(); 
      } catch (IOException iox) {
      }
   }
   
   public static void clearAccountHistory (String userName) {
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter("."+File.separator+"accountHistory"+File.separator + userName + ".txt", false)); 
         out.close(); 
      } catch (IOException iox) {
      }
   }
   
   public static String loadAccountHistory (String userName) {
      String result = ""; 
      try {
         BufferedReader in = new BufferedReader (new FileReader("."+File.separator+"accountHistory"+File.separator + userName + ".txt")); 
         String input = in.readLine(); 
         while (input != null) {
            result += input + "\n"; 
            input = in.readLine(); 
         }
         in.close(); 
      } catch (IOException iox) {
      }
      return result; 
   }
   
   /////////////////////////////////////////////////////////////////
   
}