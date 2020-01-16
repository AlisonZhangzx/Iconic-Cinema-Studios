class Candy extends Snack {
   private static final double PRICE_SKTL = 7;
   private static final double PRICE_MNRD = 3; 
   private static final double PRICE_MNM = 5; 
   private static final String SKITTLE = "Skittles"; 
   private static final String MAYNARD = "Maynard"; 
   private static final String MNM = "M & M"; 
   private String type; 
   
   public Candy () {
      super(0); 
   }
   
   public void setType (String s) {
      type = s; 
      if (type.equals(SKITTLE)) {
         price = PRICE_SKTL; 
      } else if (type.equals(MAYNARD)) {
         price = PRICE_MNRD; 
      } else {
         price = PRICE_MNM; 
      }
   }
   
   // public void setPrice () {
//       if (type.equals(SKITTLE)) {
//          price = PRICE_SKTL; 
//       } else if (type.equals(MAYNARD)) {
//          price = PRICE_MNRD; 
//       } else {
//          price = PRICE_MNM; 
//       }
//    }
   
   public String toString () {
      return type + "\t\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return type + "Candy"; 
   }
   
}

////////////////////////////////// /// NOTICE /// ////////////////////////////////////////////
// 1, pls make sure the input is from gui buttons and can only choose among constants       //
// 2, make sure setType(String) is called before setPrice(), both methods have to be called //
// 3, first letter of type should be capitalized                                            //
//////////////////////////////////////////////////////////////////////////////////////////////

// done // 5-31-2019 // Alison //