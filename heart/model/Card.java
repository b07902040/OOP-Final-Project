package heart.model;

import java.io.Serializable;

public interface Card extends Serializable {
    static final long serialVersionUID = 2L;

    String getName();

    int getCost();

    void setCost(int cost);

    int getBaseCost();

    String getDescription();

    void setDescription(String description);

}