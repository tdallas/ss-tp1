package engine;

import model.Cell;
import model.Matrix;
import model.Particle;

import java.util.*;

public class CellIndexMethod {
    private final Matrix matrix;
    private final Map<Particle, Set<Particle>> neighbours;
    private final boolean periodicBoundary;
    private final double interactionRadius;
    private final double length;
    private final int size;
    private final double cellLength;
    private final long startTime;
    private final long endTime;
    private double maxRadius;

    public CellIndexMethod(List<Particle> particles, boolean periodicBoundary, double interactionRadius, double length, int size) throws IllegalArgumentException{
        this.matrix = new Matrix(length, size);
        this.periodicBoundary = periodicBoundary;
        this.interactionRadius = interactionRadius;
        this.length = length;
        this.size = size;
        this.cellLength = length / size;
        this.neighbours = new HashMap<>();
        this.maxRadius = 0;
        for (Particle p : particles) {
            if(p.getRadius() > maxRadius){
                maxRadius = p.getRadius();
            }
            neighbours.put(p, new HashSet<>());
        }
        //Check argument l/m > rmax + rc
        if((length / size) < (maxRadius + interactionRadius)){
            throw new IllegalArgumentException("Incorrect arguments for Cell Index Method.");
        }
        fillMatrix(particles);
        startTime = System.nanoTime();
        fillNeighbours();
        endTime = System.nanoTime();
    }

    public Map<Particle, Set<Particle>> getNeighbours() {
        return neighbours;
    }

    public long getTotalTime(){
        return endTime - startTime;
    }

    public double getLength() {
        return length;
    }

    public double getInteractionRadius() {
        return interactionRadius;
    }

    public int getSize() {
        return size;
    }

    private void fillMatrix(List<Particle> particles) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.getMatrix()[i][j] = new Cell();
            }
        }
        for (Particle particle : particles) {
            matrix.getMatrix()[(int) (particle.getX() / cellLength)][(int) (particle.getY() / cellLength)].addParticle(particle);
        }
    }

    private void fillNeighbours() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (Particle p : matrix.getMatrix()[x][y].getParticles()) {
                    //ESTA FEO, SE PUEDE PONER MAS PROLIJO
                    //DIRECCIONES: CENTRO, ABAJO, ABAJO DERECHA, DERECHA, ARIBA DERECHA
                    int[] dx = {0, 0, 1, 1, 1};
                    int[] dy = {0, 1, 1, 0, -1};
                    for (int k = 0; k < 5; k++) {
                        int xComp = x + dx[k];
                        int yComp = y + dy[k];
                        if (xComp < size && yComp < size && xComp >= 0 && yComp >= 0) {
                            for (Particle q : matrix.getMatrix()[xComp][yComp].getParticles()) {
                                addNeighbour(p, q);
                            }
                        } else if (periodicBoundary) {
                            xComp = (xComp + size) % size;
                            yComp = (yComp + size) % size;
                            for (Particle q : matrix.getMatrix()[xComp][yComp].getParticles()) {
                                addNeighbour(p, q);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addNeighbour(Particle p, Particle q) {
        if(p.equals(q)){
            return;
        }

        double pX = p.getX();
        double pY = p.getY();
        double qX = q.getX();
        double qY = q.getY();

        if (periodicBoundary) {
            if (pX < qX && pX + 2 * cellLength <= qX) {
                pX += length;
            }
            if (qX < pX && qX + 2 * cellLength <= pX) {
                qX += length;
            }
            if (pY < qY && pY + 2 * cellLength <= qY) {
                pY += length;
            }
            if (qY < pY && qY + 2 * cellLength <= pY) {
                qY += length;
            }
        }

        double distance = Math.hypot(pX - qX, pY - qY) - p.getRadius() - q.getRadius();

        if (distance < interactionRadius) {
            neighbours.get(p).add(q);
            neighbours.get(q).add(p);
        }
    }
}