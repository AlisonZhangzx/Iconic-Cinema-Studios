public class Tacos extends Snack {
   private static final double PRICE = 20;
   
   public Tacos () {
      super (PRICE); 
   }
   
   public String toString () {
      return "Tacos " + "\t\t\t\t\t$" + price; 
   }
   
   public String snackName (){
      return "Tacos"; 
   }
}