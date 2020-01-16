

public class Movie {
   private String title, rating, director, actors, imageAddress;

   public Movie(String title, String rating, String director, String actors, String imageAddress) {
      this.title = title;
      this.rating = rating;
      this.director = director;
      this.actors = actors;
      this.imageAddress = imageAddress;
   }

   public String getTitle() {
      return title;
   }

   public String getRating() {
      return rating;
   }
   
   public String getDirector() {
      return director;
   }
   
   public String getActors() {
      return actors;
   }
   
   public String getImageAddress() {
      return imageAddress;
   }
   
   public String toString() {
      return "\nTitle: " + title + "\nRating: " + rating + "\nDirector: " + director + "\nActor: " + actors;
   }
   
}