package src.model.spell;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class HellFire extends AbstractSpell implements Card, Spell{
    
    private static String name = "HellFire";
    private static String description = "Deal 3 damage to all characters";
    private static int baseCost = 0;

    public HellFire(){
        super(HellFire.name, HellFire.description, HellFire.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : user.getAlly())
            targets.add(minion);
        for(Minion minion : user.getEnemy())
            targets.add(minion);
        for(Minion minion : targets)
            minion.setHP(minion.getHP() - 3);
    }

    
}