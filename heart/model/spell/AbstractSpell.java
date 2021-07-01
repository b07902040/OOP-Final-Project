package heart.model.spell;

import heart.model.*;

public abstract class AbstractSpell implements Spell{
    // spell property

    // card property
    protected int cost;
    protected int baseCost;
    protected String name;
    protected String description;

    public AbstractSpell(String name, String description, int baseCost) {
        this.name = name;
        this.description = description;
        this.baseCost = baseCost;
        this.cost = baseCost;
    }

    // card
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int getBaseCost() {
        return this.baseCost;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    // spell
    @Override
    public abstract void takeEffect(Player user, Minion target);

}