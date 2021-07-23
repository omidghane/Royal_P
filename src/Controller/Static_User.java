package Controller;

import java.util.ArrayList;

/**
 * The type Static user.
 */
public class Static_User {
    private static Static_User user ;

    /**
     * The constant username.
     */
    public static String username;
    /**
     * The constant password.
     */
    public static String password;
    /**
     * The constant grade.
     */
    public static int grade;
    /**
     * The constant XP.
     */
    public static int XP;
    /**
     * The Card names in string.
     */
    public static ArrayList<String> cardNamesInString;
    /**
     * The Winloss.
     */
    public static ArrayList<String> winloss;

    /**
     * Get instance static user.
     *
     * @return the static user
     */
    public static Static_User getInstance(){
        user = new Static_User( username,  password, grade);
        return user;
    }

    private Static_User(String username, String password,int grade){
        this.username = username;
        this.password = password;
        this.grade = grade;
        XP = 0;
    }

}
