package heart.event;

public class EventCardShow implements Event {
    private String name = "EventCardShow";
    private int showIndex;
    private boolean valid;

    public EventCardShow(int showIndex, boolean valid) {
        this.showIndex = showIndex;
        this.valid = valid;
    }

    public int getShowIndex() {
        return this.showIndex;
    }

    public boolean getValid() {
        return this.valid;
    }

    @Override
    public String getName() {
        return this.name;
    }

}