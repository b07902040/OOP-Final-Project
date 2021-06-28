package src.model.spell;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class HolyFire extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "HolyFire";
    private static String description = "Deal 5 damage.Restore 5 HP to your hero";
    private static int baseCost = 5;

    public HolyFire(){
        super(HolyFire.name, HolyFire.description, HolyFire.baseCost);    
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
        target.setHP(target.getHP() - 5);
        user.getHero().setHP(user.getHero().getHP() + 5);
    }

    
}