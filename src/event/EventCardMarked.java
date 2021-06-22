package src.event;

public class EventCardMarked implements Event{
    private String name = "EventCardMarked";
    private int markedIndex;

    public EventCardMarked(int markedIndex){
        this.markedIndex = markedIndex;
    }
    
    public int getMarkedIndex() {
        return this.markedIndex;
    }


    @Override
    public String getName(){
        return this.name;
    }
}