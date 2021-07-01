package hearthclone.event;

public class EventEveryTick implements Event {
    private String name = "EventEveryTick";

    @Override
    public String getName() {
        return this.name;
    }
}