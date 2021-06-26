package src.model.spell;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class SoulFire extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "SoulFire";
    private static String description = "Deal 10 damage";
    private static int baseCost = 10;

    public SoulFire(){
        super(SoulFire.name, SoulFire.description, SoulFire.baseCost);    
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
        target.setHP(target.getHP() - 4);
        List<Card> handCards = user.getHandCards();
        if(handCards.size() > 0){
            int index = Game.getRandom(handCards.size()) - 1;
            user.throwCard(index);
        }            
    }

    
}