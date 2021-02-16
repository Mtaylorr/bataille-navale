package ensta;

public abstract class AbstractShip implements java.io.Serializable{
    protected char label;
    protected String name;
    protected int size;
    protected Orientation orientation;
    protected int strikeCount;
    public AbstractShip(String _name, char _label, int _size, Orientation _orienation){
        this.name=_name;
        this.label=_label;
        this.size=_size;
        this.orientation=_orienation;
    }

    public void addStrike(){
        strikeCount++;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public boolean isSunk(){
        return (strikeCount==size);
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public char getLabel() {
        return label;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}