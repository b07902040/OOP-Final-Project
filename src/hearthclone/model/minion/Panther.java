package hearthclone.model.minion;

import hearthclone.model.Card;
import hearthclone.model.Minion;
import hearthclone.model.Poisonous;
import hearthclone.model.Stealth;
import hearthclone.model.Taunt;

//Complete
public class Panther extends AbstractMinion implements Stealth {

    private static String name = "Panther";
    private static String description = "Stealth";
    private static int baseCost = 3;
    private static int baseATK = 4;
    private static int baseHP = 2;
    private boolean stealth = true;

    public Panther() {
        super(Panther.name, Panther.description, Panther.baseCost, Panther.baseHP, Panther.baseATK);
    }
    
    @Override
    public boolean getStealth(){
        return this.stealth;
    }

    @Override
    public boolean canAttacked() {
        if (this.stealth)
            return false;
        if (this instanceof Taunt)
            return true;
        for (Minion minion : this.master.getAlly()) {
            if (minion instanceof Taunt)
                return false;
        }
        return true;
    }

    @Override
    public boolean canTargeted() {
        return !this.stealth;
    }

    @Override
    public void attack(Minion target) {
        if (this.stealth)
            this.stealth = false;
        System.out.printf("%s attack %s\n", name, ((Card) target).getName());
        if (this instanceof Poisonous && !(target instanceof Hero))
            target.setHP(0);
        else
            target.setHP(target.getHP() - this.getATK());
        if (target instanceof Poisonous)
            this.setHP(0);
        else
            this.setHP(this.getHP() - target.getATK());
        this.attackCount++;
    }
}