package main;

import model.Cell;
import model.Matrix;
import model.Particle;

import java.util.*;

public class CellIndexMethod {
    private final Matrix matrix;
    private final Map<Particle, Set<Particle>> neighbours;
    private final boolean periodicBounds;
    private final double rc;
    private final double l;
    private final int m;
    private final double cellLength;

    public CellIndexMethod(List<Particle> particles, boolean periodicBounds, double rc, double l, int m) {
        this.matrix = new Matrix(l, m);
        this.periodicBounds = periodicBounds;
        this.rc = rc;
        this.l = l;
        this.m = m;
        this.cellLength = l / m;
        this.neighbours = new HashMap<>();
        for (Particle p : particles) {
            neighbours.put(p, new HashSet<>());
        }
        fillMatrix(particles);
        fillNeighbours();
    }

    public Map<Particle, Set<Particle>> getNeighbours() {
        return neighbours;
    }

    private void fillMatrix(List<Particle> particles) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix.getMatrix()[i][j] = new Cell();
            }
        }
        for (Particle particle : particles) {
            matrix.getMatrix()[(int) (particle.getX() / cellLength)][(int) (particle.getY() / cellLength)].addParticle(particle);
        }
    }

    private void fillNeighbours() {
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < m; y++) {
                for (Particle p : matrix.getMatrix()[x][y].getParticles()) {
                    //ESTA FEO, SE PUEDE PONER MAS PROLIJO
                    //DIRECCIONES: ARRIBA, ABAJO, DERECHA, IZQUIERDA
                    int[] dx = { 0, 0, 0, 1, -1 };
                    int[] dy = { 0, 1, -1, 0, 0 };
                    for (int k = 0; k < 5; k++) {
                        int xx = x + dx[k];
                        int yy = y + dy[k];
                        if (periodicBounds) {
                            for (Particle q : matrix.getMatrix()[xx][yy].getParticles()) {
                                addNeighbour(p, q);
                            }
                        }
                        else{
                            if (xx < m && yy < m && xx >= 0 && yy >= 0) {
                                for (Particle q : matrix.getMatrix()[xx][yy].getParticles()) {
                                    addNeighbour(p, q);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private void addNeighbour(Particle p, Particle q) {
        if (p.equals(q)) {
            return;
        }

        if (periodicBounds) {
            //HAY QUE VERLO
        }
        else {
            if (p.interactsWith(q, rc)) {
                neighbours.get(p).add(q);
                neighbours.get(q).add(p);
            }
        }
    }
}
