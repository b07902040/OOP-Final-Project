package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class Banana extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "Banana";
    private static String description = "Give a minion +1/+1";
    private static int baseCost = 1;

    public Banana(){
        super(Banana.name, Banana.description, Banana.baseCost);    
    }    
    
    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(!(minion instanceof Hero)  &&  minion.canTargeted())
                    candidates.add(minion);              
        }
        for(Minion minion : player.getEnemy()){
            if(!(minion instanceof Hero)  &&  minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target){
        target.setBuffHP(target.getBuffHP() +1);
        target.setHP(target.getHP() +1);
        target.setATK(target.getATK() +1);         
    }

    
}