public class Pattern {
    public static void main(String[] args)
    {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int t = 0;
        int b = a.length - 1;
        int l = 0;
        int r = a[0].length - 1;
        while(t <= b && l <= r)
        {
            for(int i = l; i <= r; i++)
            {
                System.out.print(a[t][i] + " ");
            }
            t++;

            for(int i = t; i <= b; i++)
            {
                System.out.print(a[i][r] + " ");
            }
            r--;

            if(t <= b)
            {
                for(int i = r; i >= l; i--)
                {
                    System.out.print(a[b][i] + " ");
                }
                b--;
            }

            if(l <= r)
            {
                for(int i = b; i >= t; i--)
                {
                    System.out.print(a[i][l] + " ");
                }
                l++;
            }
        }

    }
}
