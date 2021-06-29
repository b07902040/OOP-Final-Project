package heart.model.minion;
import java.util.List;

import heart.model.*;
//Complete
public class ManaTideTotem extends AbstractMinion implements Card, Minion{
    
    private static String name = "ManaTideTotem";
    private static String description = "At the end of your turn, draw a card";
    private static int baseCost = 3;
    private static int baseATK = 0;
    private static int baseHP = 3;
   

    public ManaTideTotem (){
        super(ManaTideTotem.name, ManaTideTotem.description, ManaTideTotem.baseCost,
                 ManaTideTotem.baseHP, ManaTideTotem.baseATK);    
    }    
    
    @Override   
    public void doTurnEnd(){
        this.master.drawCards(1);
    }
    
}