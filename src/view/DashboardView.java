package view;

import com.jfoenix.controls.JFXButton;
import controller.DashboardController;
import controller.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model_rework.*;

public class DashboardView extends View {

	private SongPlayerModel songplayermodel;
	private LibraryModel librarymodel;
	private ProfileModel profilemodel;

	@FXML JFXButton myProfileBtn;
	@FXML JFXButton uploadAddSongsBtn;

	public DashboardView (Stage stage, SongPlayerModel songplayermodel, LibraryModel librarymodel, DashboardController controller) {

		super(controller);
		this.songplayermodel = songplayermodel;
		this.librarymodel = librarymodel;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(stage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven");
	}

	public DashboardView (Stage stage, SongPlayerModel songplayermodel, LibraryModel librarymodel, ProfileModel profilemodel, DashboardController controller) {

		super(controller);
		this.songplayermodel = songplayermodel;
		this.librarymodel = librarymodel;
		this.profilemodel = profilemodel;
		this.stage = stage;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardTemplate.fxml"));
		loader.setController(this);

		StageManager sm = new StageManager(stage);
		sm.loadScene(loader);
		sm.setWindowName("Beathoven");
	}

	@Override
	public void Update() {

	}

	public void changePane(ActionEvent actionEvent) {
		if (actionEvent.getSource() == myProfileBtn) {
			controller.viewProfile();
		}
		else if (actionEvent.getSource() == uploadAddSongsBtn) {
			controller.uploadSong();
		}
	}

	public void createPlaylist(ActionEvent actionEvent) {
		controller.sayHi();
	}
}

