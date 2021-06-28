package src.model.spell;
import src.model.*;
import src.model.minion.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class HolyNova extends AbstractSpell implements Card, Spell{
    
    private static String name = "HolyNova";
    private static String description = "Deal 2 damage to all enemy minions. Restore 2 HP to all friendly characters.";
    private static int baseCost = 5;

    public HolyNova(){
        super(HolyNova.name, HolyNova.description, HolyNova.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        for(Minion minion : user.getAlly()){
            minion.setHP(minion.getHP() + 2);
        }
            
        for(Minion minion : user.getEnemy()){
            if(!(minion instanceof Hero))
                minion.setHP(minion.getHP() - 2);
        }
            
    }

    
}