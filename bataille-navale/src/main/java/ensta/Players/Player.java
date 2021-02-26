package ensta.Players;
import ensta.Boards.Board;
import ensta.Boards.Hit;
import ensta.Helpers.InputHelper;
import ensta.Ships.AbstractShip;
import ensta.Ships.Orientation;

import java.util.List;
import java.lang.*;

public class Player implements java.io.Serializable{
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /** **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /** **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips(){
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("%s : placer %d : %s(%d)",this.board.getName(), i + 1, s.getName(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            // TODO set ship orientatio
            switch (res.orientation){
                case "n":
                    s.setOrientation(Orientation.NORTH);
                    break;
                case "e":
                    s.setOrientation(Orientation.EAST);
                    break;
                case "w":
                    s.setOrientation(Orientation.WEST);
                    break;
                case "s":
                    s.setOrientation(Orientation.SOUTH);
                    break;
            }
            // TODO put ship at given position
            if(board.canPutShip(s, res.y,res.x)){
                board.putShip(s, res.y,res.x);
            }else{
                System.out.println("Invalid Position or direction");
                continue;
            }
            // TODO when ship placement successful
            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done;
        Hit hit = null;

        do {
            done=false;
            System.out.println( board.getName()+": ou frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            try {
                hit = this.opponentBoard.sendHit(hitInput.y, hitInput.x);
                this.board.setHit((hit!=Hit.MISS),hitInput.y ,hitInput.x);
                coords[0] = hitInput.y;
                coords[1] = hitInput.x;
                done=true;
            }catch(Exception e){
                System.out.println(e);
                continue;
            }
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
