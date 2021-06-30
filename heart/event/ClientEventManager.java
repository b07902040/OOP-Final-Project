package heart.event;

import java.util.ArrayList;
import java.util.List;

import heart.client.Client;

/**
 * Coordinate communication between the Model, View, and Controller.
 */
public class ClientEventManager implements EventManager {
    /**
     * list of listeners that registered
     */
    private List<EventListener> listeners = new ArrayList<EventListener>();

    /**
     * Add a listener to the listeners list it receives Post()ed event through its
     * notify() method
     */
    public void register(EventListener listener) {
        this.listeners.add(listener);
    }

    public int getListenerslen() {
        return listeners.size();
    }

    /**
     * broadcast an event to all listeners
     */

    public void post(Event event) {
        for (EventListener listener : this.listeners) {
            listener.notify(event);
        }
        Client.sendMessage(new Message(Message.EVENT, Client.getPlayerID(), event));
    }

    public void localPost(Event event) {
        for (EventListener listener : this.listeners) {
            listener.notify(event);
        }
    }
}