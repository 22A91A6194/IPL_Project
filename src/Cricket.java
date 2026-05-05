import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.lang.*;
public class Cricket
{
    public static void main(String[] args) throws Exception
    {
        //-----------TASK 1---------------------------
        BufferedReader b = new BufferedReader(new FileReader("matches.csv"));
        String s = b.readLine();
        HashMap<String, Integer> d = new HashMap<>();
        while((s = b.readLine()) != null)
        {
            String[] t = s.split(",");
            String y = t[1];
            if(!t[1].isEmpty())
            {
                d.put(y, d.getOrDefault(y, 0) + 1); // that means if y is in map the for its count +1 will be added if not then 0 will be its count and for that we will add 1 as present exsistance
            }
        }
        System.out.println("TASK 1 OUTPUT");
        System.out.println(d + "\n\n\n");


        //---------TASK 2-----------------------------
       BufferedReader b1 = new BufferedReader(new FileReader("matches.csv"));
        String s1 = b1.readLine();
        HashMap<String, Integer> d1 = new HashMap<>();

        while((s1 = b1.readLine()) != null)
        {
            String[] t1 = s1.split(",");
            String w = t1[10];
            if(w!= null && !w.isEmpty()) {
                d1.put(w, d1.getOrDefault(w, 0) + 1);
            }
        }
        System.out.println("TASK - 2 OUTPUT");
        System.out.println(d1 + "\n\n\n");


        //--------------TASK 3 ----------------------------------
        BufferedReader b2 = new BufferedReader(new FileReader("matches.csv"));
        String s2 = b2.readLine();
        System.out.println(s2+"\n\n\n");
        Set<Integer> mid = new HashSet<>();
        while((s2 = b2.readLine()) != null)
        {
            String[] t2 = s2.split(",");
            Integer id = Integer.parseInt(t2[0]);
            Integer y = Integer.parseInt(t2[1])  ;
            if(y == 2016) {
                mid.add(id);
            }
        }
        System.out.println("The Unique matche ID in year 2016 are:"+mid);

        BufferedReader b3 = new BufferedReader(new FileReader("deliveries.csv"));
        String s3 = b3.readLine();
        System.out.println(s3+"\n\n\n");

        HashMap<String, Integer> d2 = new HashMap<>();
        while((s3 = b3.readLine()) != null) {
            String[] t4 = s3.split(",");
            if (t4.length > 16) {
                String bt = t4[3];
                Integer er = Integer.parseInt(t4[16]);
                Integer id = Integer.parseInt(t4[0]);
                if (mid.contains(id)) {
                    d2.put(bt, d2.getOrDefault(bt, 0) + er);
                }
            }
        }
        System.out.println("TASK 3 OUTPUT");
        System.out.println(d2 + "\n\n\n");


        BufferedReader b4 = new BufferedReader(new FileReader("matches.csv"));
        String s5 = b4.readLine();
        Set<Integer> umid = new HashSet<>();
        while((s5 = b4.readLine()) != null)
        {
            String[] t5 = s5.split(",");
            Integer i = Integer.parseInt(t5[0]);
            Integer yr = Integer.parseInt(t5[1]);
            if(yr == 2015)
            {
                umid.add(i);
            }
        }
        System.out.println("The unique match id in 2015: "+ umid);


        BufferedReader b5 = new BufferedReader(new FileReader("deliveries.csv"));
        String s6 = b5.readLine();

        HashMap<String, Integer> rns = new HashMap<>();
        HashMap<String, Integer> balls = new HashMap<>();

        while((s6 = b5.readLine()) != null)
        {
            String[] t6 = s6.split(",");
            int mtid = Integer.parseInt(t6[0]);
            int runs = Integer.parseInt(t6[17]);
            String bw =  t6[8];
            if(umid.contains(mtid))
            {
                rns.put(bw, rns.getOrDefault(bw, 0) + runs);
                balls.put(bw, balls.getOrDefault(bw, 0) +  1);
            }
        }

        List<Map.Entry<String, Double>> l = new ArrayList<>();
        for(String i: rns.keySet())
        {
            int tr = rns.get(i);
            int tb = balls.get(i);
            double ecnmy = tr / (tb / 6.0);
            l.add(new AbstractMap.SimpleEntry<>(i, ecnmy));
        }
        l.sort((a, q) -> Double.compare(a.getValue(), q.getValue()));
        System.out.println("\n\nTASK 4 OUTPUT");
        System.out.println("Top Economical Bowlers (2015):");
        for (int i = 0; i < Math.min(10, l.size()); i++) // prevent errors if the list<10 (Math.min(10, l.size())
        {
            System.out.println(l.get(i).getKey() + " → " + l.get(i).getValue()); //l.get(i).getKey() = bowler nm and l.get(i).getValue() - economy val
        }


        //-----TASK 5------------------
        // Find Number of matches played in each city and max matches held among all cities
        BufferedReader br = new BufferedReader(new FileReader("matches.csv"));
        String stn = br.readLine();
        HashMap<String, Integer> m = new HashMap<>();
        while((stn = br.readLine()) != null)
        {
            String[] t = stn.split(",");
            String c = t[2];
            if(c != null && !c.isEmpty())
            {
                m.put(c, m.getOrDefault(c, 0) + 1);
            }
        }
        System.out.println("Matches per city:" + m);

        String mcity = "";
        int cm = 0;
        for(String i: m.keySet())
        {
            if(m.get(i) > cm)
            {
                mcity = i;
                cm = m.get(i);
            }
        }

        System.out.println("THe maximum matches held in city "+mcity + "\n no of matches "+cm);
    }
;}