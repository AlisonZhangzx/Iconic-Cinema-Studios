public class Seat {
   private int seatNumber;
   private int rowNumber;
   private boolean available;
   private boolean preferred;

   public Seat (int rowNumber, int seatNumber){
      this.seatNumber = seatNumber;
      this.rowNumber = rowNumber;
      available = true;
      preferred = false;
   }
   
   public boolean getPreferred () {
      return preferred; 
   }

   public boolean getAvailability() {
      return available;
   }

   // true = available, false = taken
   public void setAvailability(boolean available) {
      this.available = available;
   }
   
   public void setPreferred (boolean prefer) {
      preferred = prefer; 
   }
}