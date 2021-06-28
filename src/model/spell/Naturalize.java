package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class Naturalize extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "Naturalize";
    private static String description = "Destroy a minion. Your opponent draws 2 cards.";
    private static int baseCost = 1;

    public Naturalize(){
        super(Naturalize.name, Naturalize.description, Naturalize.baseCost);    
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
        target.setAlive(false);
        user.getOpponent().drawCards(2);
    }

    
}