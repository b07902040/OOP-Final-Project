package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class Execute extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "Execute";
    private static String description = "Destroy a damaged enemy minion";
    private static int baseCost = 1;

    public Execute(){
        super(Execute.name, Execute.description, Execute.baseCost);    
    }    
    
    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getEnemy()){
            if(minion.canTargeted() && !(minion instanceof Hero) )
                if(minion.getHP() < minion.getBuffHP()){
                    candidates.add(minion);
                }
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target){
        target.setAlive(false); 
    }

    
}