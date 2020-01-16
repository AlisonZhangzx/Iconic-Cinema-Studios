import java.util.*; 

public class Membership {
   private static final int REDEEM_RT = 1000;
   private static final int REWARD_RT = 100;
   private Date expiryDate;
   private int pointBalance;
   
   // constructor 
   public Membership(Date date, int numYears) {
      expiryDate = date;
      expiryDate.addYear(numYears);
      pointBalance = 0; 
   }
   
   // accessor 
   public Date getExpiryDate() {
      return expiryDate;
   }
   
   public int getPointBalance () {
      return pointBalance; 
   }
   
   // method
   public void renew (int numYears) {
      expiryDate.addYear(numYears);
   }
   
   public boolean checkValid() {
      return expiryDate.compareToDays(Date.getCurrentDate()) > 0; 
   }  
}