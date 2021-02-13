import java.util.*;
import java.lang.*;
import java.io.*;
public class ShipState{
    AbstractShip ship;
    boolean struck;
    public ShipState(AbstractShip ship){
        this.ship=ship;
    }
    public ShipState(){
        this(null);
    }
    public void addStrike(){
        if(!struck){
            struck=true;
            ship.addStrike();
        }
    }

    public String toString(){
        if(struck)
        return ColorUtil.colorize(Character.toString(ship.getLabel()),ColorUtil.Color.RED);
        return Character.toString(ship.getLabel());

    }

    public AbstractShip getShip() {
        return ship;
    }

    boolean isSunk(){
        return ship.isSunk();
    }
}