public class BattleShip extends AbstractShip{
    public BattleShip(Orientation orientation){
        super("BattleShip",'B', 4, orientation);
    }
    public BattleShip(){
        this(Orientation.EAST);
    }
}