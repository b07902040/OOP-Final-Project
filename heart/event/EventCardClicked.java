package heart.event;

public class EventCardClicked implements Event{
    private String name = "EventCardClicked";
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