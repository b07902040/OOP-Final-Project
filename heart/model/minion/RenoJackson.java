package heart.model.minion;
import heart.model.*;
//Complete
public class RenoJackson extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "RenoJackson";
    private static String description = "BattleCry: Heal your hero 15 HP";
    private static int baseCost = 6;
    private static int baseATK = 4;
    private static int baseHP = 6;
    
    public RenoJackson(){
        super(RenoJackson.name, RenoJackson.description, RenoJackson.baseCost,
                    RenoJackson.baseHP, RenoJackson.baseATK);    
    }
    
    @Override   
    public void doBattleCryEffect(Minion target){ 
       Minion hero = this.master.getHero();
       hero.setHP(hero.getHP() + 15);
    }
    
}