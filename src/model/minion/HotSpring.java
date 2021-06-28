package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class HotSpring extends AbstractMinion implements Card, Minion, BattleCry, Targeting, Taunt{
    
    private static String name = "HotSpring";
    private static String description = "Taunt & BattleCry: Restore 3 HP";
    private static int baseCost = 3;
    private static int baseATK = 2;
    private static int baseHP = 4;
    

    public HotSpring (){
        super(HotSpring.name, HotSpring.description, HotSpring.baseCost,
                 HotSpring.baseHP, HotSpring.baseATK);    
    }

    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(minion.canTargeted())
                candidates.add(minion);              
        }
        for(Minion minion : player.getEnemy()){
            if(minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override 
    public void doBattleCryEffect(Minion target){ 
        target.setHP(target.getHP() + 3);
    }
    
}