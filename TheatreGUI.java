/*
   Group Members: Alison Zhang, Kevin Huang, Shairagavi Selvachandran, Sunny Lin
   Group Name: Iconic Cinema Studios (ICS)
   Description: the manager controls
*/
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;


public class TheatreGUI {

   private boolean go;


   public static int choiceDialog(String prompt, String titlebar, String[] options){	
      int choice = JOptionPane.showOptionDialog(
            null                       
            , prompt // Instructions
            , titlebar            		  //titlebar
            , JOptionPane.YES_NO_OPTION  // Option type
            , JOptionPane.PLAIN_MESSAGE  // messageType
            , null                       // Icon (none)
            , options                     // button options
            , ""  
            );
      return choice+1;
   }

      
   public static int createPersonDialog(){	
      String[] button = {"Staff","Customer"}; //difficulty options
      int choice = JOptionPane.showOptionDialog(
            null                       
            , "Add:" // Instructions
            , "Login"             		  //titlebar
            , JOptionPane.YES_NO_OPTION  // Option type
            , JOptionPane.PLAIN_MESSAGE  // messageType
            , null                       // Icon (none)
            , button                     // button options
            , ""  
            );
      return choice+1;
   }
      
      
   public static int customerDialog() {	
      String[] button = {"Login as Customer","Create Account"}; //difficulty options
      int choice = JOptionPane.showOptionDialog(
         null                       
         , "Options:" // Instructions
         , "Customer"             		  //titlebar
         , JOptionPane.YES_NO_OPTION  // Option type
         , JOptionPane.PLAIN_MESSAGE  // messageType
         , null                       // Icon (none)
         , button                     // button options
         , ""  
         );
      return choice+1;
   }
   
   public static String accessCode () {
      return JOptionPane.showInputDialog("Enter access code:");
   }
      
   public static void showText (String text){
      JOptionPane.showMessageDialog(null, text);
   }
      
   public static String[] multipleInputs(String[] prompts){   
      JPanel myPanel = new JPanel();
      myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
      JTextField[] textFields = new JTextField [prompts.length];
      for (int i=0;i<prompts.length;i++){
         myPanel.add(new JLabel(prompts[i]));
         textFields[i] = new JTextField(15);
         myPanel.add(textFields[i]);
      }
      String[] responses = new String[prompts.length];
      int result = JOptionPane.showConfirmDialog(null, myPanel, 
                  "Login:", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         for(int i=0;i<prompts.length;i++){
            responses[i] = textFields[i].getText();
         }
      }
      return responses;
   }
   
