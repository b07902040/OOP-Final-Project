package heart.event;

public class EventEffecting implements Event{
    private String name = "EventEffecting";

    private int cardIndex;
    private int cardPlayerIndex;
    private int targetIndex;
    private int targetPlayerIndex;

    public EventEffecting(int cardIndex, int cardPlayerIndex, int targetIndex, int targetPlayerIndex){
        this.cardIndex = cardIndex;
        this.cardPlayerIndex = cardPlayerIndex;
        this.targetIndex = targetIndex;
        this.targetPlayerIndex = targetPlayerIndex;
    }
    
    public int getCardIndex(){
        return this.cardIndex;
    }
    
    public int getCardPlayerIndex(){
        return this.cardPlayerIndex;
    }

    public int getTargetIndex(){
        return this.targetIndex;
    }

    public int getTargetPlayerIndex(){
        return this.targetPlayerIndex;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
}