package populatorServices;

import model_rework.Song;

import java.util.List;

public interface PopulateSongs<T> {
    List<T> populateListView (List<Song> songlist);
}
