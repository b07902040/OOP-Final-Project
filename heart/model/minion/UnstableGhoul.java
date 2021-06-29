package heart.model.minion;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class UnstableGhoul extends AbstractMinion implements Card, Minion, DeathRattle, Taunt{
    
    private static String name = "UnstableGhoul";
    private static String description = "DeathRattle: Deal 1 damage to all minions";
    private static int baseCost = 2;
    private static int baseATK = 1;
    private static int baseHP = 3;

    public UnstableGhoul (){
        super(UnstableGhoul.name, UnstableGhoul.description, UnstableGhoul.baseCost,
                 UnstableGhoul.baseHP, UnstableGhoul.baseATK);    
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