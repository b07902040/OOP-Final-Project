package src.model.spell;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class DivineHeal extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "DivineHeal";
    private static String description = "Restore 8 HP and draw 3 cards";
    private static int baseCost = 10;

    public DivineHeal(){
        super(DivineHeal.name, DivineHeal.description, DivineHeal.baseCost);    
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
        target.setHP(target.getHP() + 8);
        user.drawCards(3);
    }

    
}