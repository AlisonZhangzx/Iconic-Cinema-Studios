public class ChickenWings extends Snack {
   private static final double PRICE = 20;
   
   public ChickenWings () {
      super (PRICE); 
   }
   
   public String toString () {
      return "Chicken Wings" + "\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return "ChickenWings"; 
   }
}
