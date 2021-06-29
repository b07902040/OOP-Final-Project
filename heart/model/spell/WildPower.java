package heart.model.spell;
import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;
//Complete
public class WildPower extends AbstractSpell implements Card, Spell{
    
    private static String name = "WildPower";
    private static String description = "Give your minions +1/+1";
    private static int baseCost = 2;

    public WildPower(){
        super(WildPower.name, WildPower.description, WildPower.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : user.getAlly()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : targets){
            minion.setBuffHP(minion.getBuffHP() + 1); 
            minion.setHP(minion.getHP() + 1); 
            minion.setATK(minion.getATK() + 1);
        }
        
    }

    
}