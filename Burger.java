public class Burger extends Snack {
   private static final double PRICE = 20;
   
   public Burger () {
      super (PRICE); 
   }
   
   public String toString () {
      return "Burger " + "\t\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return "Burger"; 
   }
}
