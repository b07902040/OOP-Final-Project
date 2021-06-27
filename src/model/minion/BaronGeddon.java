package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class BaronGeddon extends AbstractMinion implements Card, Minion{
    
    private static String name = "BaronGeddon";
    private static String description = "At the end of your turn, deal 2 damage to all other characters.";
    private static int baseCost = 7;
    private static int baseATK = 7;
    private static int baseHP = 5;
   

    public BaronGeddon (){
        super(BaronGeddon.name, BaronGeddon.description, BaronGeddon.baseCost,
                 BaronGeddon.baseHP, BaronGeddon.baseATK);    
    }    

    @Override   
    public void doTurnEnd(){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : this.master.getAlly())
            targets.add(minion);
        for(Minion minion : this.master.getEnemy())
            targets.add(minion);
        targets.remove(this);
        for(Minion minion : targets)
            minion.setHP(minion.getHP() - 2);
    }
    
}