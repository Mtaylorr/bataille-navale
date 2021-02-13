public abstract class AbstractShip{
    private char label;
    private String name;
    private int size;
    private Orientation orientation;
    public AbstractShip(String _name, char _label, int _size, Orientation _orienation){
        this.name=_name;
        this.label=_label;
        this.size=_size;
        this.orientation=_orienation;
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