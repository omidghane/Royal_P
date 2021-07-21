package Model;

import javafx.geometry.Point2D;
import Controller.User;

import java.io.FileInputStream;

public class MySelf extends Player{

    public MySelf(){
        System.out.println("check 1");
        getCard_map().put(new Point2D(18,3),new InfernoTower());
        if(getMy_user() == null){
            System.out.println("null 3");
        }
    }

}
