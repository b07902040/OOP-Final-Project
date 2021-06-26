package src.model.spell;
import src.model.*;
//Complete
public class TheCoin extends AbstractSpell implements Card, Spell{
    
    private static String name = "TheCoin";
    private static String description = "Gain 1 mana this turn only";
    private static int baseCost = 0;

    public TheCoin(){
        super(TheCoin.name, TheCoin.description, TheCoin.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        user.setMana(user.getMana() + 1);
    }

    
}