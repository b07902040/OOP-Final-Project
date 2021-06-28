package src.model.minion;
import src.model.*;
//Complete
public class Penguinn extends AbstractMinion implements Card, Minion{
    
    private static String name = "Penguinn";
    private static String description = "";
    private static int baseCost = 0;
    private static int baseATK = 1;
    private static int baseHP = 1;
   
    public Penguinn (){
        super(Penguinn.name, Penguinn.description, Penguinn.baseCost,
                 Penguinn.baseHP, Penguinn.baseATK);    
    }
   
}