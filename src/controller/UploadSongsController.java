package controller;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class UploadSongsController {

    public void uploadFile(ActionEvent actionEvent) {

        String pathname = parent.appdirectory + "/Music";
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
}
