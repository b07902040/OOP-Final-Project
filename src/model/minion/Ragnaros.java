package src.model.minion;
import src.model.*;
import java.util.List;
//Complete
public class Ragnaros extends AbstractMinion implements Card, Minion{
    
    private static String name = "Ragnaros";
    private static String description = "At the end of your turn, deal 8 damge to a random enemy";
    private static int baseCost = 8;
    private static int baseATK = 8;
    private static int baseHP = 8;
   

    public Ragnaros (){
        super(Ragnaros.name, Ragnaros.description, Ragnaros.baseCost,
                 Ragnaros.baseHP, Ragnaros.baseATK);    
    }    
    
    @Override
    public boolean canAttack(){        
        return false;
    }

    @Override   
    public void doTurnEnd(){
        List<Minion> enemy = this.master.getEnemy();
        int targetId = Game.getRandom(enemy.size()) - 1;
        Minion e = enemy.get(targetId);
        e.setHP(e.getHP() - 8);
    }
    
}