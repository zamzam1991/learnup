package springboottest.events;

import org.springframework.context.ApplicationEvent;
import springboottest.annotations.LogMethod;

public class Attempt extends ApplicationEvent {

    private final int num;

    public Attempt(int num) {
        super(num);
        this.num = num;
    }

    @LogMethod
    public int getNum() {
        return num;
    }

}
