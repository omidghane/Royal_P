package Controller;

import java.io.Serializable;

public class User implements Serializable {
    private String username;

    private String password;

    private int grade;

    public User(String username, String password, int grade){
        this.username = username;
        this.password = password;
        this.grade = grade;
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
