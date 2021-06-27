package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class Brawl extends AbstractSpell implements Card, Spell{
    
    private static String name = "Brawl";
    private static String description = "Destroy all minions except one. (chosen randomly)";
    private static int baseCost = 5;

    public Brawl(){
        super(Brawl.name, Brawl.description, Brawl.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : user.getAlly()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : user.getEnemy()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        if(targets.size() < 1)
            return;
        int index = Game.getRandom(targets.size()) - 1;
        targets.remove(index);
        for(Minion minion : targets)
            minion.setAlive(false);
    }

    
}