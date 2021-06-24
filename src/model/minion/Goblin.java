package src.model.minion;
import src.model.*;
import src.constant.*;

public class Goblin extends AbstractMinion implements Minion, Card{
    static private String name = "Goblin";
    static private String description = "DeathRattle: Deal 2 damage to the enemy Goblin.";
    static private int baseCost = 1;
    static private int baseHP = 1;
    static private int baseATK = 1;
    public Goblin (){
        super(Goblin.name, Goblin.description, Goblin.baseCost, Goblin.baseHP, Goblin.baseATK);    
    }
    
}