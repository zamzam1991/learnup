import events.PublisherOfAttempts;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("configuration.xml");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (ctx.isActive())
                ctx.getBean(PublisherOfAttempts.class, Locale.getDefault()).publishAttempt(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
