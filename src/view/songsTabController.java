package view;

import com.jfoenix.controls.JFXListView;

import java.awt.*;

public class songsTabController {

	public JFXListView songsListView;

	public void initialize() {
		Label n = new Label("Hi");

		for(int i=0; i<30; i++ ) {
			songsListView.getItems().add(n);
		}
	}
}
