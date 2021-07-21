package Controller;

import Model.*;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MapCon {

    public void setUser(User user){
        this.user = user;
        if(user == null){
            System.out.println("null 2");
        }else{
            System.out.println("ok 2");
        }
//        playRound.getMe().setMy_user(user);
    }

    public final static double CELL_WIDTH = 29.2;
    private int time_elixir;

    private int columnCount;
    private int rowCount;
    private ArrayList<Image> images;
    private Timer timer_up;
    private Timer timer;
    private boolean putCard;
    private User user;

    //did not newed
    private Image archer;
    private Image valkyrieImage;
    private Image giant;
    private Image dragon;
    private Image cannon;

    private Image water;
    private Image quin;
    private Image king;
    private Image[] ground;

    private PlayRound playRound;
    private menuController menu;

    @FXML
    private GridPane mapview;

    @FXML
    private Label elixirLable;

    @FXML
    private ImageView cardImage1;

    @FXML
    private ImageView cardImage2;

    @FXML
    private ImageView cardImage3;

    @FXML
    private ImageView cardImage4;

    @FXML
    private Label cardLable1;

    @FXML
    private Label cardLable2;

    @FXML
    private Label cardLable3;

    @FXML
    private Label cardLable4;

    @FXML
    private AnchorPane parentImage;

    /**
     * constructor of map controller
     */
    public MapCon(){
        ground = new Image[5];
        images = new ArrayList<>();
        rowCount = 32;
        columnCount = 18;
        putCard = false;

        quin = new Image("/photoes/towerDefense_tile249.png");
        king = new Image("/photoes/towerDefense_tile250.png");
        ground[0] = new Image("/photoes/tile_56.png");
        ground[1] = new Image("/photoes/tile_55.png");
        ground[2] = new Image("/photoes/tile_85.png");
        ground[3] = new Image("/photoes/tile_86.png");
        ground[4] = new Image("/photoes/tile_17.png");
        water = new Image("/photoes/tile_73.png");

//        menuController menu = new menuController();
//        this.user = menu.getUser();
        if(this.user == null){
            System.out.println("null 1 s");
        }
//        playRound = new PlayRound();
//        playRound.getMe().setMy_user(menu.getUser());
//        playRound.getMe().set_user_cardBar();
//        playRound.getMe().initialize_cardBar();
//        playRound.getBot().initialize_cardBar();
    }

    /**
     * creating the first map
     * and starting the timer for playing and make elixir
     */
    public void initialize() {
//        menu = new menuController();
//        this.user = menu.getUser();
//        System.out.println(this.user.getUsername());
        playRound = new PlayRound();
        edit_imageView();
        initializeGrid();
        initializeMap();
        initialize_cardBar();
        update_elixirs();
        startTimer();

        mapview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                double x_par = t.getX()/(0.97 * CELL_WIDTH);
                double y_par = t.getY()/(0.73 * CELL_WIDTH);
                System.out.println(x_par + " " + y_par );
                put_cardMap(x_par,y_par);
                putCard = true;
            }
        });
    }

    /**
     * start timer of making elixir
     * and updating the map
     */
    private void startTimer() {
        timer_up = new Timer();
//        TimerTask timerTask = nul
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        time_elixir++;
                        if(time_elixir == 10){
                            update_elixirs();
                            time_elixir = 0;
                        }
                        update_map();
//                        timerTask.cancel();
                    }
                });
            }
        };
        long frameTime = (long) (1000/5);
        timer_up.schedule(timerTask,0,frameTime);
    }

    @FXML
    void cardBarClick(MouseEvent event) {
        if(timer != null) {
            timer.cancel();
        }
        Thread t;
        putCard = false;

        boolean selectCard = false;
        if(event.getSource() == cardImage1){
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                                if(putCard){
                                    System.out.println("break");
                                    putCard = false;
                                    playRound.getMe().update_cardBar(cardImage1.getImage());
                                    cardImage1.setImage(playRound.getMe().getCard_bar().get(3).getCard_image());
                                    cardLable1.setText(playRound.getMe().getCard_bar().get(3).toString());
                                    timer.cancel();
                                }
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,500);

//            while (true){
//                if(putCard){
//                    System.out.println("break");
//                    putCard = false;
//                    break;
//                }
//            }
//            playRound.getMe().update_cardBar(cardImage1.getImage());
//            cardImage1.setImage(playRound.getMe().getCard_bar().get(3).getCard_image());
//            cardLable1.setText(playRound.getMe().getCard_bar().get(3).toString());
        }else if(event.getSource() == cardImage2){
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(putCard){
                                System.out.println("break");
                                    putCard = false;
                                playRound.getMe().update_cardBar(cardImage2.getImage());
                                cardImage2.setImage(playRound.getMe().getCard_bar().get(3).getCard_image());
                                cardLable2.setText(playRound.getMe().getCard_bar().get(3).toString());
                                timer.cancel();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,500);
        }else if(event.getSource() == cardImage3){
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(putCard){
                                System.out.println("break");
                                putCard = false;
                                playRound.getMe().update_cardBar(cardImage3.getImage());
                                cardImage3.setImage(playRound.getMe().getCard_bar().get(3).getCard_image());
                                cardLable3.setText(playRound.getMe().getCard_bar().get(3).toString());
                                timer.cancel();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,500);
        }else if(event.getSource() == cardImage4){
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(putCard){
                                System.out.println("break");
                                putCard = false;
                                playRound.getMe().update_cardBar(cardImage4.getImage());
                                cardImage4.setImage(playRound.getMe().getCard_bar().get(3).getCard_image());
                                cardLable4.setText(playRound.getMe().getCard_bar().get(3).toString());
                                timer.cancel();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,500);
        }
    }



    @FXML
    void again(){
            initialize_cardBar();
    }

    /**
     * update the card bar
     */
    private void initialize_cardBar() {
//
        cardImage1.setImage(playRound.getMe().getCard_bar().get(0).getCard_image());
        cardLable1.setText(playRound.getMe().getCard_bar().get(0).toString());
        cardImage2.setImage(playRound.getMe().getCard_bar().get(1).getCard_image());
        cardLable2.setText(playRound.getMe().getCard_bar().get(1).toString());
        cardImage3.setImage(playRound.getMe().getCard_bar().get(2).getCard_image());
        cardLable3.setText(playRound.getMe().getCard_bar().get(2).toString());
        cardImage4.setImage(playRound.getMe().getCard_bar().get(3).getCard_image());
        cardLable4.setText(playRound.getMe().getCard_bar().get(3).toString());
//                playRound.getMe().getNumber_check().clear();
    }

    /**
     * update the elixir in map
     */
    public void update_elixirs(){
        elixirLable.setText("Elixirs : " + playRound.getMe().gain_elixir());
    }

    public void put_cardMap(double x_par,double y_par){
        if( playRound.putCard(x_par,y_par)){
//            mapview.add();
        }
    }

    /**
     * updating the map
     */
    private void update_map() {
        update_sceneMap();
        update_cardMap();

        ImageView a = new ImageView(new Valkyrie().getCard_image());
        mapview.add(a,2,19);
        a.setFitWidth(CELL_WIDTH);
        a.setFitHeight(CELL_WIDTH);
    }

    /**
     * update the card added to map
     */
    private void update_cardMap() {
        boolean existCard = false;
        ImageView map_display = null;
        for(int row = 0 ; row < rowCount ; row++) {
            for (int column = 0; column < columnCount; column++) {
                if(playRound.getMe().getCard_map().get(new Point2D(row,column)) != null){
                    Card card = playRound.getMe().getCard_map().get(new Point2D(row,column));
                    map_display = new ImageView(card.getCard_image());
                    existCard = true;
                }
                if(playRound.getBot().getCard_map().get(new Point2D(row,column)) != null){
                    Card card = playRound.getBot().getCard_map().get(new Point2D(row,column));
                    map_display = new ImageView(card.getCard_image());
                    existCard = true;
                }
                if(existCard) {
                    map_display.setFitWidth(CELL_WIDTH);
                    map_display.setFitHeight(CELL_WIDTH);
                    mapview.add(map_display, column, row);
                    existCard = false;
                }
            }
        }
    }

    /**
     * update the scene in map
     */
    private void update_sceneMap() {
        for(int row = 0 ; row < rowCount ; row++){
            for(int column = 0 ; column < columnCount ; column++){
                ImageView map_display = null;
                if(playRound.getCellValue(row,column) == PlayRound.CellValue.QUIN) {
                    map_display = new ImageView(quin);
                    map_display.setFitWidth(2 * CELL_WIDTH);
                    map_display.setFitHeight(2 * CELL_WIDTH);
                    mapview.add(map_display, column, row);

                    if(row < 15){
                        map_display.setRotate(180);
                    }
                } else if(playRound.getCellValue(row,column) == PlayRound.CellValue.KING){
                    map_display = new ImageView(king);
                    map_display.setFitWidth(3 * CELL_WIDTH);
                    map_display.setFitHeight(3 * CELL_WIDTH);
                    mapview.add(map_display, column, row);

                    if(row == 1){
                        map_display.setRotate(180);
                    }
                }
            }
        }

    }

    public void initializeMap() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView map_display = null;
                if (playRound.getCellValue(row, column) == PlayRound.CellValue.WATER) {
                    map_display = new ImageView(water);
                    map_display.setFitWidth(CELL_WIDTH);
                    map_display.setFitHeight(0.8 * CELL_WIDTH);
                    mapview.add(map_display, column, row);
                } else if (playRound.getCellValue(row, column) == PlayRound.CellValue.QUIN) {
                    map_display = new ImageView(quin);
                    map_display.setFitWidth(2 * CELL_WIDTH);
                    map_display.setFitHeight(2 * CELL_WIDTH);
                    mapview.add(map_display, column, row);

                    if (row < 15) {
                        map_display.setRotate(180);
                    }
                } else if (playRound.getCellValue(row, column) == PlayRound.CellValue.GROUND) {
                    if (row == 15) {
                        if (column == 7) {
                            map_display = new ImageView(ground[2]);
                        } else {
                            map_display = new ImageView(ground[0]);
                        }
                        map_display.setRotate(180);
                    } else {
                        map_display = new ImageView(ground[1]);
                    }
                    map_display.setFitWidth(1 * CELL_WIDTH);
                    map_display.setFitHeight(0.7 * CELL_WIDTH);
                    mapview.add(map_display, column, row);
                } else if (playRound.getCellValue(row, column) == PlayRound.CellValue.KING) {
                    map_display = new ImageView(king);
                    map_display.setFitWidth(3 * CELL_WIDTH);
                    map_display.setFitHeight(3 * CELL_WIDTH);
                    mapview.add(map_display, column, row);
                    if (row == 1) {
                        map_display.setRotate(180);
                    }
                }
            }
        }
    }


    /**
     * first creating the grid
     */
    private void initializeGrid() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView map_display = new ImageView();
                map_display.setX(column * CELL_WIDTH);
                map_display.setY(row * CELL_WIDTH);
//                map_display.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent t) {
//                        System.out.println("click");
//                    }
//                });

            }
        }
    }

    /**
     * some edits for imageView
     */
    private void edit_imageView() {
        cardImage1.setFitWidth(1.5 *CELL_WIDTH);
        cardImage1.setFitHeight(3 * CELL_WIDTH);
        cardImage1.setTranslateX(15);
        cardImage1.setTranslateY(-5);
        cardImage2.setFitWidth(1.5 *CELL_WIDTH);
        cardImage2.setFitHeight(3 * CELL_WIDTH);
        cardImage2.setTranslateX(10);
        cardImage2.setTranslateY(-5);
        cardImage3.setFitWidth(1.5 *CELL_WIDTH);
        cardImage3.setFitHeight(3 * CELL_WIDTH);
        cardImage3.setTranslateX(10);
        cardImage3.setTranslateY(-5);
        cardImage4.setFitWidth(1.5 *CELL_WIDTH);
        cardImage4.setFitHeight(3 * CELL_WIDTH);
        cardImage4.setTranslateX(20);
        cardImage4.setTranslateY(-5);
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

}
