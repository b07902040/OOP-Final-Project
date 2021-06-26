package src.model.minion;
import src.model.*;
import java.util.List;
//Complete
public class BoomBot extends AbstractMinion implements Card, Minion, DeathRattle{
    
    private static String name = "BoomBot";
    private static String description = "DeathRattle: Deal 1~4 damage to a random enemy";
    private static int baseCost = 1;
    private static int baseATK = 1;
    private static int baseHP = 1;
   

    public BoomBot (){
        super(BoomBot.name, BoomBot.description, BoomBot.baseCost,
                 BoomBot.baseHP, BoomBot.baseATK);    
    }

    private int getRandom(int range){
        return ((int) (Math.random() * range + 1));
    }
    
    @Override
    public void doDeathRattleEffect(){
        List<Minion> enemy = this.master.getEnemy();
        int targetId = this.getRandom(enemy.size()) - 1;
        int dmg = this.getRandom(4);
        Minion e = enemy.get(targetId);
        e.setHP(e.getHP() - dmg);
    }
    
}