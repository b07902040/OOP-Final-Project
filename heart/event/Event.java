package heart.event;

/**
 * base of events
 * post()ed by EventManager, notify()ed by EventListener
 */
import java.io.Serializable;

public interface Event extends Serializable {
    static final long serialVersionUID = 2L;

    String getName();
}