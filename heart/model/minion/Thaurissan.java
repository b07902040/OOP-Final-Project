package heart.model.minion;
import heart.model.*;
import heart.model.spell.*;
//Complete
public class Thaurissan extends AbstractMinion implements Card, Minion{
    
    private static String name = "Thaurissan";
    private static String description = "DeathRattle: Add 1 Coin to your hand.";
    private static int baseCost = 6;
    private static int baseATK = 5;
    private static int baseHP = 5;
    
    public Thaurissan (){
        super(Thaurissan.name, Thaurissan.description, Thaurissan.baseCost,
                 Thaurissan.baseHP, Thaurissan.baseATK);    
    }

    @Override   
    public void doTurnEnd(){
        for(int i = 0; i < 3; i++)
            this.master.addHandCards(new Coin());
    }
}