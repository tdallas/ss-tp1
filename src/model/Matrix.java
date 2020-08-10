package model;

public class Matrix {
    private final Cell[][] matrix;
    private final double cellLength;
    private final double l;
    private final int m;

    public Matrix(double l, int m) {
        this.matrix = new Cell[m][m];
        this.l = l;
        this.m = m;
        this.cellLength = l / m;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public double getCellLength() {
        return cellLength;
    }

    public double getL() {
        return l;
    }

    public int getM() {
        return m;
    }
}
