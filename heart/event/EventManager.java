package heart.event;

import java.io.Serializable;

public interface EventManager extends Serializable {
    static final long serialVersionUID = 1L;

    void register(EventListener listener);

    void post(Event event);
}
