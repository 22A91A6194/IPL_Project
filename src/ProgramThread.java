import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

public class ProgramThread implements Runnable
{
    private String name;

    public ProgramThread()
    {
        this.name = "Default";
    }

    public ProgramThread(String name)
    {
        this.name = name;
    }

    public void run()
    {
        Date rightNow;
        Locale currentLocale;
        DateFormat timeFormatter;
        String timeOutput;

        rightNow = new Date();

        currentLocale = new Locale("en");

        timeFormatter =
                DateFormat.getTimeInstance(
                        DateFormat.DEFAULT,
                        currentLocale
                );

        timeOutput = timeFormatter.format(rightNow);

        System.out.println(
                Thread.currentThread().getName()
                        + " -> "
                        + name
                        + " : "
                        + timeOutput
        );
    }
}