class Pizza extends Snack {
   private static double PRICE_CHES = 10; 
   private static double PRICE_HWIN = 15; 
   private static double PRICE_PPRN = 10; 
   private static String CHEESE = "Cheese"; 
   private static String HAWAIIAN = "Hawaiian"; 
   private static String PEPPERONI = "Pepperoni"; 
   private String type; 
   
   public Pizza () {
      super(0); 
   }
   
   public void setType (String s) {
      type = s; 
      if (type.equals(CHEESE)) {
         price = PRICE_CHES; 
      } else if (type.equals(HAWAIIAN)) {
         price = PRICE_HWIN; 
      } else {
         price = PRICE_PPRN; 
      }
   }
   
   // public void setPrice () {
//       if (type.equals(CHEESE)) {
//          price = PRICE_CHES; 
//       } else if (type.equals(HAWAIIAN)) {
//          price = PRICE_HWIN; 
//       } else {
//          price = PRICE_PPRN; 
//       }
//    }
   
   public String toString () {
      return type + "\t\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return type + "Pizza"; 
   }
   
}

////////////////////////////////// /// NOTICE /// ////////////////////////////////////////////
// 1, pls make sure the input is from gui buttons and can only choose among constants       //
// 2, make sure setType(String) is called before setPrice(), both methods have to be called //
// 3, first letter of type should be capitalized                                            //
//////////////////////////////////////////////////////////////////////////////////////////////

// done // 6-3-2019 // Alison //