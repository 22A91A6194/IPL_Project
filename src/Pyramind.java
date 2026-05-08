public class Pyramind {
    public static void main(String[] args)
    {
        int n = 7;
        int h = (n / 2) + 1;
        for (int i = 0; i <= n; i++)
        {
            for(int k = 0; k <= i; k++)
            {
                System.out.print(" ");
            }
            for (int j = i + 1; j <= n - i ; j++)
            {
                    System.out.print(j + " ");
            }
            if(i == h) {
                System.out.println(i);
                break;
            }
            System.out.println();
        }
        //System.out.println("The second pyramid");
        System.out.println(h);
        int a = h - 1;
        int b = h + 1;
        for(int i = h; i < (n - h); i--)
        {
            for(int k = 1; k < i; k++)
            {
                System.out.print(" ");
            }
            for(int j = a; j <= b; j++)
            {
                System.out.print(j + " ");
            }
            if(a > 0 && b < n)
            {
                a -= 1;
                b += 1;
            }
            else
                break;
        }
        /*int a = h - 1, b = h;
        for (int i = h; i > 0; i--)
        {
            for(int k = 0; k < i; k++)
            {
                System.out.print(" ");
            }
            for (int j = a; j <= b + 1; j++)
            {
                 System.out.print(j + " ");
            }
            a -= 1;
            if(b > 2)
                b += 1;
            System.out.println();
        }*/
    }
}