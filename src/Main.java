import engine.BruteForceMethod;
import engine.CellIndexMethod;
import model.FileWriter;
import model.Particle;
import model.ParticlesGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        ParticlesGenerator particlesGenerator = new ParticlesGenerator(0.25, 20, 500);
        List<Particle> particles = particlesGenerator.getParticles();
//        Particle p1 = new Particle(1,0.5, 0.5, 0.25);
//        Particle p2 = new Particle(2,4.5, 0.5, 0.25);
//        Particle p3 = new Particle(3,1.75, 2.5, 0.25);
//        Particle p4 = new Particle(4,2.5, 2.5, 0.25);
//        particles = new ArrayList<>();
//        particles.add(p1);
//        particles.add(p2);
//        particles.add(p3);
//        particles.add(p4);

        BruteForceMethod bfm = new BruteForceMethod(particles, 5, 0.5, true);
        CellIndexMethod cim = new CellIndexMethod(particles, true, 1, 20, 19);
        FileWriter.writeInput(particles, "test", 20.0, 500);
        FileWriter.writeOutput(particles, cim.getNeighbours(), "test_bfm", bfm.getTotalTime(), bfm.getInteractionRadius());
        FileWriter.writeOutput(particles, cim.getNeighbours(), "test_cim", cim.getTotalTime(), cim.getInteractionRadius());
    }
}
