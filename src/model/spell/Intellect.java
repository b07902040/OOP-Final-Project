package src.model.spell;
import src.model.*;
//Complete
public class Intellect extends AbstractSpell implements Card, Spell{
    
    private static String name = "Intellect";
    private static String description = "Draw 2 cards";
    private static int baseCost = 0;

    public Intellect(){
        super(Intellect.name, Intellect.description, Intellect.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        user.drawCards(2);
    }

    
}