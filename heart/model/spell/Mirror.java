package heart.model.spell;
import heart.model.*;
import heart.model.minion.*;
//Complete
public class Mirror extends AbstractSpell implements Card, Spell{
    
    private static String name = "Mirror";
    private static String description = "Summon two 0/2 minions with Taunt";
    private static int baseCost = 1;

    public Mirror(){
        super(Mirror.name, Mirror.description, Mirror.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        user.summonAlly(new MirrorImage(),user.getAlly().size());
        user.summonAlly(new MirrorImage(),user.getAlly().size());
    }

    
}