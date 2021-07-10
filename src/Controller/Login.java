package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;

import java.io.*;
import java.util.Scanner;

public class Login {

    private BufferedReader br;
    private BufferedWriter bw;
    private ObjectOutputStream out;
    private ObjectInputStream in;
//    private FileOutputStream out_sign_number;
    private FileReader in_sign_number;

    private User user ;

    private File filename;

    private int sign_num ;

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
        out = new ObjectOutputStream(new FileOutputStream(filename,true));
//        in = new ObjectInputStream(new FileInputStream(filename));
//        user = new User();
        in_sign_number = new FileReader("user_number");
        sign_num = in_sign_number.read();
        System.out.println(sign_num);
    }

    @FXML
    void join_process(ActionEvent event) {
        errorText.setText("");
        if (String.valueOf(usernameField.getCharacters()).equals("") || String.valueOf(passwordField.getCharacters()).equals("")) {
            System.out.println("no 1");
            return;
        }
        try (FileInputStream fi = new FileInputStream(filename) ; FileWriter out_sign_number = new FileWriter("user_number") ; PrintWriter pr = new PrintWriter(out_sign_number)) {
            in = new ObjectInputStream(fi);

            if (event.getSource() == loginButton) {
                    System.out.println(usernameField.getCharacters());

                if(!check_double_username()){
                    errorText.setText("username does not exist");
                    return;
                }else if(!check_password(fi)){
                    errorText.setText("password is wrong");
                    return;
                }

//        finally {
//            try {
////                out.close();
//                in.close();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
            }

            if (event.getSource() == signUpButton) {
                System.out.println(usernameField.getCharacters());
//                    in = new ObjectInputStream(fi);
                if(check_double_username()){
                    errorText.setText("this username exist");
                    return;
                }

                sign_num++;
                out_sign_number.write(sign_num);
                System.out.println( "sign num: " +in_sign_number.read());
                out.writeObject(new User(String.valueOf(usernameField.getCharacters()), String.valueOf(passwordField.getCharacters()), 1));
                System.out.println("signed successfully f");
            }

            next_page();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private boolean check_password(FileInputStream fi) throws IOException, ClassNotFoundException {
//        in = new ObjectInputStream(fi);
        for(int i = 0 ; i < sign_num ; i++){
            User user = (User)in.readObject();
            System.out.println(user.getUsername() + " pass");
            if(passwordField.getText().equals(user.getPassword())){
                System.out.println("pass ok");
                return true;
            }
        }
//        errorText.setText("password is wrong");
        return false;
    }

    private boolean check_double_username() throws IOException, ClassNotFoundException {
//        System.out.println("sign up num: " + in_sign_number.read());
//        in = new ObjectInputStream(fi);
       for(int i = 0 ; i < sign_num ; i++){
            User user = (User)in.readObject();
            System.out.println(user.getUsername() + " K");
            if(usernameField.getText().equals(user.getUsername())){
                System.out.println("equal user k");
                return true;
            }
        }
        return false;
    }

    private void next_page() {

    }


}


