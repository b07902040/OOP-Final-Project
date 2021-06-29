package heart.model.minion;
import heart.model.*;
//Complete
public class DancingSwords extends AbstractMinion implements Card, Minion, DeathRattle{
    
    private static String name = "DancingSwords";
    private static String description = "DeathRattle: Your opponent draws a card";
    private static int baseCost = 3;
    private static int baseATK = 4;
    private static int baseHP = 4;
    
    public DancingSwords (){
        super(DancingSwords.name, DancingSwords.description, DancingSwords.baseCost,
                 DancingSwords.baseHP, DancingSwords.baseATK);    
    }

    @Override 
    public void doDeathRattleEffect(){ 
        this.master.getOpponent().drawCards(1);
    }
    
}