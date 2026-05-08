import java.util.*;

public class Prime {
    public static void main(String[] args)
    {
        String sentence = "Java is worst";
        String result = " ";
        for(int i = sentence.length() - 1; i >= 0 ; i--)
        {
            result += sentence.charAt(i);
        }
        System.out.println(result);
    }
}
