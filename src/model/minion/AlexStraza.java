package src.model.minion;
import src.model.*;
import java.util.List;
import java.util.ArrayList;
//Complete
public class AlexStraza extends AbstractMinion implements Card, Minion, BattleCry, Targeting{
    
    private static String name = "AlexStraza";
    private static String description = "BattleCry: Set a hero's remaining HP to 15";
    private static int baseCost = 9;
    private static int baseATK = 8;
    private static int baseHP = 8;
    

    public AlexStraza (){
        super(AlexStraza.name, AlexStraza.description, AlexStraza.baseCost,
                 AlexStraza.baseHP, AlexStraza.baseATK);    
    }

    @Override 
    public List<Minion> getCandidates(Player player){ 
        List<Minion> candidates = new ArrayList<Minion>();
        candidates.add(player.getHero());
        candidates.add(player.getOpponent().getHero());
        return candidates;
    }

    @Override 
    public void doBattleCryEffect(Minion target){ 
        target.reWriteHP(15);        
    }
    
}