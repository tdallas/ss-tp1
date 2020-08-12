package model;

import java.util.Objects;

public class Particle {
    private final int id;
    private final double x;
    private final double y;
    private final double radius;

    public Particle(int id, double x, double y, double radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return Double.compare(particle.x, x) == 0 &&
                Double.compare(particle.y, y) == 0 &&
                Double.compare(particle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, radius);
    }
}
