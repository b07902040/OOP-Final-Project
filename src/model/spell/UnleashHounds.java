package src.model.spell;
import src.model.*;
import src.model.minion.*;
//Complete
public class UnleashHounds extends AbstractSpell implements Card, Spell{
    
    private static String name = "UnleashHounds";
    private static String description = "For each enemy minion, summon a 1/1 Hound with Charge.";
    private static int baseCost = 3;

    public UnleashHounds(){
        super(UnleashHounds.name, UnleashHounds.description, UnleashHounds.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        for(int i = 0;i < user.getEnemy().size() - 1; i++)
            user.summonAlly(new Hound(), user.getAlly().size());
    }

    
}