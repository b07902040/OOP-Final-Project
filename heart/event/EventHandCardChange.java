package heart.event;
import heart.model.*;

public class EventHandCardChange implements Event{

    private String name = "EventHandCardChange";
    private int playerId;
    private int index;
    private Card card;
    //REMOVE
    public EventHandCardChange(int playerId, int index){
        this.playerId = playerId;
        this.index = index;
        this.card = null; 
    }
    //ADD
    public EventHandCardChange(int playerId, Card card){
        this.playerId = playerId;
        this.card = card;
    }
    
    public int getPlayerId(){
        return this.playerId;
    }

    public int getIndex(){
        return this.index;
    }

    public Card getCard(){
        return this.card;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
}