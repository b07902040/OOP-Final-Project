package heart.model.spell;
import heart.model.*;
//Complete
public class DivineFavor extends AbstractSpell implements Card, Spell{
    
    private static String name = "DivineFavor";
    private static String description = "Draw cards until you have as many handcards as you opponent";
    private static int baseCost = 3;

    public DivineFavor(){
        super(DivineFavor.name, DivineFavor.description, DivineFavor.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        int drawNum = user.getOpponent().getHandCards().size() - user.getHandCards().size();
        if(drawNum <= 0) return;
        user.drawCards(drawNum);
    }

    
}