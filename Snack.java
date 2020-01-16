class Snack {
   protected double price; 
   
   public Snack (double num) {
      price = num; 
   }
   
   public double getPrice() {
      return price; 
   } 
   
   // public String toString () {
//       return "\t\t\t" + price; 
//    }
    
   //abstract public String snackName (); 
   //abstract public String toString(); 
   public String snackName () {
      return "Snack"; 
   }
   
   
   public void setType (String s) {
   
   }
}