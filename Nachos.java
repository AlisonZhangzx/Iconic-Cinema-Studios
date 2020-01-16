public class Nachos extends Snack {
   private static final double PRICE = 10;
   
   public Nachos () {
      super (PRICE); 
   }
   
   public String toString () {
      return "Nachos " + "\t\t\t\t\t$" + price; 
   }
   
   public String snackName() {
      return "Nachos"; 
   }
}
