package heart.model.spell;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class FireBall extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "FireBall";
    private static String description = "Deal 6 damage";
    private static int baseCost = 4;

    public FireBall(){
        super(FireBall.name, FireBall.description, FireBall.baseCost);    
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
        target.setHP(target.getHP() - 6);
    }

    
}