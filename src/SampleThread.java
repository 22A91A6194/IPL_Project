class ThreadSample extends Thread
{
    public void run()
    {
        for(int i = 1; i <= 5; i++)
        {
            System.out.println(i);
        }
    }
}

public class SampleThread
{
    public static void main(String[] args)
    {
        ThreadSample thread = new ThreadSample();
        thread.start();
        for(int i = 100; i <= 105; i++)
        {
            System.out.println(i);
        }
    }
}
