package src.model.minion;
import src.model.*;
//Complete
public class Maexxna extends AbstractMinion implements Card, Minion, Poisonous{
    
    private static String name = "Maexxna";
    private static String description = "Poisonous";
    private static int baseCost = 6;
    private static int baseATK = 2;
    private static int baseHP = 8;
   
    public Maexxna (){
        super(Maexxna.name, Maexxna.description, Maexxna.baseCost,
                 Maexxna.baseHP, Maexxna.baseATK);    
    }
    
}