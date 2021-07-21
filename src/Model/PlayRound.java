package Model;

import Controller.menuController;
import javafx.geometry.Point2D;
import Controller.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayRound {

    public enum CellValue{
        WATER,GROUND,QUIN,KING,EMPTY
    }

    private ArrayList<Integer> number_check;
    private CellValue[][] grid;
    private int columnCount;
    private int rowCount;

    private MySelf me;
    private Bot bot;
    private menuController menu_obj;

    public PlayRound(){
        columnCount = 18;
        rowCount = 32;
        number_check = new ArrayList<>();
        me = new MySelf();
        bot = new Bot();
        menu_obj = new menuController();
//        setUser();
        initialize_map();
    }

//    public void setUser(){
//        me.setMy_user(menu_obj.getUser());
//    }

    public boolean check_putCard(double x_par,double y_par){
        int x = (int) x_par;
        int y = (int) y_par;
                if(getMe().getCard_map().get(new Point2D(x,y)) != null){
                    return false;
                }
                if(grid[y][x] == CellValue.KING || grid[y][x] == CellValue.QUIN || grid[y][x] == CellValue.GROUND){
                    return false;
                }
        return true;
    }

    public boolean putCard(double x_par,double y_par){
        for(int i = 0 ; i < 18 ; i ++){
            if(x_par > i & x_par < (i+1)){
                 x_par = i;
            }
        }
        for(int i = 0 ; i < 32 ; i ++){
            if(y_par > i & y_par < (i+1)){
                y_par = i;
            }
        }

        if(check_putCard(x_par,y_par)){
            getMe().getCard_map().put(new Point2D(x_par,y_par),new Barbarians());
            System.out.println("true");
            return true;
        }else {
            System.out.println("false");
            return false;
        }
    }

    private void initialize_map() {
        File file = new File("D:\\Java Projects\\Royal_P\\src\\playMap.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        grid = new CellValue[rowCount][columnCount];
        int row = 0;
        while(sc.hasNextLine()){
            int column = 0;
            String line= sc.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()){
                String value = lineScanner.next();
                CellValue thisValue;
                switch (value) {
                    case "w":
                        thisValue = CellValue.WATER;
                        break;
                    case "g":
                        thisValue = CellValue.GROUND;
                        break;
                    case "q":
                        thisValue = CellValue.QUIN;
                        break;
                    case "k":
                        thisValue = CellValue.KING;
                        break;
                    case "e":
                        thisValue = CellValue.EMPTY;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + value);
                }
                grid[row][column] = thisValue;
                column++;
            }
            row++;
        }
    }

    //unused
    private boolean check_repetition(ArrayList<Integer> number_check, int number) {
        for(int num : number_check){
            if(num == number){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getNumber_check() {
        return number_check;
    }

    public void setNumber_check(ArrayList<Integer> number_check) {
        this.number_check = number_check;
    }

    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }

    public CellValue[][] getGrid() {
        return grid;
    }

    public void setGrid(CellValue[][] grid) {
        this.grid = grid;
    }

    public Bot getBot() {
        return bot;
    }

    public MySelf getMe() {
        return me;
    }
}
