package src.model.minion;
import src.model.*;
import java.util.List;
//Complete
public class ZombieHorse extends AbstractMinion implements Card, Minion, DeathRattle{
    
    private static String name = "ZombieHorse";
    private static String description = "DeathRattle: Summon a ZombieHorse";
    private static int baseCost = 4;
    private static int baseATK = 1;
    private static int baseHP = 1;
    
    public ZombieHorse (){
        super(ZombieHorse.name, ZombieHorse.description, ZombieHorse.baseCost,
                 ZombieHorse.baseHP, ZombieHorse.baseATK);    
    }


    @Override
    public void doDeathRattleEffect(){
        this.master.summonAlly(new ZombieHorse(), this.master.getAlly().size());
    }
    
}