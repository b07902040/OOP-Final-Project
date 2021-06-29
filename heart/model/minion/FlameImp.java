package heart.model.minion;
import heart.model.*;
//Complete
public class FlameImp extends AbstractMinion implements Card, Minion, BattleCry{
    
    private static String name = "FlameImp";
    private static String description = "BattleCry: Deal 3 damage to your Hero";
    private static int baseCost = 1;
    private static int baseATK = 3;
    private static int baseHP = 2;
   
    public FlameImp (){
        super(FlameImp.name, FlameImp.description, FlameImp.baseCost,
                 FlameImp.baseHP, FlameImp.baseATK);    
    }

    @Override   
    public void doBattleCryEffect(Minion target){ 
        System.out.printf("%s BattleCry!\n",this.name);
        Minion hero = this.master.getHero();
        hero.setHP(hero.getHP() - 3);
    }
    
}