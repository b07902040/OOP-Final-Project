package heart.event;

public class EventMinionClicked implements Event{
    private String name = "EventMinionClicked";
    private int playerId;
    private int clickedIndex;

    public EventMinionClicked(int playerId, int clickedIndex){
        this.playerId = playerId;
        this.clickedIndex = clickedIndex;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public int getClickedIndex(){
        return this.clickedIndex;
    }

    @Override
    public String getName(){
        return this.name;
    }
}