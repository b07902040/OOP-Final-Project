package heart.model.spell;
import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;
//Complete
public class DoubleATK extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "DoubleATK";
    private static String description = "Double a minion's ATK";
    private static int baseCost = 4;

    public DoubleATK(){
        super(DoubleATK.name, DoubleATK.description, DoubleATK.baseCost);    
    }    
    
    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(minion.canTargeted() && !(minion instanceof Hero))
                candidates.add(minion);              
        }
        for(Minion minion : player.getEnemy()){
            if(minion.canTargeted() && !(minion instanceof Hero) )
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target){
        target.setATK(target.getATK() * 2); 
    }

    
}