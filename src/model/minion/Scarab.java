package src.model.minion;
import src.model.*;
//Complete
public class Scarab extends AbstractMinion implements Card, Minion, Taunt{
    
    private static String name = "Scarab";
    private static String description = "Taunt";
    private static int baseCost = 2;
    private static int baseATK = 1;
    private static int baseHP = 5;
    
    public Scarab (){
        super(Scarab.name, Scarab.description, Scarab.baseCost,
                 Scarab.baseHP, Scarab.baseATK);    
    }


}