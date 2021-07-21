package Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class menuController {

    User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button myProfileButton;

    @FXML
    private Button deckButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button trainingButtton;

    @FXML
    private Button Play1v1Button;

    @FXML
    void deckView(ActionEvent event) throws IOException, InterruptedException {
        Media media = new Media(new File("D:\\Java Projects\\Royal_P\\src\\sounds\\Mouse-Click-03-c-FesliyanStudios.com.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        Stage stage = (Stage) deckButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/deck.fxml"));
        loader.load();
        deckController deckController = loader.getController();
        deckController.setUser(user);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("deck");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void historyView(ActionEvent event) {
        System.out.println("history");

    }

    @FXML
    void play1v1View(ActionEvent event) {
        System.out.println("1v1");


    }

    @FXML
    void profileView(ActionEvent event) throws IOException {
        Media media = new Media(new File("D:\\Java Projects\\Royal_P\\src\\sounds\\Mouse-Click-03-c-FesliyanStudios.com.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        Stage stage = (Stage) myProfileButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/profile.fxml"));
        loader.load();
        profileController profileController = loader.getController();
        profileController.setUser(user);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("deck");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void trainingCampView(ActionEvent event) throws IOException {
        System.out.println(user.getUsername() + " l");
        System.out.println("trining");
        Stage stage = (Stage) trainingButtton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/Map.fxml"));
        loader.load();
        MapCon mapCon = loader.getController();
        mapCon.setUser(user);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("play");
        stage.setResizable(true);
        stage.show();

    }


}
