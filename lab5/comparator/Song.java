public class Song
{
   private String artist;
   private String title;
   private int year;

   public Song(String artist, String title, int year)
   {
      this.artist = artist;
      this.title = title;
      this.year = year;
   }

   public String getArtist()
   {
      return artist;
   }

   public String getTitle()
   {
      return title;
   }

   public int getYear()
   {
      return year;
   }

   public String toString()
   {
      return "\"" + title + "\" by " + artist + " (" + year + ")";
   }
}

