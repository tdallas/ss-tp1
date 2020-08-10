package model;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final Set<Particle> particles;

    public Cell() {
        this.particles = new HashSet<>();
    }

    public Set<Particle> getParticles() {
        return particles;
    }

    public boolean addParticle(Particle newParticle){
        return particles.add(newParticle);
    }
}
