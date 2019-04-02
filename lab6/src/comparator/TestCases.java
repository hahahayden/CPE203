import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

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
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      Song[]test=new Song[]{songs[1],songs[0]};    // Decemberists compared to Rogue Wave

      Arrays.sort(test,new ArtistComparator());    // sorts it
      Comparator<Song> myCompare = new ArtistComparator();

      assertTrue(myCompare.compare(songs[0],songs[1])<0);    // should be less than becasue d is after r
       Song[] song=new Song[]{songs[0],songs[1]};
      assertEquals(test,song);

      Song[]test2=new Song[]{songs[6],songs[4]};    // queen vs city and colour

      Arrays.sort(test2,new ArtistComparator());    // sorts it
      Comparator<Song> myCompare2 = new ArtistComparator();

      assertTrue(myCompare.compare(songs[0],songs[1])<0);    // should be less than becasue d is after r
      Song[] song2=new Song[]{songs[4],songs[6]};
      assertEquals(test2,song2);

   }

   @Test
   public void testLambdaTitleComparator()
   {
   Comparator <Song> compTitle= (Song s1, Song s2)-> s1.getTitle().compareTo(s2.getTitle());  // function kinda

//   Song[]test1= new Song[] {songs[2],songs[7]};
//   Arrays.sort (test1,compTitle);
      List<Song>test1=new ArrayList<> ();
      test1.add(songs[2]);
      test1.add(songs[7]);
      test1.sort(compTitle);
//   Song[]result1= new Song[] {songs[7],songs[2]};
      List<Song>result1=new ArrayList<> ();
      result1.add(songs[7]);
      result1.add(songs[2]);
   assertEquals(test1,result1);

   Song[] test2 = new Song[] { songs[0], songs[1] };
   Arrays.sort(test2, compTitle);
   Song[] result2 = new Song[] { songs[1], songs[0] };
   assertEquals(test2, result2);

   Song[] test3 = new Song[] { songs[1], songs[6] };
   Arrays.sort(test3, compTitle);
   Song[] result3 = new Song[] { songs[6], songs[1] };
   assertEquals(test3, result3);

   assertTrue(compTitle.compare(songs[2],songs[7])>0);
   assertEquals(compTitle.compare(songs[3],songs[7]),0);
   assertTrue(compTitle.compare(songs[1],songs[0])<0);

   }

   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> compYear = Comparator.comparing(Song::getYear, (s1,s2) -> s2.compareTo(s1));  // it takes the value in comapres it; if s2 greater than s1 returns >0 and so it doesn't sort

      //      compYear.reversed();
//      Comparator <Song> compYear= (Song s1, Song s2)-> s1.getYear().comparingInt(s2.getYear());  // function kinda

      Song[] test2 = new Song[] { songs[2], songs[7] };
      Arrays.sort(test2, compYear);
      Song[] result2 = new Song[] { songs[2], songs[7] };
      assertEquals(test2, result2);

      Song[] test22 = new Song[] { songs[7], songs[3] };
      Arrays.sort(test22, compYear);
      Song[] result22 = new Song[] { songs[3], songs[7] };
      assertEquals(test22, result22);

      Song[] test222 = new Song[] { songs[5], songs[0] };
      Arrays.sort(test222, compYear);
      Song[] result222 = new Song[] { songs[0], songs[5] };
      assertEquals(test222, result222);
      assertTrue(compYear.compare(songs[2],songs[7])<0);
      assertTrue(compYear.compare(songs[7],songs[6])<0);
      assertEquals(compYear.compare(songs[0],songs[1]),0);
   }

   @Test
   public void testComposedComparator()
   {
       Comparator<Song> compareYear = Comparator.comparing(Song::getYear);//, (s1,s2) -> s1.compareTo(s2));

      Comparator<Song> compareTitle = (Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle());
      // Test1
      Comparator<Song> artistThenYear = new ComposedComparator(new ArtistComparator(), compareYear);

       assertTrue(artistThenYear.compare(songs[2],songs[1])<0);
       assertTrue(artistThenYear.compare(songs[6],songs[7])>0);

      Song[] songList1 = new Song[] {songs[3], songs[7]};
//      Arrays.sort(songList1, compareYear.thenComparing(compareTitle));
      Arrays.sort(songList1, artistThenYear);
      Song[] expected1 = new Song[] {songs[7], songs[3]};
      assertEquals(expected1, songList1);

      //Test2
      Comparator<Song> titleThenArtist = new ComposedComparator(compareTitle, new ArtistComparator());
      Comparator<Song> myCompare2 = new ArtistComparator();
      Song[] songList2 = new Song[] {songs[3], songs[5]};
      Arrays.sort(songList2,titleThenArtist);
      Song[] expected2 = new Song[] {songs[5], songs[3]};
      assertEquals(expected2, songList2);

      //Test3
      Comparator<Song> yearThenTitle = new ComposedComparator(compareYear, compareTitle);
      Song[] songList3 = new Song[] {songs[1], songs[0]};
      Arrays.sort(songList3, yearThenTitle);
      Song[] expected3 = new Song[] {songs[1], songs[0]};
      assertEquals(expected3, songList3);
   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> titleThenArtist = Comparator.comparing(Song::getTitle, (s1,s2) -> s1.compareTo(s2))
              .thenComparing(new ArtistComparator());

      Song[] list1 = new Song[] {songs[0], songs[1]};
      Arrays.sort(list1, titleThenArtist);
      Song[] list2 = new Song[] {songs[1], songs[0]};
      assertEquals(list2, list1);

      Song[] list4 = new Song[] {songs[3], songs[6]};

      Arrays.sort(list1, titleThenArtist);
      Song[] list3 = new Song[] {songs[3], songs[6]};
      assertEquals(list4, list3);

       assertTrue(titleThenArtist.compare(songs[2],songs[7])>0);
       assertTrue(titleThenArtist.compare(songs[7],songs[6])<0);


   }

   @Test
   public void runSort()
   {
//      Comparator<Song> test6 = Comparator.comparing(Song::getArtist, (s1,s2) -> s1.compareTo(s2))
//              .thenComparing(Song::getTitle, (s1,s2) -> s1.compareTo(s2))
//              .thenComparing(Song::getYear, (s1,s2) -> s1.compareTo(s2));
      Comparator<Song> test6 = Comparator.comparing(Song::getArtist, (s1,s2) -> s1.compareTo(s2))
              .thenComparing(Song::getTitle, (s1,s2) -> s1.compareTo(s2))
              .thenComparing(Song::getYear, (s1,s2) -> s1.compareTo(s2));
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Gerry Rafferty", "Baker Street", 1978),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
      );


      songList.sort(test6);  // .sort() for arraylist  different from array

      assertEquals(songList, expectedList);

   }
}
