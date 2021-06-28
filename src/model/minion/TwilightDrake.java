package src.model.minion;
import src.model.*;
//Complete
public class TwilightDrake extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "TwilightDrake";
    private static String description = "BattleCry: Gain +1 HP for each card in your hand.";
    private static int baseCost = 4;
    private static int baseATK = 4;
    private static int baseHP = 1;
   

    public TwilightDrake (){
        super(TwilightDrake.name, TwilightDrake.description, TwilightDrake.baseCost,
                 TwilightDrake.baseHP, TwilightDrake.baseATK);    
    }

    
    @Override
    public void doBattleCryEffect(Minion target){
        int buffValue = this.master.getHandCards().size();
        this.setBuffHP(this.getBuffHP() + buffValue);
        this.setHP(this.getHP() + buffValue);
    }
    
}