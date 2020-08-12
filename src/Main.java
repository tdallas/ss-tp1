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
        BruteForceMethod bfm = new BruteForceMethod(particles, 5, 1, true);
        CellIndexMethod cim = new CellIndexMethod(particles, true, 1, 20, 19);
        FileWriter.writeInput(particles, "test", 20.0, 500);
        FileWriter.writeOutput(particles, cim.getNeighbours(), "test_bfm", bfm.getTotalTime(), bfm.getInteractionRadius());
        FileWriter.writeOutput(particles, cim.getNeighbours(), "test_cim", cim.getTotalTime(), cim.getInteractionRadius());
    }
}
