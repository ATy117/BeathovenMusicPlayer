package comparatorServices;

import model_rework.Song;

public class SongComparatorByYear implements SongComparator {
    private static SongComparatorByYear instance;
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getYear() - o2.getYear();
    }

    public static SongComparatorByYear getInstance(){
        if(instance == null){
            synchronized (SongComparatorByYear.class) {
                if(instance == null){
                    instance = new SongComparatorByYear();
                }
            }
        }
        return instance;
    }
}
