package hearthclone.event;

public class EventStateChange implements Event {
    private String name = "EventStateChange";
    private int state;

    public EventStateChange(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    @Override
    public String getName() {
        return this.name;
    }
}