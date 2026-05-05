import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {

        // TASK 1
        BufferedReader b1 = new BufferedReader(new FileReader("matches.csv"));
        String s = b1.readLine();

        HashMap<String, Integer> m = new HashMap<>();

        while ((s = b1.readLine()) != null) {
            String[] a = s.split(",");
            String y = a[1];
            m.put(y, m.getOrDefault(y, 0) + 1);
        }

        System.out.println("Matches per year: " + m);

        // TASK 2
        BufferedReader b2 = new BufferedReader(new FileReader("matches.csv"));
        String s2 = b2.readLine();

        HashMap<String, Integer> m1 = new HashMap<>();

        while ((s2 = b2.readLine()) != null) {
            String[] a = s2.split(",");
            String w = a[10];

            if (w != null && !w.equals("") && !w.equals("winner")) {
                m1.put(w, m1.getOrDefault(w, 0) + 1);
            }
        }

        System.out.println("Matches won by teams: " + m1);

        // TASK 3
        Set<Integer> set2016 = new HashSet<>();

        BufferedReader b3 = new BufferedReader(new FileReader("matches.csv"));
        String s3 = b3.readLine();

        while ((s3 = b3.readLine()) != null) {
            String[] a = s3.split(",");
            int season = Integer.parseInt(a[1]);
            int matchId = Integer.parseInt(a[0]);

            if (season == 2016) {
                set2016.add(matchId);
            }
        }

        BufferedReader b4 = new BufferedReader(new FileReader("deliveries.csv"));
        String d1 = b4.readLine();

        HashMap<String, Integer> extraRuns = new HashMap<>();

        while ((d1 = b4.readLine()) != null) {
            String[] a = d1.split(",");

            int matchId = Integer.parseInt(a[0]);
            String team = a[3];
            int er = Integer.parseInt(a[16]);

            if (set2016.contains(matchId)) {
                extraRuns.put(team, extraRuns.getOrDefault(team, 0) + er);
            }
        }

        System.out.println("Extra runs (2016): " + extraRuns);

        // TASK 4
        Set<Integer> set2015 = new HashSet<>();

        BufferedReader b5 = new BufferedReader(new FileReader("matches.csv"));
        String s5 = b5.readLine();

        while ((s5 = b5.readLine()) != null) {
            String[] a = s5.split(",");
            int season = Integer.parseInt(a[1]);
            int matchId = Integer.parseInt(a[0]);

            if (season == 2015) {
                set2015.add(matchId);
            }
        }

        BufferedReader b6 = new BufferedReader(new FileReader("deliveries.csv"));
        String d2 = b6.readLine();

        HashMap<String, Integer> runs = new HashMap<>();
        HashMap<String, Integer> balls = new HashMap<>();

        while ((d2 = b6.readLine()) != null) {
            String[] a = d2.split(",");

            int matchId = Integer.parseInt(a[0]);
            String bowler = a[8];
            int run = Integer.parseInt(a[17]);

            if (set2015.contains(matchId)) {
                runs.put(bowler, runs.getOrDefault(bowler, 0) + run);
                balls.put(bowler, balls.getOrDefault(bowler, 0) + 1);
            }
        }

        List<Map.Entry<String, Double>> list = new ArrayList<>();

        for (String bowler : runs.keySet()) {
            double eco = runs.get(bowler) / (balls.get(bowler) / 6.0);
            list.add(new HashMap.SimpleEntry<>(bowler, eco));
        }

        list.sort((a, b) -> Double.compare(a.getValue(), b.getValue()));

        System.out.println("Top Economical Bowlers (2015):");

        for (int i = 0; i < Math.min(10, list.size()); i++) {
            System.out.println(list.get(i).getKey() + " → " + list.get(i).getValue());
        }
    // Which team scored the most total runs in IPL
        BufferedReader br = new BufferedReader(new FileReader("deliveries.csv"));
        String line = br.readLine();

        HashMap<String, Integer> teamRuns = new HashMap<>();

        while ((line = br.readLine()) != null) {
            String[] a = line.split(",");

            String team = a[2]; // batting team
            int r = Integer.parseInt(a[17]); // total runs

            teamRuns.put(team, teamRuns.getOrDefault(team, 0) + r);
        }

        System.out.println("Total runs scored by teams: " + teamRuns);

    }
}