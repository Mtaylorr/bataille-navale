package ensta.Ships;


public class Carrier extends AbstractShip {
    public Carrier(Orientation orientation){
        super("Carrier",'C', 5, orientation);
    }
    public Carrier(){
        this(Orientation.EAST);
    }
}