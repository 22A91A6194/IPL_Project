import java.io.*;
import java.util.*;
public class Main
{   public static void main(String[] args) throws Exception
    {
        List<String[]> matches = loadCSV("matches.csv");
        List<String[]> deliveries = loadCSV("deliveries.csv");

        Set<String> match2016 = new HashSet<>();
        for (String[] row : matches)
        {
            if (row.length > 1 && row[1].equals("2016"))
            {
                match2016.add(row[0]);
            }
        }
        Map<String, Integer> mostNoOfWickets = new HashMap<>();
        for(String[] row: deliveries)
        {
            if(match2016.contains(row[0]))
            {
                if(row.length > 19)
                {
                    String bowler = row[8];
                    String wicket = row[19];
                    if(!wicket.isEmpty() && wicket != null && !wicket.equals("run out"))
                    {
                        mostNoOfWickets.put(bowler, mostNoOfWickets.getOrDefault(bowler, 0) + 1);
                    }
                }
            }
        }
        System.out.println("\n" + mostNoOfWickets);
        System.out.println();
        mostNoOfWickets.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(2).forEach(e -> System.out.println(e.getKey() + " -> "+ e.getValue() + " wickets"));
    }
    //Input Reading
    static List<String[]> loadCSV(String file) throws Exception
    {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            System.out.println(br.readLine()); // skip header
            //System.out.println(br.readLine());
            String line;
            while ((line = br.readLine()) != null)
            {
                data.add(line.split(","));
            }
        }
        return data;
    }
}