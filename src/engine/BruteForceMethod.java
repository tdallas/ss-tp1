package engine;

import model.Particle;

import java.util.*;

public class BruteForceMethod {
    private final Map<Particle, Set<Particle>> neighbours;
    private final boolean periodicBoundary;
    private final double length;
    private final double interactionRadius;
    private double maxRadius;
    private final long startTime;
    private final long endTime;

    public BruteForceMethod(List<Particle> particles, final double length, final double interactionRadius, final boolean periodicBoundary) {
        this.length = length;
        this.interactionRadius = interactionRadius;
        this.periodicBoundary = periodicBoundary;
        neighbours = new HashMap<>();
        this.maxRadius = 0;
        for (Particle p : particles) {
            if(p.getRadius() > maxRadius){
                maxRadius = p.getRadius();
            }
            neighbours.put(p, new HashSet<>());
        }
        startTime = System.currentTimeMillis();
        fillNeighbours(particles);
        endTime = System.currentTimeMillis();
    }

    public Map<Particle, Set<Particle>> getNeighbours() {
        return neighbours;
    }

    public double getInteractionRadius() {
        return interactionRadius;
    }

    public long getTotalTime(){
        return endTime - startTime;
    }

    private void fillNeighbours(List<Particle> particles) {
        for (int i = 0; i < particles.size(); i++) {
            for (int j = i + 1; j < particles.size(); j++) {
                addNeighbour(particles.get(i), particles.get(j));
            }
        }
    }

    private void addNeighbour(Particle p, Particle q) {
        if (p.equals(q)) {
            return;
        }

        double pX = p.getX();
        double pY = p.getY();
        double qX = q.getX();
        double qY = q.getY();

        if (periodicBoundary) {
            if (pX < qX && pX + maxRadius * (length / interactionRadius) <= qX) {
                pX += length;
            }
            if (qX < pX && qX + maxRadius * (length / interactionRadius)  <= pX) {
                qX += length;
            }
            if (pY < qY && pY + maxRadius * (length / interactionRadius)  <= qY) {
                pY += length;
            }
            if (qY < pY && qY + maxRadius * (length / interactionRadius)  <= pY) {
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
