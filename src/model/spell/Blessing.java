package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class Blessing extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "Blessing";
    private static String description = "Give a minion +4/+4";
    private static int baseCost = 4;

    public Blessing(){
        super(Blessing.name, Blessing.description, Blessing.baseCost);    
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
        target.setBuffHP(target.getBuffHP() + 4); 
        target.setHP(target.getHP() + 4);  
        target.setATK(target.getATK() + 4); 
    }

    
}