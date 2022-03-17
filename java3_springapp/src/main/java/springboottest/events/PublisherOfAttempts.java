package springboottest.events;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class PublisherOfAttempts implements ApplicationContextAware {

    private ConfigurableApplicationContext ctx;
    private Locale locale = Locale.getDefault();

    public void publishAttempt(int num) {
        ctx.publishEvent(new Attempt(num));
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = (ConfigurableApplicationContext) ctx;
        System.out.println(getContext().getMessage("attempt", null, locale));
    }

    public ConfigurableApplicationContext getContext() {
        return ctx;
    }
}
