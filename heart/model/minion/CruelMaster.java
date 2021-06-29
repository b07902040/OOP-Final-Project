package heart.model.minion;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class CruelMaster extends AbstractMinion implements Card, Minion, BattleCry, Targeting{
    
    private static String name = "CruelMaster";
    private static String description = "BattleCry: Deal 1 damage to a minion and give it +2 ATK";
    private static int baseCost = 2;
    private static int baseATK = 2;
    private static int baseHP = 2;
    

    public CruelMaster (){
        super(CruelMaster.name, CruelMaster.description, CruelMaster.baseCost,
                 CruelMaster.baseHP, CruelMaster.baseATK);    
    }

    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(!(minion instanceof Hero) &&  minion.canTargeted())
                candidates.add(minion);              
        }
        for(Minion minion : player.getEnemy()){
            if(!(minion instanceof Hero)  &&  minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override 
    public void doBattleCryEffect(Minion target){ 
        target.setHP(target.getHP() - 1);
        target.setATK(target.getATK() + 1); 
    }
    
}