class Popcorn extends Snack {
   private static final double PRICE_BUTR = 5.5;
   private static final double PRICE_CRML = 7; 
   private static final double PRICE_PLIN = 5; 
   private static final String BUTTER = "Butter"; 
   private static final String CARAMEL = "Caramel"; 
   private static final String PLAIN = "Plain"; 
   private String type; 
   
   public Popcorn () {
      super(0); 
   }
   
   public void setType (String s) {
      type = s; 
      if (type.equals(BUTTER)) {
         price = PRICE_BUTR; 
      } else if (type.equals(CARAMEL)) {
         price = PRICE_CRML; 
      } else {
         price = PRICE_PLIN; 
      }
   }
   
   // public void setPrice () {
//       if (type.equals(BUTTER)) {
//          price = PRICE_BUTR; 
//       } else if (type.equals(CARAMEL)) {
//          price = PRICE_CRML; 
//       } else {
//          price = PRICE_PLIN; 
//       }
//    }
   
   public String toString () {
      return type + "\t\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return type + "Popcorn";
   }
   
}

////////////////////////////////// /// NOTICE /// ////////////////////////////////////////////
// 1, pls make sure the input is from gui buttons and can only choose among constants       //
// 2, make sure setType(String) is called before setPrice(), both methods have to be called //
// 3, first letter of type should be capitalized                                            //
//////////////////////////////////////////////////////////////////////////////////////////////

// done // 6-3-2019 // Alison //