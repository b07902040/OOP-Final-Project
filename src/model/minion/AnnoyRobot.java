package src.model.minion;
import src.model.*;
//Complete
public class AnnoyRobot extends AbstractMinion implements Card, Minion, DivineShield, Taunt{
    
    private static String name = "AnnoyRobot";
    private static String description = "DivineShield & Taunt";
    private static int baseCost = 2;
    private static int baseATK = 1;
    private static int baseHP = 2;
   

    public AnnoyRobot (){
        super(AnnoyRobot.name, AnnoyRobot.description, AnnoyRobot.baseCost,
                 AnnoyRobot.baseHP, AnnoyRobot.baseATK);    
    }

    
}