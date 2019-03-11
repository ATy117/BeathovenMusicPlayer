package comparatorServices;

import model_rework.Song;

public class SongComparatorByArtist implements SongComparator {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getArtist_name().compareTo(o2.getArtist_name());
    }
}
