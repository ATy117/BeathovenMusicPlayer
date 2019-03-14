package comparatorServices;

import model_rework.Song;

public class SongComparatorByArtist implements SongComparator {
    private static SongComparatorByArtist instance;
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getArtist_name().compareTo(o2.getArtist_name());
    }

    public static SongComparatorByArtist getInstance(){
        if(instance == null){
            synchronized (SongComparatorByArtist.class) {
                if(instance == null){
                    instance = new SongComparatorByArtist();
                }
            }
        }
        return instance;
    }
}
