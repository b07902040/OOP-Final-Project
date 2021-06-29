package heart.model.minion;
import java.util.List;

import heart.model.*;
import heart.model.spell.*;
//Complete
public class Antonidas extends AbstractMinion implements Card, Minion{
    
    private static String name = "Antonidas";
    private static String description = "At the end of your turn, add 2 FireBalls to your hand";
    private static int baseCost = 7;
    private static int baseATK = 5;
    private static int baseHP = 7;
   

    public Antonidas (){
        super(Antonidas.name, Antonidas.description, Antonidas.baseCost,
                 Antonidas.baseHP, Antonidas.baseATK);    
    }    

    @Override   
    public void doTurnEnd(){
        this.master.addHandCards(new FireBall());
        this.master.addHandCards(new FireBall());
    }
    
}