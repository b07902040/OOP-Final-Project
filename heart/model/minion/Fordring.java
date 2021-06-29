package heart.model.minion;

import heart.model.DeathRattle;
import heart.model.DivineShield;
import heart.model.Taunt;

//Complete
public class Fordring extends AbstractMinion implements DeathRattle, DivineShield, Taunt {

    private static String name = "Fordring";
    private static String description = "DivineShield & Taunt & DeathRattle: Summon a 5/3 AshBringer";
    private static int baseCost = 8;
    private static int baseATK = 6;
    private static int baseHP = 6;
    private boolean divineShield = true;

    public Fordring() {
        super(Fordring.name, Fordring.description, Fordring.baseCost, Fordring.baseHP, Fordring.baseATK);
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
    public void doDeathRattleEffect() {
        this.master.summonAlly(new AshBringer(), this.master.getAlly().size());
    }

    @Override
    public boolean hasDivineShield() {
        return this.divineShield;
    }
}