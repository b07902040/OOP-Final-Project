package heart.model.spell;
import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;
//Complete
public class PowerShield extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "PowerShield";
    private static String description = "Give a minion +2 Health. Draw a card";
    private static int baseCost = 1;

    public PowerShield(){
        super(PowerShield.name, PowerShield.description, PowerShield.baseCost);    
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
        target.setBuffHP(target.getBuffHP() + 2); 
        target.setHP(target.getHP() + 2);  
        user.drawCards(1);
    }

    
}