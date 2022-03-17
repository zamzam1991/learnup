package springboottest.events;

import org.springframework.context.ApplicationEvent;

public class Attempt extends ApplicationEvent {

    private final int num;

    public Attempt(int num) {
        super(num);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

}
