package src.event;

public class EventClickedEmpty implements Event{
    private String name = "EventClickedEmpty";
    private int clickedIndex = 0;

    public EventCardClicked(int clickedIndex){
        this.clickedIndex = clickedIndex;
    }

    public int getClickedIndex() {
        return this.clickedIndex;
    }

    @Override
    public String getName(){
        return this.name;
    }
    
}