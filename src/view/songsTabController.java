package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

public class songsTabController {

	public JFXListView songsListView;
	public JFXButton fileUploadButton;

	public void initialize() {
		Label n = new Label("Hi");

		for(int i=0; i<30; i++ ) {
			songsListView.getItems().add(n);
		}
	}

	public void uploadFile(ActionEvent actionEvent) throws IOException {
		String pathname = System.getProperty("user.home") + "/Documents/Beathoven";
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select mp3 files");

		File songsDirectory = new File(pathname);
		if(!songsDirectory.exists()) {
			songsDirectory.mkdir();
		}

		File findDirectory = new File(System.getProperty("user.home")+"/Downloads");
		fileChooser.setInitialDirectory(findDirectory);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));

		Stage myStage = (Stage) fileUploadButton.getScene().getWindow();
		List<File> selectedFiles = fileChooser.showOpenMultipleDialog(myStage);

		CopyOption[] options = new CopyOption[]{
				StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES
		};

		if (selectedFiles != null) {
			for (File newsong: selectedFiles) {
				Path target = Paths.get(newsong.toURI());
				Path destination = Paths.get(pathname+ "/" + newsong.getName());
				try {
					Files.copy(target, destination, options);
				} catch (IOException ex) {
					System.out.println("File couldn't be moved");
				}
			}
		}
	}

	public void copy(String from, String to) {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(from);
			fw = new FileWriter(to);
			int c = fr.read();
			while(c!=-1) {
				fw.write(c);
				c = fr.read();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			close(fr);
			close(fw);
		}
	}
	public static void close(Closeable stream) {
		try {
			if (stream != null) {
				stream.close();
			}
		} catch(IOException e) {
			//...
		}
	}

}
