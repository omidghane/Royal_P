package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class deckController {

    User user;

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Boolean> slots = new ArrayList<>();

    private Boolean sl1 = false;
    private Boolean sl2 = false;
    private Boolean sl3 = false;
    private Boolean sl4 = false;
    private Boolean sl5 = false;
    private Boolean sl6 = false;
    private Boolean sl7 = false;
    private Boolean sl8 = false;


    private Boolean barbarianUsed = false;
    private Boolean ArcherUsed = false;
    private Boolean ArrowUsed = false;
    private Boolean giantUsed = false;
    private Boolean wizardUsed = false;
    private Boolean cannonUsed = false;
    private Boolean infernoUsed = false;
    private Boolean fireBallUsed = false;
    private Boolean pekkaUsed = false;
    private Boolean valkeryUsed = false;
    private Boolean rageUsed = false;
    private Boolean dragonUsed = false;

    public ArrayList<Boolean> getSlots() {
        return slots;
    }


    public void settingImage(Image image) {
        if (!sl1) {
            slot1.setImage(image);
            sl1 = true;
        } else if (!sl2) {
            slot2.setImage(image);
            sl2 = true;
        } else if (!sl3) {
            slot3.setImage(image);
            sl3 = true;
        } else if (!sl4) {
            slot4.setImage(image);
            sl4 = true;
        } else if (!sl5) {
            slot5.setImage(image);
            sl5 = true;
        } else if (!sl6) {
            slot6.setImage(image);
            sl6 = true;
        } else if (!sl7) {
            slot7.setImage(image);
            sl7 = true;
        } else if (!sl8) {
            slot8.setImage(image);
            sl8 = true;
        }
    }


    public void deletingSlut(ImageView slot) {
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
        if (slot.getImage().getUrl().equals(archer.getUrl())) {
            ArcherUsed = false;
        } else if (slot.getImage().getUrl().equals(arrow.getUrl())) {
            ArrowUsed = false;
        } else if (slot.getImage().getUrl().equals(dragon.getUrl())) {
            dragonUsed = false;
        } else if (slot.getImage().getUrl().equals(barbar.getUrl())) {
            barbarianUsed = false;
        } else if (slot.getImage().getUrl().equals(cannon.getUrl())) {
            cannonUsed = false;
        } else if (slot.getImage().getUrl().equals(pekka.getUrl())) {
            pekkaUsed = false;
        } else if (slot.getImage().getUrl().equals(fireball.getUrl())) {
            fireBallUsed = false;
        } else if (slot.getImage().getUrl().equals(giant.getUrl())) {
            giantUsed = false;
        } else if (slot.getImage().getUrl().equals(inferno.getUrl())) {
            infernoUsed = false;
        } else if (slot.getImage().getUrl().equals(rage.getUrl())) {
            rageUsed = false;
        } else if (slot.getImage().getUrl().equals(valkery.getUrl())) {
            valkeryUsed = false;
        } else if (slot.getImage().getUrl().equals(Wizard.getUrl())) {
            wizardUsed = false;
        }
    }

    @FXML
    private Button backButtton;

    @FXML
    private Button saveButton;

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
    void backToMenu(ActionEvent event) throws IOException {
        Media media = new Media(new File("D:\\Java Projects\\Royal_P\\src\\" +
                "sounds\\Mouse-Click-03-c-FesliyanStudios.com.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Stage stage = (Stage) backButtton.getScene().getWindow();
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

    public void savingCard(ImageView slot) {
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
        if (slot.getImage().getUrl().equals(archer.getUrl())) {
            user.addToCardNames("archer");
        } else if (slot.getImage().getUrl().equals(arrow.getUrl())) {
            user.addToCardNames("arrow");

        } else if (slot.getImage().getUrl().equals(dragon.getUrl())) {
            user.addToCardNames("babyDragon");

        } else if (slot.getImage().getUrl().equals(barbar.getUrl())) {
            user.addToCardNames("barbarian");

        } else if (slot.getImage().getUrl().equals(cannon.getUrl())) {
            user.addToCardNames("cannon");

        } else if (slot.getImage().getUrl().equals(pekka.getUrl())) {
            user.addToCardNames("pekka");

        } else if (slot.getImage().getUrl().equals(fireball.getUrl())) {
            user.addToCardNames("fireBall");

        } else if (slot.getImage().getUrl().equals(giant.getUrl())) {
            user.addToCardNames("giant");

        } else if (slot.getImage().getUrl().equals(inferno.getUrl())) {
            user.addToCardNames("inferno");

        } else if (slot.getImage().getUrl().equals(rage.getUrl())) {
            user.addToCardNames("rage");

        } else if (slot.getImage().getUrl().equals(valkery.getUrl())) {
            user.addToCardNames("valkyrie");

        } else if (slot.getImage().getUrl().equals(Wizard.getUrl())) {
            user.addToCardNames("wizard");

        }
    }

    @FXML
    void saveCards(ActionEvent event) {
        Media media = new Media(new File("D:\\Java Projects\\Royal_P\\src\\sounds\\" +
                "warfare_swords_x_2_hit_scrape_002.mp3")
                .toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        user.getCardNamesInString().clear();

        System.out.println(user.getCardNamesInString());
        savingCard(slot1);
        savingCard(slot2);
        savingCard(slot3);
        savingCard(slot4);
        savingCard(slot5);
        savingCard(slot6);
        savingCard(slot7);
        savingCard(slot8);
        System.out.println(user.getCardNamesInString());

        save(user.getUsername(), this.user);
    }

    public void save(String fileName, User theUser) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(theUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void choosingArcher(MouseEvent event) {
        Image image = new Image("/photoes/ArchersCard.jpg");
        if (!ArcherUsed) {
            settingImage(image);
        }
        ArcherUsed = true;
    }

    @FXML
    void choosingArrows(MouseEvent event) {
        Image image = new Image("/photoes/ArrowsCard.jpg");
        if (!ArrowUsed) {
            settingImage(image);
        }
        ArrowUsed = true;
    }

    @FXML
    void choosingBarbarian(MouseEvent event) {
        Image image = new Image("/photoes/BarbariansCard.jpg");
        if (!barbarianUsed) {
            settingImage(image);
        }
        barbarianUsed = true;
    }

    @FXML
    void choosingCanon(MouseEvent event) {
        Image image = new Image("/photoes/CannonCard.jpg");
        if (!cannonUsed) {
            settingImage(image);
        }
        cannonUsed = true;
    }

    @FXML
    void choosingDragon(MouseEvent event) {
        Image image = new Image("/photoes/BabyDragonCard.jpg");
        if (!dragonUsed) {
            settingImage(image);
        }
        dragonUsed = true;
    }

    @FXML
    void choosingFireBall(MouseEvent event) {
        Image image = new Image("/photoes/FireballCard.jpg");
        if (!fireBallUsed) {
            settingImage(image);
        }
        fireBallUsed = true;
    }

    @FXML
    void choosingGiant(MouseEvent event) {
        Image image = new Image("/photoes/GiantCard.jpg");
        if (!giantUsed) {
            settingImage(image);
        }
        giantUsed = true;
    }

    @FXML
    void choosingInferno(MouseEvent event) {
        Image image = new Image("/photoes/InfernoTowerCard.jpg");
        if (!infernoUsed) {
            settingImage(image);
        }
        infernoUsed = true;
    }

    @FXML
    void choosingPekka(MouseEvent event) {
        Image image = new Image("/photoes/Card_icon_Mini_P.E.K.K.A..jpg");
        if (!pekkaUsed) {
            settingImage(image);
        }
        pekkaUsed = true;
    }

    @FXML
    void choosingRage(MouseEvent event) {
        Image image = new Image("/photoes/RageCard.jpg");
        if (!rageUsed) {
            settingImage(image);
        }
        rageUsed = true;
    }

    @FXML
    void choosingWizard(MouseEvent event) {
        Image image = new Image("/photoes/WizardCard.jpg");
        if (!wizardUsed) {
            settingImage(image);
        }
        wizardUsed = true;
    }

    @FXML
    void choosingvalkery(MouseEvent event) {
        Image image = new Image("/photoes/ValkyrieCard.jpg");
        if (!valkeryUsed) {
            settingImage(image);
        }
        valkeryUsed = true;
    }
//................................................................................................


    @FXML
    void deleteSlot1(MouseEvent event) throws InterruptedException {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot1);
        slot1.setImage(image);
        sl1 = false;


    }

    @FXML
    void deleteSlot2(MouseEvent event) {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot2);
        slot2.setImage(image);
        sl2 = false;
    }

    @FXML
    void deleteSlot4(MouseEvent event) {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot4);
        slot4.setImage(image);
        sl4 = false;
    }

    @FXML
    void deleteSlot5(MouseEvent event) {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot5);
        slot5.setImage(image);
        sl5 = false;
    }

    @FXML
    void deleteSlot6(MouseEvent event) {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot6);
        slot6.setImage(image);
        sl6 = false;
    }

    @FXML
    void deleteSlot7(MouseEvent event) {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot7);
        slot7.setImage(image);
        sl7 = false;
    }

    @FXML
    void deleteSlot8(MouseEvent event) {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot8);
        slot8.setImage(image);
        sl8 = false;
    }

    @FXML
    void seleteSlot3(MouseEvent event) {
        Image image = new Image("/photoes/Clash-Royale-emblem.jpg");
        deletingSlut(slot3);
        slot3.setImage(image);
        sl3 = false;
    }


}