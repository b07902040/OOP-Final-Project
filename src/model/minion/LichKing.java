package src.model.minion;
import src.model.*;
import src.model.spell.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class LichKing extends AbstractMinion implements Card, Minion, Taunt{
    
    private static String name = "LichKing";
    private static String description = "Taunt & At the end of your turn, add a random DeathKnight card to your hand";
    private static int baseCost = 8;
    private static int baseATK = 8;
    private static int baseHP = 8;
   
    public LichKing (){
        super(LichKing.name, LichKing.description, LichKing.baseCost,
                 LichKing.baseHP, LichKing.baseATK);    
    }    

    private Card getDeathKnight(){
        List<Card> deathKnights = new ArrayList<Card>();
        deathKnights.add(new DeathCoil());
        deathKnights.add(new Decay());
        deathKnights.add(new Obliterate());
        int index = Game.getRandom(3) - 1;
        return deathKnights.get(index);
    }

    @Override   
    public void doTurnEnd(){
        Card deathKnight = this.getDeathKnight();
        this.master.addHandCards(deathKnight);
    }   
}