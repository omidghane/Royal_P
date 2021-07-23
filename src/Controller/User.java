package Controller;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type User.
 */
public class User implements Serializable {

    private String username;
    private int XP;
    private ArrayList<String> cardNamesInString;
    private ArrayList<String> winLoss;

    /**
     * Gets xp.
     *
     * @return the xp
     */
    public int getXP() {

        return XP;
    }

    private String password;

    private int grade;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        winLoss = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.grade = 1;
        this.cardNamesInString = new ArrayList<>();
    }

    private void setLevel_XP(){
        if(XP >= 2500 ){
            grade = 6;
        }else if(XP >= 1700 ){
            grade = 5;
        }else if(XP >= 900 ){
            grade = 4;
        }else if(XP >= 500 ){
            grade = 3;
        }else if(XP >= 200 ){
            grade = 2;
        }
    }

    /**
     * Sets xp.
     *
     * @param XP the xp
     */
    public void setXP(int XP) {
        this.XP = XP;
        setLevel_XP();
    }

    /**
     * Sets win loss.
     *
     * @param winLoss the win loss
     */
    public void setWinLoss(ArrayList<String> winLoss) {
        this.winLoss = winLoss;
    }

    /**
     * Gets win loss.
     *
     * @return the win loss
     */
    public ArrayList<String> getWinLoss() {
        return winLoss;
    }

    /**
     * Gets card names in string.
     *
     * @return the card names in string
     */
    public ArrayList<String> getCardNamesInString() {
        return cardNamesInString;
    }

    /**
     * Add to card names.
     *
     * @param card the card
     */
    public void addToCardNames(String card){
        cardNamesInString.add(card);
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }
}
