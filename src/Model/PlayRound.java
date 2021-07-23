package Model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * The type Play round.
 */
public class PlayRound {

    /**
     * The enum Cell value.
     */
    public enum CellValue {
        /**
         * Water cell value.
         */
        WATER,
        /**
         * Ground cell value.
         */
        GROUND,
        /**
         * Quin cell value.
         */
        QUIN,
        /**
         * King cell value.
         */
        KING,
        /**
         * Empty cell value.
         */
        EMPTY,
        /**
         * Out cell value.
         */
        OUT
    }

    private ArrayList<Integer> number_check;
    private CellValue[][] grid;
    private int columnCount;
    private int rowCount;

    private MySelf me;
    private Bot bot;
    /**
     * The List idea.
     */
    private ArrayList<Integer> list_idea;

    /**
     * Instantiates a new Play round.
     */
    public PlayRound() {
        columnCount = 18;
        rowCount = 32;
        number_check = new ArrayList<>();
        list_idea = new ArrayList<>();
        initialize_map();
        me = new MySelf(grid);
        bot = new Bot(grid);
        me.getPrincessTowers().get(0).setIdea(set_idea());
        me.getPrincessTowers().get(1).setIdea(set_idea());
        bot.getPrincessTowers().get(0).setIdea(set_idea());
        bot.getPrincessTowers().get(1).setIdea(set_idea());
        me.getKingTower().setIdea(set_idea());
        bot.getKingTower().setIdea(set_idea());
    }

    /**
     * Check finish string.
     *
     * @return the string
     */
    public String check_finish() {
//        System.out.println(getMe().getKingTower().getHP() + " HP3");
        if ((getMe().getPrincessTowers().get(0).getHP() <= 0 && getMe().getKingTower().getHP() <= 0) || (getMe().getPrincessTowers().get(1).getHP() <= 0 && getMe().getKingTower().getHP() <= 0)) {
            getMe().setExp(getMe().getExp() + 200);
            getMe().set_user_file("win");
            return "me";
        } else if ((getBot().getPrincessTowers().get(0).getHP() <= 0 && getBot().getKingTower().getHP() <= 0) || (getBot().getPrincessTowers().get(1).getHP() <= 0 && getBot().getKingTower().getHP() <= 0)) {
            getMe().setExp(getMe().getExp() + 70);
            getMe().set_user_file("lose");
            return "bot";
        }
        return null;
    }

    /**
     * Put card bot.
     */
    public void putCard_bot() {
        Card card = null;
        Point2D position = rand_position();
        Iterator<Card> card2 = getBot().getCard_bar().iterator();
        while (card2.hasNext()) {
            Card card1 = card2.next();
            if (card1 instanceof Arrows || card1 instanceof Fireball || card1 instanceof Rage) {
                continue;
            }
            if (getBot().cost_card(card1)) {
                card = card1;
                card.setIdea(set_idea());
                card.setX_pos((int) position.getY());
                card.setY_pos((int) position.getX());
                getBot().getCardIdea_map().put(card.getIdea(), card);
                getBot().getCard_map().put(card.getIdea(), new Point2D(position.getX(), position.getY()));
                getBot().update_cardBar(card);
                break;
            }
        }
    }

    /**
     * Put card boolean.
     *
     * @param x_par      the x par
     * @param y_par      the y par
     * @param card_image the card image
     * @return the boolean
     */
    public boolean putCard(double x_par, double y_par, Image card_image) {
        Card card = getMe().find_card_image(card_image);
        card.setIdea(set_idea());
        for (int i = 0; i < 18; i++) {
            if (x_par > i & x_par < (i + 1)) {
                x_par = i;
            }
        }
        for (int i = 0; i < 32; i++) {
            if (y_par > i & y_par < (i + 1)) {
                y_par = i;
            }
        }
        if (card instanceof Fireball) {
            card.setX_pos((int) x_par);
            card.setY_pos((int) 22);
            getMe().getCard_map().put(card.getIdea(), new Point2D(22, x_par));
            getMe().getCardIdea_map().put(card.getIdea(), card);
            getMe().getHit_cards().put(card.getIdea(), true);
            return true;
        } else if (card instanceof Arrows) {
            card.setX_pos((int) x_par);
            card.setY_pos((int) y_par);
            getMe().getCard_map().put(card.getIdea(), new Point2D(y_par, x_par));
            getMe().getCardIdea_map().put(card.getIdea(), card);
            getMe().getHit_cards().put(card.getIdea(), true);
            return true;
        }

        if (check_putCard(x_par, y_par, card)) {
            card.setX_pos((int) x_par);
            card.setY_pos((int) y_par);
            getMe().getCard_map().put(card.getIdea(), new Point2D(y_par, x_par));
            getMe().getCardIdea_map().put(card.getIdea(), card);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check put card boolean.
     *
     * @param x_par the x par
     * @param y_par the y par
     * @param card  the card
     * @return the boolean
     */
    public boolean check_putCard(double x_par, double y_par, Card card) {
        int x = (int) x_par;
        int y = (int) y_par;
        if (getMe().getCard_map().get(card.getIdea()) != null) {
            return false;
        }
        if (grid[y][x] == CellValue.KING || grid[y][x] == CellValue.QUIN || grid[y][x] == CellValue.GROUND) {
            return false;
        }
        return true;
    }

    /**
     * the first creating of map
     */
    private void initialize_map() {
        File file = new File("playMap");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        grid = new CellValue[rowCount][columnCount];
        int row = 0;
        while (sc.hasNextLine()) {
            int column = 0;
            String line = sc.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
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
                    case "o":
                        thisValue = CellValue.OUT;
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

    /**
     * @return an idea
     */
    private int set_idea() {
        int help = 0;
        int rand;
        Random random = new Random();
        while (true) {
            rand = random.nextInt(4000);
            for (int i : list_idea) {
                if (i == rand) {
                    help = 1;
                }
            }
            if (help == 0) {
                break;
            }
        }
        list_idea.add(rand);
        return rand;
    }

    /**
     * Rand position point 2 d.
     *
     * @return the point 2 d
     */
    public Point2D rand_position() {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(14);
            int column = random.nextInt(14);
            if ((row > 6 && column > 2 && column < 6) || (row > 6 && column > 11)) {
                return new Point2D(row, column);
            }
        }
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
     * Gets cell value.
     *
     * @param row    the row
     * @param column the column
     * @return the cell value
     */
    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }

    /**
     * Get grid cell value [ ] [ ].
     *
     * @return the cell value [ ] [ ]
     */
    public CellValue[][] getGrid() {
        return grid;
    }

    /**
     * Sets grid.
     *
     * @param grid the grid
     */
    public void setGrid(CellValue[][] grid) {
        this.grid = grid;
    }

    /**
     * Gets bot.
     *
     * @return the bot
     */
    public Bot getBot() {
        return bot;
    }

    /**
     * Gets me.
     *
     * @return the me
     */
    public MySelf getMe() {
        return me;
    }
}
