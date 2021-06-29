package heart.model.spell;
import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;
//Complete
public class BlackHole extends AbstractSpell implements Card, Spell{
    
    private static String name = "BlackHole";
    private static String description = "Destroy all minions";
    private static int baseCost = 8;

    public BlackHole(){
        super(BlackHole.name, BlackHole.description, BlackHole.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : user.getAlly()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : user.getEnemy()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : targets)
            minion.setAlive(false);
    }

    
}