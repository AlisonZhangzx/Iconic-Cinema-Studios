public class Session {
   private static final String MORNING = "morning";
   private static final String NOON = "noon";
   private static final String AFTERNOON = "afternoon";
   private static final String EVENING = "evening";
   private static final String MIDNIGHT = "midnight";
   private String session;

   public Session(String session){
      this.session = session;
   }

   public void setSession(String session) {
      this.session = session;
   }

   public String getSession() {
      return session;
   }
}