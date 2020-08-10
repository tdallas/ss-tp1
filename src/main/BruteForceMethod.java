package main;

import model.Particle;

import java.util.*;

public class BruteForceMethod {
    private final Map<Particle, Set<Particle>> neighbours;
    private final boolean periodicBounds;
    private final double rc;
    private final double l;

    public BruteForceMethod(List<Particle> particles, double l, double rc, boolean periodicBounds) {
        this.rc = rc;
        this.l = l;
        this.periodicBounds = periodicBounds;
        neighbours = new HashMap<>();
        for (Particle p : particles) {
            neighbours.put(p, new HashSet<>());
        }
        fillNeighbours(particles);
    }

    public Map<Particle, Set<Particle>> getNeighbours() {
        return neighbours;
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

        if (periodicBounds) {
            //HAY QUE VERLO
        }
        else{
            if(p.interactsWith(q, rc)) {
                neighbours.get(p).add(q);
                neighbours.get(q).add(p);
            }
        }
    }
}
