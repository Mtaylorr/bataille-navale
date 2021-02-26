package ensta;

import ensta.Players.Game;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            new Game().init().run();
        }catch(Exception e){
            System.out.println("Some problem happened !! ");
        }
    }
}
