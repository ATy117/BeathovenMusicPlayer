package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class loginPageView {
    public JFXButton loginBtn;

    public void sayHi(ActionEvent actionEvent) throws IOException{
        if(actionEvent.getSource() == loginBtn){
            System.out.println("LogIn!");
            Parent dashboard = FXMLLoader.load(getClass().getResource("dashboardTemplate.fxml"));

            Stage myStage = (Stage) loginBtn.getScene().getWindow();
            myStage.setScene(new Scene(dashboard));
        }
    }

}
