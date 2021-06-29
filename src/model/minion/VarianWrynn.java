package src.model.minion;
import src.model.*;
import src.constant.*;
import java.util.List;
//Complete
public class VarianWrynn extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "VarianWrynn";
    private static String description = "BattleCry: Draw 3 cards, summon any minion you drew";
    private static int baseCost = 10;
    private static int baseATK = 7;
    private static int baseHP = 7;

    public VarianWrynn (){
        super(VarianWrynn.name, VarianWrynn.description, VarianWrynn.baseCost,
                 VarianWrynn.baseHP, VarianWrynn.baseATK);    
    }    
    
    @Override   
    public void doBattleCryEffect(Minion target){ 
        for(int i = 0 ;i < 3;i++){
            List<Card> deck = this.master.getDeck();
            if(deck.size() > 0){
                if(deck.get(0) instanceof Minion){
                    if(this.master.getAlly().size() < Const.BOARD_SPACE + 1){
                        this.master.summonAlly((Minion) deck.get(0), this.master.getAlly().size());
                        deck.remove(0);
                        this.master.getGame().cardDrew(this.master.getPlayerId(), false, true);
                        continue;
                    }
                }
            }
            this.master.drawCards(1);   
        }
        
    }
    
}