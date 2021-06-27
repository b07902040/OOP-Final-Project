package src.event;

public class EventMinionShow implements Event{
    private String name = "EventMinionShow";
    private int showPlayerIndex;
    private int showIndex;   
    private boolean valid;
    
    public EventMinionShow(int playerId, int index, boolean valid){
        this.showPlayerIndex = playerId;
        this.showIndex = index;
        this.valid = valid;
    }
    public int getShowPlayerIndex() {
        return this.showPlayerIndex;
    }
    public int getShowIndex() {
        return this.showIndex;
    }
    public boolean getValid() {
        return this.valid;
    }

    @Override
    public String getName(){
        return this.name;
    }
    
}