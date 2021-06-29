package heart.model.minion;
import java.util.List;

import heart.model.*;

import java.util.ArrayList;
//Complete
public class ForgottenKing extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "ForgottenKing";
    private static String description = "BattleCry:  Full your mana";
    private static int baseCost = 10;
    private static int baseATK = 7;
    private static int baseHP = 7;

    public ForgottenKing (){
        super(ForgottenKing.name, ForgottenKing.description, ForgottenKing.baseCost,
                 ForgottenKing.baseHP, ForgottenKing.baseATK);    
    }    
    
    @Override   
    public void doBattleCryEffect(Minion target){ 
       this.master.setMana(this.master.getFullMana());
    }
    
}