package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;

import java.io.*;

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

    public Login() throws IOException {
        filename = new File("user");
        out = new ObjectOutputStream(new FileOutputStream(filename, true)) {
            protected void writeStreamHeader() throws IOException {
                reset();
            }
        };
    }

    @FXML
    void join_process(ActionEvent event) {
        errorText.setText("");
        if (String.valueOf(usernameField.getCharacters()).equals("") ||
                String.valueOf(passwordField.getCharacters()).equals("")) {
            System.out.println("no 1");
            return;
        }
        try (FileInputStream fi = new FileInputStream(filename)
        ) {
            in = new ObjectInputStream(fi);

            if (event.getSource() == loginButton) {
//                    System.out.println(usernameField.getCharacters());

                if (!check_login()) {
                    errorText.setText("username or password is wrong");
                    return;
                }

            }

            if (event.getSource() == signUpButton) {
//                System.out.println(usernameField.getCharacters());
                if (check_double_username()) {
                    errorText.setText("this username exist");
                    return;
                }


                out.writeObject(new User((usernameField.getText()), (passwordField.getText()), 1));
                System.out.println("signed successfully f");
            }

            next_page();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean check_login() throws IOException, ClassNotFoundException {
        while (true) {
            try {
                User user = (User) in.readObject();
                System.out.println(user.getUsername() + " pass");
                if (usernameField.getText().equals(user.getUsername())) {
                    if (passwordField.getText().equals(user.getPassword())) {
                        System.out.println("pass ok");
                        return true;
                    }
                }
            } catch (EOFException e) {
                break;
            }
        }
        return false;
    }

    private boolean check_double_username() throws IOException, ClassNotFoundException {
        while (true) {
            try {
                User user = (User) in.readObject();
                System.out.println(user.getUsername() + " K");
                if (usernameField.getText().equals(user.getUsername())) {
                    System.out.println("equal user k");
                    return true;
                }
            } catch (EOFException e) {
                System.out.println("er 1");
                break;
            }
        }
        return false;
    }

    private void next_page() {

    }

}
