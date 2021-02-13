
public class TestBoard{
    public static void main(String[] args){
        Board board = new Board("mahdi",5);
        BattleShip ship1 = new BattleShip();
        Destroyer ship2 = new Destroyer(Orientation.NORTH);
        board.putShip(ship1,1,1);
        board.putShip(ship2,3,3);
        board.print();
    }
}