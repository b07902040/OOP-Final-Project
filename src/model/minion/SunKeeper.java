package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class SunKeeper extends AbstractMinion implements Card, Minion, Taunt, BattleCry{
    
    private static String name = "SunKeeper";
    private static String description = "Taunt & BattleCry: Set all other minions' ATK and HP to 3";
    private static int baseCost = 6;
    private static int baseATK = 3;
    private static int baseHP = 7;
   

    public SunKeeper (){
        super(SunKeeper.name, SunKeeper.description, SunKeeper.baseCost,
                 SunKeeper.baseHP, SunKeeper.baseATK);    
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
        for(Minion minion : targets){
            minion.setBuffHP(3);
            minion.reWriteHP(3);
            minion.setATK(3);
        }
    }
    
}