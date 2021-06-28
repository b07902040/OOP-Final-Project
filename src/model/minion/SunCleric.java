package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class SunCleric extends AbstractMinion implements Card, Minion, BattleCry, Targeting{
    
    private static String name = "SunCleric";
    private static String description = "BattleCry: Give a friendly minion +1/+1";
    private static int baseCost = 3;
    private static int baseATK = 3;
    private static int baseHP = 2;
    

    public SunCleric (){
        super(SunCleric.name, SunCleric.description, SunCleric.baseCost,
                 SunCleric.baseHP, SunCleric.baseATK);    
    }

    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(!(minion instanceof Hero) &&  minion.canTargeted())
                candidates.add(minion);              
        }
        return candidates;
    }

    @Override 
    public void doBattleCryEffect(Minion target){     
        target.setBuffHP(target.getBuffHP() +1);
        target.setHP(target.getHP() +1);
        target.setATK(target.getATK() +1);
    }
    
}