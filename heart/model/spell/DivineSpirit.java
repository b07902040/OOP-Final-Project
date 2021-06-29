package heart.model.spell;
import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;
//Complete
public class DivineSpirit extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "DivineSpirit";
    private static String description = "Double a minion's HP";
    private static int baseCost = 2;

    public DivineSpirit(){
        super(DivineSpirit.name, DivineSpirit.description, DivineSpirit.baseCost);    
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
        target.setBuffHP(target.getBuffHP() * 2); 
        target.setHP(target.getHP() * 2);  
    }

    
}