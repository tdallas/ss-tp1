package model;

public class Matrix {
    private final Cell[][] matrix;
    private final double cellLength;
    private final double length;
    private final int size;

    public Matrix(double length, int size) {
        this.matrix = new Cell[size][size];
        this.length = length;
        this.size = size;
        this.cellLength = length / size;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public double getCellLength() {
        return cellLength;
    }

    public double getLength() {
        return length;
    }

    public int getSize() {
        return size;
    }
}
