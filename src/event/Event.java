package src.event;
/**
 * base of events
 * post()ed by EventManager, notify()ed by EventListener
 */
public interface Event {
    String getName();
}