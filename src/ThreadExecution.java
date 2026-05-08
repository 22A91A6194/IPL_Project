import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecution
{
    public static void main(String[] args)
    {
        addThreadsToPool();

        Runnable getMail = new GetMail(10);
        Runnable getMailAgain = new GetMail(20);

        new Thread(getMail).start();
        new Thread(getMailAgain).start();
    }

    public static void addThreadsToPool()
    {
        ScheduledThreadPoolExecutor eventPool =
                new ScheduledThreadPoolExecutor(5);

        eventPool.scheduleAtFixedRate(
                new ProgramThread(),
                0,
                2,
                TimeUnit.SECONDS
        );

        eventPool.scheduleAtFixedRate(
                new ProgramThread("Mail"),
                5,
                5,
                TimeUnit.SECONDS
        );

        eventPool.scheduleAtFixedRate(
                new ProgramThread("Calendar"),
                10,
                5,
                TimeUnit.SECONDS
        );

        System.out.println(
                "Number of Threads: " + Thread.activeCount()
        );

        Thread[] listOfThreads =
                new Thread[Thread.activeCount()];

        Thread.enumerate(listOfThreads);

        System.out.println("\nThread Names:");

        for(Thread i : listOfThreads)
        {
            if(i != null)
            {
                System.out.println(i.getName());
            }
        }

        System.out.println("\nThread Priorities:");

        for(Thread i : listOfThreads)
        {
            if(i != null)
            {
                System.out.println(i.getPriority());
            }
        }

        try
        {
            Thread.sleep(20000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        eventPool.shutdown();
    }
}