package heart.model.minion;

import heart.model.Charge;
import heart.model.DivineShield;
import heart.model.Taunt;
import heart.model.WindFury;

//Complete
public class AlAkir extends AbstractMinion implements Charge, DivineShield, Taunt, WindFury {

    private static String name = "AlAkir";
    private static String description = "Charge & DivineShield & Taunt & WindFury";
    private static int baseCost = 8;
    private static int baseATK = 3;
    private static int baseHP = 6;
    private boolean divineShield = true;

    public AlAkir() {
        super(AlAkir.name, AlAkir.description, AlAkir.baseCost, AlAkir.baseHP, AlAkir.baseATK);
    }

    @Override
    public void setHP(int HP) {
        // Heal
        if (this.HP < HP) {
            System.out.printf("%s +%d HP.\n", name, HP - this.HP);
            this.HP = Math.min(HP, this.buffHP);
        }
        // damage
        else if (this.HP > HP) {
            if (this.divineShield) {
                System.out.printf("%s deny damage by DivineShield.\n", name);
                this.divineShield = false;
            } else {
                System.out.printf("%s -%d HP.\n", name, this.HP - HP);
                this.HP = HP;
            }
        }
        this.minionChange();
    }

    @Override
    public boolean hasDivineShield() {
        return this.divineShield;
    }

}