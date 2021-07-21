package Model;

import Controller.User;
import Controller.menuController;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player {
    private ArrayList<Integer> number_check;
    private ArrayList<Card> card_bar;
    private ArrayList<Card> card_play;
    private ArrayList<Princess> princessTowers;
    private King kingTower;
    private HashMap<Point2D,Card> card_map;
    private int elixir;
    private User my_user;

    public Player(){
//        Controller.menuController menu = new menuController();
//        my_user = menu.getUser();
//        if(menu.getUser() == null){
//            System.out.println("null 4");
//        }
        System.out.println("check 2");

        number_check = new ArrayList<>();
        card_bar = new ArrayList<>();
        card_play = new ArrayList<>();
        princessTowers = new ArrayList<>();
        kingTower = new King();
        card_map = new HashMap<>();
        elixir = 3;

        Princess princess1 = new Princess();
        Princess princess2=  new Princess();
        princessTowers.add(princess1);
        princessTowers.add(princess2);
        kingTower = new King();

        card_play.add(new Arrows());
        card_play.add(new Archer());
        card_play.add(new Barbarians());
        card_play.add(new InfernoTower());
        card_play.add(new Cannon());
        card_play.add(new Wizard());
        card_play.add(new Fireball());
        card_play.add(new Giant());

        initialize_cardBar();
    }

    public int gain_elixir(){
        if(elixir == 10){

        }else{
            elixir++;
        }
        return elixir;
    }

    public void initialize_cardBar() {
        for(int i = 0 ; i < 4 ;i++){
            getCard_bar().add(give_bar_card());
        }
    }

    public void update_cardBar(Image cardClicked_image){
        Card cardClicked = find_card_image(cardClicked_image);
        getCard_bar().remove(cardClicked);
        getCard_bar().add(3,give_bar_card());
        getNumber_check().remove((Object) cardClicked.getCard_index());
    }

    public Card give_bar_card(){
        Random rand = new Random();
        Card card = null;
        int card_num = 0;
        while(true) {
            while (true) {
                card_num = rand.nextInt(12);
                if (!check_repetition(number_check, card_num)) {
                    break;
                }
            }
            number_check.add(card_num);
            for (int i = 0; i < 8; i++) {
                if (card_num == card_play.get(i).getCard_index()) {
                    return card_play.get(i);
                }
            }
        }
    }

    public Card find_card_image(Image cardImage){
        for(Card card : card_play){
            if(card.getCard_image() == cardImage){
                return card;
            }
        }
        System.out.println("error find_card_image");
        return null;
    }

    private boolean check_repetition(ArrayList<Integer> number_check, int number) {
        for(int num : number_check){
            if(num == number){
                return true;
            }
        }
        return false;
    }

    public void set_user_cardBar() {
        for(String card_name : my_user.getCardNamesInString()){
            System.out.println( card_name + " setter");
            if(card_name.equals( new Valkyrie().toString())){
                card_play.add(new Valkyrie());
            }else if(card_name.equals( new Archer().toString())){
                card_play.add(new Archer());
            }else if(card_name.equals( new Arrows().toString())){
                card_play.add(new Arrows());
            }else if(card_name.equals( new BabyDragon().toString())){
                card_play.add(new BabyDragon());
            }else if(card_name.equals( new Barbarians().toString())){
                card_play.add(new Barbarians());
            }else if(card_name.equals( new Cannon().toString())){
                card_play.add(new Cannon());
            }else if(card_name.equals( new Fireball().toString())){
                card_play.add(new Fireball());
            }else if(card_name.equals( new Giant().toString())){
                card_play.add(new Giant());
            }else if(card_name.equals( new InfernoTower().toString())){
                card_play.add(new InfernoTower());
            }else if(card_name.equals( new Pekka().toString())){
                card_play.add(new Pekka());
            }else if(card_name.equals( new Rage().toString())){
                card_play.add(new Rage());
            }else if(card_name.equals( new Wizard().toString())){
                card_play.add(new Wizard());
            }
        }
    }

    public HashMap<Point2D, Card> getCard_map() {
        return card_map;
    }

    public void setCard_map(HashMap<Point2D, Card> card_map) {
        this.card_map = card_map;
    }

    public ArrayList<Card> getCard_bar() {
        return card_bar;
    }

    public void setCard_bar(ArrayList<Card> card_bar) {
        this.card_bar = card_bar;
    }

    public ArrayList<Card> getCard_play() {
        return card_play;
    }

    public void setCard_play(ArrayList<Card> card_play) {
        this.card_play = card_play;
    }

    public int getElixir() {
        return elixir;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    public void setKingTower(King kingTower) {
        this.kingTower = kingTower;
    }

    public King getKingTower() {
        return kingTower;
    }

    public void setPrincessTowers(ArrayList<Princess> princessTowers) {
        this.princessTowers = princessTowers;
    }

    public ArrayList<Princess> getPrincessTowers() {
        return princessTowers;
    }

    public ArrayList<Integer> getNumber_check() {
        return number_check;
    }

    public void setNumber_check(ArrayList<Integer> number_check) {
        this.number_check = number_check;
    }

    public void setMy_user(User my_user) {
        this.my_user = my_user;
    }

    public User getMy_user() {
        return my_user;
    }
}
