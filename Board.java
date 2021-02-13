import java.util.*;
import java.lang.*;
import java.io.*;
public class Board implements IBoard{
    String name;
    int size;
    Boolean [][]hitGrid;
    ShipState[][]shipGrid;
    public Board(String _name, int _size){
        this.name=_name;
        this.size=_size;
        this.hitGrid = new Boolean[_size][_size];
        this.shipGrid = new ShipState[_size][_size];
        for(int i=0;i<_size;i++){
            for(int j=0;j<_size;j++){
                hitGrid[i][j]=null;
                shipGrid[i][j]=null;
            }
        }
    }
    public Board(String _name){
        this(_name, 10);
    }

    public void print(){
        String sizeAsString = Integer.toString(size);
        int space = sizeAsString.length();

        String spaces="";
        for(int i=0;i<space;i++)
            spaces+=" ";
        spaces+=" ";
        int totalLength = space+1+2*size +3 ;
        System.out.print("Navires:");
        for(int i=0;i<totalLength-"Navires:".length();i++){
            System.out.print(" ");
        }
        System.out.println("Frappes:");
        String line="";
        line+=spaces;
        for(int i=0;i<size;i++){
            line+=(char)('A'+i);
            line+=" ";
        }
        line+=" ";
        line+="  ";
        line+=spaces;
        for(int i=0;i<size;i++){
            line+=(char)('A'+i);
            line+=" ";
        }
        System.out.println(line);
        for(int i=0;i<size;i++){
            line=Integer.toString(i+1);
            int sz = line.length();
            for(int j=0;j<space-sz;j++)
                line+=" ";
            line+=" ";
            for(int j=0;j<size;j++){
                if(shipGrid[i][j]!=null)
                    line+=shipGrid[i][j].toString();
                else
                    line+=".";
                line+=" ";
            }
            line+=" ";
            line+="  ";
            line+=Integer.toString(i+1);
            for(int j=0;j<space-sz;j++)
                line+=" ";
            line+=" ";
            for(int j=0;j<size;j++){
                if(hitGrid[i][j]!=null && hitGrid[i][j]==false)
                    line+="X";
                else if(hitGrid[i][j]!=null && hitGrid[i][j]==true) {
                    line += ColorUtil.colorize("X", ColorUtil.Color.RED);
                    System.out.println(ColorUtil.colorize("X", ColorUtil.Color.RED));
                }
                else
                    line+='.';
                line+=" ";
            }
            System.out.println(line);
        }
    }

    public ShipState[][] getShipGrid() {
        return shipGrid;
    }

    public Boolean[][] getHitGrid() {
        return hitGrid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setHitGrid(Boolean[][] hitGrid) {
        this.hitGrid = hitGrid;
    }

    public void setShipGrid(ShipState[][] shipGrid) {
        this.shipGrid = shipGrid;
    }

    public void putShip(AbstractShip ship, int x, int y) throws Exception{
        x--;y--;
        int dx[]= {-1,1,0,0};
        int dy[]= {0,0,1,-1};
        int pos = ship.getOrientation().ordinal();
        for(int i=0;i<ship.getSize();i++){
            int nx = x+i*dx[pos];
            int ny = y+i*dy[pos];
            if(nx<0 || nx>=size || ny<0 || ny>=size || shipGrid[nx][ny]!=null){
                throw new Exception("Invalid Position");
                //return ;
            }
        }
        for(int i=0;i<ship.getSize();i++){
            shipGrid[x+i*dx[pos]][y+i*dy[pos]]=new ShipState(ship);
        }
    }

    public boolean hasShip(int x, int y){
        x--;y--;
        return (!shipGrid[x][y].isSunk());
    }

    public void setHit(Boolean hit, int x, int y){
        hitGrid[x-1][y-1]=hit;
    }

    public Boolean getHit(int x, int y){
        return hitGrid[x-1][y-1];
    }

}