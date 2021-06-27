package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class Obliterate extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String type = "DeathKnight";
    private static String name = "Obliterate";
    private static String description = "Destroy a minion, your hero takes damage equal to its HP.";
    private static int baseCost = 2;

    public Obliterate(){
        super(Obliterate.name, Obliterate.description, Obliterate.baseCost);    
    }    
    
    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(!(minion instanceof Hero) &&  minion.canTargeted())
                candidates.add(minion);              
        }
        for(Minion minion : player.getEnemy()){
            if(!(minion instanceof Hero) &&  minion.canTargeted())
                candidates.add(minion);
        }
        return candidates;
    }

    @Override
    public void takeEffect(Player user, Minion target){
        target.setAlive(false);
        user.getHero().setHP(user.getHero().getHP() - target.getHP());      
    }
    
}