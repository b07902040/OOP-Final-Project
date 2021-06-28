package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class LanternFish extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "LanternFish";
    private static String description = "BattleCry: Each player draws 2 cards";
    private static int baseCost = 3;
    private static int baseATK = 2;
    private static int baseHP = 2;
   

    public LanternFish (){
        super(LanternFish.name, LanternFish.description, LanternFish.baseCost,
                 LanternFish.baseHP, LanternFish.baseATK);    
    }    

    @Override   
    public void doBattleCryEffect(Minion target){
        this.master.drawCards(1);
        this.master.getOpponent().drawCards(1);
        this.master.drawCards(1);
        this.master.getOpponent().drawCards(1);
    }
    
}