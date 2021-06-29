package heart.model.minion;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class LightRagnaros extends AbstractMinion implements Card, Minion{
    
    private static String name = "LightRagnaros";
    private static String description = "At the end of your turn, deal 8 damge to a random enemy";
    private static int baseCost = 8;
    private static int baseATK = 8;
    private static int baseHP = 8;
   
    public LightRagnaros (){
        super(LightRagnaros.name, LightRagnaros.description, LightRagnaros.baseCost,
                 LightRagnaros.baseHP, LightRagnaros.baseATK);    
    }    

    @Override   
    public void doTurnEnd(){
        List<Minion> ally = this.master.getAlly();
        List<Minion> damagedAlly = new ArrayList<Minion>();
        for(Minion minion : ally){
            if(minion.isDamaged())
                damagedAlly.add(minion);
        }
        if(damagedAlly.size() > 0){
            int targetId = Game.getRandom(damagedAlly.size()) - 1;
            Minion e = damagedAlly.get(targetId);
            e.setHP(e.getHP() + 8);
        }
    }
    
}