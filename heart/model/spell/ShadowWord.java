package heart.model.spell;
import java.util.List;

import heart.model.*;
import heart.model.minion.*;

import java.util.ArrayList;
//Complete
public class ShadowWord extends AbstractSpell implements Card, Spell, Targeting{
    
    private static String name = "ShadowWord";
    private static String description = "Destroy a minion with 5 or more ATK";
    private static int baseCost = 3;

    public ShadowWord(){
        super(ShadowWord.name, ShadowWord.description, ShadowWord.baseCost);    
    }    
    
    @Override 
    public List<Minion> getCandidates(Player player){
        List<Minion> candidates = new ArrayList<Minion>();
        for(Minion minion : player.getAlly()){
            if(minion.canTargeted() && !(minion instanceof Hero)){
                if(minion.getATK() >= 5)
                    candidates.add(minion);
            }
        }
        for(Minion minion : player.getEnemy()){
            if(minion.canTargeted() && !(minion instanceof Hero) ){
                if(minion.getATK() >= 5)
                    candidates.add(minion);
            }
        }
        return candidates; 
    }

    @Override
    public void takeEffect(Player user, Minion target){
        target.setAlive(false); 
    }

    
}