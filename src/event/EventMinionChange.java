package src.event;
import src.model.*;

public class EventMinionChange implements Event{
    private String name = "EventMinionChange";
    private boolean ally;
    private int index;
    private Minion minion;

    public EventMinionChange(boolean ally, int index, Minion minion){
        this.ally = ally;
        this.index = index;
        this.minion = minion;
    }
    
    public boolean getAlly(){
        return this.ally;
    }

    public int getIndex(){
        return this.index;
    }
    
    public Minion getMinion(){
        return this.minion;
    }

    @Override
    public String getName(){
        return this.name;
    }
}