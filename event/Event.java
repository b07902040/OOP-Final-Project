package event;
/**
 * base of events
 * post()ed by EventManager, notify()ed by EventListener
 */
interface Event {
    String getName();
}