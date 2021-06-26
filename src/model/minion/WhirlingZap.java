package src.model.minion;
import src.model.*;
//Complete
public class WhirlingZap extends AbstractMinion implements Card, Minion, WindFury{
    
    private static String name = "WhirlingZap";
    private static String description = "WindFury";
    private static int baseCost = 2;
    private static int baseATK = 3;
    private static int baseHP = 2;
   
    public WhirlingZap (){
        super(WhirlingZap.name, WhirlingZap.description, WhirlingZap.baseCost,
                 WhirlingZap.baseHP, WhirlingZap.baseATK);    
    }
    
}