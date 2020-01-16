public class Showtime {
   private Movie movie;
   private Auditorium auditorium;
   private Session session;
   private Date date;
   private String showType;
   
   public Showtime (Movie movie, Auditorium auditorium, Session session, Date date, String showType) {
      this.movie = movie;
      this.auditorium = auditorium;
      this.session = session;
      this.date = date;
      this.showType = showType;
   }

   public Movie getMovie() {
      return movie;
   }

   public Auditorium getAuditorium() {
      return auditorium;
   }
   
   public Session getSession() {
      return session;
   }

   public Date getDate() {
      return date;
   }

   public String getShowType() {
      return showType;
   }
   
   public String toString() {
      return "Movie: " + movie.getTitle() + "\nAuditorium: " + auditorium.getAuditoriumNum() + "\nSession: " + session.getSession() + "\nDate: " + date +  "\nShow Type: " + showType + "\n";
   }
}