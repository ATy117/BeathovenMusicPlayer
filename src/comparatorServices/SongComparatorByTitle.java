package comparatorServices;

import model_rework.Song;

public class SongComparatorByTitle implements SongComparator {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getSong_name().compareTo(o2.getSong_name());
    }
}
