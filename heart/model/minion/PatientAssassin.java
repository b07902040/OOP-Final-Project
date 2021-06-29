package heart.model.minion;

import heart.model.Card;
import heart.model.Minion;
import heart.model.Poisonous;
import heart.model.Stealth;
import heart.model.Taunt;

//Complete
public class PatientAssassin extends AbstractMinion implements Poisonous, Stealth {

    private static String name = "PatientAssassin";
    private static String description = "Poisonous & Stealth";
    private static int baseCost = 2;
    private static int baseATK = 1;
    private static int baseHP = 1;
    private boolean stealth = true;

    public PatientAssassin() {
        super(PatientAssassin.name, PatientAssassin.description, PatientAssassin.baseCost, PatientAssassin.baseHP,
                PatientAssassin.baseATK);
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