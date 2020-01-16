class Drink extends Snack {
   private static final double PRICE_COKE = 5; 
   private static final double PRICE_WATR = 3; 
   private static final double PRICE_JUCE = 5;
   private static final String COKE = "Coke"; 
   private static final String WATER = "Water"; 
   private static final String JUICE = "Juice"; 
   private String type; 
   
   public Drink () {
      super(0); 
   }
   
   public void setType (String s) {
      type = s;
      if (type.equals(COKE)) {
         price = PRICE_COKE; 
      } else if (type.equals(WATER)) {
         price = PRICE_WATR; 
      } else if (type.equals(JUICE)) {
         price = PRICE_JUCE; 
      } 
   }
      
   // private boolean underAge (Date birthDate) {
//       return Date.getCurrentDate().compareToFullYear(birthDate) > DRINK_AGE; 
//    }
   
   // public void setPrice () {
//       if (type.equals(COKE)) {
//          price = PRICE_COKE; 
//       } else if (type.equals(WATER)) {
//          price = PRICE_WATR; 
//       } else if (type.equals(WINE)) {
//          price = PRICE_WINE; 
//       } else {
//          price = 0; 
//       }
//    }
   
   public String toString () {
      return type + "\t\t\t\t$" + price; 
   }
   
   public String snackName () {
      return type; 
   }
   
}

////////////////////////////////// /// NOTICE /// ///////////////////////////////////////////////////////////////////////
// 1, pls make sure the input is from gui buttons and can only choose among constants                                  //
// 2, make sure to call in order: setBirthDate(Date) -> setType(String) -> setPrice(), all 3 methods have to be called //
// 3, first letter of type should be capitalized                                                                       //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// done // 6-3-2019 // Alison //