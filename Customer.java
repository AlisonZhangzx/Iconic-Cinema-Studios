/*
   Group Members: Alison Zhang, Kevin Huang, Shairagavi Selvachandran, Sunny Lin
   Group Name: Iconic Cinema Studios (ICS)
   Description: Customer class
*/

public class Customer extends Person{
   private Membership membership;
   private boolean isMember; 
   private Date birthDate; 
   
   // constructor
   public Customer (String first, String last, String user, String pass, long id, String birthdate) {
      super(first, last, user, pass, id);
      birthDate = new Date (birthdate); 
      membership = new Membership(Date.getCurrentDate(), 0); 
      isMember = membership.checkValid(); 
   }
   
   // accessor 
   public Membership getMembership () {
      return membership; 
   }
   
   public boolean getIsMember () {
      return isMember; 
   }
   
   public Date getBirthDate () {
      return birthDate; 
   }
   
   // mutator 
   public void setMembership (int numYear) {
      membership = new Membership (Date.getCurrentDate(), numYear); 
   }
   
   
   
   // methods
   public void displayOptions (){
      TheatreGUI.showText("customer options");
   }
   
   
   public String toString () {
      return super.toString() + "\nBirthday: " + birthDate.toString();
   }

}