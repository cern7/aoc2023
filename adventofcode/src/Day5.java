import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;

public class Day5 {
    static class Data {
        long source;
        long destination;
        long range;

        Data(long source, long destination, long range) {
            this.source = source;
            this.destination = destination;
            this.range = range;
        }

        public long getSource() {
            return source;
        }

        public long getDestination() {
            return destination;
        }

        public long getRange() {
            return range;
        }

        public String toString() {
            return String.format("{Source:%d; Destination:%d; Range:%d;}", source, destination, range);
        }
    }

    private static List<Long> seeds = new ArrayList<>();
    private static Map<Integer, List<Data>> maps = new HashMap<>();

    public static void main(String[] args) {
//        String filePath = "inputPuzzle\\day5";
        String filePath = "inputPuzzle\\day5Test";

        long location = Long.MAX_VALUE;
        getFullMaps(filePath);

        for (int i = 0; i < seeds.size(); i += 2) {
            System.out.println("i=" + i);
            long minDiff = 0;
            for (long j = 0; j < seeds.get(i + 1); j += minDiff) {
                long seed = seeds.get(i) + j;
                System.out.printf("Parsing for Seed: {%d}\n", seed);
                System.out.printf("minDiff: {%d}\n", minDiff);
                minDiff = Long.MAX_VALUE;
                for (int map = 1; map < 8; map++) {
                    List<Data> tempMaps = maps.get(map);
//                    System.out.printf("Checking in map {%d}\n Content: %s", map, tempMaps.toString());
                    for (int k = 0; k < tempMaps.size(); k++) {
//                        System.out.printf("\nmapping for seed {%d} ", seed);
                        long prevDest = tempMaps.get(k).getDestination();
                        long prevRange = tempMaps.get(k).getRange();
                        long prevSource = tempMaps.get(k).getSource();
                        if (seed >= prevSource && seed <= prevSource + prevRange) {
                            minDiff = Math.min(minDiff, (prevRange + seed - prevSource));
                            seed = prevDest + seed - prevSource;
                            break;
                        }
//                        System.out.printf("is {%d}\n", seed);
                    }
                }
                location = Math.min(location, seed);
            }
        }
        System.out.println("the lowest location number can be obtained from seed number :" + location);
        long smallestLocation = 0;

        int currentMap = 1;

        Data mappingSoil = null;
        for (Data mapping : maps.get(currentMap)) {
            if (location >= mapping.getSource()
                    && location <= mapping.getSource() + mapping.getRange()) {
                mappingSoil = new Data(location,
                        mapping.getDestination() + location - mapping.getSource(),
                        mapping.getRange());
                break;
            } else {
                mappingSoil = new Data(location, location, 1L);
            }
        }
//            Data mappingSoil = maps.get(currentMap++)
//                    .stream()
//                    .filter(x -> seed >= x.getSource()
//                            && seed <= x.getSource() + x.getRange())
//                    .findFirst()
//                    .orElse(new Data(seed, seed, 1L));

        currentMap++;
        Data mappingFertilizer = null;
        for (Data mapping : maps.get(currentMap)) {

            long prevDest = mappingSoil.getDestination();
            long currDest = mapping.getDestination();
            long currSource = mapping.getSource();
            long currRange = mapping.getRange();

            if (prevDest >= mapping.getSource()
                    && prevDest <= currSource + currRange) {
                mappingFertilizer = new Data(prevDest,
                        currDest + prevDest - currSource,
                        currRange);
                break;
            } else {
                mappingFertilizer = new Data(prevDest, prevDest, 1L);
            }
        }

        currentMap++;
        Data mappingWater = null;
        for (Data mapping : maps.get(currentMap)) {

            long prevDest = mappingFertilizer.getDestination();
            long currDest = mapping.getDestination();
            long currSource = mapping.getSource();
            long currRange = mapping.getRange();

            if (prevDest >= mapping.getSource()
                    && prevDest <= currSource + currRange) {
                mappingWater = new Data(prevDest,
                        currDest + prevDest - currSource,
                        currRange);
                break;
            } else {
                mappingWater = new Data(prevDest, prevDest, 1L);
            }
        }

        currentMap++;
        Data mappingLight = null;
        for (Data mapping : maps.get(currentMap)) {

            long prevDest = mappingWater.getDestination();
            long currDest = mapping.getDestination();
            long currSource = mapping.getSource();
            long currRange = mapping.getRange();

            if (prevDest >= mapping.getSource()
                    && prevDest <= currSource + currRange) {
                mappingLight = new Data(prevDest,
                        currDest + prevDest - currSource,
                        currRange);
                break;
            } else {
                mappingLight = new Data(prevDest, prevDest, 1L);
            }
        }

        currentMap++;
        Data mappingTemp = null;
        for (Data mapping : maps.get(currentMap)) {

            long prevDest = mappingLight.getDestination();
            long currDest = mapping.getDestination();
            long currSource = mapping.getSource();
            long currRange = mapping.getRange();

            if (prevDest >= mapping.getSource()
                    && prevDest <= currSource + currRange) {
                mappingTemp = new Data(prevDest,
                        currDest + prevDest - currSource,
                        currRange);
                break;
            } else {
                mappingTemp = new Data(prevDest, prevDest, 1L);
            }
        }

        currentMap++;
        Data mappingHumidity = null;
        for (Data mapping : maps.get(currentMap)) {

            long prevDest = mappingTemp.getDestination();
            long currDest = mapping.getDestination();
            long currSource = mapping.getSource();
            long currRange = mapping.getRange();

            if (prevDest >= mapping.getSource()
                    && prevDest <= currSource + currRange) {
                mappingHumidity = new Data(prevDest,
                        currDest + prevDest - currSource,
                        currRange);
                break;
            } else {
                mappingHumidity = new Data(prevDest, prevDest, 1L);
            }
        }

        currentMap++;
        Data mappingLocation = null;
        for (Data mapping : maps.get(currentMap)) {

            long prevDest = mappingHumidity.getDestination();
            long currDest = mapping.getDestination();
            long currSource = mapping.getSource();
            long currRange = mapping.getRange();

            if (prevDest >= mapping.getSource()
                    && prevDest <= currSource + currRange) {
                mappingLocation = new Data(prevDest,
                        currDest + prevDest - currSource,
                        currRange);
                break;
            } else {
                mappingLocation = new Data(prevDest, prevDest, 1L);
            }
        }


        smallestLocation = mappingLocation.getDestination();


        System.out.println(smallestLocation);
    }


    private static void saveSeeds(String args) {

        for (String seed : args.split(" ")) {
            seeds.add(Long.valueOf(seed));
        }
    }

    private static void getFullMaps(String filePath) {
        String line;
        try {
            int map = 0;

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            List<Data> listOfMappings = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("seeds:")) {
                    // function to get all seeds no saved
                    saveSeeds(line.split(":")[1].trim());
                } else if (!line.isBlank()) {
                    if (line.contains("map:")) {
                        listOfMappings = new ArrayList<>();
                        map++;
                    } else {
                        String[] currMap = line.split(" ");
                        long destinationStart = Long.parseLong(currMap[0]);
                        long sourceStart = Long.parseLong(currMap[1]);
                        long range = Long.parseLong(currMap[2]);
                        listOfMappings.add(new Data(sourceStart, destinationStart, range));

                    }
                } else if (map != 0) {
                    maps.put(map, listOfMappings);
                }

                // method that will actually save parse the numbers and save
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
