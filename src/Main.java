import engine.BruteForceMethod;
import engine.CellIndexMethod;
import model.FileWriter;
import model.Particle;
import model.ParticlesGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args){
        ParticlesGenerator particlesGenerator = new ParticlesGenerator(0.25, 20, 5000);
        List<Particle> particles = particlesGenerator.getParticles();
        FileWriter.writeInput(particles, "variable_m" , 20.0, 5000);
        for(int m = 1; m < 14; m++) {
            CellIndexMethod cim = new CellIndexMethod(particles, true, 1.0, 20, m);
            FileWriter.writeOutput(particles, cim.getNeighbours(), "variable_m_" + m, cim.getTotalTime(), cim.getInteractionRadius());
        }
//        double r = 0.25;
//        double l = 20.0;
//        double rc = 1.0;
//        int m = 10;
//        int quantity = 1000;
//        boolean periodicBoundary = true;
//
//        ParticlesGenerator particlesGenerator = new ParticlesGenerator(r, l, quantity);
//        List<Particle> particles = particlesGenerator.getParticles();
//        FileWriter.writeInput(particles, "prueba", l, quantity);
//
//
//        BruteForceMethod bfm = new BruteForceMethod(particles, l, rc, periodicBoundary);
//        FileWriter.writeOutput(particles, bfm.getNeighbours(), "prueba_bfm", bfm.getTotalTime(), bfm.getInteractionRadius());
//
//        CellIndexMethod cim = new CellIndexMethod(particles, periodicBoundary, rc, l, m);
//        FileWriter.writeOutput(particles, cim.getNeighbours(), "prueba_cim", cim.getTotalTime(), cim.getInteractionRadius());
    }
}
