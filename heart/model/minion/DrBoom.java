package heart.model.minion;
import heart.model.*;
//Complete
public class DrBoom extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "DrBoom";
    private static String description = "BattleCry: Summon two 1/1 BoomBots";
    private static int baseCost = 7;
    private static int baseATK = 7;
    private static int baseHP = 7;

    public DrBoom (){
        super(DrBoom.name, DrBoom.description, DrBoom.baseCost,
                 DrBoom.baseHP, DrBoom.baseATK);    
    }    
    
    @Override   
    public void doBattleCryEffect(Minion target){ 
        this.master.summonAlly(new BoomBot(), this.master.getAlly().size()-1);
        this.master.summonAlly(new BoomBot(),this.master.getAlly().size());
    }
    
}