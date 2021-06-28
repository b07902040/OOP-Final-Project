package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class BigDragon extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "BigDragon";
    private static String description = "Set a minion's ATK and HP to 10";
    private static int baseCost = 8;

    public BigDragon(){
        super(BigDragon.name, BigDragon.description, BigDragon.baseCost);    
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
        target.setBuffHP(10); 
        target.setHP(10);  
        target.setATK(10); 
    }

    
}