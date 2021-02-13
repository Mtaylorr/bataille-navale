
public class TestBoard{
    public static void main(String[] args) throws Exception{
        Board board = new Board("mahdi",5);
        BattleShip ship1 = new BattleShip();
        Destroyer ship2 = new Destroyer(Orientation.NORTH);
        try {
            board.putShip(ship1, 1, 1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try {
            board.putShip(ship2, 2, 2);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        board.print();
    }
}