import java.util.* ;
import java.text.*; 

public class Date {
   private int day, month, year;
   
   // constructor 
   public Date(int day, int month, int year) {
      this.day = day;
      this.month = month;
      this.year = year;
   }
   
   // constructor  (DD/MM/YYYY)
   public Date(String date) {
      System.out.println(date);
      this.day = Integer.parseInt(date.substring(0,2));
      this.month = Integer.parseInt(date.substring(3,5));
      this.year = Integer.parseInt(date.substring(6));
   } 
   
   // accessor & mutator
   public int getDay () {
      return day; 
   }
   
   public int getMonth () {
      return month; 
   }
   
   public int getYear () {
      return year; 
   }
   
   public void addYear (int num) {
      year += num; 
   }
   
   public void addMonth (int num) {
      month += num; 
   }
   
   public void addDay (int num) {
      day += num; 
   }
   
   // methods 
   private int countDays () { // count number of days in the year of the given Date
      int result = 0; 
      for (int m = 1; m < month; m++) {
         if (m == 2) {
            if (year % 4 == 0) {
               result += 29; 
            } else {
               result += 28; 
            }
         } else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m ==12) {
            result += 31; 
         } else {
            result += 30; 
         }
      }
      return result + getDay(); 
   }
   
   public int compareToDays (Date other) { // return a positive number if this Date is later than other; negative if this Date is earlier than other 
      int result = 0; 
      for (int y = other.getYear(); y < this.getYear(); y++) {
         if (y % 4 == 0) {
            result += 366; 
         } else {
            result += 365; 
         }
      }
      return result + this.countDays() - other.countDays(); 
   }

   public int compareToFullYear (Date other) { // return a positive number of full years if this Date is later that other; negative if this Date earlier than other
      int diff_year = this.getYear() - other.getYear(); 
      int diff_month = this.getMonth() - other.getMonth(); 
      int diff_day = this.getDay() - other.getDay(); 
      
      if (diff_year > 0){
         if (diff_month > 0){
            return diff_year; 
         } else if (diff_month < 0) {
            return diff_year - 1; 
         } else {
            if (diff_day >= 0) {
               return diff_year; 
            } else {
               return diff_year - 1; 
            }
         }
      } else if (diff_year < 0) {
         if (diff_month < 0) {
            return 0 - diff_year; 
         } else if (diff_month > 0) {
            return 1 - diff_year; 
         } else {
            if (diff_day <= 0) {
               return 0 - diff_year; 
            } else {
               return 1 - diff_year; 
            }
         }
      } else {
         return 0; 
      }
   }
   
   public String toString () {
      String result = ""; 
      if (day < 10) {
         result = result + 0 + day + "/"; 
      } else {
         result = result + day + "/"; 
      }
      if (month < 10) {
         result = result + 0 + month + "/"; 
      } else {
         result = result + month + "/"; 
      }
      return result + year; 
   }
   
   public static Date getCurrentDate () {
      // java.util.Date date = new java.util.Date();     
//       SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");       
//       Date currentDate = new Date((Integer.parseInt(formatter.format(date).substring(0, 1))), (Integer.parseInt(formatter.format(date).substring(3,5))), (Integer.parseInt(formatter.format(date).substring(6))));
//       return currentDate; 
      
      long millis=System.currentTimeMillis();  
      java.sql.Date d2=new java.sql.Date(millis);  
      System.out.println(d2); 
      String date = d2.toString();
      int year = Integer.parseInt(date.substring(0, 4));
      int month = Integer.parseInt(date.substring(5, 7));
      int day = Integer.parseInt(date.substring(8));
      Date currentDate = new Date(day, month, year);
      
      return currentDate; 
   }

}

// done // 5-31-2019 // Alison //