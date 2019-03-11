package comparatorServices;

import model_rework.Song;

public class SongComparatorByYear implements SongComparator {

    @Override
    public int compare(Song o1, Song o2) {
        return o1.getYear() - o2.getYear();
    }
}
