package src.model.spell;
import src.model.*;
//Complete
public class ExcessMana extends AbstractSpell implements Card, Spell{
    
    private static String name = "ExcessMana";
    private static String description = "Draw a card. (You can only have 10 Full Mana)";
    private static int baseCost = 0;

    public ExcessMana(){
        super(ExcessMana.name, ExcessMana.description, ExcessMana.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        user.drawCards(1);
    }

    
}