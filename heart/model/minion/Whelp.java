package heart.model.minion;
import heart.model.*;
//Complete
public class Whelp extends AbstractMinion implements Card, Minion{
    
    private static String name = "Whelp";
    private static String description = "";
    private static int baseCost = 1;
    private static int baseATK = 1;
    private static int baseHP = 1;
   
    public Whelp (){
        super(Whelp.name, Whelp.description, Whelp.baseCost,
                 Whelp.baseHP, Whelp.baseATK);    
    }
   
}