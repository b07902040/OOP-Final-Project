package heart.model.minion;
import java.util.List;

import heart.model.*;
//Complete
public class Hogger extends AbstractMinion implements Card, Minion{
    
    private static String name = "Hogger";
    private static String description = "At the end of your turn, summon a 2/2 Gnoll with Taunt";
    private static int baseCost = 6;
    private static int baseATK = 4;
    private static int baseHP = 4;
   

    public Hogger (){
        super(Hogger.name, Hogger.description, Hogger.baseCost,
                 Hogger.baseHP, Hogger.baseATK);    
    }    
    
    @Override   
    public void doTurnEnd(){
        this.master.summonAlly(new Gnoll(), this.master.getAlly().size());
    }
    
}