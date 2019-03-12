package comparatorServices;

import model_rework.Song;

public class SongComparatorByGenre implements SongComparator {
    private static SongComparatorByGenre instance;
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getGenre().compareTo(o2.getGenre());
    }

    public static SongComparatorByGenre getInstance(){
        if(instance == null){
            synchronized (SongComparatorByGenre.class) {
                if(instance == null){
                    instance = new SongComparatorByGenre();
                }
            }
        }
        return instance;
    }
}