   public static String[] buyTickets (String[] movies, String[] images, String[] dates, String[] sessions, ArrayList<Showtime> showtimes){ 
      Map<String, String> movieImages = new HashMap<String,String>(); 
      for (int i = 0; i < movies.length; i++) {
         movieImages.put(movies[i], images[i]);
      }
      
   
   
      JFrame frame = new JFrame("Select Showtime");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(750, 900);
      frame.setLocation(130, 50);
      JPanel panel = new JPanel();
      frame.add(panel);
    
      // MOVIE
      JLabel lbl = new JLabel("Select a movie:");
      lbl.setVisible(true);
      panel.add(lbl);
      JComboBox<String> movieList = new JComboBox<String>(movies);
      movieList.setVisible(true);
      panel.add(movieList);
      
      String curMovie = (String)movieList.getSelectedItem();
      
      // IMAGE
      BufferedImage image = null;
      JLabel img = null;
      
      ArrayList<String> datesForCurMovie = new ArrayList<String>();
      for(int i=0;i<showtimes.size();i++){
         if(showtimes.get(i).getMovie().getTitle().equals(curMovie)){
            datesForCurMovie.add(showtimes.get(i).getDate().toString());
         }
      }            
      
   //       System.out.println("aaa");
   //       for(int i=0;i<datesForCurMovie.size();i++){
   //          System.out.println(datesForCurMovie.get(i));
   //       }
   //    
      // DATE
      JLabel lbl1 = new JLabel("Select a date:");
      lbl1.setVisible(true);
      panel.add(lbl1);
      String[] options = {"Select Movie"};
      JComboBox<String> dateList = new JComboBox<String>(options);
      dateList.setVisible(true);
      panel.add(dateList);
      updateDates(curMovie,dateList,showtimes);
      
      //SESSION
      JLabel lbl2 = new JLabel("Select a session:");
      lbl1.setVisible(true);
      panel.add(lbl2);
      JComboBox<String> sessionList = new JComboBox<String>(new String[1]);
      sessionList.setVisible(true);
      panel.add(sessionList);
      updateSessions(curMovie,(String)dateList.getSelectedItem(),sessionList,showtimes);
           
      // BUTTON       
      JButton btn = new JButton("OK");
      panel.add(btn);
   
      try{
         image = ImageIO.read(new File("."+File.separator+"images"+File.separator+movieImages.get((String)movieList.getSelectedItem())));
      }catch(Exception eee){
         System.out.println("error");
      }
      img = new JLabel(new ImageIcon(image));
      panel.add(img);
      img.setVisible(true);
      final JLabel img2 = img;
      movieList.addActionListener (
            new ActionListener () {
               public void actionPerformed(ActionEvent e) {
                  String currentMovie = (String)movieList.getSelectedItem();
               
                  try{
                     img2.setIcon(new ImageIcon(getClass().getResource("."+File.separator+"images"+File.separator+movieImages.get(currentMovie))));
                  }catch (Exception eeeee){
                     img2.setIcon(new ImageIcon(getClass().getResource("."+File.separator+"images"+File.separator+"default.jpg")));
                  }
                  panel.add(img2);
                  img2.setVisible(true);
                  
                  
                  updateDates(currentMovie,dateList,showtimes);

               }
            });
            
      dateList.addActionListener (
            new ActionListener () {
               public void actionPerformed(ActionEvent e) {
                  String currentMovie = (String)movieList.getSelectedItem();
                  String currentDate = (String)dateList.getSelectedItem();
               
                  updateSessions(currentMovie,currentDate,sessionList,showtimes);

               }
            });
       
      String[] responses = new String[3];
      frame.setVisible(true);
        
      btn.addActionListener(
         new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
               String selectedMovie = (String) movieList.getSelectedItem();
               System.out.println("You selected the movie: " + selectedMovie); 
               String selectedDate = (String) dateList.getSelectedItem();
               System.out.println("You selected the date: " + selectedDate);
               String selectedSession = (String) sessionList.getSelectedItem();
               System.out.println("You selected the session: " + selectedSession); 
               responses[0] = selectedMovie;
               responses[1] = selectedDate;
               responses[2] = selectedSession;
               frame.setVisible(false);
            }  
         
         });
         
      while (responses[0]==null) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
      
      }
      
      return responses;
   
   }
   
   public static void updateDates(String currentMovie,  JComboBox<String> dateList, ArrayList<Showtime> showtimes){
               
      ArrayList<String> datesForCurMovie = new ArrayList<String>();
      for(int i=0;i<showtimes.size();i++){
         if(showtimes.get(i).getMovie().getTitle().equals(currentMovie)){
            if(!datesForCurMovie.contains(showtimes.get(i).getDate().toString())){
               datesForCurMovie.add(showtimes.get(i).getDate().toString());
            }
         }
      }
      dateList.removeAllItems();
      for (int i = 0; i < datesForCurMovie.size(); i++) {
         dateList.addItem(datesForCurMovie.get(i));
      }
   }
   
   public static void updateSessions(String currentMovie, String currentDate, JComboBox<String> sessionList, ArrayList<Showtime> showtimes){
               
      ArrayList<String> sessionsForCurMovieAndDate = new ArrayList<String>();
      for(int i=0;i<showtimes.size();i++){
         if(showtimes.get(i).getMovie().getTitle().equals(currentMovie) && showtimes.get(i).getDate().toString().equals(currentDate)){
            if(!sessionsForCurMovieAndDate.contains(showtimes.get(i).getSession().getSession())){
               sessionsForCurMovieAndDate.add(showtimes.get(i).getSession().getSession());
            }
         }
      }
      sessionList.removeAllItems();
      for (int i = 0; i < sessionsForCurMovieAndDate.size(); i++) {
         sessionList.addItem(sessionsForCurMovieAndDate.get(i));
      }                    
   
   
   }
   
   public static String selectShowtime (Showtime[] showtimes){   
      JFrame frame = new JFrame("Select Showtime");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(750, 900);
      frame.setLocation(130, 50);
      JPanel panel = new JPanel();
      frame.add(panel);
      
      String[] showtimeInfo = new String[showtimes.length];
      
      for (int i = 0; i < showtimes.length; i++) {
         showtimeInfo[i] = showtimes[i].toString();
      }
      
      // SHOWTIMES
      JLabel lbl = new JLabel("Select a showtime:");
      lbl.setVisible(true);
      panel.add(lbl);
      JComboBox<String> showtimeList = new JComboBox<String>(showtimeInfo);
      showtimeList.setVisible(true);
      panel.add(showtimeList);
           
      // BUTTON       
      JButton btn = new JButton("OK");
      panel.add(btn);
   
      String[] responses = new String[1];
      frame.setVisible(true);
        
      btn.addActionListener(
         new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
               String selectedShowtime = (String) showtimeList.getSelectedItem();
               System.out.println("You selected the showtime: " + selectedShowtime); 
               responses[0] = selectedShowtime;
               frame.setVisible(false);
            }  
         
         });
         
      while (responses[0]==null) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
      
      }
      
      return responses[0];
   
   }
   
 /*  
   public static String selectShowtime(Showtime[] showtimes) {
      JFrame frame = new JFrame("Select Showtimes");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 400);
      frame.setLocation(130, 100);
      frame.setLayout(new GridLayout(showtimes.length + 1, 1, 5, 10));
      
      frame.add(new JLabel("Select a showtime: "));
      JButton[] buttons = new JButton[showtimes.length];
      for (int i = 0; i < showtimes.length; i++) {
         buttons[i] = new JButton(showtimes[i].toString());
         frame.add(buttons[i]);
      }
      
      frame.setVisible(true);
      
      buttons[i].addActionListener(
         new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            
            }
         });
         
      btn.addActionListener(
         new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            
            }
         });
         
      return "Hey";
   }  */
        
   public static boolean[][] selectSeats(boolean[][] spots){
      JFrame frame = new JFrame ("Select Seats");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JButton[][] buttons = new JButton[6][7];
      JPanel panel = new JPanel();
      panel.setSize(4000, 1000);
      panel.setLayout(new GridLayout(8,7, 5, 5));
      boolean[][] dummy = new boolean [6][7];
      for (int i = 0; i < spots.length; i++) {
         for (int j = 0; j < spots[i].length; j++) {
            dummy[i][j] = spots[i][j];
         }
      }
      
      JLabel txt = new JLabel("", JLabel.CENTER);
      JLabel txt1 = new JLabel("", JLabel.CENTER);
      JLabel txt2 = new JLabel("", JLabel.CENTER);
      JLabel txt3 = new JLabel("Screen", JLabel.CENTER);
      JLabel txt4 = new JLabel("", JLabel.CENTER);
      JLabel txt5 = new JLabel("", JLabel.CENTER);
      JLabel txt6 = new JLabel("", JLabel.CENTER);
      panel.add(txt);
      panel.add(txt1);
      panel.add(txt2);
      panel.add(txt3);
      panel.add(txt4);
      panel.add(txt5);
      panel.add(txt6);
      String seatNumber[][]={{"A1", "A2","A3","A4","A5","A6","A7"},
         {"B1", "B2","B3","B4","B5","B6","B7"},
         {"C1", "C2","C3","C4","C5","C6","C7"},
         {"D1", "D2","D3","D4","D5","D6","D7"},
         {"E1", "E2","E3","E4","E5","E6","E7"},
         {"F1", "F2","F3","F4","F5","F6","F7"}};
      for(int i = 0; i < buttons.length; i++) {
         for (int j = 0; j < buttons[i].length; j++) {
            buttons[i][j] = new JButton(seatNumber[i][j]);
            buttons[i][j].setSize(80, 80);
            buttons[i][j].setActionCommand(seatNumber[i][j]);
            if (!spots[i][j]) {
               buttons[i][j].setBackground(Color.GRAY);
            }
            else {
               buttons[i][j].setBackground(Color.GREEN);
            }
            int row = i;
            int col = j;
            //sets all false
            //spots[i][j] = false;
            if ( spots[i][j] == false) {
               buttons[i][j].addActionListener(
                  new ActionListener(){
                     public void actionPerformed(ActionEvent e) {
                        String choice = e.getActionCommand();
                        if(!spots[row][col]) {
                           buttons[row][col].setBackground(Color.GREEN);
                           spots[row][col] = true;
                        }else {
                           buttons[row][col].setBackground(Color.GRAY);
                           spots[row][col] = false;
                        }
                     }
                  });
            } 
            panel.add(buttons[i][j]);
         }
      }
      JLabel xtxt = new JLabel("", JLabel.CENTER);
      JLabel xtxt1 = new JLabel("", JLabel.CENTER);
      JButton ok = new JButton("OK");
      JLabel xtxt3 = new JLabel("", JLabel.CENTER);
      JButton cancel = new JButton("Cancel");
      JLabel xtxt5 = new JLabel("", JLabel.CENTER);
      JLabel xtxt6 = new JLabel("", JLabel.CENTER);
      panel.add(xtxt);
      panel.add(xtxt1);
      panel.add(ok);
      panel.add(xtxt3);
      panel.add(cancel);
      panel.add(xtxt5);
      panel.add(xtxt6);
      frame.add(panel);
      frame.pack();
      frame.setVisible(true);  
      boolean[] done = new boolean[1];
      ok.addActionListener(
               new ActionListener(){
                  public void actionPerformed(ActionEvent e) {
                     String choice = e.getActionCommand();
                     System.out.println("Ok");
                  //return spots;
                     frame.setVisible(false);
                     done[0] = true;
                     System.out.println(done[0]);
                     //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                  }
               });
               
      cancel.addActionListener(
               new ActionListener(){
                  public void actionPerformed(ActionEvent e) {
                     String choice = e.getActionCommand();
                     System.out.println("cancel");
                     //return spots;
                     //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    //  done = true;
                     for (int i = 0; i < spots.length; i++) {
                        for (int j = 0; j < spots[i].length; j++) {        
                           spots[i][j] = dummy[i][j];
                        }
                     }
                     frame.setVisible(false);
                     done[0] = true;
                  
                  }
               });
      while (!done[0]) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
      
         System.out.println(done[0]);
      }
      return spots;
      
   }
   
   private static Snack[] generateReceipt (Snack[] result, String newSnack) {
      Snack[] eat = new Snack[1];  
      if (newSnack.equals("Burger")) {
         eat[0] = new Burger(); 
      } else if (newSnack.equals("Maynards") || newSnack.equals("M & M") || newSnack.equals("Skittles")) {
         eat[0] = new Candy(); 
         eat[0].setType(newSnack); 
      } else if (newSnack.equals("ChickenWing")) {
         eat[0] = new ChickenWings(); 
      } else if (newSnack.equals("Water") || newSnack.equals("Coke") || newSnack.equals("Juice")) {
         eat[0] = new Drink(); 
         eat[0].setType(newSnack);  
      } else if (newSnack.equals("Fries")) {
         eat[0] = new Fries(); 
      } else if (newSnack.equals("HotDog")) {
         eat[0] = new HotDog(); 
      } else if (newSnack.equals("Nachos")) {
         eat[0] = new Nachos(); 
      } else if (newSnack.equals("Cheese") || newSnack.equals("Pepperoni") || newSnack.equals("Hawaiian")) {
         eat[0] = new Pizza(); 
         eat[0].setType(newSnack); 
      } else if (newSnack.equals("Plain") || newSnack.equals("Butter") || newSnack.equals("Caramel")) {
         eat[0] = new Popcorn(); 
         eat[0].setType(newSnack); 
      } else if (newSnack.equals("Tacos")) {
         eat[0] = new Tacos(); 
      } 
      
      Snack[] newResult = new Snack[result.length+1];
      
      for (int i = 0; i < result.length; i++) {
         newResult[i] = result[i];
      }
      
      newResult[newResult.length-1] = eat[0]; 
      
      return newResult;
   }
   
   public static void checkOutSnacks (String userName, String[] snackList, boolean type, Snack[] result, String newSnack) {
      //boolean quit = false; 
      Snack[] updatedReceipt = generateReceipt(result, newSnack); 
      
      JFrame frame = new JFrame("Snack Check-Out: ");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 400);
      frame.setLocation(130, 100);
      frame.setLayout(new GridLayout(updatedReceipt.length+2, 2, 5, 10));
      
      JButton checkOut = new JButton("Check Out");
      JButton selectAnother = new JButton("Select Another");
      double subTotal = 0; 
      for (int i = 0; i < updatedReceipt.length; i++) {
         frame.add(new JLabel("   " + updatedReceipt[i].snackName()));
         frame.add(new JLabel("$" + updatedReceipt[i].getPrice()));
         frame.add(new JLabel("   "));
         subTotal += updatedReceipt[i].getPrice(); 
      }
      
      //if (updatedReceipt.length != 1) {
      frame.add(new JLabel("   "));
      frame.add(new JLabel("Sub Total")); 
      frame.add(new JLabel("   $" + subTotal)); 
      //}
      
      frame.add(checkOut);
      frame.add(selectAnother);
   
   ///////////////////////////// OLD CODE /////////////
   //       JPanel panel = new JPanel();
   //       frame.add(panel);
   // 
   //       JLabel lbl = new JLabel(updatedReceipt);
   //       lbl.setVisible(true);
   //       panel.add(lbl);
   //        
   //       JLabel lb2 = new JLabel("Kawhi");
   //       lbl.setVisible(true);
   //       panel.add(lb2);
   //       
   //       JButton checkOut = new JButton("Check Out");
   //       panel.add(checkOut);
   //       JButton selectAnother = new JButton("Select Another");
   //       panel.add(selectAnother); 
   ////////////////////////////////////////////////////      
      
      frame.setVisible(true);
      
      String[] action = new String[1];
      
      selectAnother.addActionListener(
               new ActionListener(){
                  public void actionPerformed(ActionEvent e) {
                     String choice = e.getActionCommand();
                     action[0] = choice;
                     frame.setVisible(false);
                  }
               });
      checkOut.addActionListener(
               new ActionListener(){
                  public void actionPerformed(ActionEvent e) {
                     String choice = e.getActionCommand();
                     action[0] = choice;
                     frame.setVisible(false);
                     ////////////////////////////////////////////add userName in parameter & recursion/////////////////////////////////////////////////////////////////////
                     String snackHistory = updatedReceipt[0].snackName(); 
                     for (int i = 1; i < updatedReceipt.length; i++) {
                        snackHistory += ", " + updatedReceipt[i].snackName(); 
                     }
                     TheatreManager.logAccountHistory(userName, "Snack selected: " + snackHistory); 
                     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                     
                     //return result; 
                     //quit = true; 
                  }
               });
      
      while (action[0]==null) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
           
      }
           
      if (action[0].equals("Select Another")) {
         checkOutSnacks (userName, snackList, type, updatedReceipt, buySnacks(snackList, type)); 
      }
      //return result; 
   }

   public static String buySnacks (String[] snacks, boolean type){
      JFrame frame = new JFrame("Buy Snacks");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(650, 500);
      frame.setLocation(130, 100);
      
      JPanel panel = new JPanel();
      frame.add(panel);
    
      // SNACK
      JLabel lbl = new JLabel("Select a snack:");
      lbl.setVisible(true);
      panel.add(lbl);
      JComboBox<String> snackList = new JComboBox<String>(snacks);
      snackList.setVisible(true);
      panel.add(snackList);
   
      JButton btn = new JButton("OK");
      panel.add(btn);
      
      frame.setVisible(true);
      
      ///////
      String[] responses = new String[1];
      
      btn.addActionListener(
         new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
               String selectedSnack = (String) snackList.getSelectedItem();
               System.out.println("You selected the snack: " + selectedSnack);
               responses[0] = selectedSnack;
               
               frame.setVisible(false);
            }  
         
         });
         
      while (responses[0]==null) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
           
      }
      if (!type) { 
               //   responses[0] = selectedSnack;
         if (responses[0].equals("Popcorn")) {
            String[] popcorn = {"Plain", "Butter", "Caramel"};
            responses[0] = buySnacks(popcorn, true);
         }
         else if (responses[0].equals("Drink")) {
            String[] drinks = {"Water", "Coke", "Juice"};
            responses[0] = buySnacks(drinks, true);
         }
         else if (responses[0].equals("Candy")) {
            String[] candy = {"Maynards", "M & M", "Skittles"};
            responses[0] = buySnacks(candy, true);
         }
         else if (responses[0].equals("Pizza")) {
            String[] pizza = {"Cheese", "Pepperoni", "Hawaiian"};
            responses[0] = buySnacks(pizza, true);
         }
      }
         
      while (responses[0]==null) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
           
      }
      return responses[0];
   }
      
   public static int addAuditorium(int num) {	
      String[] button = {"OK","Cancel"}; //options
      int choice = JOptionPane.showOptionDialog(
         null                       
         , "Adding: Auditorium #" + num + "\n# of Rows: 6\n# of Columns: 7\n\nClick OK to confirm" // Instructions
         , "Add Auditorium"             		  //titlebar
         , JOptionPane.YES_NO_OPTION  // Option type
         , JOptionPane.PLAIN_MESSAGE  // messageType
         , null                       // Icon (none)
         , button                     // button options
         , ""  
         );
      return choice+1;
   }
      
   public static String[] createShowtime (String[] movies, String[] images, String[] dates, String[] sessions, String[] auditoriums, String[] showTypes){
     /* Map<String, String> movieImages = new HashMap<String,String>(); 
      movieImages.put("Avengers: Endgame", "avengers.jpg"); 
      movieImages.put("Aladdin", "aladdin.jpg");
      movieImages.put("Detective Pikachu", "detective.jpg"); 
      movieImages.put("John Wick 3", "john.jpg");
      movieImages.put("Godzilla", "godzilla.jpg");  */
      
      Map<String, String> movieImages = new HashMap<String,String>(); 
      for (int i = 0; i < movies.length; i++) {
         movieImages.put(movies[i], images[i]);
      }
        
      JFrame frame = new JFrame("Select Showtime");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(700, 900);
      frame.setLocation(130, 50);
      
      JPanel panel = new JPanel();
   
      frame.add(panel);
    
      // MOVIE
      JLabel lbl = new JLabel("Select a movie:");
      lbl.setVisible(true);
      panel.add(lbl);
      JComboBox<String> movieList = new JComboBox<String>(movies);
      movieList.setVisible(true);
      panel.add(movieList);
      BufferedImage image = null;
      JLabel img = null;
   
      // DATE
      JLabel lbl1 = new JLabel("Select a date:");
      lbl1.setVisible(true);
      panel.add(lbl1);
      JComboBox<String> dateList = new JComboBox<String>(dates);
      dateList.setVisible(true);
      panel.add(dateList);
   
      //SESSION
      JLabel lbl2 = new JLabel("Select a session:");
      lbl1.setVisible(true);
      panel.add(lbl2);
      JComboBox<String> sessionList = new JComboBox<String>(sessions);
      sessionList.setVisible(true);
      panel.add(sessionList);
      
      //AUDITORIUM
      JLabel lbl3 = new JLabel("Select a auditorium:");
      lbl3.setVisible(true);
      panel.add(lbl3);
      JComboBox<String> auditoriumList = new JComboBox<String>(auditoriums);
      auditoriumList.setVisible(true);
      panel.add(auditoriumList);
      
      //SHOW TYPE
      JLabel lbl4 = new JLabel("Select a show type:");
      lbl4.setVisible(true);
      panel.add(lbl4);
      JComboBox<String> showTypeList = new JComboBox<String>(showTypes);
      showTypeList.setVisible(true);
      panel.add(showTypeList);
          
      JButton btn = new JButton("OK");
      panel.add(btn);
    
      // try{
//          image = ImageIO.read(new File(movieImages.get(movieList.getSelectedItem())));
//       }catch(Exception eee){
//          System.out.println("error");
//       }
//       img = new JLabel(new ImageIcon(image));
//          panel.add(img);
//          img.setVisible(true);
//          final JLabel img2 = img;
//          movieList.addActionListener (
//                new ActionListener () {
//                   public void actionPerformed(ActionEvent e) {
//                      String currentMovie;
//                      currentMovie = (String)movieList.getSelectedItem();
//                      try{
//                         img2.setIcon(new ImageIcon(getClass().getResource(movieImages.get(currentMovie))));
//                      }catch (Exception eeeee){
//                         img2.setIcon(new ImageIcon(getClass().getResource("default.jpg")));
//                      }
//                      panel.add(img2);
//                      img2.setVisible(true);
//                   }
//                });
       
      String[] responses = new String[5];
      frame.setVisible(true);
      
      btn.addActionListener(
         new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
               String selectedMovie = (String) movieList.getSelectedItem();
               System.out.println("You selected the movie: " + selectedMovie); 
               String selectedDate = (String) dateList.getSelectedItem();
               System.out.println("You selected the date: " + selectedDate); 
               String selectedSession = (String) sessionList.getSelectedItem();
               System.out.println("You selected the session: " + selectedSession); 
               String selectedAuditorium = (String) auditoriumList.getSelectedItem();
               System.out.println("You selected the auditorium: " + selectedSession);
               String selectedShowType = (String) showTypeList.getSelectedItem();
               System.out.println("You selected the show type: " + selectedSession); 
               responses[0] = selectedMovie;
               responses[1] = selectedDate;
               responses[2] = selectedSession;
               responses[3] = selectedAuditorium;
               responses[4] = selectedShowType;
               frame.setVisible(false);
            }  
         
         });
   
      while (responses[0]==null) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
      
      }
      
      return responses;
   
   }
   
   public static String removeMovie (String[] movies){
      Map<String, String> movieImages = new HashMap<String,String>(); 
      movieImages.put("Avengers: Endgame", "avengers.jpg"); 
      movieImages.put("Aladdin", "aladdin.jpg");
      movieImages.put("Detective Pikachu", "detective.jpg"); 
      movieImages.put("John Wick 3", "john.jpg");
      movieImages.put("Godzilla", "godzilla.jpg"); 
        
      JFrame frame = new JFrame("Remove Movie");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(700, 900);
      frame.setLocation(130, 50);
      
      JPanel panel = new JPanel();
   
      frame.add(panel);
    
      // MOVIE
      JLabel lbl = new JLabel("Select a movie:");
      lbl.setVisible(true);
      panel.add(lbl);
      JComboBox<String> movieList = new JComboBox<String>(movies);
      movieList.setVisible(true);
      panel.add(movieList);
      BufferedImage image = null;
      JLabel img = null;
          
      JButton btn = new JButton("OK");
      panel.add(btn);
    
      // try{
//          image = ImageIO.read(new File(movieImages.get(movieList.getSelectedItem())));
//       }catch(Exception eee){
//          System.out.println("error");
//       }
//       img = new JLabel(new ImageIcon(image));
//       panel.add(img);
//       img.setVisible(true);
//       final JLabel img2 = img;
//       movieList.addActionListener (
//             new ActionListener () {
//                public void actionPerformed(ActionEvent e) {
//                   String currentMovie;
//                   currentMovie = (String)movieList.getSelectedItem();
//                   try{
//                      img2.setIcon(new ImageIcon(getClass().getResource(movieImages.get(currentMovie))));
//                   }catch (Exception eeeee){
//                      img2.setIcon(new ImageIcon(getClass().getResource("default.jpg")));
//                   }
//                   panel.add(img2);
//                   img2.setVisible(true);
//                }
//             });
       
      String[] responses = new String[1];
      frame.setVisible(true);
      
      btn.addActionListener(
         new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
               String selectedMovie = (String) movieList.getSelectedItem();
               System.out.println("You selected the movie: " + selectedMovie);  
               responses[0] = selectedMovie;
               frame.setVisible(false);
            }  
         
         });
   
      while (responses[0]==null) {
         try {
            Thread.sleep(200);
         } catch(InterruptedException e) {
         }
      
      }
      
      return responses[0];
   
   }
   
}
