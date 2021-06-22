package src.event;

public class EventMinionClicked implements Event{
    private String name = "EventMinionClicked";
    private boolean isAlly;
    private int clickedIndex;

    public EventMinionClicked(boolean isAlly,int clickedIndex){
        this.isAlly = isAlly;
        this.clickedIndex = clickedIndex;
    }

    public boolean getIsAlly(){
        return this.isAlly;
    }

    public int getClickedIndex(){
        return this.clickedIndex;
    }

    @Override
    public String getName(){
        return this.name;
    }
}