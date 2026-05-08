import java.util.*;
import java.io.*;
import java.lang.*;
public class Practice {
    public static void main(String[] args) throws Exception
    {
        List<String[]> matches = loadCSV("matches.csv");
        List<String[]> deliveries = loadCSV("deliveries.csv");

        HashMap<String, Integer> matchesPerYear = new HashMap<>();
        for(String[] row: matches)
        {
            matchesPerYear.put(row[1], matchesPerYear.getOrDefault(row[1], 0) + 1);
        }
        System.out.println(matchesPerYear);

        int max = 0;
        String maxMatchYear = "";
        for(Map.Entry<String, Integer> e: matchesPerYear.entrySet())
        {
            if(e.getValue() > max)
            {
                max = e.getValue();
                maxMatchYear = e.getKey();
            }
        }
        System.out.println("The max matches in year: " + maxMatchYear);

        HashMap<String, Integer> winMatches = new HashMap<>();
        for(String[] row: matches)
        {
            if(row[10] != null && !row[10].isEmpty())
            {
                winMatches.put(row[10], winMatches.getOrDefault(row[10], 0) + 1);
            }
        }
        System.out.println("Winning matches in each year: "+ winMatches);

        Set<Integer> match2016 = new HashSet<>();
        for(String[] row: matches)
        {
            if(row[1].equals("2016"))
            {
                match2016.add(Integer.parseInt(row[0]));
            }
        }
        System.out.println(match2016);

        HashMap<String, Integer> extraRuns = new HashMap<>();
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
        System.out.println("The extra runs per each team: " + extraRuns);


        Set<Integer> match2015 = new HashSet<>();
        for(String[] row: matches)
        {
            if(row[1].equals("2015"))
            {
                match2015.add(Integer.parseInt(row[0]));
            }
        }

        System.out.println("MAtches id in 2015: " + match2015);

        HashMap<String, double[]> bowler = new HashMap<>();
        for(String[] row: deliveries)
        {
            int id = Integer.parseInt(row[0]);
            if(match2015.contains(id))
            {
                String bowlerName = row[8];
                int runs = Integer.parseInt(row[17]);
                bowler.putIfAbsent(bowlerName,new double[2]);
                bowler.get(bowlerName)[0] += runs;
                bowler.get(bowlerName)[1] += 1;
            }
        }

        List<Map.Entry<String, Double>> economyList = new ArrayList<>();
        for(String row: bowler.keySet())
        {
            double run = bowler.get(row)[0];
            double balls = bowler.get(row)[1];
            double economy = run/(balls / 6.0);
            economyList.add(new AbstractMap.SimpleEntry<>(row, economy));
        }
        System.out.println(economyList);
        economyList.sort(Comparator.comparingDouble(Map.Entry::getValue));
        economyList.stream().limit(10).forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

        HashMap<String, Integer> sixesByBatsman = new HashMap<>();
        for(String[] row: deliveries)
        {
            String batsman = row[6];
            int runs = Integer.parseInt(row[15]);
            if(runs == 6)
            {
                sixesByBatsman.put(batsman, sixesByBatsman.getOrDefault(batsman, 0) + 1);
            }
        }
        System.out.println("\n\nTASK 5 Output");
        sixesByBatsman.entrySet().stream().sorted((a,b) -> b.getValue() - a.getValue()).limit(1).forEach(e -> System.out.println(e.getKey() + "-> " + e.getValue()));


        //TASK 6 matches played per teams
        HashMap<String, Integer> matchesPlayedPerTeam = new HashMap<>();
        for(String[] row: matches) {
            if (row[4] != null && row[5] != null && !row[4].isEmpty() && !row[5].isEmpty()) {
                matchesPlayedPerTeam.put(row[4], matchesPlayedPerTeam.getOrDefault(row[4], 0) + 1);
                matchesPlayedPerTeam.put(row[5], matchesPlayedPerTeam.getOrDefault(row[5], 0) + 1);
            }
        }
        System.out.println("Matches Played per teams:" + matchesPlayedPerTeam);

        //TASK 7 man of the match
        HashMap<String, Integer> manOfMatch = new HashMap<>();
        for(String[] row: matches)
        {
            if(row[13] != null && !row[13].isEmpty()) {
                manOfMatch.put(row[13], manOfMatch.getOrDefault(row[13], 0) + 1);
            }
        }
        System.out.println("\n\nThe man of match: "+ manOfMatch);

        String MatchMan = "";
        int maxMatchVal = 0;
        for(Map.Entry<String, Integer> e: manOfMatch.entrySet())
        {
            if(e.getValue() > maxMatchVal)
            {
                MatchMan = e.getKey();
                maxMatchVal = e.getValue();
            }
        }
        System.out.println("\nThe man of the match: "+ MatchMan + "The value is: " + maxMatchVal);

        //TASK 8 Bowler who conceded most runs
        HashMap<String, Integer> mostRuns = new HashMap<>();
        for(String[] row: deliveries)
        {
            int runs = Integer.parseInt(row[17]);
            String bowlerPerson = row[8];
            mostRuns.put(bowlerPerson, mostRuns.getOrDefault(bowlerPerson, 0) + runs);
        }

        int maxRuns = 0;
        String bowlerMaxRuns = "";
        for(Map.Entry<String, Integer> e: mostRuns.entrySet())
        {
            if(e.getValue() > maxRuns)
            {
                maxRuns = e.getValue();
                bowlerMaxRuns = e.getKey();
            }
        }

        System.out.println("The max runs by bowler: " + bowlerMaxRuns +" by runs " + maxRuns);


        //TASK Toss Win Teams Count
        HashMap<String, Integer> tossWin = new HashMap<>();
        for(String[] row: matches)
        {
            if(row[6]!=null && !row[6].isEmpty())
            {
                tossWin.put(row[6], tossWin.getOrDefault(row[6], 0) + 1);
            }
        }
        System.out.println("The toss win: " + tossWin);
    }

    static List<String[]> loadCSV(String file) throws Exception
    {
        List<String[]> data = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
