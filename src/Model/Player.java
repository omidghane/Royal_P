package Model;

import Controller.Static_User;
import Controller.User;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * The type Player.
 */
public class Player {
    private int exp;
    private int level;
    private String name;
    private String password;

    private ArrayList<Integer> number_check;
    private ArrayList<Card> card_bar;
    private ArrayList<Card> card_play;
    private ArrayList<Princess> princessTowers;
    private King kingTower;
    private HashMap<Integer, Point2D> card_map;
    private HashMap<Integer, Card> cardIdea_map;
    private HashMap<Integer, Point2D> hit_map;
    private int elixir;
    private HashMap<Integer, Boolean> move_cards;
    private HashMap<Integer, Boolean> stop_cards;
    private HashMap<Integer, Boolean> hit_cards;
    private HashMap<Integer, Integer> hit_timer;
    private HashMap<Integer, Integer> rotate_cards;

    private PlayRound.CellValue[][] grid;

    /**
     * Instantiates a new Player.
     *
     * @param grid the grid
     */
    public Player(PlayRound.CellValue[][] grid) {
        name = Static_User.username;
        password = Static_User.password;
        exp = Static_User.XP;
        level = Static_User.grade;
        this.grid = grid;
//
        move_cards = new HashMap<>();
        number_check = new ArrayList<>();
        card_bar = new ArrayList<>();
        card_play = new ArrayList<>();
        princessTowers = new ArrayList<>();
        kingTower = new King();
        card_map = new HashMap<>();
        cardIdea_map = new HashMap<>();
        stop_cards = new HashMap<>();
        hit_timer = new HashMap<>();
        hit_map = new HashMap<>();
        hit_cards = new HashMap<>();
        rotate_cards = new HashMap<>();
        elixir = 3;
        Princess princess1;
        Princess princess2;
        if (this instanceof MySelf) {
            princess1 = new Princess(new Point2D(5, 4));
            princess2 = new Princess(new Point2D(5, 13));
        } else {
            princess1 = new Princess(new Point2D(26, 4));
            princess2 = new Princess(new Point2D(26, 13));
        }
        princessTowers.add(princess1);
        princessTowers.add(princess2);
        kingTower = new King();

        if (this instanceof MySelf) {
            set_user_cardBar();
//            card_play.add(new Pekka());
//            card_play.add(new Archer());
//            card_play.add(new Arrows());
//            card_play.add(new BabyDragon());
//            card_play.add(new Valkyrie());
//            card_play.add(new Wizard());
//            card_play.add(new Fireball());
//            card_play.add(new Giant());
        }

        if (this instanceof Bot) {
            card_play.add(new Pekka());
            card_play.add(new Archer());
            card_play.add(new Barbarians());
            card_play.add(new BabyDragon());
            card_play.add(new Valkyrie());
            card_play.add(new Wizard());
            card_play.add(new Fireball());
            card_play.add(new Giant());
        }

        set_level_cardplays_towers();

        initialize_cardBar();
    }

    /**
     * Sets user file.
     *
     * @param result the result
     */
    public void set_user_file(String result) {
        User user = new User(name, password);
        System.out.println(exp + " saved");
        user.setXP(exp);
        Static_User.winloss.add(result);
        System.out.println(Static_User.winloss + " winloss");
        user.setWinLoss(Static_User.winloss);
        try (FileOutputStream fo = new FileOutputStream(name)) {
            ObjectOutputStream out = new ObjectOutputStream(fo);
            out.writeObject(user);
        } catch (IOException e) {

        }
    }

    /**
     * Cost card boolean.
     *
     * @param card the card
     * @return the boolean
     */
    public boolean cost_card(Card card) {
        if( (elixir - card.getCost()) >= 0) {
            elixir -= card.getCost();
            return true;
        }else{
            return false;
        }
//        return true;
    }

    /**
     * Remove kill card.
     *
     * @param card the card
     */
    public void remove_killCard(Card card) {
        card_map.remove(card.getIdea());
        cardIdea_map.remove(card.getIdea());
        hit_map.remove(card.getIdea());
        hit_cards.remove(card.getIdea());
        stop_cards.remove(card.getIdea());
        move_cards.remove(card.getIdea());
    }

