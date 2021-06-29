package heart.model.spell;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class DeathCoil extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String type = "DeathKnight";
    private static String name = "DeathCoil";
    private static String description = "Deal 5 damge to an enemy, or restore 5 HP to ally";
    private static int baseCost = 2;

    public DeathCoil(){
        super(DeathCoil.name, DeathCoil.description, DeathCoil.baseCost);    
    }    
    
    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(minion.canTargeted())
                    candidates.add(minion);              
        }
        for(Minion minion : player.getEnemy()){
            if(minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target){
        if(user.getAlly().contains(target))
            target.setHP(target.getHP() + 5);
        else
            target.setHP(target.getHP() - 5);       
    }
    
}