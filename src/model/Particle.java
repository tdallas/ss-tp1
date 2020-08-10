package model;

import java.util.Objects;

public class Particle {
    private final double x;
    private final double y;
    private final double radius;

    public Particle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
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

    public double distanceTo(final Particle particle){
        return Math.hypot(x - particle.x, y - particle.y) - radius - particle.radius;
    }

    public boolean interactsWith(final Particle particle, final double rc){
        double distance = distanceTo(particle);
        if (distance < 0){
            return true;
        }
        return distance < rc;
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
