package heart.model.minion;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class UldmanKeeper extends AbstractMinion implements Card, Minion, BattleCry, Targeting{
    
    private static String name = "UldmanKeeper";
    private static String description = "BattleCry: Set a minion's ATK and HP to 3";
    private static int baseCost = 4;
    private static int baseATK = 3;
    private static int baseHP = 4;
    

    public UldmanKeeper (){
        super(UldmanKeeper.name, UldmanKeeper.description, UldmanKeeper.baseCost,
                 UldmanKeeper.baseHP, UldmanKeeper.baseATK);    
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
        System.out.printf("%s BattleCry!\n",this.name);
        target.setATK(3);
        target.setBuffHP(3);
        target.reWriteHP(3);        
    }
    
}