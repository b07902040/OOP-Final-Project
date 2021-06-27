package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class DeathWing extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "DeathWing";
    private static String description = "BattleCry: Destroy all other minions and discard your hand";
    private static int baseCost = 10;
    private static int baseATK = 12;
    private static int baseHP = 12;

    public DeathWing (){
        super(DeathWing.name, DeathWing.description, DeathWing.baseCost,
                 DeathWing.baseHP, DeathWing.baseATK);    
    }    
    
    @Override   
    public void doBattleCryEffect(Minion target){ 
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : this.master.getAlly()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        for(Minion minion : this.master.getEnemy()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        targets.remove(this);
        for(Minion minion : targets)
            minion.setAlive(false);
        while(this.master.getHandCards().size() > 0)
            this.master.throwCard(0);
    }
    
}