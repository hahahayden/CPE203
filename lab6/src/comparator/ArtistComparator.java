import java.util.Comparator;
public class ArtistComparator implements Comparator<Song> {
    public int compare(Song a, Song b){
        int result= a.getArtist().compareTo(b.getArtist());
        if (result!=0){
            return result;
        }
        return 0;



    }

}
