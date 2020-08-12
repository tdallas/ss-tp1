package model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileWriter {
    public static void writeInput(List<Particle> particles, String filename){

    }

    public static void writeOutput(List<Particle> particles, Map<Particle, Set<Particle>> neighbours, String filename, Long timeSpent, Double interactionRadius){
        Path file = Paths.get("resources/output_" + filename + ".txt");
        List<String> lines = new ArrayList<>();

        lines.add("Time: " + timeSpent.toString() + " ms");
        lines.add("Interaction Radius: " + interactionRadius.toString());
        lines.add("Output:");
        for( Particle p: particles) {
            StringBuilder s = new StringBuilder(p.getId() + ": [ ");

            for(Particle q : neighbours.get(p)) {
                s.append(q.getId()).append(" ");
            }
            s.append("]");
            lines.add(s.toString());
        }

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
