package src.model.minion;
import src.model.*;
//Complete
public class Penguin extends AbstractMinion implements Card, Minion{
    
    private static String name = "Penguin";
    private static String description = "";
    private static int baseCost = 0;
    private static int baseATK = 1;
    private static int baseHP = 1;
   
    public Penguin (){
        super(Penguin.name, Penguin.description, Penguin.baseCost,
                 Penguin.baseHP, Penguin.baseATK);    
    }
   
}