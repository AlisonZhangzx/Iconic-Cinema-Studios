/*
   Group Members: Alison Zhang, Kevin Huang, Shairagavi Selvachandran, Sunny Lin
   Group Name: Iconic Cinema Studios (ICS)
   Description: abstract Person class
*/

abstract class Person {
   protected String first, last, user, pass;
   protected long id;
         
   // constructor
   public Person (String first, String last, String user, String pass, long id) {
      this.first = first;
      this.last = last;
      this.user = user;
      this.pass = pass;
      this.id = id;
   }
   
   public String getFirst () {
      return first;
   }
   
   public String getLast () {
      return last;
   }
   
   public String getUser () {
      return user;
   }
   
   public String getPass () {
      return pass;
   }
   
   public Long getId () {
      return id;
   }
   
   public String toString () {
      return "Name: " + first + " " + last + "\nUsername: " + user + "\nPassword: " + pass + "\nID: " + id;
   }
   
}