    /**
     * Hit tower.
     */
    public void hit_tower() {
        target_tower();
        for (Princess princess : princessTowers) {
            hit_timer.putIfAbsent(princess.getIdea(), 0);
            if (hit_timer.get(princess.getIdea()) == 3) {
                Card card = find_card_idea(princess.getTarget_card());
                card.setHP(card.getHP() - princess.getDamage());
                getHit_timer().put(princess.getIdea(), 0);
                if (card.getHP() <= 0) {
                    princess.setTarget_card(-1);
                    remove_killCard(card);
                }
            }
        }
        hit_timer.putIfAbsent(kingTower.getIdea(), 0);
        if (hit_timer.get(kingTower.getIdea()) == 3) {
            Card card = find_card_idea(kingTower.getTarget_card());
            card.setHP(card.getHP() - kingTower.getDamage());
            getHit_timer().put(kingTower.getIdea(), 0);
            if (card.getHP() <= 0) {
                kingTower.setTarget_card(-1);
                remove_killCard(card);
            }
        }
    }

    /**
     * Target tower.
     */
    public void target_tower() {
        try {
            for (Card card : get_cardMap_arr()) {
                int x = card.getX_pos();
                int y = card.getY_pos();
                if (this instanceof MySelf) {
                    if (princessTowers.get(1).getTarget_card() == -1 && x > 8 && y < 13) {
                        princessTowers.get(1).setTarget_card(card.getIdea());
                    } else if (princessTowers.get(0).getTarget_card() == -1 && x < 9 && y < 13) {
                        princessTowers.get(0).setTarget_card(card.getIdea());
                    } else if (kingTower.getTarget_card() == -1 && x > 1 && x < 16 && y < 6) {
                        kingTower.setTarget_card(card.getIdea());
                    }
                } else if (this instanceof Bot) {
                    if (princessTowers.get(1).getTarget_card() == -1 && x > 8 && y > 20) {
                        princessTowers.get(1).setTarget_card(card.getIdea());
                    } else if (princessTowers.get(0).getTarget_card() == -1 && x < 9 && y > 20) {
                        princessTowers.get(0).setTarget_card(card.getIdea());
                    } else if (kingTower.getTarget_card() == -1 && x > 1 && x < 16 && y > 26) {
                        kingTower.setTarget_card(card.getIdea());
                    }
                }
            }
        } catch (NullPointerException e) {
        }
    }

    private void check_princessHP(Card card) {
        for (Princess princess : princessTowers) {
            if (princess.getHP() <= 0) {
                if (this instanceof MySelf) {
                    if (princess.getPosition().getY() == 4) {
                        grid[5][4] = PlayRound.CellValue.WATER;
                    } else if (princess.getPosition().getY() == 13) {
                        grid[5][13] = PlayRound.CellValue.WATER;
                    }
                } else {
                    if (princess.getPosition().getY() == 4) {
                        grid[26][4] = PlayRound.CellValue.WATER;
                    } else if (princess.getPosition().getY() == 13) {
                        grid[26][13] = PlayRound.CellValue.WATER;
                    }
                }
                hit_timer.put(princess.getIdea(), 0);
                princess.setTarget_card(-1);
                stop_cards.put(card.getIdea(), false);
                hit_timer.put(card.getIdea(), 0);
                hit_cards.put(card.getIdea(), false);
                hit_map.remove(card.getIdea());
            }

        }
    }

    /**
     * Move shot.
     *
     * @param card the card
     */
    public void move_shot(Card card) {
        boolean help = false;
        hit_map.putIfAbsent(card.getIdea(), new Point2D(card.getY_pos(), card.getX_pos()));
        if (card instanceof Arrows) {
        }
        help = hit_conditional(hit_map, card);

        if (help) {
            double damage;
            if (card instanceof Spell) {
                damage = card.getAreaDamage();
            } else {
                damage = card.getDamage();
            }
//           hit_map.remove(card.getIdea());
            hit_cards.put(card.getIdea(), false);
            int x_hit = (int) hit_map.get(card.getIdea()).getY();
            int y_hit = (int) hit_map.get(card.getIdea()).getX();
            if (x_hit < 6 && x_hit > 3) {
                princessTowers.get(0).setHP(princessTowers.get(0).getHP() - (int) damage);
                check_princessHP(card);
                System.out.println(princessTowers.get(0).getHP() + " 1");
            } else if (x_hit > 11 && x_hit < 15) {
                princessTowers.get(1).setHP(princessTowers.get(1).getHP() - (int) damage);
                check_princessHP(card);
                System.out.println(princessTowers.get(1).getHP() + " 2");
            } else if (x_hit > 5 && x_hit < 12) {
                kingTower.setHP(kingTower.getHP() - (int) damage);
                System.out.println(kingTower.getHP() + " 3 " + card.toString());
            }
            hit_map.remove(card.getIdea());
        }
//       check_princessHP(card);
    }

