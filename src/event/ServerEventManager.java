package src.event;
import java.util.List;
import java.util.ArrayList;
import src.*;
import src.event.Message;

/**
 * Coordinate communication between the Model, View, and Controller.
 */
public class ServerEventManager {
    /**
     * list of listeners that registered
     */
    private List<EventListener> listeners = new ArrayList<EventListener> ();
    /**
     * Add a listener to the listeners list
     * it receives Post()ed event through its notify() method
     */
    public void register(EventListener listener) {
        this.listeners.add(listener);
    }

    /**
     * broadcast an event to all listeners
     */
    public void post(Event event) {
        for (EventListener listener : this.listeners) {
            listener.notify(event);
        }
        Server.boardcastMessage(new Message(Message.EVENT, -2, event));
    }

    public void localPost(Event event) {
        for (EventListener listener : this.listeners) {
            listener.notify(event);
        }
    }
}