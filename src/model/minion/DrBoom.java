package src.model.minion;
import src.model.*;
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
        System.out.printf("%s BattleCry!\n",this.name);
        for(int i = 0; i < 2; i++)
            this.master.addAlly(new BoomBot());
    }
    
}