import java.util.*;
import java.lang.*;
import java.io.*;
public class Board implements IBoard{
    String name;
    int size;
    boolean [][]hitGrid;
    char [][]shipGrid;
    public Board(String _name, int _size){
        this.name=_name;
        this.size=_size;
        this.hitGrid = new boolean[_size][_size];
        this.shipGrid = new char[_size][_size];
        for(int i=0;i<_size;i++){
            for(int j=0;j<_size;j++){
                hitGrid[i][j]=false;
                shipGrid[i][j]='.';
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
                line+=shipGrid[i][j];
                line+=" ";
            }
            line+=" ";
            line+="  ";
            line+=Integer.toString(i+1);
            for(int j=0;j<space-sz;j++)
                line+=" ";
            line+=" ";
            for(int j=0;j<size;j++){
                if(hitGrid[i][j])
                    line+="X";
                else
                    line+=".";
                line+=" ";
            }
            System.out.println(line);
        }
    }

    public char[][] getShipGrid() {
        return shipGrid;
    }

    public boolean[][] getHitGrid() {
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

    public void setHitGrid(boolean[][] hitGrid) {
        this.hitGrid = hitGrid;
    }

    public void setShipGrid(char[][] shipGrid) {
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
            if(nx<0 || nx>=size || ny<0 || ny>=size || shipGrid[nx][ny]!='.'){
                throw new Exception("Invalid Position");
                //return ;
            }
        }
        for(int i=0;i<ship.getSize();i++){
            shipGrid[x+i*dx[pos]][y+i*dy[pos]]=ship.getLabel();
        }
    }

    public boolean hasShip(int x, int y){
        x--;y--;
        return (shipGrid[x][y]!='.');
    }

    public void setHit(boolean hit, int x, int y){
        hitGrid[x-1][y-1]=hit;
    }

    public Boolean getHit(int x, int y){
        return hitGrid[x-1][y-1];
    }

}