public class Fries extends Snack {
   private static final double PRICE = 5;
   
   public Fries () {
      super (PRICE); 
   }
   
   public String toString () {
      return "Fries" + "\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return "Fries"; 
   }
}