public class GetMail implements Runnable
{
    private int count;

    public GetMail(int count)
    {
        this.count = count;
    }

    public void run()
    {
        for(int i = 1; i <= count; i++)
        {
            System.out.println(
                    Thread.currentThread().getName()
                            + " : Mail "
                            + i
            );

            try
            {
                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}