import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Rick Astley", "Never Gonna Give You Up", 1987)
      };

   @Test
   public void testArtistComparator()
   {
      ArtistComparator ac = new ArtistComparator();
      Song song1 = songs[2];
      Song song2 = songs[5];
      assertTrue(ac.compare(song1, song2) < 0);
   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> comp = (Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle());
      Song song1 = songs[0];
      Song song2 = songs[4];
      assertTrue(comp.compare(song1, song2) > 0);
   }

   @Test
   public void testComposedComparator()
   {
      Song FFBS = songs[5];
      Song GRBS98 = songs[3];
      Song GRBS78 = songs[7];
      Comparator<Song> byTitle = (Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle());
      Comparator<Song> byArtist = (Song s1, Song s2) -> s1.getArtist().compareTo(s2.getArtist());
      Comparator<Song> byYear = (Song s1, Song s2) -> s1.getYear() - s2.getYear();
      ComposedComparator ccTA = new ComposedComparator(byTitle, byYear);
      assertTrue(ccTA.compare(FFBS, GRBS98) < 0);
      ComposedComparator ccTAY = new ComposedComparator(ccTA, byYear);
      assertTrue(ccTAY.compare(GRBS98, GRBS78) > 0);
   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> ccT = (Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle());
      Comparator<Song> ccTA = ccT.thenComparing((Song s1, Song s2) -> s1.getArtist().compareTo(s2.getArtist()));
      Song FFBS = songs[5];
      Song GRBS98 = songs[3];
      Song GRBS78 = songs[7];
      assertTrue(ccTA.compare(FFBS, GRBS98) < 0);
      Comparator<Song> ccTAY = ccTA.thenComparing((Song s1, Song s2) -> s1.getYear() - s2.getYear());
      assertTrue(ccTAY.compare(GRBS98, GRBS78) > 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      Comparator<Song> artistComp = (Song s1, Song s2) -> s1.getArtist().compareTo(s2.getArtist());
      Comparator<Song> artistTitleComp = artistComp.thenComparing((Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle()));
      Comparator<Song> comp = artistTitleComp.thenComparing((Song s1, Song s2) -> s1.getYear() - s2.getYear());

      songList.sort(
         (Song s1, Song s2) -> s1.getYear() - s2.getYear()
      );
      printList(songList);
   }

   private static void printList(List<Song> songList)
   {
      int i = 0;
      System.out.println("");
      for (Song song : songList)
      {
         System.out.println(i + ": " + song);
         i++;
      }
   }
}
