package heart.model.minion;
import java.util.List;

import heart.model.*;
//Complete
public class DamagedGolem extends AbstractMinion implements Card, Minion{
    
    private static String name = "DamagedGolem";
    private static String description = "";
    private static int baseCost = 1;
    private static int baseATK = 2;
    private static int baseHP = 1;
    
    public DamagedGolem (){
        super(DamagedGolem.name, DamagedGolem.description, DamagedGolem.baseCost,
                 DamagedGolem.baseHP, DamagedGolem.baseATK);    
    }
    
}