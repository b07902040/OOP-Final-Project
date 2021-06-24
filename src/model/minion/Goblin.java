package src.model.minion;
import src.model.*;
import src.constant.*;

public class Goblin extends AbstractMinion implements Minion, Card{
    private static String name = "Goblin";
    private static String description = "DeathRattle: Deal 2 damage to the enemy Goblin.";
    private static int baseCost = 1;
    private static int baseHP = 1;
    private static int baseATK = 30;

    public Goblin (){
        super(Goblin.name, Goblin.description, Goblin.baseCost,
                 Goblin.baseHP, Goblin.baseATK);    
    }
    
}