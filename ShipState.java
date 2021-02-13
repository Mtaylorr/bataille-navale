public class ShipState{
    AbstractShip ship;
    boolean struck;
    public ShipState(AbstractShip ship){
        this.ship=ship;
    }
    public void addStrike(){
        if(!struck){
            struck=true;
            ship.addStrike();
        }
    }

    public String toString(){
        if(struck)
        return ColorUtil.colorize((string(ship.getLabel()),ColorUtil.Color.RED);
        return ship.getLabel();
    }

    public AbstractShip getShip() {
        return ship;
    }

    boolean isSunk(){
        return ship.isSunk();
    }
}