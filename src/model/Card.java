package src.model;
import java.util.List;
import java.util.ArrayList;

public interface Card{

    String getName();

    int getCost();

    String getDescription();
    
    
    void playedEffect(Player user, Minion target);

}