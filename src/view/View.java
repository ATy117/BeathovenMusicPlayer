package view;

import controller.Controller;
import controller.MasterController;
import javafx.stage.Stage;

public abstract class View {

	public MasterController controller;
	public Stage stage;

	public abstract void Update();
}
