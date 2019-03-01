package model_rework;

import dbservice.PlaylistDAO;

public class DashboardModel extends Model {
    private Dashboard dashboardState;

    public DashboardModel(User user){
        dashboardState = new Dashboard((GuestUser)user);
    }

    public Dashboard getDashboardState() {
        return dashboardState;
    }

    public void setDashboardState(Dashboard dashboardState) {
        this.dashboardState = dashboardState;
    }

    public void addPlaylist(Playlist p){
        PlaylistDAO pd = dashboardState.getPlaylistDAO();
        pd.addPlaylist(p);
        Notify();
    }


}
