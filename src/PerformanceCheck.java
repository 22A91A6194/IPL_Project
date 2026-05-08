import java.util.concurrent.locks.ReentrantLock;

public class PerformanceCheck implements Runnable
{
    private String checkWhat;

    private static ReentrantLock lock =
            new ReentrantLock();

    public PerformanceCheck(String checkWhat)
    {
        this.checkWhat = checkWhat;
    }

    public void run()
    {
        lock.lock();

        try
        {
            System.out.println(
                    "Checking " + this.checkWhat
            );
        }
        finally
        {
            lock.unlock();
        }
    }
}