    /**
     * Hit.
     *
     * @param card the card
     */
    public void hit(Card card) {
        hit_cards.putIfAbsent(card.getIdea(), false);
        hit_timer.putIfAbsent(card.getIdea(), 0);
        if (hit_timer.get(card.getIdea()) != 0) {
            if ((hit_timer.get(card.getIdea()) % 6) == 0 && card.getHit_speed() == 1.2) {
                hit_cards.put(card.getIdea(), true);
            } else if ((hit_timer.get(card.getIdea()) % 7) == 0 && card.getHit_speed() == 1.4) {
                hit_cards.put(card.getIdea(), true);
            } else if ((hit_timer.get(card.getIdea()) % 8) == 0 && card.getHit_speed() == 1.6) {
                hit_cards.put(card.getIdea(), true);
            } else if ((hit_timer.get(card.getIdea()) % 9) == 0 && card.getHit_speed() == 1.8) {
                hit_cards.put(card.getIdea(), true);
            }
        }
        if (hit_cards.get(card.getIdea())) {
            move_shot(card);
        }
    }

    /**
     * Stop position.
     *
     * @param player the player
     */
    public void stop_position(Player player) {
        try {
            ArrayList<Card> card_array = new ArrayList<>();
            card_array.addAll(get_cardMap_arr());

            Iterator<Card> card1 = card_array.iterator();
            while (card1.hasNext()) {
                Card card = card1.next();

                stop_cards.putIfAbsent(card.getIdea(), false);
                int y_pos = card.getY_pos();
                int x_pos = card.getX_pos();
                try {
                    if (card.getRange() == 0) {
                        if (this instanceof MySelf) {
                            if (grid[y_pos - 1][x_pos] == PlayRound.CellValue.EMPTY || grid[y_pos - 1][x_pos] == PlayRound.CellValue.QUIN
                                    || grid[y_pos - 1][x_pos - 1] == PlayRound.CellValue.EMPTY || grid[y_pos - 1][x_pos + 1] == PlayRound.CellValue.EMPTY) {
                                stop_cards.put(card.getIdea(), true);
                                hit(card);
                            }
                        }
                        if (this instanceof Bot) {
                            if (grid[y_pos + 1][x_pos] == PlayRound.CellValue.EMPTY || grid[y_pos + 1][x_pos] == PlayRound.CellValue.QUIN
                                    || grid[y_pos + 1][x_pos + 1] == PlayRound.CellValue.EMPTY || grid[y_pos + 1][x_pos - 1] == PlayRound.CellValue.EMPTY) {
                                stop_cards.put(card.getIdea(), true);
                                hit(card);
                            }
                        }
                    } else if (card.getRange() == 5) {
                        if (player instanceof MySelf) {
                            if (grid[y_pos - 5][x_pos] == PlayRound.CellValue.EMPTY || grid[y_pos - 5][x_pos] == PlayRound.CellValue.QUIN
                                    || grid[y_pos - 4][x_pos - 4] == PlayRound.CellValue.EMPTY || grid[y_pos - 4][x_pos + 4] == PlayRound.CellValue.EMPTY) {
                                stop_cards.put(card.getIdea(), true);
                                hit(card);
                            }
                        }
                        if (player instanceof Bot) {
                            if (grid[y_pos + 5][x_pos] == PlayRound.CellValue.EMPTY || grid[y_pos + 5][x_pos] == PlayRound.CellValue.QUIN
                                    || grid[y_pos + 4][x_pos + 4] == PlayRound.CellValue.EMPTY || grid[y_pos + 4][x_pos - 4] == PlayRound.CellValue.EMPTY) {
                                stop_cards.put(card.getIdea(), true);
                                hit(card);
                            }
                        }
                    } else if (card.getRange() == 3) {
                        if (player instanceof MySelf) {
                            if (grid[y_pos - 3][x_pos] == PlayRound.CellValue.EMPTY || grid[y_pos - 3][x_pos] == PlayRound.CellValue.QUIN
                                    || grid[y_pos - 3][x_pos - 3] == PlayRound.CellValue.EMPTY || grid[y_pos - 3][x_pos + 3] == PlayRound.CellValue.EMPTY) {
                                stop_cards.put(card.getIdea(), true);
                                hit(card);
                            }
                        }
                        if (player instanceof Bot) {
                            if (grid[y_pos + 3][x_pos] == PlayRound.CellValue.EMPTY || grid[y_pos + 3][x_pos] == PlayRound.CellValue.QUIN
                                    || grid[y_pos + 3][x_pos + 3] == PlayRound.CellValue.EMPTY || grid[y_pos + 3][x_pos - 3] == PlayRound.CellValue.EMPTY) {
                                stop_cards.put(card.getIdea(), true);
                                hit(card);
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
//               System.out.println("errr LOP");
                }
//            }
            }
        } catch (NullPointerException e) {
        }
    }

    /**
     * moving cards depends on their speed
     *
     * @param speed  the speed
     * @param player the player
     */
    public void move(Troop.SPEED speed, Player player) {
        try {
            stop_position(player);
            ArrayList<Card> card_array = new ArrayList<>();
            card_array.addAll(get_cardMap_arr());

            Iterator<Card> card1 = card_array.iterator();
            while (card1.hasNext()) {
                Card card = null;
                card = card1.next();

                stop_cards.putIfAbsent(card.getIdea(), false);
                if (stop_cards.get(card.getIdea())) {

                } else {
                    if (card.getSpeed() == speed) {
                        card_map.remove(card.getIdea(), new Point2D(card.getY_pos(), card.getX_pos()));
                        move_conditional(card);
                        card_map.put(card.getIdea(), new Point2D(card.getY_pos(), card.getX_pos()));
                        move_cards.put(card.getIdea(), true);
                    }
                }
            }
        } catch (NullPointerException e) {
        }
    }

    /**
     * Hit conditional boolean.
     *
     * @param hit_map the hit map
     * @param card    the card
     * @return the boolean
     */
    public boolean hit_conditional(HashMap<Integer, Point2D> hit_map, Card card) {
        int y = (int) hit_map.get(card.getIdea()).getX();
        int x = (int) hit_map.get(card.getIdea()).getY();
        if (this instanceof MySelf) {
            if (card instanceof Arrows) {
                hit_map.put(card.getIdea(), new Point2D(y - 1, x));
            } else {
                if (card.getY_pos() < 8 && card.getX_pos() < 9 && princessTowers.get(0).getHP() <= 0) {
                    hit_map.put(card.getIdea(), new Point2D(y - 1, x + 1));
                } else if (card.getY_pos() < 8 && card.getX_pos() > 8 && princessTowers.get(1).getHP() <= 0) {
                    hit_map.put(card.getIdea(), new Point2D(y - 1, x - 1));
                } else {
                    hit_map.put(card.getIdea(), new Point2D(y - 1, x));
                }
            }
            if (card instanceof Arrows && (y == card.getY_pos() - 3)) {
                return true;
            } else if (!(card instanceof Arrows) && (grid[y][x] == PlayRound.CellValue.EMPTY || grid[y][x] == PlayRound.CellValue.QUIN)
            ) {
                return true;
            }
        } else if (this instanceof Bot) {
            if (card instanceof Arrows) {
                hit_map.put(card.getIdea(), new Point2D(y + 1, x));
            } else {
                if (card.getY_pos() > 23 && card.getX_pos() < 9 && princessTowers.get(0).getHP() <= 0) {
                    hit_map.put(card.getIdea(), new Point2D(y + 1, x + 1));
                } else if (card.getY_pos() > 23 && card.getX_pos() > 8 && princessTowers.get(1).getHP() <= 0) {
                    hit_map.put(card.getIdea(), new Point2D(y + 1, x - 1));
                } else {
                    hit_map.put(card.getIdea(), new Point2D(y + 1, x));
                }
            }
            if (card instanceof Arrows && (y == card.getY_pos() + 3)) {
                return true;
            } else if (!(card instanceof Arrows) && (grid[y][x] == PlayRound.CellValue.EMPTY || grid[y][x] == PlayRound.CellValue.QUIN)) {
                return true;
            }
        }
        return false;
    }

    private void move_conditional(Card card) {
        if (this instanceof MySelf) {
            if (card.getY_pos() < 9 && card.getX_pos() < 9 && princessTowers.get(0).getHP() <= 0) {
                card.setY_pos(card.getY_pos() - 1);
                card.setX_pos(card.getX_pos() + 1);
                rotate_cards.put(card.getIdea(), 45);
            } else if (card.getY_pos() < 9 && card.getX_pos() > 9 && princessTowers.get(1).getHP() <= 0) {
                card.setY_pos(card.getY_pos() - 1);
                card.setX_pos(card.getX_pos() - 1);
                rotate_cards.put(card.getIdea(), -45);
            } else {
                card.setY_pos(card.getY_pos() - 1);
            }
        } else if (this instanceof Bot) {
            if (card.getY_pos() > 22 && card.getX_pos() < 9 && princessTowers.get(0).getHP() <= 0) {
                card.setY_pos(card.getY_pos() + 1);
                card.setX_pos(card.getX_pos() + 1);
                rotate_cards.put(card.getIdea(), -45);
            } else if (card.getY_pos() > 22 && card.getX_pos() > 9 && princessTowers.get(1).getHP() <= 0) {
                card.setY_pos(card.getY_pos() + 1);
                card.setX_pos(card.getX_pos() - 1);
                rotate_cards.put(card.getIdea(), 45);
            } else {
                card.setY_pos(card.getY_pos() + 1);
            }
        }
    }

    /**
     * increase the elixir number
     *
     * @return the new elixir number
     */
    public int gain_elixir() {
        if (elixir == 10) {

        } else {
            elixir++;
        }
        return elixir;
    }

    /**
     * first adding the cards to the cardBar
     */
    public void initialize_cardBar() {
        for (int i = 0; i < 4; i++) {
            getCard_bar().add(give_bar_card());
        }
    }

    /**
     * change a cardBar
     *
     * @param cardClicked_image the card clicked image
     */
    public void update_cardBar(Card cardClicked_image) {
        getCard_bar().remove(cardClicked_image);
        getCard_bar().add(3, give_bar_card());
        getNumber_check().remove((Object) cardClicked_image.getCard_index());
    }

    /**
     * Give bar card card.
     *
     * @return card but check its not repeated
     */
    public Card give_bar_card() {
        Random rand = new Random();
        Card card = null;
        int card_num = 0;
        while (true) {
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

    /**
     * Gets card map arr.
     *
     * @return the card map arr
     */
    public ArrayList<Card> get_cardMap_arr() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Integer cardIdea : card_map.keySet()) {
            cards.add(find_card_idea(cardIdea));
        }
        return cards;
    }

    /**
     * Find card idea card.
     *
     * @param idea the idea
     * @return the card
     */
    public Card find_card_idea(int idea) {
        for (Integer cardIdea : cardIdea_map.keySet()) {
            if (idea == cardIdea) {
                return cardIdea_map.get(cardIdea);
            }
        }
//        System.out.println("idea not found");
        return null;
    }

    /**
     * Find card image card.
     *
     * @param cardImage the card image
     * @return the card based on its image
     */
    public Card find_card_image(Image cardImage) {
        for (Card card : card_play) {
            if (card.getCard_image_bar() == cardImage) {
                return card;
            }
        }
        System.out.println("error find_card_image");
        return null;
    }

    /**
     * @param number_check
     * @param number
     * @return true if there is the numberParam in arrayParam
     */
    private boolean check_repetition(ArrayList<Integer> number_check, int number) {
        for (int num : number_check) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * set level of cards and towers in play
     */
    private void set_level_cardplays_towers() {
        for (Card card : card_play) {
            card.setLevel(level);
        }
        princessTowers.get(0).setLevel(level);
        princessTowers.get(1).setLevel(level);
        kingTower.setLevel(level);
    }

    /**
     * set cardBar based userCards
     */
    public void set_user_cardBar() {
        for (String card_name : Static_User.cardNamesInString) {
            if (card_name.equals(new Valkyrie().toString())) {
                card_play.add(new Valkyrie());
            } else if (card_name.equals(new Archer().toString())) {
                card_play.add(new Archer());
            } else if (card_name.equals(new Arrows().toString())) {
                card_play.add(new Arrows());
            } else if (card_name.equals(new BabyDragon().toString())) {
                card_play.add(new BabyDragon());
            } else if (card_name.equals(new Barbarians().toString())) {
                card_play.add(new Barbarians());
            } else if (card_name.equals(new Cannon().toString())) {
                card_play.add(new Cannon());
            } else if (card_name.equals(new Fireball().toString())) {
                card_play.add(new Fireball());
            } else if (card_name.equals(new Giant().toString())) {
                card_play.add(new Giant());
            } else if (card_name.equals(new InfernoTower().toString())) {
                card_play.add(new InfernoTower());
            } else if (card_name.equals(new Pekka().toString())) {
                card_play.add(new Pekka());
            } else if (card_name.equals(new Rage().toString())) {
                card_play.add(new Rage());
            } else if (card_name.equals(new Wizard().toString())) {
                card_play.add(new Wizard());
            }
        }
    }


    /**
     * Gets card map.
     *
     * @return the card map
     */
    public HashMap<Integer, Point2D> getCard_map() {
        return card_map;
    }

    /**
     * Sets card map.
     *
     * @param card_map the card map
     */
    public void setCard_map(HashMap<Integer, Point2D> card_map) {
        this.card_map = card_map;
    }

    /**
     * Gets card bar.
     *
     * @return the card bar
     */
    public ArrayList<Card> getCard_bar() {
        return card_bar;
    }

    /**
     * Sets card bar.
     *
     * @param card_bar the card bar
     */
    public void setCard_bar(ArrayList<Card> card_bar) {
        this.card_bar = card_bar;
    }


    /**
     * Gets card play.
     *
     * @return the card play
     */
    public ArrayList<Card> getCard_play() {
        return card_play;
    }

    /**
     * Sets card play.
     *
     * @param card_play the card play
     */
    public void setCard_play(ArrayList<Card> card_play) {
        this.card_play = card_play;
    }

    /**
     * Gets elixir.
     *
     * @return the elixir
     */
    public int getElixir() {
        return elixir;
    }

    /**
     * Sets elixir.
     *
     * @param elixir the elixir
     */
    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    /**
     * Sets king tower.
     *
     * @param kingTower the king tower
     */
    public void setKingTower(King kingTower) {
        this.kingTower = kingTower;
    }

    /**
     * Gets king tower.
     *
     * @return the king tower
     */
    public King getKingTower() {
        return kingTower;
    }

    /**
     * Sets princess towers.
     *
     * @param princessTowers the princess towers
     */
    public void setPrincessTowers(ArrayList<Princess> princessTowers) {
        this.princessTowers = princessTowers;
    }

    /**
     * Gets princess towers.
     *
     * @return the princess towers
     */
    public ArrayList<Princess> getPrincessTowers() {
        return princessTowers;
    }

    /**
     * Gets number check.
     *
     * @return the number check
     */
    public ArrayList<Integer> getNumber_check() {
        return number_check;
    }

    /**
     * Sets number check.
     *
     * @param number_check the number check
     */
    public void setNumber_check(ArrayList<Integer> number_check) {
        this.number_check = number_check;
    }

    /**
     * Gets move cards.
     *
     * @return the move cards
     */
    public HashMap<Integer, Boolean> getMove_cards() {
        return move_cards;
    }

    /**
     * Sets move cards.
     *
     * @param move_cards the move cards
     */
    public void setMove_cards(HashMap<Integer, Boolean> move_cards) {
        this.move_cards = move_cards;
    }


    /**
     * Gets stop cards.
     *
     * @return the stop cards
     */
    public HashMap<Integer, Boolean> getStop_cards() {
        return stop_cards;
    }

    /**
     * Sets stop cards.
     *
     * @param stop_cards the stop cards
     */
    public void setStop_cards(HashMap<Integer, Boolean> stop_cards) {
        this.stop_cards = stop_cards;
    }

    /**
     * Gets hit timer.
     *
     * @return the hit timer
     */
    public HashMap<Integer, Integer> getHit_timer() {
        return hit_timer;
    }

    /**
     * Get grid play round . cell value [ ] [ ].
     *
     * @return the play round . cell value [ ] [ ]
     */
    public PlayRound.CellValue[][] getGrid() {
        return grid;
    }

    /**
     * Gets hit map.
     *
     * @return the hit map
     */
    public HashMap<Integer, Point2D> getHit_map() {
        return hit_map;
    }

    /**
     * Gets hit cards.
     *
     * @return the hit cards
     */
    public HashMap<Integer, Boolean> getHit_cards() {
        return hit_cards;
    }

//    public ArrayList<Card> getCard_map_arr() {
//        return card_map_arr;
//    }

    /**
     * Gets card idea map.
     *
     * @return the card idea map
     */
    public HashMap<Integer, Card> getCardIdea_map() {
        return cardIdea_map;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets exp.
     *
     * @param exp the exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * Gets exp.
     *
     * @return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * Gets rotate cards.
     *
     * @return the rotate cards
     */
    public HashMap<Integer, Integer> getRotate_cards() {
        return rotate_cards;
    }
}
