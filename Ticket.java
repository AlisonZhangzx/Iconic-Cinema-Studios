public class Ticket {
   private static final double PRICE_REG = 10;
   private static final double ADD_PRICE_3D = 2;
   private static final double ADD_PRICE_IMAX = 5;
   private static final double ADD_PRICE_PREFER = 5; 
   private static final String TYPE_REG = "regular";
   private static final String TYPE_3D = "3D";
   private static final String TYPE_IMAX = "IMAX";
   private Showtime showtime;
   private Seat seat;
   private double price;
   
   public Ticket (Showtime showtime, Seat seat) {
      this.showtime = showtime;
      this.seat = seat;
   }

   public double calculatePrice() {     
      double result = PRICE_REG; 
      String showtype = showtime.getShowType(); 
      if (seat.getPreferred()) {
         result += ADD_PRICE_PREFER; 
      } 
      if (showtype.equals(TYPE_3D)) {
         result += ADD_PRICE_3D; 
      } else if (showtype.equals(TYPE_IMAX)) {
         result += ADD_PRICE_IMAX; 
      }
      return result; 
   }

   public void printTicket() {
   
   }
}