public class TestGame{
    public static void main(String[] args){
        Board board = new Board("Issa", 8);
        board.print();
        ArrayList<AbstractShip> ships = new ArrayList<>() ;
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new Submarine());
        ships.add(new BattleShip());
        ships.add(new Carrier());
        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships.toArray());
        int NumberOfDestroyedBoats=0;
        while(NumberOfDestroyedBoats!=ships.size()){
            sleep(2000);
            int [] coords = new int[2];
            Hit h = ai.sendHit(coords);
            if(h.getValue()<=0){
                System.out.println(h);
            }else{
                NumberOfDestroyedBoats++;
                System.out.println(h+" coule");
            }
            board.print();
        }
    }
}