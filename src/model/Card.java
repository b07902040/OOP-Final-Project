package src.model;
import java.util.List;
import java.util.ArrayList;

public interface Card{

    String getName();

    int getCost();

    void setCost(int cost);

    int getBaseCost();
    
    String getDescription();

    void setDescription(String description);


}