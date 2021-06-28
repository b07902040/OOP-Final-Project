package src.model.spell;
import src.model.*;
import src.model.minion.*;
//Complete
public class PenguinParty extends AbstractSpell implements Card, Spell{
    
    private static String name = "PenguinParty";
    private static String description = "Summon 7 Penguins";
    private static int baseCost = 5;

    public PenguinParty(){
        super(PenguinParty.name, PenguinParty.description, PenguinParty.baseCost);    
    }    
    
    @Override
    public void takeEffect(Player user, Minion target){
        user.summonAlly(new PartyPenguin(), user.getAlly().size());
        user.summonAlly(new Penguin(), user.getAlly().size());
        user.summonAlly(new Penguin(), user.getAlly().size());
        user.summonAlly(new PartyPenguin(), user.getAlly().size());
        user.summonAlly(new Penguinn(), user.getAlly().size());
        user.summonAlly(new Penguinn(), user.getAlly().size());
        user.summonAlly(new PartyPenguin(), user.getAlly().size());
    }

    
}