package src.model.minion;
import src.model.*;
import java.util.ArrayList;
import java.util.List;

//Complete
public class VileSpine extends AbstractMinion implements Card, Minion, BattleCry, Combo{
    
    private static String name = "VileSpine";
    private static String description = "BattleCry & Combo: Destroy a random enemy minion";
    private static int baseCost = 5;
    private static int baseATK = 4;
    private static int baseHP = 4;
   

    public VileSpine (){
        super(VileSpine.name, VileSpine.description, VileSpine.baseCost,
                 VileSpine.baseHP, VileSpine.baseATK);    
    }

    @Override public int getCombo(Player master){
        return master.getCardPlayed() - 1;
    }
    
    @Override
    public void doBattleCryEffect(Minion target){
        int combo = ((Combo) this).getCombo(this.master);
        if(combo < 1) return;
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : this.master.getEnemy()){
            if(!(minion instanceof Hero)  &&  minion.canTargeted())
                candidates.add(minion);
        }
        if(candidates.size() == 0) return;
        int targetId = Game.getRandom(candidates.size()) - 1;
        Minion e = candidates.get(targetId);
        e.setAlive(false);
    }
    
}