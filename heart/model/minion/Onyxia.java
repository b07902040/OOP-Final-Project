package heart.model.minion;
import heart.constant.*;
import heart.model.*;
//Complete
public class Onyxia extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "Onyxia";
    private static String description = "BattleCry: Summon 1/1 Whelps until your board is full";
    private static int baseCost = 9;
    private static int baseATK = 8;
    private static int baseHP = 8;

    public Onyxia (){
        super(Onyxia.name, Onyxia.description, Onyxia.baseCost,
                 Onyxia.baseHP, Onyxia.baseATK);    
    }    
    
    @Override   
    public void doBattleCryEffect(Minion target){ 
        int summonNum = (Const.BOARD_SPACE + 1) - this.master.getAlly().size();
        int center = this.master.getAlly().size() ;
        int sumCount = 0;
        boolean direction = true;
        while(sumCount < summonNum){
            if(direction){
                this.master.summonAlly(new Whelp(),center - 1);
                center++;
            }
            else
                this.master.summonAlly(new Whelp(),center );
            direction = !direction;
            sumCount += 1;
            
        }
        
    }
    
}