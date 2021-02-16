package ensta;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;
    private char multiPlayer;

    /* ***
     * Constructeurs
     */
    public Game() {
    }

    public Game init() {
        if (!loadSave()) {
            // init attributes

            sin = new Scanner(System.in);

            System.out.print("Multijoueur (y/n) ? ");
            multiPlayer = sin.nextLine().charAt(0);

            while (multiPlayer != 'y' && multiPlayer != 'n') {

                System.out.println("caractère invalide ! essayez de nouveau");
                multiPlayer = sin.nextLine().charAt(0);

            }
            // TODO init boards
            Board b1, b2;

            if (multiPlayer == 'n') {

                System.out.print("Entre ton nom: ");
                String playerName = sin.nextLine();

                b1 = new Board(playerName, 6);
                b2 = new Board("AI", 6);
            } else {
                System.out.print("Entre le nom du premier joueur: ");
                String playerName1 = sin.nextLine();

                System.out.print("Entre le nom du deuxième joueur: ");
                String playerName2 = sin.nextLine();

                b1 = new Board(playerName1, 6);
                b2 = new Board(playerName2, 6);

            }
            // TODO init this.player1 & this.player2
            ArrayList<AbstractShip> ships1 = new ArrayList<>();
            ships1.add(new Destroyer());
            ships1.add(new Submarine());
            ships1.add(new Submarine());
            ships1.add(new BattleShip());
            ships1.add(new Carrier());
            AbstractShip[] shipsAsArray1 = ships1.toArray(new AbstractShip[ships1.size()]);

            List<AbstractShip> ships2 = new ArrayList<>();
            ships2.add(new Destroyer());
            ships2.add(new Submarine());
            ships2.add(new Submarine());
            ships2.add(new BattleShip());
            ships2.add(new Carrier());
            AbstractShip[] shipsAsArray2 = ships2.toArray(new AbstractShip[ships2.size()]);
            if (multiPlayer == 'n') {
                player1 = new Player(b1, b2, ships1);
                player2 = new AIPlayer(b2, b1, ships2);
            }else{
                player1 = new Player(b1, b2, ships1);
                player2 = new Player(b2, b1, ships2);

            }
            b1.print();
            player1.putShips();
            if (multiPlayer == 'y')
                b2.print();
            player2.putShips();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() throws Exception {
        int[] coords = new int[2];

        Board b1 = player1.board;
        Hit hit;

        // main loop

        boolean done;
        do {
            b1.print();
            hit = player1.sendHit(coords); // TODO player1 send a hit
            boolean strike = (hit != Hit.MISS); // TODO set this hit on his board (b1)

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                if (multiPlayer == 'y') {
                    player2.board.print();
                }
                do {
                    hit = player2.sendHit(coords); // TODO player2 send a hit.

                    strike = (hit != Hit.MISS);
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (multiPlayer == 'y') {
                        player2.board.print();
                    }

                    if (!done) {
                        save();
                    }
                } while (strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
       /* try {
            // TODO bonus 2 : uncomment
            //  if (!SAVE_FILE.exists()) {
            //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            //  }

            // TODO bonus 2 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private boolean loadSave() {
        /*if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coule";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[1])),
                (coords[0] + 1), msg);
        return msg;//ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()});
    }

    public static void main(String args[]) throws Exception {
        new Game().init().run();
    }
}
