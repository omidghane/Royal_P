package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * The type Login.
 */
public class Login {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private User user;
    private File filename;


    @FXML
    private ImageView royalImage;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Text errorText;


    /**
     * Join process.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void join_process(ActionEvent event) throws IOException {

        if (event.getSource() == signUpButton) {
            String name = usernameField.getText();
            String password = passwordField.getText();
            if (Files.exists(Path.of(name))) {
                errorText.setText("you have already signed up!");
                return;
            }
            this.user = new User(name,password);
//            user.setXP(500);
//            ArrayList<String> a = new ArrayList<>();
//            a.add("lose");
//            user.setWinLoss(a);
//            System.out.println(user.getUsername_p() + " X " + user.getPassword() );
            save(name, user);
        }

        if (event.getSource() == loginButton) {
            String name = usernameField.getText();
            String password = passwordField.getText();
            if (Files.exists(Path.of(name))) {
                try (FileInputStream fi = new FileInputStream(name)) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(fi);
                    User user = (User) objectInputStream.readObject();
//                    System.out.println(user.getUsername_p() + " X " + user.getPassword_p() );
                    if (user.getPassword().equals(password)) {
                        this.user = user;
                    } else {
                        errorText.setText("password or name is incorrect");
                        return;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                errorText.setText("you should sign up first");
                return;
            }
        }
       set_staticUser();

        next_page(user);
    }

    private void set_staticUser() {
        System.out.println(user.getXP() + "XP");
        System.out.println(user.getWinLoss() + " winloss");
        Static_User.username = user.getUsername();
        Static_User.password = user.getPassword();
        Static_User.grade = user.getGrade();
        Static_User.XP = user.getXP();
        Static_User.cardNamesInString = user.getCardNamesInString();
        Static_User.winloss = user.getWinLoss();
        System.out.println(Static_User.XP);
        System.out.println(Static_User.winloss);
    }

    /**
     * Save.
     *
     * @param fileName the file name
     * @param theUser  the the user
     */
    public void save(String fileName, User theUser) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(theUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(user.getUsername() + " X " + user.getPassword() );
    }

    private void next_page(User user) throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
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

}
