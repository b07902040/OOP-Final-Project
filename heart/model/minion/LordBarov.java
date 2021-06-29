package heart.model.minion;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class LordBarov extends AbstractMinion implements Card, Minion, BattleCry, DeathRattle{
    
    private static String name = "LordBarov";
    private static String description = "BattleCry: Set the HP of all other minions to 1. DeathRattle: Deal 1 damage to all minions";
    private static int baseCost = 4;
    private static int baseATK = 3;
    private static int baseHP = 2;

    public LordBarov (){
        super(LordBarov.name, LordBarov.description, LordBarov.baseCost,
                 LordBarov.baseHP, LordBarov.baseATK);    
    }    
    @Override   
    public void doBattleCryEffect(Minion target){ 
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : this.master.getAlly()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : this.master.getEnemy()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        targets.remove(this);
        for(Minion minion : targets){
            minion.setBuffHP(1);
            minion.reWriteHP(1);  
        }
    }
    
    @Override   
    public void doDeathRattleEffect(){ 
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : this.master.getAlly()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : this.master.getEnemy()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : targets)
            minion.setHP(minion.getHP() - 1);
    }
    
}