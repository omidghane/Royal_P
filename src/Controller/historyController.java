package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type History controller.
 */
public class historyController {

    /**
     * The Cards.
     */
    ArrayList<String> cards = new ArrayList<>();

    /**
     * The User.
     */
    User user;

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private ListView<String> listView;

    /**
     * Back.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        Media media = new Media(new File("C:\\Users\\HUAWEI\\IdeaProjects\\royal_A\\src\\sounds\\src_sounds_Mouse-Click-03-c-FesliyanStudios.com.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Stage stage = (Stage) listView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/menu.fxml"));
        loader.load();
        menuController menuController = loader.getController();
        menuController.setUser(this.user);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Update.
     *
     * @param event the event
     */
    @FXML
    void update(ActionEvent event) {
        Media media = new Media(new File("C:\\Users\\HUAWEI\\IdeaProjects\\royal_A\\src\\sounds\\src_sounds_zapsplat_sport_gold_ball_knock_into_hole_cup_001_64188.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        listView.getItems().addAll(user.getWinLoss());
    }

}
