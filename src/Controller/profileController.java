package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class profileController {

    User user;

    public void setUser(User user) {

        this.user = user;
    }

    @FXML
    private TextField nameText;

    @FXML
    private TextField levelText;

    @FXML
    private Label xpText;

    @FXML
    private ImageView slot1;

    @FXML
    private ImageView slot2;

    @FXML
    private ImageView slot3;

    @FXML
    private ImageView slot4;

    @FXML
    private ImageView slot5;

    @FXML
    private ImageView slot6;

    @FXML
    private ImageView slot7;

    @FXML
    private ImageView slot8;

    @FXML
    private Button backButton;

    @FXML
    private Button updateProfileButton;

    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        Media media = new Media(new File("D:\\Java Projects\\Royal_P\\src\\" +
                "sounds\\Mouse-Click-03-c-FesliyanStudios.com.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/menu.fxml"));
        loader.load();
        menuController menuController = loader.getController();
        menuController.setUser(user);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void updatingProfile(ActionEvent event) {
        Media media = new Media(new File("D:\\Java Projects\\Royal_P\\src\\sounds" +
                "\\zapsplat_sport_gold_ball_knock_into_hole_cup_001_64188.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        String name = user.getUsername();
        if (Files.exists(Path.of(name))) {
            try (FileInputStream fi = new FileInputStream(name)) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fi);
                User theUser = (User) objectInputStream.readObject();
                nameText.setText(theUser.getUsername());
                xpText.setText(String.valueOf(theUser.getXP()));
                if ((theUser.getXP()) < 200) {
                    levelText.setText("1");
                } else if (theUser.getXP() > 200 && theUser.getXP() < 400) {
                    levelText.setText("2");
                } else if (theUser.getXP() > 400 && theUser.getXP() < 600) {
                    levelText.setText("3");
                } else if (theUser.getXP() > 600 && theUser.getXP() < 800) {
                    levelText.setText("4");
                } else if (theUser.getXP() > 800 && theUser.getXP() < 1000) {
                    levelText.setText("5");
                }
                setDeck(theUser.getCardNamesInString().get(0), slot1);
                setDeck(theUser.getCardNamesInString().get(1), slot2);
                setDeck(theUser.getCardNamesInString().get(2), slot3);
                setDeck(theUser.getCardNamesInString().get(3), slot4);
                setDeck(theUser.getCardNamesInString().get(4), slot5);
                setDeck(theUser.getCardNamesInString().get(5), slot6);
                setDeck(theUser.getCardNamesInString().get(6), slot7);
                setDeck(theUser.getCardNamesInString().get(7), slot8);


            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

//        int Xp = user.getXP();
//        int level = user.getGrade();
//        System.out.println(name);
//        nameText.setText(name);
//        xpText.setText(String.valueOf(Xp));
//        levelText.setText(String.valueOf(level));
    }

    public void setDeck(String str, ImageView slot) {
        Image archer = new Image("/photoes/ArchersCard.jpg");
        Image arrow = new Image("/photoes/ArrowsCard.jpg");
        Image dragon = new Image("/photoes/BabyDragonCard.jpg");
        Image barbar = new Image("/photoes/BarbariansCard.jpg");
        Image cannon = new Image("/photoes/CannonCard.jpg");
        Image pekka = new Image("/photoes/Card_icon_Mini_P.E.K.K.A..jpg");
        Image fireball = new Image("/photoes/FireballCard.jpg");
        Image giant = new Image("/photoes/GiantCard.jpg");
        Image inferno = new Image("/photoes/InfernoTowerCard.jpg");
        Image rage = new Image("/photoes/RageCard.jpg");
        Image valkery = new Image("/photoes/ValkyrieCard.jpg");
        Image Wizard = new Image("/photoes/WizardCard.jpg");
        if (str.equals("barbarian")) {
            slot.setImage(barbar);
        } else if (str.equals("archer")) {
            slot.setImage(archer);
        } else if (str.equals("arrow")) {
            slot.setImage(arrow);
        } else if (str.equals("babyDragon")) {
            slot.setImage(dragon);
        } else if (str.equals("cannon")) {
            slot.setImage(cannon);
        } else if (str.equals("pekka")) {
            slot.setImage(pekka);
        } else if (str.equals("fireBall")) {
            slot.setImage(fireball);
        } else if (str.equals("giant")) {
            slot.setImage(giant);
        } else if (str.equals("inferno")) {
            slot.setImage(inferno);
        } else if (str.equals("rage")) {
            slot.setImage(rage);
        } else if (str.equals("valkyrie")) {
            slot.setImage(valkery);
        } else if (str.equals("wizard")) {
            slot.setImage(Wizard);
        }

    }

}


