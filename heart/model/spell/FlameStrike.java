package heart.model.spell;
import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;
//Complete
public class FlameStrike extends AbstractSpell implements Card, Spell{
    
    private static String name = "FlameStrike";
    private static String description = "Deal 4 damage to all enemy minions";
    private static int baseCost = 7;

    public FlameStrike(){
        super(FlameStrike.name, FlameStrike.description, FlameStrike.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : user.getEnemy()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : targets)
            minion.setHP(minion.getHP() - 4); 
        
    }

    
}