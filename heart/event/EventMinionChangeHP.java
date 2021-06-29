package heart.event;

public class EventMinionChangeHP implements Event {
    private String name = "EventMinionChangeHP";
    private int playerId;
    private int index;
    private int hpDiff;

    public EventMinionChangeHP(int playerId, int index, int hpDiff) {
        this.playerId = playerId;
        this.index = index;
        this.hpDiff = hpDiff;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getIndex() {
        return this.index;
    }

    public int getHpDiff() {
        return this.hpDiff;
    }

    @Override
    public String getName() {
        return this.name;
    }
}