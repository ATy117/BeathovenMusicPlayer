package comparatorServices;

import model_rework.Song;

public class SongComparatorByTitle implements SongComparator {
    private static SongComparatorByTitle instance;
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getSong_name().compareTo(o2.getSong_name());
    }

    public static SongComparatorByTitle getInstance(){
        if(instance == null){
            synchronized (SongComparatorByTitle.class) {
                if(instance == null){
                    instance = new SongComparatorByTitle();
                }
            }
        }
        return instance;
    }
}
