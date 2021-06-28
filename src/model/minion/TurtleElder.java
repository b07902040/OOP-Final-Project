package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class TurtleElder extends AbstractMinion implements Card, Minion, DeathRattle, Taunt{
    
    private static String name = "TurtleElder";
    private static String description = "Taunt & DeathRattle: Give a random friendly minion +1/+1";
    private static int baseCost = 4;
    private static int baseATK = 2;
    private static int baseHP = 6;

    public TurtleElder (){
        super(TurtleElder.name, TurtleElder.description, TurtleElder.baseCost,
                 TurtleElder.baseHP, TurtleElder.baseATK);    
    }    
    
    @Override   
    public void doDeathRattleEffect(){ 
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : this.master.getAlly()){
            if(!(minion instanceof Hero))
                targets.add(minion);
        }
        if(targets.size() == 0) return;
        int index = Game.getRandom(targets.size()) - 1;
        Minion target = targets.get(index);
        target.setBuffHP(target.getBuffHP() + 1);
        target.setHP(target.getHP() + 1);
        target.setATK(target.getATK() + 1);
        
    }
    
}