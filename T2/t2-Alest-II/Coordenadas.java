public class Coordenadas {
    private int x, y;
    private Coordenadas parent;

    public Coordenadas(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null; 
    }

    public Coordenadas(int x, int y, Coordenadas parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Coordenadas getParent() { return parent; }
}