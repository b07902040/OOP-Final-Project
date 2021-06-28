package src.model.minion;
import src.model.*;
//Complete
public class EliteWarrior extends AbstractMinion implements Card, Minion, Charge{
    
    private static String name = "EliteWarrior";
    private static String description = "Charge";
    private static int baseCost = 4;
    private static int baseATK = 4;
    private static int baseHP = 3;
   
    public EliteWarrior (){
        super(EliteWarrior.name, EliteWarrior.description, EliteWarrior.baseCost,
                 EliteWarrior.baseHP, EliteWarrior.baseATK);    
    }
   
}