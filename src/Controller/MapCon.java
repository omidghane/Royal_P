package Controller;

import Model.*;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The type Map con.
 */
public class MapCon {

    /**
     * Set user.
     *
     * @param user the user
     */
    public void setUser(User user){
        this.user = user;
        if(user == null){
            System.out.println("null 2");
        }else{
            System.out.println("ok 2");
        }
    }

    /**
     * The constant CELL_WIDTH.
     */
    public final static double CELL_WIDTH = 29.2;
    private int time_elixir;
    private int time_move = 0;
    private int main_time;
    private int hit_show;

    private int columnCount;
    private int rowCount;
    private double x_par;
    private double y_par;
    private ArrayList<Image> images;
    private Timer timer_up1;
    private Timer timer_up2;
    private Timer timer_up3;
    private Timer timer;
    private boolean putCard;
    private boolean finish_game;
    private User user;

    private Image water;
    private Image quin;
    private Image king;
    private Image[] ground;

    private PlayRound playRound;

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

    @FXML
    private Label time_show;

    /**
     * constructor of map controller
     */
    public MapCon(){
        main_time = 180;
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

        if(this.user == null){
            System.out.println("null 1 s");
        }
    }

    /**
     * creating the first map
     * and starting the timer for playing and make elixir
     */
    public void initialize() {
        playRound = new PlayRound();
        edit_imageView();
        initializeGrid();
        updateMap();
        initialize_cardBar();
//        update_elixirs();
        elixirLable.setText("Elixirs : 3");
        time_show.setText("180 s");
//        moveTimer();
        startTimer1();
        startTimer2();
        startTimer3();

        mapview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                putCard = false;
                x_par = t.getX()/(0.97 * CELL_WIDTH);
                y_par = t.getY()/(0.73 * CELL_WIDTH);
                putCard = true;
            }
        });
    }

    /**
     * new thread timer
     */
    private void startTimer3() {
        timer_up3 = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        playRound.putCard_bot();
                    }
                });
            }
        };
        timer_up3.schedule(timerTask,0,25000);
    }

    /**
     * start timer of moving and hitting cards
     */
    private void startTimer1() {
        timer_up1 = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        time_move++;
                        card_move();
                        card_hit();
                        tower_hit();
                        spell_hit();
                    }

                    private void tower_hit() {
                        playRound.getMe().hit_tower();
                        for(Princess princess : playRound.getMe().getPrincessTowers()){
                            if(princess.getTarget_card() != -1){
                                playRound.getMe().getHit_timer().putIfAbsent(princess.getIdea(), 0);
                                playRound.getMe().getHit_timer().put(princess.getIdea(), playRound.getMe().getHit_timer().get(princess.getIdea()) + 1);
                            }
                        }
                        King king = playRound.getMe().getKingTower();
                        if(king.getTarget_card() != -1){
                            playRound.getMe().getHit_timer().putIfAbsent(king.getIdea(),0);
                            playRound.getMe().getHit_timer().put(king.getIdea(),playRound.getMe().getHit_timer().get(king.getIdea()) + 1);
                        }
                        playRound.getBot().hit_tower();
                        for(Princess princess : playRound.getBot().getPrincessTowers()){
                            if(princess.getTarget_card() != -1){
                                playRound.getBot().getHit_timer().putIfAbsent(princess.getIdea(), 0);
                                playRound.getBot().getHit_timer().put(princess.getIdea(), playRound.getBot().getHit_timer().get(princess.getIdea()) + 1);
                            }
                        }
                        king = playRound.getBot().getKingTower();
                        if(king.getTarget_card() != -1){
                            playRound.getBot().getHit_timer().putIfAbsent(king.getIdea(),0);
                            playRound.getBot().getHit_timer().put(king.getIdea(),playRound.getBot().getHit_timer().get(king.getIdea()) + 1);
                        }
                    }

                    public void spell_hit(){
                        for(Card card : playRound.getMe().get_cardMap_arr()){
                            if( (card instanceof Fireball && playRound.getMe().getHit_cards().get(card.getIdea())) || (card instanceof Arrows && playRound.getMe().getHit_cards().get(card.getIdea()))) {
                                playRound.getMe().move_shot(card);
                            }
                        }
                    }

                    public void card_hit(){
                        try {
                            ArrayList<Card> card_array1 = new ArrayList<>();
                            card_array1.addAll(playRound.getMe().get_cardMap_arr());
                            Iterator<Card> card1 = card_array1.iterator();
                            while (card1.hasNext()) {
                                Card card = card1.next();
                                playRound.getMe().getStop_cards().putIfAbsent(card.getIdea(), false);
                                if (playRound.getMe().getStop_cards().get(card.getIdea())) {
                                    playRound.getMe().getHit_timer().putIfAbsent(card.getIdea(), 0);
                                    playRound.getMe().getHit_timer().put(card.getIdea(), playRound.getMe().getHit_timer().get(card.getIdea()) + 1);
                                }
                            }
                            ArrayList<Card> card_array2 = new ArrayList<>();
                            card_array2.addAll(playRound.getBot().get_cardMap_arr());
                            Iterator<Card> card2 = card_array2.iterator();
                            while (card2.hasNext()) {
                                Card card = card2.next();
                                playRound.getBot().getStop_cards().putIfAbsent(card.getIdea(), false);
                                if (playRound.getBot().getStop_cards().get(card.getIdea())) {
                                    playRound.getBot().getHit_timer().putIfAbsent(card.getIdea(), 0);
                                    playRound.getBot().getHit_timer().put(card.getIdea(), playRound.getBot().getHit_timer().get(card.getIdea()) + 1);
                                }
                            }
                        }catch (NullPointerException e){
                        }
                    }

                    private void card_move() {
                        if(time_move != 0) {
                            if ((time_move % 3) == 0) {
                                playRound.getMe().move(Troop.SPEED.FAST,playRound.getMe());
                                playRound.getBot().move(Troop.SPEED.FAST,playRound.getBot());
                            } if ((time_move % 5) == 0) {
                                playRound.getMe().move(Troop.SPEED.MEDIUM,playRound.getMe());
                                playRound.getBot().move(Troop.SPEED.MEDIUM,playRound.getBot());
                            } if((time_move % 10) == 0){
                                playRound.getMe().move(Troop.SPEED.SLOW,playRound.getMe());
                                playRound.getBot().move(Troop.SPEED.SLOW,playRound.getBot());
                            }
                        }
                    }
                });
            }

        };
        long frameTime = (long) (1000/3);
        timer_up1.schedule(timerTask,0,frameTime);
    }

    /**
     * Start timer 2.
     */
    public void startTimer2(){
        timer_up2 = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(main_time <= 0){
//                            timer_up1.cancel();
//                            timer_up3.cancel();
//                            timer_up2.cancel();
                        }else if(main_time <= 60){
                            time_elixir += 2;
                        }else {
                            time_elixir++;
                        }
                        timer_show();
                        if (time_elixir == 10) {
                            update_elixirs();
                            time_elixir = 0;
                        }
                        if(!finish_game) {
                            update_map();
                            String winner = playRound.check_finish();
                            try {
                                if (winner.equals("bot")) {
                                    result_page("you loose");
                                } else if (winner.equals("me")) {
                                    result_page("you win");
                                }
                            } catch (NullPointerException e) {
                            }
                        }
                    }

                        public void timer_show(){
                            if(time_elixir == 5 || time_elixir == 10){
                                if(main_time > 0) {
                                    main_time--;
                                    time_show.setText(main_time + " s");
                                }
                            }
                        }
                    });
            };

        };
        long frameTime = (long) (1000/5);
        timer_up2.schedule(timerTask,0,frameTime);

    }

    private void result_page(String player) {
        Static_User.winloss.add(player);
        try {
            Stage stage = (Stage) cardImage1.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/finishScreen.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("");
            stage.setResizable(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("result saved");
        finish_game = true;
//        timer_up1.cancel();
//        timer_up2.cancel();
//        timer_up3.cancel();
    }

    /**
     * Card bar click.
     *
     * @param event the event
     */
    @FXML
    void cardBarClick(MouseEvent event) {
        if(timer != null) {
            timer.cancel();
            timer.purge();
        }
        putCard = false;

        if(event.getSource() == cardImage1){
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                                if(putCard){
                                    Card cardImage = playRound.getMe().find_card_image(cardImage1.getImage());
                                    if(playRound.getMe().cost_card(cardImage)) {
                                        playRound.putCard(x_par, y_par, cardImage1.getImage());
                                        putCard = false;
                                        playRound.getMe().update_cardBar(cardImage);
                                        cardImage1.setImage(playRound.getMe().getCard_bar().get(3).getCard_image_bar());
                                        cardLable1.setText(playRound.getMe().getCard_bar().get(3).toString());
                                    }
                                    timer.cancel();
                                }
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,500);
        }else if(event.getSource() == cardImage2){
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(putCard){
                                Card cardImage = playRound.getMe().find_card_image(cardImage2.getImage());
                                if(playRound.getMe().cost_card(cardImage)) {
                                    playRound.putCard(x_par, y_par, cardImage2.getImage());
                                    putCard = false;
                                    playRound.getMe().update_cardBar(cardImage);
                                    cardImage2.setImage(playRound.getMe().getCard_bar().get(3).getCard_image_bar());
                                    cardLable2.setText(playRound.getMe().getCard_bar().get(3).toString());
                                }
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
                                Card cardImage = playRound.getMe().find_card_image(cardImage3.getImage());
                                playRound.getMe().cost_card(cardImage);
                                    playRound.putCard(x_par, y_par, cardImage3.getImage());
                                    putCard = false;
                                    playRound.getMe().update_cardBar(cardImage);
                                    cardImage3.setImage(playRound.getMe().getCard_bar().get(3).getCard_image_bar());
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
                                Card cardImage = playRound.getMe().find_card_image(cardImage4.getImage());
                                if(playRound.getMe().cost_card(cardImage)) {
                                    playRound.putCard(x_par, y_par, cardImage4.getImage());
                                    putCard = false;
                                    playRound.getMe().update_cardBar(cardImage);
                                    cardImage4.setImage(playRound.getMe().getCard_bar().get(3).getCard_image_bar());
                                    cardLable4.setText(playRound.getMe().getCard_bar().get(3).toString());
                                }
                                timer.cancel();
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,500);
        }
    }

    /**
     * update the elixir in map
     */
    public void update_elixirs(){
        playRound.getBot().gain_elixir();
        elixirLable.setText("Elixirs : " + playRound.getMe().gain_elixir());
    }

    /**
     * update the card bar
     */
    private void initialize_cardBar() {
//
        cardImage1.setImage(playRound.getMe().getCard_bar().get(0).getCard_image_bar());
        cardLable1.setText(playRound.getMe().getCard_bar().get(0).toString());
        cardImage2.setImage(playRound.getMe().getCard_bar().get(1).getCard_image_bar());
        cardLable2.setText(playRound.getMe().getCard_bar().get(1).toString());
        cardImage3.setImage(playRound.getMe().getCard_bar().get(2).getCard_image_bar());
        cardLable3.setText(playRound.getMe().getCard_bar().get(2).toString());
        cardImage4.setImage(playRound.getMe().getCard_bar().get(3).getCard_image_bar());
        cardLable4.setText(playRound.getMe().getCard_bar().get(3).toString());
    }

    /**
     * updating the map
     */
    private void update_map() {
        hit_show++;
        updateMap();
        update_cardMap();
        update_hitMap();
        if((hit_show % 2) == 0) {
            update_hitTower();
        }
    }

    private void update_hitTower() {
        int x = 0;
        int y = 0;
        ImageView map_display = null;
        for(Princess princess : playRound.getMe().getPrincessTowers()){
            if(princess.getTarget_card() != -1){
                Card card = playRound.getMe().find_card_idea(princess.getTarget_card());
                 x = card.getX_pos();
                 y = card.getY_pos();
                map_display = new ImageView(princess.getShut_image());
                map_display.setFitWidth(CELL_WIDTH);
                map_display.setFitHeight(CELL_WIDTH);
                mapview.add(map_display,x,y);
            }
        }
        King king = playRound.getMe().getKingTower();
        if(king.getTarget_card() != -1){
            Card card = playRound.getMe().find_card_idea(king.getTarget_card());
            x = card.getX_pos();
            y = card.getY_pos();
            map_display = new ImageView(king.getShut_image());
            map_display.setFitWidth(CELL_WIDTH);
            map_display.setFitHeight(CELL_WIDTH);
            mapview.add(map_display,x,y);
        }
        for(Princess princess : playRound.getBot().getPrincessTowers()){
            if(princess.getTarget_card() != -1){
                Card card = playRound.getBot().find_card_idea(princess.getTarget_card());
                 x = card.getX_pos();
                 y = card.getY_pos();
                map_display = new ImageView(princess.getShut_image());
                map_display.setFitWidth(CELL_WIDTH);
                map_display.setFitHeight(CELL_WIDTH);
                mapview.add(map_display,x,y);
            }
        }
        king = playRound.getBot().getKingTower();
        if(king.getTarget_card() != -1){
            Card card = playRound.getBot().find_card_idea(king.getTarget_card());
            x = card.getX_pos();
            y = card.getY_pos();
            map_display = new ImageView(king.getShut_image());
            map_display.setFitWidth(CELL_WIDTH);
            map_display.setFitHeight(CELL_WIDTH);
            mapview.add(map_display,x,y);
        }
    }

    private void update_hitMap() {
        try {
            ImageView map_display = null;

            Card card1 = null;
            int x_p1 = 0;
            int y_p1 = 0;
            try {
                for (Card card : playRound.getMe().get_cardMap_arr()) {
                    card1 = card;
                    if (playRound.getMe().getHit_map().get(card.getIdea()) != null) {
                        int x_p = (int) playRound.getMe().getHit_map().get(card.getIdea()).getY();
                        int y_p = (int) playRound.getMe().getHit_map().get(card.getIdea()).getX();
                        x_p1 = x_p;
                        y_p1 = y_p;
                        playRound.getMe().getHit_cards().putIfAbsent(card.getIdea(), false);
                        if (playRound.getMe().getHit_cards().get(card.getIdea())) {
                            map_display = new ImageView(water);
                            map_display.setFitWidth(CELL_WIDTH);
                            map_display.setFitHeight(CELL_WIDTH);
                            mapview.add(map_display, x_p, y_p + 1);
                            playRound.getMe().getMove_cards().replace(card.getIdea(), false);
                        }
                        if (card instanceof Arrows) {
                            map_display = new ImageView(card.getShut_image());
                            map_display.setFitWidth(2 * CELL_WIDTH);
                            map_display.setFitHeight(2 * CELL_WIDTH);
                        } else if (y_p >= 4 && y_p <= 6 && card instanceof Fireball) {
                            map_display = new ImageView(card.getExplode_image());
                            map_display.setFitWidth(3 * CELL_WIDTH);
                            map_display.setFitHeight(3 * CELL_WIDTH);
                        } else if (card instanceof Fireball) {
                            map_display.setFitWidth(1 * CELL_WIDTH);
                            map_display.setFitHeight(1 * CELL_WIDTH);
                            map_display = new ImageView(card.getShut_image());
                        } else {
                            map_display = new ImageView(card.getShut_image());
                        }
                        mapview.add(map_display, x_p, y_p);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
//            playRound.getMe().getHit_map().remove(card1.getIdea(),new Point2D(y_p1,x_p1));
//            System.out.println("hit except HOP");
            }
            try {
                for (Card card : playRound.getBot().get_cardMap_arr()) {
                    card1 = card;
                    if (playRound.getBot().getHit_map().get(card.getIdea()) != null) {
                        int x_p = (int) playRound.getBot().getHit_map().get(card.getIdea()).getY();
                        int y_p = (int) playRound.getBot().getHit_map().get(card.getIdea()).getX();
                        x_p1 = x_p;
                        y_p1 = y_p;
                        playRound.getBot().getHit_cards().putIfAbsent(card.getIdea(), false);
                        if (playRound.getBot().getHit_cards().get(card.getIdea())) {
                            map_display = new ImageView(water);
                            map_display.setFitWidth(CELL_WIDTH);
                            map_display.setFitHeight(CELL_WIDTH);
                            mapview.add(map_display, x_p, y_p - 1);
                            playRound.getBot().getMove_cards().replace(card.getIdea(), false);
                        }

                        map_display = new ImageView(card.getShut_image());
                        mapview.add(map_display, x_p, y_p);
                    }
                }
            } catch (IllegalArgumentException e) {
                try {
//                    playRound.getBot().getHit_map().remove(card1.getIdea(), new Point2D(y_p1, x_p1));
//                    System.out.println("hit except HOP");
                } catch (NullPointerException a) {
                }
            }
        }catch (NullPointerException e){}
    }

    /**
     * update the card added to map
     */
    private void update_cardMap() {
        try {
            ImageView map_display = null;
            for (Card card : playRound.getMe().get_cardMap_arr()) {
                if (playRound.getMe().getCard_map().get(card.getIdea()) != null) {
                    int x_p = (int) playRound.getMe().getCard_map().get(card.getIdea()).getY();
                    int y_p = (int) playRound.getMe().getCard_map().get(card.getIdea()).getX();
//
                    map_display = new ImageView(card.getCard_image());
                    rotate_basedCard(map_display, card, "me");
                    map_display.setFitWidth(CELL_WIDTH);
                    map_display.setFitHeight(CELL_WIDTH);
                    mapview.add(map_display, x_p, y_p);
                }
            }
            for (Card card : playRound.getBot().getCardIdea_map().values()) {
                if (playRound.getBot().getCard_map().get(card.getIdea()) != null) {
                    int x_p = (int) playRound.getBot().getCard_map().get(card.getIdea()).getY();
                    int y_p = (int) playRound.getBot().getCard_map().get(card.getIdea()).getX();
                    playRound.getBot().getMove_cards().putIfAbsent(card.getIdea(), false);
                    if (playRound.getBot().getMove_cards().get(card.getIdea())) {
                        map_display = new ImageView(water);
                        map_display.setFitWidth(CELL_WIDTH);
                        map_display.setFitHeight(CELL_WIDTH);
                        mapview.add(map_display, x_p, y_p - 1);
                        playRound.getBot().getMove_cards().replace(card.getIdea(), false);
                    }

                    map_display = new ImageView(card.getCard_image());
                    rotate_basedCard(map_display, card, "bot");
                    map_display.setFitWidth(CELL_WIDTH);
                    map_display.setFitHeight(CELL_WIDTH);
                    mapview.add(map_display, x_p, y_p);
                }
            }
        }catch (NullPointerException e){}
    }

    /**
     * update map view
     */
    public void updateMap() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView map_display = null;
                if (playRound.getCellValue(row, column) == PlayRound.CellValue.WATER) {
                    map_display = new ImageView(water);
                    map_display.setFitWidth(CELL_WIDTH);
                    map_display.setFitHeight(0.8 * CELL_WIDTH);
                    mapview.add(map_display, column, row);
                }else if (playRound.getCellValue(row, column) == PlayRound.CellValue.GROUND) {
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
                }else if(playRound.getCellValue(row, column) == PlayRound.CellValue.EMPTY ){
                    ArrayList<Princess> princesses;
                    if(row < 16) {
                        princesses = playRound.getMe().getPrincessTowers();
                    }else{
                        princesses = playRound.getBot().getPrincessTowers();
                    }
                    for(Princess princess : princesses){
                        int x = (int) princess.getPosition().getY();
                        int y = (int) princess.getPosition().getX();
                        if(princess.getHP() <= 0){
                            if( (row == y-1 && column == x-1) || (row == y && column == x-1) || (row == y+1 && column == x-1) || (row == y-1 && column == x) || (row == y && column == x) || (row == y+1 && column == x) || (row == y-1 && column == x+1) || (row == y && column == x+1) || (row == y+1 && column == x+1)){
                                map_display = new ImageView(water);
                                playRound.getGrid()[row][column] = PlayRound.CellValue.WATER;
                                mapview.add(map_display,column,row);
                            }
                            if(playRound.getMe().getKingTower().getHP() <= 0){

                            }
                        }
                    }

                }else if (playRound.getCellValue(row, column) == PlayRound.CellValue.QUIN) {
                    map_display = new ImageView(quin);
                    map_display.setFitWidth(2 * CELL_WIDTH);
                    map_display.setFitHeight(2 * CELL_WIDTH);
                    mapview.add(map_display, column, row);

                    if (row < 15) {
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
            }
        }
    }

    /**
     * rotate the card based on card
     *
     * @param map_display the map display
     * @param card        the card
     * @param player      the player
     */
    public void rotate_basedCard(ImageView map_display, Card card, String player){
        int rotate ;
        playRound.getMe().getRotate_cards().putIfAbsent(card.getIdea(),0);
        rotate = playRound.getMe().getRotate_cards().get(card.getIdea());
        if(player.equals("me")) {
            if (card instanceof InfernoTower) {
                map_display.setRotate(rotate);
            } else if (card instanceof Cannon) {
                map_display.setRotate(-90 + rotate);
            }else if(card instanceof BabyDragon){
                map_display.setRotate(-90 + rotate);
            }else{
                map_display.setRotate(180 + rotate);
            }
        }
        playRound.getBot().getRotate_cards().putIfAbsent(card.getIdea(),0);
        rotate = playRound.getBot().getRotate_cards().get(card.getIdea());
        if(player.equals("bot")){
            if (card instanceof InfernoTower) {
                map_display.setRotate(180 + rotate);
            } else if (card instanceof Cannon) {
                map_display.setRotate(+90 + rotate);
            }else if (card instanceof BabyDragon) {
                map_display.setRotate(90 + rotate);
            } else {
                map_display.setRotate(rotate);
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

    /**
     * Gets images.
     *
     * @return the images
     */
    public ArrayList<Image> getImages() {
        return images;
    }

    /**
     * Gets row count.
     *
     * @return the row count
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Gets column count.
     *
     * @return the column count
     */
    public int getColumnCount() {
        return columnCount;
    }

}
