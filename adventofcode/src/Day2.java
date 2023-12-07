import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day2 {
    private static final Map<String, Integer> cubesColors = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14);
    private static int result = 0;

    public static void main(String[] args) {
        String filePath = "inputPuzzle\\day2";
//        System.out.println(processFile(filePath));
//        String filePath = "inputPuzzle\\day2Test";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                int gameId = Integer.parseInt(line.split("Game")[1].trim().split(":")[0]);
//                result += gameId;
                String[] cubesSets = line.split(":")[1].split(";");
//                if (!countId(cubesSets)) {
//                    result -= gameId;
//                }
                result += getMinSetCubs(cubesSets);
            }
            System.out.println(result);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static int getMinSetCubs(String[] args) {
        Map<String, Integer> minCubSets = new HashMap<>(
                Map.of("red", 0, "green", 0, "blue", 0));
        for (String sets : args) {
            String[] set = sets.split(",");
            for (String cube : set) {
                int number = Integer.parseInt(cube.split("\\s+")[1]);
                String color = cube.split("\\s+")[2];
                if (minCubSets.get(color) < number) {
                    minCubSets.put(color, number);
                }
            }
        }
        return minCubSets
                .keySet()
                .stream()
                .map(minCubSets::get)
                .reduce(1, (a, b) -> a * b);
    }

    private static boolean countId(String[] args) {
        for (String sets : args) {
            String[] set = sets.split(",");
            for (String cube : set) {
                int number = Integer.parseInt(cube.split("\\s+")[1]);
                String color = cube.split("\\s+")[2];
                if (cubesColors.get(color) < number) {
                    return false;
                }
            }
        }
        return true;
    }
}
