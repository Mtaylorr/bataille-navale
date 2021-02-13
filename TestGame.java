import java.util.ArrayList;
import java.lang.*;
public class TestGame{
    public static void main(String[] args){
        Board board = new Board("Issa", 6);
        board.print();
        ArrayList<AbstractShip> ships = new ArrayList<>() ;
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new BattleShip());
        ships.add(new Carrier());
        AbstractShip [] shipsAsArray  = ships.toArray(new AbstractShip[ships.size()]);
        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(shipsAsArray);
        int NumberOfDestroyedBoats=0;
        while(NumberOfDestroyedBoats!=ships.size()){
            sleep(1000);
            int [] coords = new int[2];
            Hit h ;
            try{
                h = ai.sendHit(coords);
                System.out.println(coords[0]+" "+coords[1]);
                if(h.getValue()<=0){
                    System.out.println(h);
                }else{
                    NumberOfDestroyedBoats++;
                    System.out.println(h+" coule");
                }
            }catch(Exception e){

            }finally {
                board.print();
            }
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}