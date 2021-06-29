package heart.model.minion;
import heart.model.*;
//Complete
public class KingKrush extends AbstractMinion implements Card, Minion, Charge{
    
    private static String name = "KingKrush";
    private static String description = "Charge";
    private static int baseCost = 9;
    private static int baseATK = 8;
    private static int baseHP = 8;
   
    public KingKrush (){
        super(KingKrush.name, KingKrush.description, KingKrush.baseCost,
                 KingKrush.baseHP, KingKrush.baseATK);    
    }
   
}