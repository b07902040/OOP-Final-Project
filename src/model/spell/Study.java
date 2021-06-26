package src.model.spell;
import src.model.*;
//Complete
public class Study extends AbstractSpell implements Card, Spell{
    
    private static String name = "Study";
    private static String description = "Draw 2 cards";
    private static int baseCost = 0;

    public Study(){
        super(Study.name, Study.description, Study.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        user.drawCards(2);
    }

    
}