package ensta.Boards;
import ensta.Ships.AbstractShip;
import ensta.Helpers.ColorUtil;

import java.lang.*;

public class ShipState implements java.io.Serializable{
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

    public boolean isStruck() {
        return struck;
    }

    public String toString(){
        if(struck)
            return ColorUtil.colorize(Character.toString(ship.getLabel()),ColorUtil.Color.RED);
        return Character.toString(ship.getLabel());

    }

    public AbstractShip getShip() {
        return ship;
    }

    public boolean isSunk(){
        return ship.isSunk();
    }
}