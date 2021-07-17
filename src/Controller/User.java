package Controller;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private int XP;
    private ArrayList<String> cardNamesInString;

    public int getXP() {

        return XP;
    }


    private String password;

    private int grade;

    public User(String username, String password, int grade) {
        this.username = username;
        this.password = password;
        this.grade = grade;
        this.XP = 0;
        this.cardNamesInString = new ArrayList<>();
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public ArrayList<String> getCardNamesInString() {
        return cardNamesInString;
    }

    public void addToCardNames(String card){
        cardNamesInString.add(card);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getGrade() {
        return grade;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
