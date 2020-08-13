import engine.BruteForceMethod;
import engine.CellIndexMethod;
import model.FileWriter;
import model.Particle;
import model.ParticlesGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args){
//        for(int i=499; i<5000;i+=500) {
//            ParticlesGenerator particlesGenerator = new ParticlesGenerator(0.25, 20, i +1);
//            List<Particle> particles = particlesGenerator.getParticles();
//            FileWriter.writeInput(particles, "test_" + (i + 1), 20.0, i + 1);
//
//
//            BruteForceMethod bfm = new BruteForceMethod(particles, 20, 1.0, true);
//            FileWriter.writeOutput(particles, bfm.getNeighbours(), "test_bfm_" + (i + 1), bfm.getTotalTime(), bfm.getInteractionRadius());
//
//            CellIndexMethod cim1 = new CellIndexMethod(particles, true, 1.0, 20, 2);
//            FileWriter.writeOutput(particles, cim1.getNeighbours(), "test_cim_m_2_" + (i + 1), cim1.getTotalTime(), cim1.getInteractionRadius());
//
//            for(int m = 5; m < 21; m+=5) {
//                CellIndexMethod cim = new CellIndexMethod(particles, true, 1.0, 20, m);
//                FileWriter.writeOutput(particles, cim.getNeighbours(), "test_cim_m_" + m + "_" + (i + 1), cim.getTotalTime(), cim.getInteractionRadius());
//            }
//        }
        ParticlesGenerator particlesGenerator = new ParticlesGenerator(0.25, 20, 500);
        List<Particle> particles = particlesGenerator.getParticles();
        FileWriter.writeInput(particles, "test", 20.0, 500);


        BruteForceMethod bfm = new BruteForceMethod(particles, 20, 1.0, true);
        FileWriter.writeOutput(particles, bfm.getNeighbours(), "test_bfm", bfm.getTotalTime(), bfm.getInteractionRadius());

        CellIndexMethod cim = new CellIndexMethod(particles, true, 1.0, 20, 10);
        FileWriter.writeOutput(particles, cim.getNeighbours(), "test_cim", cim.getTotalTime(), cim.getInteractionRadius());
    }
}
