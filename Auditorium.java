public class Auditorium {
   private int auditoriumNum;
   private int totalSeats;
   private int availableSeats;
   public Seat[][] seats;
   
   public Auditorium(int auditoriumNum, int numRows, int numColumns) {
      this.auditoriumNum = auditoriumNum;
      this.totalSeats = numRows * numColumns;
      this.availableSeats = totalSeats;
      seats = new Seat[numRows][numColumns];
      
      for (int r = 0; r < numRows; r++) {
         for (int c = 0; c < numColumns; c++) {
            seats[r][c] = new Seat(r, c); 
         }
      } 
   }
   
   public int getAuditoriumNum() {
      return auditoriumNum;
   }
   
   public int getTotalSeats() {
      return totalSeats;
   }
   
   public int getNumRows() {
      return seats.length;
   }
   
   public int getNumColumns() {
      return seats[0].length;
   }
   
   public void setSeats(boolean[][] availability) {
      for (int r = 0; r < seats.length; r++) {
         for (int c = 0; c < seats[r].length; c++) {
            seats[r][c].setAvailability(availability[r][c]);
         }
      }
   }
   
   public void setSeat(int row, int col, boolean taken) {
      seats[row][col].setAvailability(taken);
   }
   
   public String toString() {
      return "Auditorium Number: " + auditoriumNum + "\nTotal Seats: " + totalSeats + "\n";
   }
   
   public boolean[][] getSpots() {
      boolean[][] result = new boolean[seats.length][seats[0].length];
      for (int r = 0; r < seats.length; r++) {
         for (int c = 0; c < seats[0].length; c++) {
               result[r][c] = seats[r][c].getAvailability();
         }
      }
      return result;
   }
   
   public void printSeats() {
      for (int r = 0; r < seats.length; r++) {
         for (int c = 0; c < seats[0].length; c++) {
            if (seats[r][c].getAvailability()) {
               System.out.print("O");
            }
            else {
               System.out.print("X");
            }
         }
         System.out.println();
      }
   }
}