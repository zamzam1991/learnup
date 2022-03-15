package events;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Locale;

public class GuessingGame implements ApplicationListener<Attempt>, ApplicationContextAware {

    private ConfigurableApplicationContext ctx;
    private Locale locale = Locale.getDefault();
    private final int randomInt;

    public GuessingGame() {
        this.randomInt = (int) (Math.random() * 1000);
    }

    public int getRandomInt() {
        return randomInt;
    }

    @Override
    public void onApplicationEvent(Attempt event) {
        if (event.getNum() == getRandomInt()) {
            System.out.println(ctx.getMessage("equality", null, locale) + event.getNum());
            ctx.close();
        } else if (event.getNum() > getRandomInt())
            System.out.println(ctx.getMessage("less", null, locale));
         else
            System.out.println(ctx.getMessage("more", null, locale));
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = (ConfigurableApplicationContext) ctx;
        System.out.println(getApplicationContext().getMessage("hello", null, locale));
    }

    public ApplicationContext getApplicationContext() {
        return ctx;
    }
}
