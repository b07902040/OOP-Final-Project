package heart.event;

import heart.model.Minion;

public class EventBoardChange implements Event {
    private String name = "EventBoardChange";
    private int playerId;
    private int index;
    private Minion minion;

    // remove
    public EventBoardChange(int playerId, int index) {
        this.playerId = playerId;
        this.index = index;
        this.minion = null;
    }

    // summon
    public EventBoardChange(int playerId, int index, Minion minion) {
        this.playerId = playerId;
        this.index = index;
        this.minion = minion;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getIndex() {
        return this.index;
    }

    public Minion getMinion() {
        return this.minion;
    }

    @Override
    public String getName() {
        return this.name;
    }
}