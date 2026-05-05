import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        List<String[]> matches = loadCSV("matches.csv");
        List<String[]> deliveries = loadCSV("deliveries.csv");

        // TASK 1: Matches per year
        Map<String, Integer> matchesPerYear = new HashMap<>();
        for (String[] row : matches) {
            matchesPerYear.put(row[1], matchesPerYear.getOrDefault(row[1], 0) + 1);
        }
        System.out.println("TASK 1 OUTPUT");
        System.out.println(matchesPerYear);

        // TASK 2: Toss winners
        Map<String, Integer> tossWins = new HashMap<>();
        for (String[] row : matches) {
            tossWins.put(row[10], tossWins.getOrDefault(row[10], 0) + 1);
        }
        System.out.println("\nTASK 2 OUTPUT");
        System.out.println(tossWins);

        // TASK 3: Matches in 2016
        Set<Integer> matches2016 = new HashSet<>();
        for (String[] row : matches) {
            if (row[1].equals("2016")) {
                matches2016.add(Integer.parseInt(row[0]));
            }
        }

        Map<String, Integer> runs2016 = new HashMap<>();
        for (String[] row : deliveries) {
            int matchId = Integer.parseInt(row[0]);

            if (matches2016.contains(matchId)) {
                String bowler = row[8];
                int runs = Integer.parseInt(row[17]);

                runs2016.put(bowler, runs2016.getOrDefault(bowler, 0) + runs);
            }
        }

        System.out.println("\nTASK 3 OUTPUT");
        System.out.println(runs2016);

        // TASK 4: Economy rate (2015)
        Set<Integer> matches2015 = new HashSet<>();
        for (String[] row : matches) {
            if (row[1].equals("2015")) {
                matches2015.add(Integer.parseInt(row[0]));
            }
        }

        Map<String, double[]> bowlerStats = new HashMap<>();

        for (String[] row : deliveries) {
            int matchId = Integer.parseInt(row[0]);

            if (matches2015.contains(matchId)) {
                String bowler = row[8];
                int runs = Integer.parseInt(row[17]);

                bowlerStats.putIfAbsent(bowler, new double[2]);
                bowlerStats.get(bowler)[0] += runs;
                bowlerStats.get(bowler)[1] += 1;
            }
        }

        List<Map.Entry<String, Double>> economyList = new ArrayList<>();

        for (String bowler : bowlerStats.keySet()) {
            double runs = bowlerStats.get(bowler)[0];
            double balls = bowlerStats.get(bowler)[1];

            double economy = runs / (balls / 6.0);

            economyList.add(new AbstractMap.SimpleEntry<>(bowler, economy));
        }

        economyList.sort(Comparator.comparingDouble(Map.Entry::getValue));

        System.out.println("\nTASK 4 OUTPUT");
        economyList.stream().limit(10).forEach(e ->
                System.out.println(e.getKey() + " -> " + e.getValue())
        );

        // TASK 5: Matches per city + max city
        Map<String, Integer> cityMatches = new HashMap<>();

        for (String[] row : matches) {
            String city = row[2];
            cityMatches.put(city, cityMatches.getOrDefault(city, 0) + 1);
        }

        System.out.println("\nTASK 5 OUTPUT");
        System.out.println(cityMatches);

        String maxCity = "";
        int max = 0;
        for (Map.Entry<String, Integer> e : cityMatches.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                maxCity = e.getKey();
            }
        }
        System.out.println("Max matches city: " + maxCity + " -> " + max);
    }

    static List<String[]> loadCSV(String file) throws Exception {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        }
        return data;
    }
}