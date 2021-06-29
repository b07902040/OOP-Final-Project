package heart.event;
import heart.model.*;

public class EventMinionDestroy implements Event{
    private String name = "EventMinionDestroy";
    private int playerId;
    private int index;

    public EventMinionDestroy(int playerId, int index){
        this.playerId = playerId;
        this.index = index;
    }
    
    public int getPlayerId(){
        return this.playerId;
    }

    public int getIndex(){
        return this.index;
    }

    @Override
    public String getName(){
        return this.name;
    }
}