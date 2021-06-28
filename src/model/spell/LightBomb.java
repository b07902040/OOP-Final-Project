package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class LightBomb extends AbstractSpell implements Card, Spell{
    
    private static String name = "LightBomb";
    private static String description = "Deal damage to each minion equal to its ATK";
    private static int baseCost = 6;

    public LightBomb(){
        super(LightBomb.name, LightBomb.description, LightBomb.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        for(Minion minion : user.getAlly()){
            if(!(minion instanceof Hero))
                minion.setHP(minion.getHP() - minion.getATK());
        }
            
        for(Minion minion : user.getEnemy()){
            if(!(minion instanceof Hero))
                minion.setHP(minion.getHP() - minion.getATK());
        }
            
    }

    
}