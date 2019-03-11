package comparatorServices;

import model_rework.Song;

public class SongComparatorByGenre implements SongComparator {

    @Override
    public int compare(Song o1, Song o2) {
        return o1.getGenre().compareTo(o2.getGenre());
    }
}
