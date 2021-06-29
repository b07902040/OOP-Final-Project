package heart.model.minion;
import heart.model.*;
//Complete
public class Ghoul extends AbstractMinion implements Card, Minion{
    
    private static String name = "Ghoul";
    private static String description = "";
    private static int baseCost = 5;
    private static int baseATK = 5;
    private static int baseHP = 5;
   
    public Ghoul(){
        super(Ghoul.name, Ghoul.description, Ghoul.baseCost,
                 Ghoul.baseHP, Ghoul.baseATK);    
    }

   
}