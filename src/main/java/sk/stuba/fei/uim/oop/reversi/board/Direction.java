package sk.stuba.fei.uim.oop.reversi.board;

public class Direction {
    int[] x;
    int[] y;

    public Direction(){
        x = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        y = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    }

    public int getX(int i) {
        return x[i];
    }

    public int getY(int i) {
        return y[i];
    }
}
