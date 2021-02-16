package ensta;
public class Submarine extends AbstractShip{
    public Submarine(Orientation orientation){
        super("Submarine",'S', 3, orientation);
    }
    public Submarine(){
        this(Orientation.EAST);
    }
}