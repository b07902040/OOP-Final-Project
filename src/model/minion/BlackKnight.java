package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class BlackKnight extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "BlackKnight";
    private static String description = "BattleCry: Destroy all enemy minion with Taunt.";
    private static int baseCost = 6;
    private static int baseATK = 4;
    private static int baseHP = 5;
   

    public BlackKnight (){
        super(BlackKnight.name, BlackKnight.description, BlackKnight.baseCost,
                 BlackKnight.baseHP, BlackKnight.baseATK);    
    }    

    @Override   
    public void doBattleCryEffect(Minion target){
        List<Minion> targets = new ArrayList<Minion>();
        for(Minion minion : this.master.getEnemy()){
            if(!(minion instanceof Hero) && (minion instanceof Taunt))
                targets.add(minion);
        }
        targets.remove(this);
        for(Minion minion : targets){;
            minion.setAlive(false);
        }
    }
    
}