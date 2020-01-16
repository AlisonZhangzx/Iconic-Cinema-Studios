public class HotDog extends Snack {
   private static final double PRICE = 3.5;
   
   public HotDog () {
      super (PRICE); 
   }
   
   public String toString () {
      return "Hot Dog" + "\t\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return "HotDog"; 
   }

}
