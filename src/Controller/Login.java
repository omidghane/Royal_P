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

import javax.swing.text.View;
import java.io.File;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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


    @FXML
    void join_process(ActionEvent event) throws IOException {

        if (event.getSource() == signUpButton) {
            String name = usernameField.getText();
            String password = passwordField.getText();
            if (Files.exists(Path.of(name))) {
                errorText.setText("you have already signed up!");
                return;
            }
            this.user = new User((usernameField.getText()), (passwordField.getText()), 1);
            save(name, user);
        }

        if (event.getSource() == loginButton) {
            String name = usernameField.getText();
            String password = passwordField.getText();
            if (Files.exists(Path.of(name))) {
                try (FileInputStream fi = new FileInputStream(name)) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(fi);
                    User user = (User) objectInputStream.readObject();
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
        next_page(user);
    }

    public void save(String fileName, User theUser) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(theUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void next_page(User user) throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/menu.fxml"));
        loader.load();
        menuController menuController = loader.getController();
        menuController.setUser(user);
        Parent root = loader.getRoot();
//        Parent root = FXMLLoader.load(getClass().getResource("../View/menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.setResizable(false);
        stage.show();
    }

}
