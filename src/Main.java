import engine.BruteForceMethod;
import engine.CellIndexMethod;
import model.FileWriter;
import model.Particle;
import model.ParticlesGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args){
        ParticlesGenerator particlesGenerator = new ParticlesGenerator(0.25, 20, 500);
        List<Particle> particles = particlesGenerator.getParticles();
        BruteForceMethod bfm = new BruteForceMethod(particles, 20, 1.0, true);
        CellIndexMethod cim = new CellIndexMethod(particles, true, 1.0, 20, 10);
        FileWriter.writeInput(particles, "test", 20.0, 500);
        FileWriter.writeOutput(particles, bfm.getNeighbours(), "test_bfm", bfm.getTotalTime(), bfm.getInteractionRadius());
        FileWriter.writeOutput(particles, cim.getNeighbours(), "test_cim", cim.getTotalTime(), cim.getInteractionRadius());
    }
}
