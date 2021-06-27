package src.model.spell;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class Decay extends AbstractSpell implements Card, Spell{
    
    private static String type = "DeathKnight";
    private static String name = "Decay";
    private static String description = "Deal 3 damge to all enemies";
    private static int baseCost = 3;

    public Decay(){
        super(Decay.name, Decay.description, Decay.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : user.getEnemy())
            targets.add(minion);
        for(Minion minion : targets)
            minion.setHP(minion.getHP() - 3);
    }

    
}