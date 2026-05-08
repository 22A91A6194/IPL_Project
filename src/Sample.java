import java.util.*;
import java.io.*;
public class Sample {
    public static void main(String[] args) throws Exception
    {
        List<String[]> matches = loadCSV("matches.csv");
        List<String[]> deliveries = loadCSV("deliveries.csv");

        Map<String, Integer> matchesPerYear = new HashMap<>();
        for(String[] row: matches)
        {
            matchesPerYear.put(row[1], matchesPerYear.getOrDefault(row[1], 0) + 1);
        }
        System.out.println("The Matches Per year: " + matchesPerYear);


        Map<String, Integer> matchesWin = new HashMap<>();
        for(String[] row: matches)
        {
            if(row[10] != null && !row[10].isEmpty())
            {
                matchesWin.put(row[10], matchesWin.getOrDefault(row[10], 0) + 1);
            }
        }
        System.out.println("The matches win per year: " + matchesWin);


        Set<Integer> match2016 = new HashSet<>();
        for(String[] row: matches)
        {
            if(row[1].equals("2016"))
            {
                match2016.add(Integer.parseInt(row[0]));
            }
        }

        Map<String, Integer> extraRuns = new HashMap<>();
        for(String[] row: deliveries)
        {
            int id = Integer.parseInt(row[0]);
            if(match2016.contains(id))
            {
                String team = row[3];
                int runs = Integer.parseInt(row[16]);
                extraRuns.put(team, extraRuns.getOrDefault(team, 0) + runs);
            }
        }
        System.out.println(extraRuns);


        Set<Integer> match2015 = new HashSet<>();
        for(String[] row: matches)
        {
            if(row[1].equals("2015"))
            {
                match2015.add(Integer.parseInt(row[0]));
            }
        }

        Map<String, double[]> bowlerRunsAndBalls = new HashMap<>();
        for(String[] row: deliveries)
        {
            int match_id = Integer.parseInt(row[0]);
            if(match2015.contains(match_id))
            {
                String bowler = row[8];
                int runs = Integer.parseInt(row[17]);
                bowlerRunsAndBalls.putIfAbsent(bowler, new double[2]);
                bowlerRunsAndBalls.get(bowler)[0] += runs;
                bowlerRunsAndBalls.get(bowler)[1] += 1;
            }
        }

        List<Map.Entry<String, Double>> economyList = new ArrayList<>();

        for(String bowler: bowlerRunsAndBalls.keySet())
        {
            double runs = bowlerRunsAndBalls.get(bowler)[0];
            double balls = bowlerRunsAndBalls.get(bowler)[1];
            double result = runs / (balls / 6.0);
            economyList.add(new AbstractMap.SimpleEntry<>(bowler, result));
        }
        economyList.sort(Comparator.comparingDouble(Map.Entry::getValue));
        economyList.stream().limit(10).forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

    }
    static List<String[]> loadCSV(String file) throws Exception
    {
        List<String[]> data = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null)
            {
                data.add(line.split(","));
            }
        }
        return data;
    }
}
