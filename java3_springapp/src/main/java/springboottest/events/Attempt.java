package events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope(value = "prototype")
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
